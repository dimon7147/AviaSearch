package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Luggage;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.LuggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/luggage")
public class LuggageAdminApiController {
    @Autowired
    private LuggageRepository luggageRepository;

    @PutMapping()
    public @ResponseBody
    Object addLuggage(HttpServletResponse response, @RequestBody Luggage luggage) {
        if (luggage.getName() == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Не указано название вида багажа");
        }
        var luggage1 = new Luggage();
        luggage1.setName(luggage.getName());
        var k = luggageRepository.save(luggage1);
        response.setStatus(200);
        return new ErrorInfo(200, "Новый вид багажа успешно добавлен с id " + k.getId());
    }

    @DeleteMapping("{id}")
    public @ResponseBody Object removeLuggage(HttpServletResponse response, @PathVariable Long id) {
        var luggage = luggageRepository.findById(id);
        if (luggage.isPresent()) {
            luggageRepository.delete(luggage.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Вид багажа успешно удален");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный вид багажа не найден");
        }
    }
    @GetMapping()
    public @ResponseBody
    List<Luggage> getLuggage() {
        return luggageRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Object getLuggageOne(HttpServletResponse response, @PathVariable Long id) {
        var luggage = luggageRepository.findById(id);
        if (luggage.isPresent()) {
            return luggage.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный вид багажа не найден");
        }
    }
    @PostMapping("{id}")
    public Object saveLuggage(HttpServletResponse response, @RequestBody @Valid Luggage luggage, BindingResult bindingResult) {
        if (luggage == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(luggage.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = luggageRepository.findById(luggage.getId()).get();
            correct.setName(luggage.getName());
            luggageRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Вид багажа успешно обновлен");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

}
