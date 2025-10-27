package com.collegeeventapp.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

   
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Validates an email address format.
     */
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

   
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= Constants.MIN_PASSWORD_LENGTH;
    }

    
    public static boolean isValidID(String idStr) {
        if (isNullOrEmpty(idStr)) {
            return false;
        }
        try {
            int id = Integer.parseInt(idStr);
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    
    public static boolean isValidDate(String dateStr) {
        if (isNullOrEmpty(dateStr)) {
            return false;
        }
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    
    public static boolean isValidTime(String timeStr) {
        if (isNullOrEmpty(timeStr)) {
            return false;
        }
        try {
            LocalTime.parse(timeStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}