package com.collegeeventapp.view;

import com.collegeeventapp.controller.EventController;
import com.collegeeventapp.model.Event;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AdminPanel extends JPanel {

    private EventController eventController;
    private JTextField idField, nameField, dateField, timeField;
    private JTextArea descriptionArea;
    private JButton addButton, updateButton, deleteButton;
    private EventListPanel eventListPanel;

    public AdminPanel() {
        this.eventController = new EventController();
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
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
    }

    private void addEvent() {
        try {
            String name = nameField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            LocalTime time = LocalTime.parse(timeField.getText());
            String desc = descriptionArea.getText();

            if (eventController.createEvent(name, date, time, desc)) {
                JOptionPane.showMessageDialog(this, "Event Added Successfully!");
                eventListPanel.refreshEventList();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
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