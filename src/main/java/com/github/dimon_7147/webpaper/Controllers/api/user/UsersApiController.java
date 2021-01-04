package com.github.dimon_7147.webpaper.Controllers.api.user;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.*;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.BookedTicketRepository;
import com.github.dimon_7147.webpaper.repos.UserRepository;
import com.github.dimon_7147.webpaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UsersApiController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BookedTicketRepository bookedTicketRepository;

    @GetMapping("/tickets")
    public @ResponseBody List<BookedTicket> getBookedTickets(@AuthenticationPrincipal User user) {
        var fullUser = userRepository.getOne(user.getId());
        var tickets = new ArrayList<BookedTicket>();
        for (var tick : fullUser.getBookedTickets()) {
            tick.setUser(user);
            tickets.add(tick);
        }
        return tickets;
    }

    @GetMapping("/tickets/sell")
    public @ResponseBody Object sellTicker(@AuthenticationPrincipal User user, @RequestParam long id) {
        bookedTicketRepository.getOne(id).getTicket().setTicketsAmount(bookedTicketRepository.getOne(id).getTicket().getTicketsAmount()+1);
        bookedTicketRepository.delete(bookedTicketRepository.getOne(id));
        return new ErrorInfo(200, "Успешно!");
    }

    @PostMapping("")
    public Object saveUser(@AuthenticationPrincipal User current_user, HttpServletResponse response, @RequestBody @Valid User user, BindingResult bindingResult) {
        if (user == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформирован запрос");
        } else {
            System.out.println(user.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = userRepository.findById(current_user.getId()).get();
            correct.setLogin(user.getLogin());
            correct.setEmail(user.getEmail());
            correct.setFullName(user.getFullName());
            correct.setPassport(user.getPassport());
            correct.setDateOfBirth(user.getDateOfBirth());
            if (user.getPassword() != null && !user.getPassword().equals("Пароль")) {
                correct.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Данные успешно обновлены");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

}
