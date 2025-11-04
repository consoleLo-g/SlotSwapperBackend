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

    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/user/{userId}")
    public List<Event> getUserEvents(@PathVariable String userId) {
        return eventService.getEventsByUser(userId);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return "Event deleted";
    }
}
