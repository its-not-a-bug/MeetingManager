package com.manager.meetingManager.util;

import com.manager.meetingManager.calendar.EventRepository;
import com.manager.meetingManager.calendar.Events;
import com.manager.meetingManager.user.User;
import com.manager.meetingManager.user.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class InitialDataLoader implements ApplicationListener<ApplicationReadyEvent> {

    private final UserService userService;

    public InitialDataLoader(UserService userService, EventRepository eventRepository) {
        this.userService = userService;
        this.eventRepository = eventRepository;
    }

    private boolean alreadySetup = false;

    private final EventRepository eventRepository;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (alreadySetup) {
            //if data is loaded - do nothing, else: load data
            return;
        }
        loadData();
        insertEvent("2018-05-22 11:00", "2018-05-22 15:00", "Jakies spotkanie", "Kraków");
        insertEvent("2018-05-23 11:00", "2018-05-23 15:00", "Szkolenie", "Warszawa");
        insertEvent("2018-05-21 11:00", "2018-05-21 15:00", "Kurs", "Wrocław");
        insertEvent("2018-05-22 08:00", "2018-05-22 11:00", "Sniadanie", "Kraków");
        alreadySetup = true;
    }

    private void loadData() {
        User nebra = User.builder()
                .firstName("Marcin").lastName("Pa")
                .email("nebra@gmail.com")
                .phoneNumber("510100100")
                .build();
        userService.saveOrUpdate(nebra);

        User user2 = User.builder()
                .firstName("Tomasz").lastName("Za")
                .email("tomaszz@gmail.com")
                .phoneNumber("520600520")
                .build();
        userService.saveOrUpdate(user2);
    }

    private void insertEvent(String start, String end, String title, String desc){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        try {
            Date date1 = sdf.parse(start);
            Date date2 = sdf.parse(end);

            Events events = Events.builder()
                    .start(date1)
                    .end(date2)
                    .title(title)
                    .description(desc)
                    .build();
            eventRepository.save(events);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
