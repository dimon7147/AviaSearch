package com.github.dimon_7147.webpaper.Objects.api;

public class AdminInfo {
    private long countries;
    private long planes;
    private long airlines;
    private long bookedTickets;
    private long luggage;
    private long roles;
    private long users;
    private long tickets;
    private long airports;

    public AdminInfo(long countries, long planes, long airlines, long bookedTickets, long luggage, long roles, long users, long tickets, long airports) {
        this.countries = countries;
        this.planes = planes;
        this.airlines = airlines;
        this.bookedTickets = bookedTickets;
        this.luggage = luggage;
        this.roles = roles;
        this.users = users;
        this.tickets = tickets;
        this.airports = airports;
    }

    public long getCountries() {
        return countries;
    }

    public void setCountries(long countries) {
        this.countries = countries;
    }

    public long getPlanes() {
        return planes;
    }

    public void setPlanes(long planes) {
        this.planes = planes;
    }

    public long getAirlines() {
        return airlines;
    }

    public void setAirlines(long airlines) {
        this.airlines = airlines;
    }

    public long getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(long bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public long getLuggage() {
        return luggage;
    }

    public void setLuggage(long luggage) {
        this.luggage = luggage;
    }

    public long getRoles() {
        return roles;
    }

    public void setRoles(long roles) {
        this.roles = roles;
    }

    public long getUsers() {
        return users;
    }

    public void setUsers(long users) {
        this.users = users;
    }

    public long getTickets() {
        return tickets;
    }

    public void setTickets(long tickets) {
        this.tickets = tickets;
    }

    public long getAirports() {
        return airports;
    }

    public void setAirports(long airports) {
        this.airports = airports;
    }
}
