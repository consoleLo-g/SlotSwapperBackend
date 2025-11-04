package com.example.SlotSwapperBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SlotSwapperBackend.Model.Event;
import com.example.SlotSwapperBackend.Repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepo;

    public Event createEvent(Event event) {
        return eventRepo.save(event);
    }

    public List<Event> getEventsByUser(String userId) {
        return eventRepo.findByUserId(userId);
    }

    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    public void deleteEvent(String id) {
        eventRepo.deleteById(id);
    }
}
