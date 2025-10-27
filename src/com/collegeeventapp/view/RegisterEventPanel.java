package com.collegeeventapp.view;

import com.collegeeventapp.controller.RegistrationController;
import com.collegeeventapp.controller.EventController;
import com.collegeeventapp.model.Event;
import com.collegeeventapp.model.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RegisterEventPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private RegistrationController registrationController;
    private EventController eventController;
    private User currentUser;
    private JButton registerButton, unregisterButton;

    public RegisterEventPanel(User user) {
        this.currentUser = user;
        this.registrationController = new RegistrationController();
        this.eventController = new EventController();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Available Events"));

        model = new DefaultTableModel(new Object[]{"ID", "Name", "Date", "Time", "Description"}, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        registerButton = new JButton("Register");
        unregisterButton = new JButton("Unregister");
        btnPanel.add(registerButton);
        btnPanel.add(unregisterButton);
        add(btnPanel, BorderLayout.SOUTH);

        refreshEventList();

        registerButton.addActionListener(e -> handleRegister());
        unregisterButton.addActionListener(e -> handleUnregister());
    }

    private void refreshEventList() {
        model.setRowCount(0);
        List<Event> events = eventController.getAllEvents();
        if (events != null) {
            for (Event e : events) {
                model.addRow(new Object[]{
                    e.getEventID(), e.getEventName(), e.getDate(), e.getTime(), e.getDescription()
                });
            }
        }
    }

    private void handleRegister() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an event to register.");
            return;
        }

        int eventID = (int) model.getValueAt(row, 0);
        if (registrationController.registerUser(currentUser.getId(), eventID)) {
            JOptionPane.showMessageDialog(this, "Registered successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed.");
        }
    }

    private void handleUnregister() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an event to unregister.");
            return;
        }

        int eventID = (int) model.getValueAt(row, 0);
        if (registrationController.cancelRegistration(currentUser.getId(), eventID)) {
            JOptionPane.showMessageDialog(this, "Unregistered successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Unregistration failed.");
        }
    }
}