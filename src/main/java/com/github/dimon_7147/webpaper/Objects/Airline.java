package com.github.dimon_7147.webpaper.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Airlines")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Airline(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Airline() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
