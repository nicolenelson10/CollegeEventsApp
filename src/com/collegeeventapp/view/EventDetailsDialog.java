package com.collegeeventapp.view;

import com.collegeeventapp.model.Event;
import com.collegeeventapp.util.ImageUtil;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class EventDetailsDialog extends JDialog {
    
    public EventDetailsDialog(JFrame parent, Event event) {
        super(parent, "Event Details - " + event.getEventName(), true);
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Header Panel with Image
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 248, 255));
        
        // Event Image
        ImageIcon eventImage = ImageUtil.loadAndResizeImage(event.getImagePath(), 200, 150);
        if (eventImage == null) {
            eventImage = ImageUtil.createCategoryImage(event.getCategory(), 200, 150);
        }
        JLabel imageLabel = new JLabel(eventImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(imageLabel, BorderLayout.CENTER);
        
        // Event Title
        JLabel titleLabel = new JLabel(event.getEventName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(titleLabel, BorderLayout.SOUTH);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Details Panel
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Event details
        addDetailRow(detailsPanel, gbc, 0, "📅 Date:", event.getDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        addDetailRow(detailsPanel, gbc, 1, "🕐 Time:", event.getTime().format(DateTimeFormatter.ofPattern("hh:mm a")));
        addDetailRow(detailsPanel, gbc, 2, "📍 Location:", event.getLocation());
        addDetailRow(detailsPanel, gbc, 3, "🏷️ Category:", event.getCategory());
        addDetailRow(detailsPanel, gbc, 4, "👥 Max Capacity:", String.valueOf(event.getMaxCapacity()));
        
        // Description
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.BOTH;
        JLabel descLabel = new JLabel("📝 Description:");
        descLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsPanel.add(descLabel, gbc);
        
        gbc.gridy = 6; gbc.weighty = 1.0;
        JTextArea descArea = new JTextArea(event.getDescription());
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setBackground(new Color(248, 248, 255));
        descArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(descArea);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        detailsPanel.add(scrollPane, gbc);
        
        add(detailsPanel, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void addDetailRow(JPanel panel, GridBagConstraints gbc, int row, String label, String value) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelComp, gbc);
        
        gbc.gridx = 1;
        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(valueComp, gbc);
    }
}