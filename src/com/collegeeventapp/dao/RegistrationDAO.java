package com.collegeeventapp.dao;

import com.collegeeventapp.model.Registration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    public void createRegistration(int userID, int eventID) throws SQLException {
        String sql = "INSERT INTO registrations (userID, eventID) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, eventID);
            stmt.executeUpdate();
            System.out.println("Registration created for User " + userID + " and Event " + eventID);
        }
    }

    public List<Registration> getRegistrationsByUserID(int userID) throws SQLException {
        List<Registration> registrations = new ArrayList<>();
        String sql = "SELECT registrationID, userID, eventID, registrationDate FROM registrations WHERE userID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    registrations.add(new Registration(
                        rs.getInt("registrationID"),
                        rs.getInt("userID"),
                        rs.getInt("eventID"),
                        rs.getTimestamp("registrationDate").toLocalDateTime()
                    ));
                }
            }
        }
        return registrations;
    }

    public void deleteRegistration(int userID, int eventID) throws SQLException {
        String sql = "DELETE FROM registrations WHERE userID = ? AND eventID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, eventID);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Registration deleted for User " + userID + " and Event " + eventID);
            } else {
                System.out.println("No matching registration found.");
            }
        }
    }
}
