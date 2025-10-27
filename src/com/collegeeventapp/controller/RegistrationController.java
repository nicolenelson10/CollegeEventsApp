package com.collegeeventapp.controller;

import com.collegeeventapp.dao.RegistrationDAO;
import com.collegeeventapp.dao.UserDAO;
import com.collegeeventapp.model.Registration;
import com.collegeeventapp.model.Event;
import java.sql.SQLException;
import java.util.List;

public class RegistrationController {

    private RegistrationDAO registrationDAO;
    private UserDAO userDAO;

    public RegistrationController() {
        this.registrationDAO = new RegistrationDAO();
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(int userID, int eventID) {
        try {
            registrationDAO.createRegistration(userID, eventID);
            return true;
        } catch (SQLException e) {
            System.err.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelRegistration(int userID, int eventID) {
        try {
            registrationDAO.deleteRegistration(userID, eventID);
            return true;
        } catch (SQLException e) {
            System.err.println("Cancellation failed: " + e.getMessage());
            return false;
        }
    }

    public List<Event> viewUserRegisteredEvents(int userID) {
        try {
            return userDAO.getRegisteredEvents(userID);
        } catch (SQLException e) {
            System.err.println("Error viewing user events: " + e.getMessage());
            return null;
        }
    }

    public List<Registration> getUserRegistrations(int userID) {
        try {
            return registrationDAO.getRegistrationsByUserID(userID);
        } catch (SQLException e) {
            System.err.println("Error retrieving registration records: " + e.getMessage());
            return null;
        }
    }
}