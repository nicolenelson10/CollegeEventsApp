package com.collegeeventapp.dao;

import com.collegeeventapp.model.User;
import com.collegeeventapp.model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
            System.out.println("User added successfully: " + user.getName());
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT id, name, email, password, role FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }
        }
        return null;
    }

    public List<Event> getRegisteredEvents(int userID) throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT e.eventID, e.eventName, e.date, e.time, e.description " +
                     "FROM events e JOIN registrations r ON e.eventID = r.eventID WHERE r.userID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    events.add(new Event(
                        rs.getInt("eventID"),
                        rs.getString("eventName"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getString("description")
                    ));
                }
            }
        }
        return events;
    }
}
