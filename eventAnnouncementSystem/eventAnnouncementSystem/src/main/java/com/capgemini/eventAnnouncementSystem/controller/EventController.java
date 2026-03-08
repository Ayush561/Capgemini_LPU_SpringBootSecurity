package com.capgemini.eventAnnouncementSystem.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.eventAnnouncementSystem.entity.Event;
import com.capgemini.eventAnnouncementSystem.service.EventService;


@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;

    // USER + ADMIN
    @GetMapping
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    // USER + ADMIN
    @GetMapping("/{id}")
    public Event getById(@PathVariable Long id) {
        return service.getEventById(id);
    }

    // USER + ADMIN
    @GetMapping("/type/{eventType}")
    public List<Event> getByType(@PathVariable String eventType) {
        return service.getByType(eventType);
    }

    // USER + ADMIN
    @GetMapping("/search")
    public List<Event> search(@RequestParam String title) {
        return service.searchByTitle(title);
    }

    // ADMIN
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return service.createEvent(event);
    }

    // ADMIN
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return service.updateEvent(id, event);
    }

    // ADMIN
    @PatchMapping("/{id}/cancel")
    public Event cancelEvent(@PathVariable Long id) {
        return service.cancelEvent(id);
    }

    // ADMIN
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        service.deleteEvent(id);
        return "Event Deleted";
    }
}