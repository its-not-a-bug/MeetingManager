package com.manager.meetingManager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "adduser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addToDB(@Valid User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            System.out.println("Wysąpił błąd walidacji formularza");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    }
            );
            return "adduser";
        } else {
            userService.saveOrUpdate(user);
            return "redirect:/users";
        }
    }

    @GetMapping("/user/{id}")
    public String detailsUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "user";
    }
}
