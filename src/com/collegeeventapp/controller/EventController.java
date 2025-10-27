package com.collegeeventapp.controller;

import com.collegeeventapp.dao.EventDAO;
import com.collegeeventapp.model.Event;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventController {

    private EventDAO eventDAO;
    public EventController() {
        this.eventDAO = new EventDAO();
    }

    public boolean createEvent(String name, LocalDate date, LocalTime time, String desc) {
        try {
            Event event = new Event(0, name, date, time, desc);
            eventDAO.addEvent(event);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEvent(Event event) {
        try {
            eventDAO.updateEvent(event);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEvent(int eventId) {
        try {
            eventDAO.deleteEvent(eventId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Event> getAllEvents() {
        try {
            return eventDAO.getAllEvents();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Event getEventById(int id) {
        try {
            return eventDAO.getEventById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}