package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Airport;
import com.github.dimon_7147.webpaper.Objects.BookedTicket;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.AirPortRepository;
import com.github.dimon_7147.webpaper.repos.BookedTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/bookedtickets")
public class BookedTicketsApiAdminController {
    @Autowired
    private BookedTicketRepository bookedTicketRepository;

    @PutMapping()
    public @ResponseBody
    Object addBTicket(HttpServletResponse response, @RequestBody BookedTicket ticket) {
        var ticket1 = new BookedTicket();
        ticket1.setPrice(ticket.getPrice());
        ticket1.setTime(LocalDateTime.now());
        ticket1.setTicket(ticket.getTicket());
        ticket1.setUser(ticket.getUser());
        var k = bookedTicketRepository.save(ticket1);
        response.setStatus(200);
        return new ErrorInfo(200, "Забронированный билет успешно добавлен с id " + k.getId());
    }

    @DeleteMapping("{id}")
    public @ResponseBody Object removeBTicket(HttpServletResponse response, @PathVariable Long id) {
        var ticket = bookedTicketRepository.findById(id);
        if (ticket.isPresent()) {
            bookedTicketRepository.delete(ticket.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Забронированный билет успешно удален");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный забронированный билет не найден");
        }
    }
    @GetMapping()
    public @ResponseBody
    List<BookedTicket> getBTickets() {
        return bookedTicketRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Object getBTicket(HttpServletResponse response, @PathVariable Long id) {
        var ticket = bookedTicketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный забронированный билет не найден");
        }
    }
    @PostMapping("{id}")
    public Object saveBTicket(HttpServletResponse response, @RequestBody @Valid BookedTicket ticket, BindingResult bindingResult) {
        if (ticket == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(ticket.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = bookedTicketRepository.findById(ticket.getId()).get();
            correct.setPrice(ticket.getPrice());
            correct.setTicket(ticket.getTicket());
            correct.setUser(ticket.getUser());
            bookedTicketRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Забронированный билет успешно обновлен");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

}
