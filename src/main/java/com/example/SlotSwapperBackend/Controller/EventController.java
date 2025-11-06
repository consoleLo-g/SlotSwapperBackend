package com.example.SlotSwapperBackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.SlotSwapperBackend.Model.Event;
import com.example.SlotSwapperBackend.Service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // ✅ Create event (JSON body)
    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // ✅ Get all events for logged user
    @GetMapping("/user")
    public List<Event> getUserEvents(@RequestParam String userId) {
        return eventService.getEventsByUser(userId);
    }

    // ✅ Get all events
    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // ✅ Make event swappable
    @PutMapping("/{id}/make-swappable")
    public Event makeSwappable(@PathVariable String id) {
        Event event = eventService.getEventById(id);
        event.setSwappable(true);
        return eventService.updateEvent(event);
    }

    // ✅ Update event
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable String id, @RequestBody Event updatedEvent) {
        return eventService.updateEventById(id, updatedEvent);
    }

    // ✅ Delete event
    @DeleteMapping
    public String deleteEvent(@RequestParam String id) {
        eventService.deleteEvent(id);
        return "Event deleted";
    }
}
