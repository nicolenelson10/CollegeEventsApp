package com.collegeeventapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private int eventID;
    private String eventName;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String imagePath;
    private String category;
    private int maxCapacity;
    private String location;

    // Constructor
    public Event(int eventID, String eventName, LocalDate date, LocalTime time, String description) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.description = description;
        this.imagePath = "";
        this.category = "General";
        this.maxCapacity = 100;
        this.location = "TBD";
    }

    // Full Constructor
    public Event(int eventID, String eventName, LocalDate date, LocalTime time, String description, 
                 String imagePath, String category, int maxCapacity, String location) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.description = description;
        this.imagePath = imagePath;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.location = location;
    }

    // Getters
    public int getEventID() { return eventID; }
    public String getEventName() { return eventName; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public String getDescription() { return description; }

    // Additional Getters
    public String getImagePath() { return imagePath; }
    public String getCategory() { return category; }
    public int getMaxCapacity() { return maxCapacity; }
    public String getLocation() { return location; }

    // Setters
    public void setEventName(String eventName) { this.eventName = eventName; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setDescription(String description) { this.description = description; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setCategory(String category) { this.category = category; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
    public void setLocation(String location) { this.location = location; }
}
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
