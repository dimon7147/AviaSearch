package com.github.dimon_7147.webpaper.Objects;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = false)
    private Plane plane;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destinationAirport;

    @ManyToOne
    @JoinColumn(name = "luggage_type_id", nullable = false)
    private Luggage luggage;

    @Column(name = "price")
    private Double price;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "tickets_total", nullable = false)
    private Integer ticketsAmount;

    public Ticket(Long id, Airline airline, Plane plane, Airport departureAirport, Airport destinationAirport, Luggage luggage, Double price, LocalDateTime departureTime, LocalDateTime arrivalTime, Integer ticketsAmount) {
        Id = id;
        this.airline = airline;
        this.plane = plane;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.luggage = luggage;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.ticketsAmount = ticketsAmount;
    }

    public Ticket() {
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setTicketsAmount(Integer ticketsAmount) {
        this.ticketsAmount = ticketsAmount;
    }

    public Long getId() {
        return Id;
    }

    public Airline getAirline() {
        return airline;
    }

    public Plane getPlane() {
        return plane;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public Luggage getLuggage() {
        return luggage;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Integer getTicketsAmount() {
        return ticketsAmount;
    }
}
