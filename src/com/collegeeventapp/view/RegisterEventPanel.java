package com.collegeeventapp.view;

import com.collegeeventapp.controller.RegistrationController;
import com.collegeeventapp.controller.EventController;
import com.collegeeventapp.model.Event;
import com.collegeeventapp.model.User;
import com.collegeeventapp.util.PDFExporter;
import com.collegeeventapp.util.EmailNotifier;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.io.File;

public class RegisterEventPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JTable allEventsTable, myEventsTable;
    private DefaultTableModel allEventsModel, myEventsModel;
    private RegistrationController registrationController;
    private EventController eventController;
    private User currentUser;
    private JButton registerButton, unregisterButton, refreshButton, exportButton, attendanceButton;

    public RegisterEventPanel(User user) {
        this.currentUser = user;
        this.registrationController = new RegistrationController();
        this.eventController = new EventController();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Event Management - " + user.getName()));

        initializeComponents();
        setupLayout();
        refreshAllData();
    }

    private void initializeComponents() {
        tabbedPane = new JTabbedPane();

        // All Events Tab
        allEventsModel = new DefaultTableModel(new Object[]{"ID", "Name", "Date", "Time", "Location", "Category", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        allEventsTable = new JTable(allEventsModel);
        allEventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        allEventsTable.getTableHeader().setReorderingAllowed(false);

        // My Registered Events Tab
        myEventsModel = new DefaultTableModel(new Object[]{"ID", "Name", "Date", "Time", "Location", "Category", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        myEventsTable = new JTable(myEventsModel);
        myEventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myEventsTable.getTableHeader().setReorderingAllowed(false);

        // Buttons
        registerButton = new JButton("Register for Event");
        unregisterButton = new JButton("Unregister from Event");
        refreshButton = new JButton("Refresh");
        exportButton = new JButton("Export to PDF");
        attendanceButton = new JButton("Mark Attendance");

        // Style buttons
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        unregisterButton.setBackground(new Color(220, 20, 60));
        unregisterButton.setForeground(Color.WHITE);
        refreshButton.setBackground(new Color(70, 130, 180));
        refreshButton.setForeground(Color.WHITE);
        exportButton.setBackground(new Color(255, 140, 0));
        exportButton.setForeground(Color.WHITE);
        attendanceButton.setBackground(new Color(138, 43, 226));
        attendanceButton.setForeground(Color.WHITE);

        // Add action listeners
        registerButton.addActionListener(e -> handleRegister());
        unregisterButton.addActionListener(e -> handleUnregister());
        refreshButton.addActionListener(e -> refreshAllData());
        exportButton.addActionListener(e -> handleExportPDF());
        attendanceButton.addActionListener(e -> handleMarkAttendance());
    }

    private void setupLayout() {
        // All Events Panel
        JPanel allEventsPanel = new JPanel(new BorderLayout());
        allEventsPanel.add(new JScrollPane(allEventsTable), BorderLayout.CENTER);
        
        JPanel allEventsButtonPanel = new JPanel(new FlowLayout());
        allEventsButtonPanel.add(registerButton);
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBackground(new Color(75, 0, 130));
        viewDetailsButton.setForeground(Color.WHITE);
        viewDetailsButton.addActionListener(e -> showEventDetails());
        allEventsButtonPanel.add(viewDetailsButton);
        allEventsButtonPanel.add(refreshButton);
        allEventsPanel.add(allEventsButtonPanel, BorderLayout.SOUTH);

        // My Events Panel
        JPanel myEventsPanel = new JPanel(new BorderLayout());
        myEventsPanel.add(new JScrollPane(myEventsTable), BorderLayout.CENTER);
        
        JPanel myEventsButtonPanel = new JPanel(new FlowLayout());
        myEventsButtonPanel.add(unregisterButton);
        myEventsButtonPanel.add(exportButton);
        myEventsButtonPanel.add(attendanceButton);
        myEventsButtonPanel.add(refreshButton);
        myEventsPanel.add(myEventsButtonPanel, BorderLayout.SOUTH);

        // Add tabs
        tabbedPane.addTab("All Events", allEventsPanel);
        tabbedPane.addTab("My Registered Events", myEventsPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(240, 248, 255));
        JLabel infoLabel = new JLabel("Select an event and use the buttons to register/unregister");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoPanel.add(infoLabel);
        add(infoPanel, BorderLayout.NORTH);
    }

    private void refreshAllData() {
        refreshAllEventsList();
        refreshMyEventsList();
    }

    private void refreshAllEventsList() {
        allEventsModel.setRowCount(0);
        List<Event> events = eventController.getAllEvents();
        List<Event> myEvents = registrationController.viewUserRegisteredEvents(currentUser.getId());
        
        if (events != null) {
            for (Event event : events) {
                String status = "Available";
                if (myEvents != null) {
                    for (Event myEvent : myEvents) {
                        if (myEvent.getEventID() == event.getEventID()) {
                            status = "Registered";
                            break;
                        }
                    }
                }
                
                allEventsModel.addRow(new Object[]{
                    event.getEventID(), 
                    event.getEventName(), 
                    event.getDate(), 
                    event.getTime(), 
                    event.getLocation(),
                    event.getCategory(),
                    status
                });
            }
        }
    }

    private void refreshMyEventsList() {
        myEventsModel.setRowCount(0);
        List<Event> myEvents = registrationController.viewUserRegisteredEvents(currentUser.getId());
        
        if (myEvents != null) {
            for (Event event : myEvents) {
                myEventsModel.addRow(new Object[]{
                    event.getEventID(), 
                    event.getEventName(), 
                    event.getDate(), 
                    event.getTime(), 
                    event.getLocation(),
                    event.getCategory(),
                    "Registered"
                });
            }
        }
    }

    private void handleRegister() {
        int row = allEventsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an event to register for.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int eventID = (int) allEventsModel.getValueAt(row, 0);
        String eventName = (String) allEventsModel.getValueAt(row, 1);
        String status = (String) allEventsModel.getValueAt(row, 6);

        if ("Registered".equals(status)) {
            JOptionPane.showMessageDialog(this, "You are already registered for this event!", "Already Registered", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Do you want to register for: " + eventName + "?", 
            "Confirm Registration", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (registrationController.registerUser(currentUser.getId(), eventID)) {
                JOptionPane.showMessageDialog(this, "Successfully registered for: " + eventName, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
                
                // Send email notification
                Event event = eventController.getEventById(eventID);
                if (event != null) {
                    EmailNotifier.sendRegistrationConfirmation(currentUser, event);
                }
                
                refreshAllData();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. You may already be registered.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleUnregister() {
        int row = myEventsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an event to unregister from.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int eventID = (int) myEventsModel.getValueAt(row, 0);
        String eventName = (String) myEventsModel.getValueAt(row, 1);

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Do you want to unregister from: " + eventName + "?", 
            "Confirm Unregistration", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (registrationController.cancelRegistration(currentUser.getId(), eventID)) {
                JOptionPane.showMessageDialog(this, "Successfully unregistered from: " + eventName, "Unregistration Successful", JOptionPane.INFORMATION_MESSAGE);
                refreshAllData();
            } else {
                JOptionPane.showMessageDialog(this, "Unregistration failed.", "Unregistration Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleExportPDF() {
        List<Event> myEvents = registrationController.viewUserRegisteredEvents(currentUser.getId());
        
        if (myEvents == null || myEvents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No registered events to export.", "No Data", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF Report");
        fileChooser.setSelectedFile(new File("MyRegisteredEvents.txt"));
        
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (PDFExporter.exportRegisteredEvents(currentUser, myEvents, file.getAbsolutePath())) {
                JOptionPane.showMessageDialog(this, "Report exported successfully to:\n" + file.getAbsolutePath(), "Export Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to export report.", "Export Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleMarkAttendance() {
        int row = myEventsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an event to mark attendance.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int eventID = (int) myEventsModel.getValueAt(row, 0);
        String eventName = (String) myEventsModel.getValueAt(row, 1);

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Mark attendance for: " + eventName + "?", 
            "Confirm Attendance", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // In a real application, you would save this to an attendance table
            JOptionPane.showMessageDialog(this, 
                "Attendance marked for: " + eventName + "\nTime: " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 
                "Attendance Recorded", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showEventDetails() {
        int row = allEventsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an event to view details.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int eventID = (int) allEventsModel.getValueAt(row, 0);
        Event event = eventController.getEventById(eventID);
        
        if (event != null) {
            // Create a temporary enhanced event with additional details
            Event enhancedEvent = new Event(
                event.getEventID(),
                event.getEventName(),
                event.getDate(),
                event.getTime(),
                event.getDescription(),
                "", // imagePath - empty for now
                "General", // category - default
                100, // maxCapacity - default
                "College Campus" // location - default
            );
            
            EventDetailsDialog dialog = new EventDetailsDialog((JFrame) SwingUtilities.getWindowAncestor(this), enhancedEvent);
            dialog.setVisible(true);
        }
    }
}