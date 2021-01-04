package com.github.dimon_7147.webpaper.Controllers;

import com.github.dimon_7147.webpaper.Objects.BookedTicket;
import com.github.dimon_7147.webpaper.Objects.User;
import com.github.dimon_7147.webpaper.Objects.api.AdminInfo;
import com.github.dimon_7147.webpaper.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        var fullUser = userRepository.getOne(user.getId());
        var tickets = new ArrayList<BookedTicket>();
        for (var tick : fullUser.getBookedTickets()) {
            tick.setUser(user);
            tickets.add(tick);
        }
        model.addAttribute("tickets", tickets);
        return "user/index";
    }
}
