package com.github.dimon_7147.webpaper.repos;

import com.github.dimon_7147.webpaper.Objects.Role;
import com.github.dimon_7147.webpaper.Objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String name);
    Optional<Role> findById(Long id);
}
