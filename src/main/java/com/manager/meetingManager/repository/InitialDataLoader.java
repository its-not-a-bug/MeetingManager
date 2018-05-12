package com.manager.meetingManager.repository;

import com.manager.meetingManager.domain.User;
import com.manager.meetingManager.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationListener<ApplicationReadyEvent> {

    private final UserService userService;

    public InitialDataLoader(UserService userService) {
        this.userService = userService;
    }

    private boolean alreadySetup = false;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (alreadySetup) {
            //if data is loaded - do nothing, else: load data
            return;
        }
        loadData();
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
}
