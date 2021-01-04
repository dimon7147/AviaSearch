package com.github.dimon_7147.webpaper.repos;

import com.github.dimon_7147.webpaper.Objects.Airline;
import com.github.dimon_7147.webpaper.Objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirLineRepository extends JpaRepository<Airline, Long> {
    Airline findByName(String name);
    Optional<Airline> findById(Long id);
}
