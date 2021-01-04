package com.github.dimon_7147.webpaper.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "short_name", unique = true)
    private String shortName;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Airport(Long id, Country country, String shortName, String name, String address) {
        this.id = id;
        this.country = country;
        this.shortName = shortName;
        this.name = name;
        this.address = address;
    }

    public Airport() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
