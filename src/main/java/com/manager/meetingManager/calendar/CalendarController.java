package com.manager.meetingManager.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CalendarController {

    @RequestMapping("calendar")
    public String calendar() {
        return "calendar";
    }

}
