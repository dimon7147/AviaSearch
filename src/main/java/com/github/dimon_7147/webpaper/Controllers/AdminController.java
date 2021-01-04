package com.github.dimon_7147.webpaper.Controllers;

import com.github.dimon_7147.webpaper.Objects.User;
import com.github.dimon_7147.webpaper.Objects.api.AdminInfo;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
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

    @GetMapping
    public String adminPage(Model model) {
        var countries = countryRepository.count();
        var planes = planeRepository.count();
        var airlines = airLineRepository.count();
        var bookedTickets = bookedTicketRepository.count();
        var luggage = luggageRepository.count();
        var roles = roleRepository.count();
        var users = userRepository.count();
        var tickets = ticketRepository.count();
        var airports = airPortRepository.count();
        model.addAttribute("info", new AdminInfo(countries, planes, airlines, bookedTickets, luggage, roles, users, tickets, airports));
        return "admin/index";
    }
    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
    @GetMapping("/airlines")
    public String airlinesPage(Model model) {
        model.addAttribute("airlines", airLineRepository.findAll());
        return "admin/airlines";
    }
    @GetMapping("/roles")
    public String rolesPage(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/roles";
    }
    @GetMapping("/countries")
    public String countriesPage(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        return "admin/countries";
    }
    @GetMapping("/airports")
    public String airportsPage(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("airports", airPortRepository.findAll());
        return "admin/airports";
    }
    @GetMapping("/bookedtickets")
    public String bookedTicketsPage(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("bookedtickets", bookedTicketRepository.findAll());
        return "admin/bookedtickets";
    }
    @GetMapping("/luggage")
    public String luggagePage(Model model) {
        model.addAttribute("luggage", luggageRepository.findAll());
        return "admin/luggage";
    }
    @GetMapping("/planes")
    public String planesPage(Model model) {
        model.addAttribute("planes", planeRepository.findAll());
        return "admin/planes";
    }
    @GetMapping("/tickets")
    public String ticketsPage(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        model.addAttribute("airports", airPortRepository.findAll());
        model.addAttribute("airlines", airLineRepository.findAll());
        model.addAttribute("planes", planeRepository.findAll());
        model.addAttribute("luggage", luggageRepository.findAll());
        return "admin/tickets";
    }
}
