package com.collegeeventapp.controller;

import com.collegeeventapp.dao.UserDAO;
import com.collegeeventapp.model.User;

public class LoginController {

    private UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    // For student login
    public User userLogin(String email, String password) {
        try {
            User user = userDAO.getUserByEmail(email);

            if (user == null) {
                System.out.println("❌ User not found.");
                return null;
            }

            if (user.getPassword().equals(password) && user.getRole().equalsIgnoreCase("student")) {
                System.out.println("✅ Student " + user.getName() + " logged in successfully.");
                return user;
            } else {
                System.out.println("❌ Incorrect password or role mismatch.");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // For admin login
    public User adminLogin(String email, String password) {
        try {
            User user = userDAO.getUserByEmail(email);

            if (user == null) {
                System.out.println("❌ Admin not found.");
                return null;
            }

            if (user.getPassword().equals(password) && user.getRole().equalsIgnoreCase("admin")) {
                System.out.println("✅ Admin " + user.getName() + " logged in successfully.");
                return user;
            } else {
                System.out.println("❌ Incorrect password or role mismatch.");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}