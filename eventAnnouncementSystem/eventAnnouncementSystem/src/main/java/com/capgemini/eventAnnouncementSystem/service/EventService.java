package com.capgemini.eventAnnouncementSystem.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eventAnnouncementSystem.entity.Event;
import com.capgemini.eventAnnouncementSystem.repository.EventRepository;



@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event getEventById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Event> getByType(String type) {
        return repository.findByEventType(type);
    }

    public List<Event> searchByTitle(String title) {
        return repository.findByTitleContaining(title);
    }

    public Event createEvent(Event event) {
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        event.setStatus("SCHEDULED");
        return repository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        Event e = repository.findById(id).orElse(null);

        if (e != null) {
            e.setTitle(event.getTitle());
            e.setDescription(event.getDescription());
            e.setEventType(event.getEventType());
            e.setLocation(event.getLocation());
            e.setEventDate(event.getEventDate());
            e.setStartTime(event.getStartTime());
            e.setEndTime(event.getEndTime());
            e.setOrganizerName(event.getOrganizerName());
            e.setContactPhone(event.getContactPhone());
            e.setUpdatedAt(LocalDateTime.now());

            return repository.save(e);
        }

        return null;
    }

    public Event cancelEvent(Long id) {

        Event e = repository.findById(id).orElse(null);

        if (e != null) {
            e.setStatus("CANCELLED");
            return repository.save(e);
        }

        return null;
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }

}
