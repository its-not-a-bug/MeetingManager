package com.manager.meetingManager.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class CalendarController {

    private final EventService eventService;

    @Autowired
    public CalendarController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping("calendar")
    public String calendar(Model model) {
        model.addAttribute("events", new Events());
        return "calendar";
    }

    @RequestMapping(value = "addevent", method = RequestMethod.POST)
    public String addEvent(@Valid Events events, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            }
            );
            model.addAttribute("error", "Wystąpił błąd, popraw formualrz");
            return "calendar";
        }else {
            eventService.saveOrUpdate(events);
            redirectAttributes.addFlashAttribute("info", "Dodałem nowe zdarzenie");
            return "redirect:/calendar";
        }

    }

}
