package com.manager.meetingManager.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Events> findAll(){ return eventRepository.findAll(); }

    @Transactional
    public void saveOrUpdate(Events events){ eventRepository.save(events); }

    @Transactional
    public void delete(final Long id){eventRepository.deleteById(id);}

    public List<Events> findByDatesBetween(Date startDate, Date endDate) { return  eventRepository.findByDatesBetween(startDate, endDate); }

}
