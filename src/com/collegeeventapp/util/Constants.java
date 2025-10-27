package com.collegeeventapp.util;

public final class Constants {

    
    private Constants() {}

    
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL_PREFIX = "jdbc:mysql://";
    public static final String DB_NAME = "college_events_db";

   
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    // --- Application Messages ---
    public static final String LOGIN_FAILED_MSG = "Invalid credentials. Please try again.";
    public static final String INPUT_ERROR_MSG = "Please check your input fields and try again.";
    public static final String DATABASE_ERROR_MSG = "A database error occurred. Check connection and logs.";
    
    // --- Security ---
    public static final int MIN_PASSWORD_LENGTH = 6;
}