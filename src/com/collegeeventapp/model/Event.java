package com.collegeeventapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private int eventID;
    private String eventName;
    private LocalDate date;
    private LocalTime time;
    private String description;

    // Constructor
    public Event(int eventID, String eventName, LocalDate date, LocalTime time, String description) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    // Getters
    public int getEventID() { return eventID; }
    public String getEventName() { return eventName; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public String getDescription() { return description; }

    // Setters
    public void setEventName(String eventName) { this.eventName = eventName; }
    
    public void setDate(LocalDate date) { this.date = date; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setDescription(String description) { this.description = description; }
}
