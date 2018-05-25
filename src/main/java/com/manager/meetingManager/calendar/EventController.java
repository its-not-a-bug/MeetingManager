package com.manager.meetingManager.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
class EventController {

    private EventService eventService;

    @Autowired
    EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value="/allevents", method=RequestMethod.GET)
    public List<Events> allEvents() {
        return eventService.findAll();
    }

    @RequestMapping(value="/event", method=RequestMethod.POST)
    public void addEvent(@RequestBody Events event) {
        eventService.saveOrUpdate(event);
    }

    @RequestMapping(value="/event", method=RequestMethod.PATCH)
    public void updateEvent(@RequestBody Events event) {
        eventService.saveOrUpdate(event);
    }

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public List<Events> getEventsInRange(@RequestParam(value = "start", required = true) String start,
                                         @RequestParam(value = "end", required = true) String end) {
        Date startDate;
        Date endDate;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        return eventService.findByDatesBetween(startDate, endDate);
    }

}
