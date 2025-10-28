package com.collegeeventapp.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtil {
    
    /**
     * Loads and resizes an image
     */
    public static ImageIcon loadAndResizeImage(String imagePath, int width, int height) {
        try {
            if (imagePath == null || imagePath.isEmpty()) {
                return createDefaultEventImage(width, height);
            }
            
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                return createDefaultEventImage(width, height);
            }
            
            BufferedImage originalImage = ImageIO.read(imageFile);
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
            
        } catch (IOException e) {
            return createDefaultEventImage(width, height);
        }
    }
    
    /**
     * Creates a default event image
     */
    public static ImageIcon createDefaultEventImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Create gradient background
        GradientPaint gradient = new GradientPaint(0, 0, new Color(70, 130, 180), 
                                                  width, height, new Color(135, 206, 250));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
        
        // Add event icon
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, Math.min(width, height) / 4));
        FontMetrics fm = g2d.getFontMetrics();
        String text = "EVENT";
        int x = (width - fm.stringWidth(text)) / 2;
        int y = (height + fm.getAscent()) / 2;
        g2d.drawString(text, x, y);
        
        g2d.dispose();
        return new ImageIcon(image);
    }
    
    /**
     * Creates category-specific default images
     */
    public static ImageIcon createCategoryImage(String category, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        Color primaryColor, secondaryColor;
        String text;
        
        switch (category.toLowerCase()) {
            case "academic":
                primaryColor = new Color(34, 139, 34);
                secondaryColor = new Color(144, 238, 144);
                text = "📚";
                break;
            case "sports":
                primaryColor = new Color(255, 69, 0);
                secondaryColor = new Color(255, 160, 122);
                text = "⚽";
                break;
            case "cultural":
                primaryColor = new Color(138, 43, 226);
                secondaryColor = new Color(221, 160, 221);
                text = "🎭";
                break;
            case "technical":
                primaryColor = new Color(70, 130, 180);
                secondaryColor = new Color(176, 196, 222);
                text = "💻";
                break;
            default:
                primaryColor = new Color(105, 105, 105);
                secondaryColor = new Color(192, 192, 192);
                text = "📅";
        }
        
        GradientPaint gradient = new GradientPaint(0, 0, primaryColor, width, height, secondaryColor);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, Math.min(width, height) / 3));
        FontMetrics fm = g2d.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = (height + fm.getAscent()) / 2;
        g2d.drawString(text, x, y);
        
        g2d.dispose();
        return new ImageIcon(image);
    }
    
    /**
     * Opens file chooser for image selection
     */
    public static String selectImageFile(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Event Image");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String name = f.getName().toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || 
                       name.endsWith(".png") || name.endsWith(".gif");
            }
            
            @Override
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });
        
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}