package com.collegeeventapp.util;

import com.collegeeventapp.model.Event;
import com.collegeeventapp.model.User;
import java.time.format.DateTimeFormatter;

public class EmailNotifier {
    
    // Note: This is a simplified email notifier
    // In a real application, you would use JavaMail API or similar
    
    /**
     * Simulates sending registration confirmation email
     */
    public static boolean sendRegistrationConfirmation(User user, Event event) {
        try {
            String subject = "Event Registration Confirmation - " + event.getEventName();
            String message = buildRegistrationMessage(user, event);
            
            // Simulate email sending (in real app, use JavaMail)
            System.out.println("=== EMAIL NOTIFICATION ===");
            System.out.println("To: " + user.getEmail());
            System.out.println("Subject: " + subject);
            System.out.println("Message:");
            System.out.println(message);
            System.out.println("=========================");
            
            // Log to console for now
            logNotification(user.getEmail(), subject, "Registration Confirmation");
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Simulates sending event reminder email
     */
    public static boolean sendEventReminder(User user, Event event) {
        try {
            String subject = "Event Reminder - " + event.getEventName() + " Tomorrow!";
            String message = buildReminderMessage(user, event);
            
            System.out.println("=== EMAIL REMINDER ===");
            System.out.println("To: " + user.getEmail());
            System.out.println("Subject: " + subject);
            System.out.println("Message:");
            System.out.println(message);
            System.out.println("=====================");
            
            logNotification(user.getEmail(), subject, "Event Reminder");
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Simulates sending cancellation email
     */
    public static boolean sendCancellationNotification(User user, Event event) {
        try {
            String subject = "Event Cancellation - " + event.getEventName();
            String message = buildCancellationMessage(user, event);
            
            System.out.println("=== CANCELLATION NOTICE ===");
            System.out.println("To: " + user.getEmail());
            System.out.println("Subject: " + subject);
            System.out.println("Message:");
            System.out.println(message);
            System.out.println("===========================");
            
            logNotification(user.getEmail(), subject, "Event Cancellation");
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static String buildRegistrationMessage(User user, Event event) {
        return String.format(
            "Dear %s,\n\n" +
            "Thank you for registering for the following event:\n\n" +
            "Event: %s\n" +
            "Date: %s\n" +
            "Time: %s\n" +
            "Description: %s\n\n" +
            "We look forward to seeing you there!\n\n" +
            "Best regards,\n" +
            "College Events Team",
            user.getName(),
            event.getEventName(),
            event.getDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")),
            event.getTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
            event.getDescription()
        );
    }
    
    private static String buildReminderMessage(User user, Event event) {
        return String.format(
            "Dear %s,\n\n" +
            "This is a friendly reminder about your registered event tomorrow:\n\n" +
            "Event: %s\n" +
            "Date: %s\n" +
            "Time: %s\n" +
            "Description: %s\n\n" +
            "Don't forget to attend!\n\n" +
            "Best regards,\n" +
            "College Events Team",
            user.getName(),
            event.getEventName(),
            event.getDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")),
            event.getTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
            event.getDescription()
        );
    }
    
    private static String buildCancellationMessage(User user, Event event) {
        return String.format(
            "Dear %s,\n\n" +
            "We regret to inform you that the following event has been cancelled:\n\n" +
            "Event: %s\n" +
            "Originally scheduled for: %s at %s\n\n" +
            "We apologize for any inconvenience caused.\n\n" +
            "Best regards,\n" +
            "College Events Team",
            user.getName(),
            event.getEventName(),
            event.getDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")),
            event.getTime().format(DateTimeFormatter.ofPattern("hh:mm a"))
        );
    }
    
    private static void logNotification(String email, String subject, String type) {
        System.out.println("[EMAIL LOG] " + type + " sent to " + email + " - " + subject);
    }
}