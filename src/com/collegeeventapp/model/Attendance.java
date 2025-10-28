package com.collegeeventapp.model;

import java.time.LocalDateTime;

public class Attendance {
    private int attendanceID;
    private int userID;
    private int eventID;
    private LocalDateTime checkInTime;
    private boolean attended;
    private String notes;

    public Attendance() {}

    public Attendance(int attendanceID, int userID, int eventID, LocalDateTime checkInTime, boolean attended, String notes) {
        this.attendanceID = attendanceID;
        this.userID = userID;
        this.eventID = eventID;
        this.checkInTime = checkInTime;
        this.attended = attended;
        this.notes = notes;
    }

    // Getters
    public int getAttendanceID() { return attendanceID; }
    public int getUserID() { return userID; }
    public int getEventID() { return eventID; }
    public LocalDateTime getCheckInTime() { return checkInTime; }
    public boolean isAttended() { return attended; }
    public String getNotes() { return notes; }

    // Setters
    public void setAttendanceID(int attendanceID) { this.attendanceID = attendanceID; }
    public void setUserID(int userID) { this.userID = userID; }
    public void setEventID(int eventID) { this.eventID = eventID; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }
    public void setAttended(boolean attended) { this.attended = attended; }
    public void setNotes(String notes) { this.notes = notes; }
}