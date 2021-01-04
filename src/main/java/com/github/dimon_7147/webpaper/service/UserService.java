package com.github.dimon_7147.webpaper.service;

import com.github.dimon_7147.webpaper.Objects.Role;
import com.github.dimon_7147.webpaper.Objects.User;
import com.github.dimon_7147.webpaper.repos.RoleRepository;
import com.github.dimon_7147.webpaper.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByLogin(s);
    }

    public boolean addUser(User user) {
        User userFromDB = userRepository.findByLogin(user.getLogin());

        if(userFromDB != null) {
            return false;
        }

        user.setActive(true);
        user.setRegisterTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var role = roleRepository.findByRole("USER");
        if (role == null) {
            role = new Role("USER");
            roleRepository.save(role);
        }
        var list = new ArrayList<Role>();
        list.add(role);
        user.setRoles(list);
        userRepository.save(user);
        return true;
    }
}
