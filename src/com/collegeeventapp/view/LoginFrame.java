package com.collegeeventapp.view;

import com.collegeeventapp.controller.LoginController;
import com.collegeeventapp.model.User;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JButton loginButton, clearButton;
    private LoginController loginController;

    public LoginFrame() {
        super("College Event Management - Login");
        this.loginController = new LoginController();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Login"));

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Role:"));
        roleBox = new JComboBox<>(new String[]{"Student", "Admin"});
        formPanel.add(roleBox);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");
        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        clearButton.addActionListener(e -> {
            emailField.setText("");
            passwordField.setText("");
        });

        loginButton.addActionListener(e -> handleLogin());

        setVisible(true);
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = roleBox.getSelectedItem().toString();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user;
        if (role.equals("Admin")) {
            user = loginController.adminLogin(email, password);
            if (user != null) {
                new DashboardFrame(true, user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Admin credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            user = loginController.userLogin(email, password);
            if (user != null) {
                new DashboardFrame(false, user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Student credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}