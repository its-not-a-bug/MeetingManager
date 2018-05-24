package com.manager.meetingManager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getUsers(Model model, @ModelAttribute("info") String info, @ModelAttribute("error") String error) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        model.addAttribute("info", info);
        model.addAttribute("error", error);
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
    public String addToDB(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if (bindingResult.hasErrors()) {
            System.out.println("Wysąpił błąd walidacji formularza");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    }
            );
            model.addAttribute("error", "Wystąpił błąd walidacji danych. Popraw dane w formularzu!");
            return "adduser";
        } else {
            userService.saveOrUpdate(user);
            redirectAttributes.addFlashAttribute("info", "Użytkownik został dodany poprawnie");
            return "redirect:/users";
        }
    }

    @GetMapping("/user/{id}")
    public String detailsUser(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            model.addAttribute("user",optionalUser.get());
            return "user";
        }else {
            redirectAttributes.addFlashAttribute("error", "Błąd!! Nie ma użytkownika z takim ID !!");
            return "redirect:/users";
        }

    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            model.addAttribute("user", optionalUser.get());
            return "edituser";
        }else{
            redirectAttributes.addFlashAttribute("error", "Błąd!! Nie ma użytkownika z takim ID !!");
            return "redirect:/users";
        }

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUserInDB(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            System.out.println("Wysąpił błąd walidacji formularza");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    }
            );
            model.addAttribute("error", "Wystąpił błąd walidacji danych. Popraw dane w formularzu!");
            return "edituser";
        } else {
            userService.saveOrUpdate(user);
            redirectAttributes.addFlashAttribute("info", "Użytkownik został poprawnie edytowany");
            return "redirect:/users";
        }
    }

}
