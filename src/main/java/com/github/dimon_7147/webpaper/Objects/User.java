package com.github.dimon_7147.webpaper.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "Введите логин")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotBlank(message = "Введите пароль!")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    @Email(message = "Введите корректный email")
    private String email;

    @NotBlank(message = "Введите ФИО!")
    @Column(name = "fullname")
    private String fullName;

    @Length(min = 10, max = 10, message = "Номер паспорта должен состоять из 10 цифр!")
    @Column(name = "passport_number", unique = true)
    private String passport;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<BookedTicket> bookedTickets;

    public Boolean isActive() {
        return active;
    }

    public boolean isAdmin() {
        for (var role : roles){
            if (role.getRole().equals("ADMIN")){
                return true;
            }
        }
        return false;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setBookedTickets(List<BookedTicket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public List<BookedTicket> getBookedTickets() {
        return bookedTickets;
    }

    public User() {

    }

    public User(Long id, String login, String password, String fullName, String passport, LocalDate dateOfBirth, LocalDateTime registerTime, Boolean active, List<Role> roles, List<BookedTicket> bookedTickets) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.passport = passport;
        this.dateOfBirth = dateOfBirth;
        this.registerTime = registerTime;
        this.active = active;
        this.roles = roles;
        this.bookedTickets = bookedTickets;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getActive() {
        return active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public String getPassport() {
        return passport;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", passport='" + passport + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", registerTime=" + registerTime +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
