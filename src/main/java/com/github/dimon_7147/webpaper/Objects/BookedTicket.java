package com.github.dimon_7147.webpaper.Objects;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Booked_tickets")
public class BookedTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ticket_id",nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "price")
    private Double price;

    public BookedTicket(Long id, Ticket ticket, User user, LocalDateTime time, Double price) {
        this.id = id;
        this.ticket = ticket;
        this.user = user;
        this.time = time;
        this.price = price;
    }

    public BookedTicket() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Double getPrice() {
        return price;
    }
}
