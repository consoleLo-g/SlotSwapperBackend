package com.example.SlotSwapperBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SlotSwapperBackend.Model.Event;
import com.example.SlotSwapperBackend.Repository.EventRepository;

import java.util.List;
import java.util.Optional;

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

    // ✅ Added: fetch event by ID
    public Event getEventById(String id) {
        return eventRepo.findById(id).orElse(null);
    }

    // ✅ Added: update event (simple save)
    public Event updateEvent(Event event) {
        return eventRepo.save(event);
    }

    // ✅ Added: update event by ID (only replace changed fields)
    public Event updateEventById(String id, Event updated) {
        Optional<Event> existingOpt = eventRepo.findById(id);
        if (existingOpt.isEmpty())
            return null;

        Event existing = existingOpt.get();

        // ✅ Only overwrite values provided by frontend
        if (updated.getTitle() != null)
            existing.setTitle(updated.getTitle());
        if (updated.getDescription() != null)
            existing.setDescription(updated.getDescription());
        if (updated.getDate() != null)
            existing.setDate(updated.getDate());
        if (updated.getStartTime() != null)
            existing.setStartTime(updated.getStartTime());
        if (updated.getEndTime() != null)
            existing.setEndTime(updated.getEndTime());
        if (updated.getSwappable() != null)
            existing.setSwappable(updated.getSwappable());

        return eventRepo.save(existing);
    }
}
