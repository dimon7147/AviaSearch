package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Airline;
import com.github.dimon_7147.webpaper.Objects.User;
import com.github.dimon_7147.webpaper.Objects.api.AdminInfo;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {
    @Autowired
    private AirLineRepository airLineRepository;
    @Autowired
    private AirPortRepository airPortRepository;
    @Autowired
    private BookedTicketRepository bookedTicketRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private LuggageRepository luggageRepository;
    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("info")
    public @ResponseBody AdminInfo getInfo() {
        var countries = countryRepository.count();
        var planes = planeRepository.count();
        var airlines = airLineRepository.count();
        var bookedTickets = bookedTicketRepository.count();
        var luggage = luggageRepository.count();
        var roles = roleRepository.count();
        var users = userRepository.count();
        var tickets = ticketRepository.count();
        var airports = airPortRepository.count();
        return new AdminInfo(countries, planes, airlines, bookedTickets, luggage, roles, users, tickets, airports);
    }
}
