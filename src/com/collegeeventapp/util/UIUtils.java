package com.collegeeventapp.util;

import javax.swing.JOptionPane;
import java.awt.Component;

public class UIUtils {

    
    private UIUtils() {}

    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    
    public static void showInfo(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public static int getIntFromField(Component parent, String text, String fieldName) {
        if (Validator.isNullOrEmpty(text)) {
            showError(parent, fieldName + " cannot be empty.");
            return -1;
        }
        try {
            int value = Integer.parseInt(text);
            if (value <= 0) {
                showError(parent, fieldName + " must be a positive number.");
                return -1;
            }
            return value;
        } catch (NumberFormatException e) {
            showError(parent, "Invalid format for " + fieldName + ". Must be a whole number.");
            return -1;
        }
    }
}