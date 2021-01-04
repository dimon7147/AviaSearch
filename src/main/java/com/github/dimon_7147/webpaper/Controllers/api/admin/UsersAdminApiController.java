package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Airline;
import com.github.dimon_7147.webpaper.Objects.Role;
import com.github.dimon_7147.webpaper.Objects.User;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.RoleRepository;
import com.github.dimon_7147.webpaper.repos.UserRepository;
import com.github.dimon_7147.webpaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UsersAdminApiController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @GetMapping()
    public @ResponseBody
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping()
    public @ResponseBody
    Object addUser(HttpServletResponse response, @RequestBody User user) {
        var user1 = new User();
        user1.setRegisterTime(LocalDateTime.now());
        user1.setPassport(user.getPassport());
        user1.setActive(user.getActive());
        user1.setEmail(user.getEmail());
        user1.setDateOfBirth(user.getDateOfBirth());
        user1.setLogin(user.getLogin());
        user1.setFullName(user.getFullName());
        List<Role> role1s = new ArrayList<>();
        for (var role : user.getRoles()) {
            var trueRole = roleRepository.getOne(role.getId());
            role1s.add(trueRole);
        }
        user1.setRoles(role1s);
        user1.setPassword(user.getPassword());
        var k = userService.addUser(user1);
        response.setStatus(200);
        return new ErrorInfo(200, "Пользователь успешно добавлен");
    }
    @GetMapping("{id}")
    public @ResponseBody Object getUser(HttpServletResponse response, @PathVariable Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный пользователь не найден");
        }
    }
    @PostMapping("{id}")
    public Object saveUser(HttpServletResponse response, @RequestBody @Valid User user, BindingResult bindingResult) {
        if (user == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(user.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correctUser = userRepository.findById(user.getId()).get();
            correctUser.setActive(user.getActive());
            correctUser.setLogin(user.getLogin());
            correctUser.setEmail(user.getEmail());
            correctUser.setFullName(user.getFullName());
            correctUser.setPassport(user.getPassport());
            correctUser.setDateOfBirth(user.getDateOfBirth());
            List<Role> role1s = new ArrayList<>();
            for (var role : user.getRoles()) {
                var trueRole = roleRepository.getOne(role.getId());
                role1s.add(trueRole);
            }
            correctUser.setRoles(role1s);
            userRepository.save(correctUser);
            response.setStatus(200);
            return new ErrorInfo(200, "Пользователь успешно обновлен");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }
    @DeleteMapping("{id}")
    public @ResponseBody Object removeUser(HttpServletResponse response, @PathVariable Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Пользователь успешно удален");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный пользователь не найден");
        }
    }

}
