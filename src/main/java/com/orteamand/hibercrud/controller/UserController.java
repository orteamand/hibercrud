package com.orteamand.hibercrud.controller;


import com.orteamand.hibercrud.model.User;
import com.orteamand.hibercrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(@RequestParam(value = "page", required = false) Long page, Model model) {
        if (page == null)
            page = 1L;
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers(page));
        model.addAttribute("searchedUser", new User());
        model.addAttribute("page", page);

        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }

        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, @RequestParam(value = "page", required = false) Long page, Model model) {
        if (page == null)
            page = 1L;
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.listUsers(page));
        model.addAttribute("searchedUser", new User());
        model.addAttribute("page", page);

        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "userdata";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String users(@ModelAttribute("searchedUser") User user, @RequestParam String action, Model model) {
        List<User> users = new ArrayList<User>();
        switch (action) {
            case "Search By Name":
                users = userService.listUsers(user.getName());
                break;
            case "Search By Age":
                users = userService.listUsers(user.getAge());
                break;
            default:
                break;
        }
        model.addAttribute("listUsers", users);

        return "search";
    }
}
