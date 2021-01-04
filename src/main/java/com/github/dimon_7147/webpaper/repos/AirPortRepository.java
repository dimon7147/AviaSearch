package com.github.dimon_7147.webpaper.repos;

import com.github.dimon_7147.webpaper.Objects.Airport;
import com.github.dimon_7147.webpaper.Objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirPortRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findById(Long id);
}
