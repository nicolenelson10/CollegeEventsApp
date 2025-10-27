package com.collegeeventapp.view;

import com.collegeeventapp.model.User;
import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame(boolean isAdmin, User user) {
        super("College Event Management - Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        if (isAdmin) {
            add(new AdminPanel(), BorderLayout.CENTER);
        } else {
            add(new RegisterEventPanel(user), BorderLayout.CENTER);
        }
    }
}