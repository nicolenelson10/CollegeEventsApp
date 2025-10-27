package com.collegeeventapp.dao;

import com.collegeeventapp.model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public void addEvent(Event event) throws SQLException {
        String sql = "INSERT INTO events (eventName, date, time, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEventName());
            stmt.setDate(2, java.sql.Date.valueOf(event.getDate()));
            stmt.setTime(3, java.sql.Time.valueOf(event.getTime()));
            stmt.setString(4, event.getDescription());
            stmt.executeUpdate();
            System.out.println("Event added successfully: " + event.getEventName());
        }
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT eventID, eventName, date, time, description FROM events ORDER BY date, time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                    rs.getInt("eventID"),
                    rs.getString("eventName"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime(),
                    rs.getString("description")
                );
                events.add(event);
            }
        }
        return events;
    }

    public Event getEventById(int eventID) throws SQLException {
        String sql = "SELECT eventID, eventName, date, time, description FROM events WHERE eventID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Event(
                        rs.getInt("eventID"),
                        rs.getString("eventName"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getString("description")
                    );
                }
            }
        }
        return null;
    }

    public void updateEvent(Event event) throws SQLException {
        String sql = "UPDATE events SET eventName = ?, date = ?, time = ?, description = ? WHERE eventID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEventName());
            stmt.setDate(2, java.sql.Date.valueOf(event.getDate()));
            stmt.setTime(3, java.sql.Time.valueOf(event.getTime()));
            stmt.setString(4, event.getDescription());
            stmt.setInt(5, event.getEventID());
            stmt.executeUpdate();
            System.out.println("Event updated successfully: " + event.getEventName());
        }
    }

    public void deleteEvent(int eventId) throws SQLException {
        String sql = "DELETE FROM events WHERE eventID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Event " + eventId + " deleted successfully.");
            } else {
                System.out.println("No event found with ID " + eventId + ".");
            }
        }
    }
}
