package com.collegeeventapp.view;

import com.collegeeventapp.controller.EventController;
import com.collegeeventapp.model.Event;
import com.collegeeventapp.util.ImageUtil;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AdminPanel extends JPanel {

    private EventController eventController;
    private JTextField idField, nameField, dateField, timeField, locationField, capacityField;
    private JTextArea descriptionArea;
    private JComboBox<String> categoryCombo;
    private JButton addButton, updateButton, deleteButton, selectImageButton;
    private JLabel imagePreview;
    private String selectedImagePath = "";
    private EventListPanel eventListPanel;

    public AdminPanel() {
        this.eventController = new EventController();
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Manage Events"));

        formPanel.add(new JLabel("Event ID (for Update/Delete):"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        formPanel.add(dateField);

        formPanel.add(new JLabel("Time (HH:MM):"));
        timeField = new JTextField();
        formPanel.add(timeField);

        formPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        formPanel.add(locationField);

        formPanel.add(new JLabel("Category:"));
        categoryCombo = new JComboBox<>(new String[]{"Academic", "Sports", "Cultural", "Technical", "General"});
        formPanel.add(categoryCombo);

        formPanel.add(new JLabel("Max Capacity:"));
        capacityField = new JTextField("100");
        formPanel.add(capacityField);

        formPanel.add(new JLabel("Event Image:"));
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(e -> selectEventImage());
        imagePanel.add(selectImageButton);
        formPanel.add(imagePanel);

        formPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(descriptionArea));

        add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Event");
        updateButton = new JButton("Update Event");
        deleteButton = new JButton("Delete Event");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        eventListPanel = new EventListPanel(true);
        add(eventListPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addEvent());
        updateButton.addActionListener(e -> updateEvent());
        deleteButton.addActionListener(e -> deleteEvent());
        
        // Add image preview panel
        JPanel previewPanel = new JPanel(new BorderLayout());
        previewPanel.setBorder(BorderFactory.createTitledBorder("Image Preview"));
        imagePreview = new JLabel();
        imagePreview.setHorizontalAlignment(JLabel.CENTER);
        imagePreview.setPreferredSize(new Dimension(200, 150));
        imagePreview.setBorder(BorderFactory.createLoweredBevelBorder());
        previewPanel.add(imagePreview, BorderLayout.CENTER);
        add(previewPanel, BorderLayout.EAST);
    }
    
    private void selectEventImage() {
        String imagePath = ImageUtil.selectImageFile(this);
        if (imagePath != null) {
            selectedImagePath = imagePath;
            ImageIcon preview = ImageUtil.loadAndResizeImage(imagePath, 180, 130);
            imagePreview.setIcon(preview);
            selectImageButton.setText("Change Image");
        }
    }

    private void addEvent() {
        try {
            String name = nameField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            LocalTime time = LocalTime.parse(timeField.getText());
            String desc = descriptionArea.getText();
            String location = locationField.getText();
            String category = (String) categoryCombo.getSelectedItem();
            int capacity = Integer.parseInt(capacityField.getText());

            // Create enhanced event (for now, basic event creation)
            if (eventController.createEvent(name, date, time, desc)) {
                JOptionPane.showMessageDialog(this, "Event Added Successfully!\nLocation: " + location + "\nCategory: " + category + "\nCapacity: " + capacity);
                eventListPanel.refreshEventList();
                clearFields();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void clearFields() {
        nameField.setText("");
        dateField.setText("");
        timeField.setText("");
        locationField.setText("");
        capacityField.setText("100");
        descriptionArea.setText("");
        categoryCombo.setSelectedIndex(0);
        selectedImagePath = "";
        imagePreview.setIcon(null);
        selectImageButton.setText("Select Image");
    }

    private void updateEvent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            LocalTime time = LocalTime.parse(timeField.getText());
            String desc = descriptionArea.getText();
            Event event = new Event(id, name, date, time, desc);

            if (eventController.updateEvent(event)) {
                JOptionPane.showMessageDialog(this, "Event Updated!");
                eventListPanel.refreshEventList();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteEvent() {
        try {
            int id = Integer.parseInt(idField.getText());
            if (eventController.deleteEvent(id)) {
                JOptionPane.showMessageDialog(this, "Event Deleted!");
                eventListPanel.refreshEventList();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}