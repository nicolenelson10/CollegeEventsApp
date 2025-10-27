package com.collegeeventapp.view;

import com.collegeeventapp.controller.EventController;
import com.collegeeventapp.model.Event;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EventListPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private EventController eventController;

    public EventListPanel(boolean readOnly) {
        this.eventController = new EventController();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Event List"));

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Date", "Time", "Description"}, 0);
        table = new JTable(tableModel);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        refreshEventList();
    }

    public void refreshEventList() {
        try {
            tableModel.setRowCount(0);
            List<Event> events = eventController.getAllEvents();
            if (events != null) {
                for (Event e : events) {
                    tableModel.addRow(new Object[]{
                        e.getEventID(),
                        e.getEventName(),
                        e.getDate(),
                        e.getTime(),
                        e.getDescription()
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading events: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}