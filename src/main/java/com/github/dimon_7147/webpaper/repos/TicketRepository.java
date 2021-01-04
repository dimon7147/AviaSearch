package com.github.dimon_7147.webpaper.repos;

import com.github.dimon_7147.webpaper.Objects.Airport;
import com.github.dimon_7147.webpaper.Objects.Luggage;
import com.github.dimon_7147.webpaper.Objects.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByDepartureTimeBefore(LocalDateTime date);
    List<Ticket> findByDepartureAirportAndDepartureTimeAfter(Airport one,LocalDateTime date);
    List<Ticket> findByDepartureAirportAndDestinationAirportAndDepartureTimeAfter(Airport one, Airport two, LocalDateTime date);
    List<Ticket> findByDepartureAirportAndDestinationAirportAndLuggageAndDepartureTimeAfter(Airport one, Airport two, Luggage luggage, LocalDateTime date);
    List<Ticket> findByDepartureTimeBeforeAndTicketsAmountGreaterThanEqual(LocalDateTime date, int amount);
    List<Ticket> findByDepartureAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqual(Airport one,LocalDateTime date, int amount);
    List<Ticket> findByDepartureAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqualAndLuggage(Airport one,LocalDateTime date, int amount, Luggage luggage);
    List<Ticket> findByDepartureAirportAndDestinationAirportAndDepartureTimeAfterAndTicketsAmountGreaterThanEqual(Airport one, Airport two, LocalDateTime date, int amount);
    List<Ticket> findByDepartureAirportAndDestinationAirportAndLuggageAndDepartureTimeAfterAndTicketsAmountGreaterThanEqual(Airport one, Airport two, Luggage luggage, LocalDateTime date, int amount);
}
