package com.github.dimon_7147.webpaper.Controllers;

import com.github.dimon_7147.webpaper.Objects.User;
import com.github.dimon_7147.webpaper.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {
    @Autowired
    private AirPortRepository airPortRepository;
    @Autowired
    private LuggageRepository luggageRepository;

    @GetMapping("/")
    public String hello(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            var fakeUser = new User();
            fakeUser.setId(-1L);
            fakeUser.setLogin("null");
            model.addAttribute("user", fakeUser);
        }
        model.addAttribute("airports", airPortRepository.findAll());
        model.addAttribute("luggage", luggageRepository.findAll());
        model.addAttribute("dateNow", LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "hello";
    }
}
