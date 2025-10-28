package com.collegeeventapp.view;

import com.collegeeventapp.model.User;
import javax.swing.*;
import java.awt.*;
import com.collegeeventapp.view.LoginFrame;

public class DashboardFrame extends JFrame {

    public DashboardFrame(boolean isAdmin, User user) {
        super("SJCET - Event Management Dashboard - " + user.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with user info and logout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 248, 255));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel userLabel = new JLabel("Welcome, " + user.getName() + " (" + user.getRole().toUpperCase() + ")");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(userLabel, BorderLayout.WEST);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        topPanel.add(logoutButton, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);

        if (isAdmin) {
            add(new AdminPanel(), BorderLayout.CENTER);
        } else {
            add(new RegisterEventPanel(user), BorderLayout.CENTER);
        }
    }
}