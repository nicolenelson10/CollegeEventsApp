package com.collegeeventapp.model;

import java.time.LocalDateTime;

public class Registration {
    private int registrationID; 
    private int userID;         
    private int eventID;        
    private LocalDateTime registrationDate; 

    public Registration(int registrationID, int userID, int eventID, LocalDateTime registrationDate) {
        this.registrationID = registrationID;
        this.userID = userID;
        this.eventID = eventID;
        this.registrationDate = registrationDate;
    }

    // Getters
    public int getRegistrationID() { return registrationID; }
    public int getUserID() { return userID; }
    public int getEventID() { return eventID; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }

    // Setters 
    public void setUserID(int userID) { this.userID = userID; }
    public void setEventID(int eventID) { this.eventID = eventID; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }
}