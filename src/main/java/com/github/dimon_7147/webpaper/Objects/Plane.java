package com.github.dimon_7147.webpaper.Objects;

import javax.persistence.*;

@Entity
@Table(name = "Planes")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Plane(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plane() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
