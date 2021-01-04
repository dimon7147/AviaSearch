package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Airline;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.AirLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/airlines")
public class AirlinesApiController {
    @Autowired
    private AirLineRepository airLineRepository;

    @PutMapping()
    public @ResponseBody
    Object addAirline(HttpServletResponse response, @RequestBody Airline airline) {
        var airline1 = new Airline();
        airline1.setName(airline.getName());
        var k = airLineRepository.save(airline1);
        response.setStatus(200);
        return new ErrorInfo(200, "Авиакомпания успешно добавлена с id " + k.getId());
    }

    @DeleteMapping("{id}")
    public @ResponseBody Object removeAirline(HttpServletResponse response, @PathVariable Long id) {
        var airline = airLineRepository.findById(id);
        if (airline.isPresent()) {
            airLineRepository.delete(airline.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Авиакомпания успешно удалена");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данная авиакомпания не найдена");
        }
    }
    @GetMapping()
    public @ResponseBody
    List<Airline> getAirlines() {
        return airLineRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Object getAirline(HttpServletResponse response, @PathVariable Long id) {
        var airline = airLineRepository.findById(id);
        if (airline.isPresent()) {
            return airline.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данная авиакомпания не найдена");
        }
    }
    @PostMapping("{id}")
    public Object saveAirLine(HttpServletResponse response, @RequestBody @Valid Airline airline, BindingResult bindingResult) {
        if (airline == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(airline.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = airLineRepository.findById(airline.getId()).get();
            correct.setName(airline.getName());
            airLineRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Авиакомпания успешно обновлена");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

}
