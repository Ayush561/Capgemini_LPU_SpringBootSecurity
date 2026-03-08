package com.capgemini.eventAnnouncementSystem.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.eventAnnouncementSystem.entity.Event;



public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEventType(String eventType);

    List<Event> findByTitleContaining(String title);

}