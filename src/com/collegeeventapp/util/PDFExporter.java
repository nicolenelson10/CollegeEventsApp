package com.collegeeventapp.util;

import com.collegeeventapp.model.Event;
import com.collegeeventapp.model.User;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFExporter {
    
    /**
     * Exports registered events to a simple text file (can be enhanced to PDF)
     */
    public static boolean exportRegisteredEvents(User user, List<Event> events, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Header
            writer.write("=".repeat(60) + "\n");
            writer.write("COLLEGE EVENTS - REGISTERED EVENTS REPORT\n");
            writer.write("=".repeat(60) + "\n\n");
            
            writer.write("Student: " + user.getName() + "\n");
            writer.write("Email: " + user.getEmail() + "\n");
            writer.write("Generated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n\n");
            
            writer.write("REGISTERED EVENTS:\n");
            writer.write("-".repeat(60) + "\n");
            
            if (events == null || events.isEmpty()) {
                writer.write("No events registered.\n");
            } else {
                int count = 1;
                for (Event event : events) {
                    writer.write(count + ". " + event.getEventName() + "\n");
                    writer.write("   Date: " + event.getDate() + "\n");
                    writer.write("   Time: " + event.getTime() + "\n");
                    writer.write("   Description: " + event.getDescription() + "\n");
                    writer.write("\n");
                    count++;
                }
            }
            
            writer.write("-".repeat(60) + "\n");
            writer.write("Total Events Registered: " + (events != null ? events.size() : 0) + "\n");
            writer.write("=".repeat(60) + "\n");
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Exports event attendance report
     */
    public static boolean exportAttendanceReport(Event event, List<User> attendees, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("=".repeat(60) + "\n");
            writer.write("EVENT ATTENDANCE REPORT\n");
            writer.write("=".repeat(60) + "\n\n");
            
            writer.write("Event: " + event.getEventName() + "\n");
            writer.write("Date: " + event.getDate() + "\n");
            writer.write("Time: " + event.getTime() + "\n");
            writer.write("Description: " + event.getDescription() + "\n\n");
            
            writer.write("ATTENDEES:\n");
            writer.write("-".repeat(60) + "\n");
            
            if (attendees == null || attendees.isEmpty()) {
                writer.write("No attendees recorded.\n");
            } else {
                int count = 1;
                for (User user : attendees) {
                    writer.write(count + ". " + user.getName() + " (" + user.getEmail() + ")\n");
                    count++;
                }
            }
            
            writer.write("-".repeat(60) + "\n");
            writer.write("Total Attendees: " + (attendees != null ? attendees.size() : 0) + "\n");
            writer.write("Generated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("=".repeat(60) + "\n");
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}