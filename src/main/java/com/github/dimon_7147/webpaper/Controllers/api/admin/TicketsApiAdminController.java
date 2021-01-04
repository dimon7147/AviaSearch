package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Airport;
import com.github.dimon_7147.webpaper.Objects.Ticket;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.RoleRepository;
import com.github.dimon_7147.webpaper.repos.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/tickets")
public class TicketsApiAdminController {
    @Autowired
    private TicketRepository ticketRepository;

    @PutMapping()
    public @ResponseBody
    Object addTicket(HttpServletResponse response, @RequestBody Ticket ticket) {
        var ticket1 = new Ticket();
        ticket1.setArrivalTime(ticket.getArrivalTime());
        ticket1.setDepartureTime(ticket.getDepartureTime());
        ticket1.setPrice(ticket.getPrice());
        ticket1.setTicketsAmount(ticket.getTicketsAmount());
        ticket1.setAirline(ticket.getAirline());
        ticket1.setDepartureAirport(ticket.getDepartureAirport());
        ticket1.setDestinationAirport(ticket.getDestinationAirport());
        ticket1.setLuggage(ticket.getLuggage());
        ticket1.setPlane(ticket.getPlane());
        var k = ticketRepository.save(ticket);
        response.setStatus(200);
        return new ErrorInfo(200, "Билет успешно добавлен с id " + k.getId());
    }

    @DeleteMapping("{id}")
    public @ResponseBody Object removeTicket(HttpServletResponse response, @PathVariable Long id) {
        var ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticketRepository.delete(ticket.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Билет успешно удален");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный билет не найден");
        }
    }
    @GetMapping()
    public @ResponseBody
    List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Object getTicket(HttpServletResponse response, @PathVariable Long id) {
        var airport = ticketRepository.findById(id);
        if (airport.isPresent()) {
            return airport.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный билет не найден");
        }
    }
    @PostMapping("{id}")
    public Object saveTicket(HttpServletResponse response, @RequestBody @Valid Ticket ticket, BindingResult bindingResult) {
        if (ticket == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(ticket.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = ticketRepository.findById(ticket.getId()).get();
            correct.setArrivalTime(ticket.getArrivalTime());
            correct.setDepartureTime(ticket.getDepartureTime());
            correct.setPrice(ticket.getPrice());
            correct.setTicketsAmount(ticket.getTicketsAmount());
            correct.setAirline(ticket.getAirline());
            correct.setDepartureAirport(ticket.getDepartureAirport());
            correct.setDestinationAirport(ticket.getDestinationAirport());
            correct.setLuggage(ticket.getLuggage());
            correct.setPlane(ticket.getPlane());
            ticketRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Билет успешно обновлен");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

}
