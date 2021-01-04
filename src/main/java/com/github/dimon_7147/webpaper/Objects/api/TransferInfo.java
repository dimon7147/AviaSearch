package com.github.dimon_7147.webpaper.Objects.api;

import com.github.dimon_7147.webpaper.Objects.Airport;
import com.github.dimon_7147.webpaper.Objects.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransferInfo {
    private ArrayList<Airport> path = new ArrayList<>();
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public int getAmount() {
        return path.size();
    }

    public TransferInfo() {
    }

    public TransferInfo(ArrayList<Airport> path, ArrayList<Ticket> tickets) {
        this.path = path;
        this.tickets = tickets;
    }

    public ArrayList<Airport> getPath() {
        return path;
    }

    public void setPath(ArrayList<Airport> path) {
        this.path = path;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
