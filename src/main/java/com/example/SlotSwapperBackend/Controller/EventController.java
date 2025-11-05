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

    // ✅ Create event using parameters
    @PostMapping("/create")
    public Event createEvent(
            @RequestParam String userId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam String startTime,
            @RequestParam String endTime) {
        Event event = new Event();
        event.setUserId(userId);
        event.setTitle(title);
        event.setDescription(description);
        event.setDate(date);
        event.setStartTime(startTime);
        event.setEndTime(endTime);

        return eventService.createEvent(event);
    }

    // ✅ Get events of a user
    @GetMapping("/user")
    public List<Event> getUserEvents(@RequestParam String userId) {
        return eventService.getEventsByUser(userId);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // ✅ Delete event by ID
    @DeleteMapping
    public String deleteEvent(@RequestParam String id) {
        eventService.deleteEvent(id);
        return "Event deleted";
    }
}
