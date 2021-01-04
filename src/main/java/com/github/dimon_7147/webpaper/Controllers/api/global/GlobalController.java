package com.github.dimon_7147.webpaper.Controllers.api.global;

import com.github.dimon_7147.webpaper.Objects.Airport;
import com.github.dimon_7147.webpaper.Objects.BookedTicket;
import com.github.dimon_7147.webpaper.Objects.Ticket;
import com.github.dimon_7147.webpaper.Objects.User;
import com.github.dimon_7147.webpaper.Objects.api.AdminInfo;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.Objects.api.TransferInfo;
import com.github.dimon_7147.webpaper.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class GlobalController {
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

    @GetMapping("tickets/buy") public @ResponseBody
    Object buyTicket(HttpServletResponse response, @RequestParam long id, @AuthenticationPrincipal User user) {
        if (user == null || user.getId() == null) {
            response.setStatus(400);
            return new ErrorInfo(400,"Не удалось идентифицировать пользователя");
        }
        if (ticketRepository.getOne(id).getTicketsAmount() < 1) {
            response.setStatus(400);
            return new ErrorInfo(400,"Билеты закончились!");
        }
        var ticket = new BookedTicket();
        ticket.setTicket(ticketRepository.getOne(id));
        ticket.setUser(user);
        ticket.setTime(LocalDateTime.now());
        ticket.setPrice(ticketRepository.getOne(id).getPrice());
        bookedTicketRepository.save(ticket);
        ticketRepository.getOne(id).setTicketsAmount(ticketRepository.getOne(id).getTicketsAmount()-1);
        return new ErrorInfo(200,"Билет успешно куплен");
    }

    @GetMapping("tickets/buys") public @ResponseBody
    Object buyTicket(HttpServletResponse response, @RequestParam long[] ids, @AuthenticationPrincipal User user) {
        if (user == null || user.getId() == null) {
            response.setStatus(400);
            return new ErrorInfo(400,"Не удалось идентифицировать пользователя");
        }
        for (var id : ids) {
            if (ticketRepository.getOne(id).getTicketsAmount() < 1) {
                response.setStatus(400);
                return new ErrorInfo(400,"Билеты закончились!");
            }
        }
        for (var id : ids) {
            var ticket = new BookedTicket();
            ticket.setTicket(ticketRepository.getOne(id));
            ticket.setUser(user);
            ticket.setTime(LocalDateTime.now());
            ticket.setPrice(ticketRepository.getOne(id).getPrice());
            bookedTicketRepository.save(ticket);
            ticketRepository.getOne(id).setTicketsAmount(ticketRepository.getOne(id).getTicketsAmount()-1);
        }
        return new ErrorInfo(200,"Билеты успешно куплены");
    }


    @GetMapping("tickets") public @ResponseBody
    Object getTickets(
            @RequestParam long depart_airport,
            @RequestParam long dest_airport,
            @RequestParam(required = false, defaultValue = "-1") long luggage_type,
            @RequestParam(required = false, defaultValue = "false") boolean transfer_allowed,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        var today = LocalDate.now();
        if (today.isAfter(date)) {
            return new ErrorInfo(400,"Поиск возможен только на текущую или будущую дату");
        }
        if (!transfer_allowed) {
            var one = airPortRepository.findById(depart_airport).get();
            var two = airPortRepository.findById(dest_airport).get();
            if (luggage_type == -1) {
                return ticketRepository.findByDepartureAirportAndDestinationAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqual
                        (one,two,date.atStartOfDay(), 1);
            } else {
                var luggage = luggageRepository.findById(luggage_type).get();
                return ticketRepository.
                        findByDepartureAirportAndDestinationAirportAndLuggageAndDepartureTimeAfterAndTicketsAmountGreaterThanEqual
                                (one,two,luggage,date.atStartOfDay(), 1);
            }
        } else {
            List<TransferInfo> result = new ArrayList<TransferInfo>();
            var one = airPortRepository.findById(depart_airport).get();
            var two = airPortRepository.findById(dest_airport).get();
            System.out.println("Нужно найти: " + one.getName() + " -> " + two.getName());
            var from_list = ticketRepository.
                    findByDepartureAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqual
                            (one, date.atStartOfDay(), 1);
            if (luggage_type != -1) {
                from_list = ticketRepository.
                        findByDepartureAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqualAndLuggage
                                (one, date.atStartOfDay(), 1, luggageRepository.getOne(luggage_type));
            }
            int i = 0;
            for (var el : from_list) {
                var airports = new ArrayList<Airport>();
                var tickets = new ArrayList<Ticket>();
                airports.add(one);
                System.out.println("------------------------------");
                System.out.println(el.getDepartureAirport().getName() + " -> " + el.getDestinationAirport().getName());
                if (el.getDestinationAirport().getId().equals(two.getId())){
                    System.out.println("Найден прямой билет");
                    airports.add(el.getDestinationAirport());
                    tickets.add(el);
                    var tInfo = new TransferInfo(airports, tickets);
                    result.add(tInfo);
                    continue;
                }
                tickets.add(el);
                var airport = el.getDestinationAirport();
                var newList = ticketRepository.findByDepartureAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqual
                        (airport, date.atStartOfDay(), 1);
                if (luggage_type != -1) {
                    newList = ticketRepository.findByDepartureAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqualAndLuggage
                            (airport, date.atStartOfDay(), 1, luggageRepository.getOne(luggage_type));
                }
                    while (true) {
                    if (newList.isEmpty()) {
                        break;
                    }
                    newList = ticketRepository.findByDepartureAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqual
                            (airport, date.atStartOfDay(), 1);
                        if (luggage_type != -1) {
                            newList = ticketRepository.findByDepartureAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqualAndLuggage
                                    (airport, date.atStartOfDay(), 1, luggageRepository.getOne(luggage_type));
                        }
                    for (var elem : newList) {
                        airports.add(elem.getDepartureAirport());
                        tickets.add(elem);
                        System.out.println(elem.getDepartureAirport().getName() + " -> " + elem.getDestinationAirport().getName());
                        if (elem.getDestinationAirport().getId().equals(two.getId())){
                            System.out.println("Найдено!!!");
                            airports.add(elem.getDestinationAirport());
                            var tInfo = new TransferInfo(airports, tickets);
                            result.add(tInfo);
                            newList.remove(elem);
                            break;
                        } else {
                            //System.out.println(airport.getName() + " -> " + el.getDestinationAirport().getName());
                            airport = elem.getDestinationAirport();
                        }
                    }
                }
                System.out.println("------------------------------");
            }
            return result;
        }
    }
}
