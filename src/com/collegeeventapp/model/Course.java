package com.collegeeventapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Course {
    private int courseID;
    private String courseName;
    private String courseCode;
    private String instructor;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime classTime;
    private String classroom;
    private int credits;
    private int maxStudents;
    private String category;

    public Course() {}

    public Course(int courseID, String courseName, String courseCode, String instructor, 
                  String description, LocalDate startDate, LocalDate endDate, 
                  LocalTime classTime, String classroom, int credits, int maxStudents, String category) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.classTime = classTime;
        this.classroom = classroom;
        this.credits = credits;
        this.maxStudents = maxStudents;
        this.category = category;
    }

    // Getters
    public int getCourseID() { return courseID; }
    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public String getInstructor() { return instructor; }
    public String getDescription() { return description; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public LocalTime getClassTime() { return classTime; }
    public String getClassroom() { return classroom; }
    public int getCredits() { return credits; }
    public int getMaxStudents() { return maxStudents; }
    public String getCategory() { return category; }

    // Setters
    public void setCourseID(int courseID) { this.courseID = courseID; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setDescription(String description) { this.description = description; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setClassTime(LocalTime classTime) { this.classTime = classTime; }
    public void setClassroom(String classroom) { this.classroom = classroom; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    public void setCategory(String category) { this.category = category; }
}