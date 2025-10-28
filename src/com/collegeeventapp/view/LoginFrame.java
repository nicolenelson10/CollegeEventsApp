package com.collegeeventapp.view;

import javax.swing.*;
import java.awt.*;
import com.collegeeventapp.controller.LoginController;
import com.collegeeventapp.model.User;
import com.collegeeventapp.dao.UserDAO;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton adminLoginButton;
    private JButton createAccountButton;
    private LoginController loginController;
    private UserDAO userDAO;

    public LoginFrame() {
        loginController = new LoginController();
        userDAO = new UserDAO();
        initializeComponents();
        setupLayout();
        setVisible(true);
    }

    private void initializeComponents() {
        setTitle("SJCET - Event Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(true);

        // Set modern look and feel
        // Use default look and feel

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Student Login");
        adminLoginButton = new JButton("Admin Login");
        createAccountButton = new JButton("Create New Account");

        // Style buttons
        loginButton.setPreferredSize(new Dimension(150, 35));
        adminLoginButton.setPreferredSize(new Dimension(150, 35));
        createAccountButton.setPreferredSize(new Dimension(200, 35));
        
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        adminLoginButton.setBackground(new Color(220, 20, 60));
        adminLoginButton.setForeground(Color.WHITE);
        createAccountButton.setBackground(new Color(34, 139, 34));
        createAccountButton.setForeground(Color.WHITE);

        loginButton.addActionListener(e -> handleStudentLogin());
        adminLoginButton.addActionListener(e -> handleAdminLogin());
        createAccountButton.addActionListener(e -> showCreateAccountDialog());
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 248, 255));
        JLabel titleLabel = new JLabel("SJCET - Event Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(25, 25, 112));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Email field
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(emailLabel, gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        emailField.setPreferredSize(new Dimension(200, 30));
        mainPanel.add(emailField, gbc);

        // Password field
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(passwordLabel, gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        passwordField.setPreferredSize(new Dimension(200, 30));
        mainPanel.add(passwordField, gbc);

        // Login buttons
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(loginButton);
        buttonPanel.add(adminLoginButton);
        mainPanel.add(buttonPanel, gbc);

        // Create account button
        gbc.gridy = 3;
        mainPanel.add(createAccountButton, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 248, 255));
        JLabel footerLabel = new JLabel("St. Joseph's College of Engineering and Technology");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void showCreateAccountDialog() {
        JDialog createDialog = new JDialog(this, "Create New Account", true);
        createDialog.setSize(400, 350);
        createDialog.setLocationRelativeTo(this);
        createDialog.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField nameField = new JTextField(20);
        JTextField newEmailField = new JTextField(20);
        JPasswordField newPasswordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"student", "admin"});

        // Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        createDialog.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        createDialog.add(nameField, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        createDialog.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        createDialog.add(emailField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        createDialog.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        createDialog.add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        createDialog.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        createDialog.add(confirmPasswordField, gbc);

        // Role
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.EAST;
        createDialog.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        createDialog.add(roleCombo, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JPanel dialogButtonPanel = new JPanel();
        
        JButton createBtn = new JButton("Create Account");
        JButton cancelBtn = new JButton("Cancel");
        
        createBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = newEmailField.getText().trim();
            String password = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String role = (String) roleCombo.getSelectedItem();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(createDialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(createDialog, "Passwords don't match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.length() < 6) {
                JOptionPane.showMessageDialog(createDialog, "Password must be at least 6 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                User newUser = new User(name, email, password, role);
                userDAO.addUser(newUser);
                JOptionPane.showMessageDialog(createDialog, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                createDialog.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(createDialog, "Error creating account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> createDialog.dispose());

        dialogButtonPanel.add(createBtn);
        dialogButtonPanel.add(cancelBtn);
        createDialog.add(dialogButtonPanel, gbc);

        createDialog.setVisible(true);
    }

    private void handleStudentLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = loginController.userLogin(email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Welcome " + user.getName() + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            // Open student dashboard
            new DashboardFrame(false, user).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials or not a student account!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAdminLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = loginController.adminLogin(email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Welcome Admin " + user.getName() + "!", "Admin Login Successful", JOptionPane.INFORMATION_MESSAGE);
            // Open admin dashboard
            new DashboardFrame(true, user).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials or not an admin account!", "Admin Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}