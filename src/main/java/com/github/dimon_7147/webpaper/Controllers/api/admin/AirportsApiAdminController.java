package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Airline;
import com.github.dimon_7147.webpaper.Objects.Airport;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.AirLineRepository;
import com.github.dimon_7147.webpaper.repos.AirPortRepository;
import com.github.dimon_7147.webpaper.repos.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/airports")
public class AirportsApiAdminController {
    @Autowired
    private AirPortRepository airPortRepository;

    @PutMapping()
    public @ResponseBody
    Object addAirport(HttpServletResponse response, @RequestBody Airport airport) {
        var airport1 = new Airport();
        airport1.setName(airport.getName());
        airport1.setAddress(airport.getAddress());
        airport1.setCountry(airport.getCountry());
        airport1.setShortName(airport.getShortName());
        var k = airPortRepository.save(airport1);
        response.setStatus(200);
        return new ErrorInfo(200, "Аэропорт успешно добавлен с id " + k.getId());
    }

    @DeleteMapping("{id}")
    public @ResponseBody Object removeAirport(HttpServletResponse response, @PathVariable Long id) {
        var airport = airPortRepository.findById(id);
        if (airport.isPresent()) {
            airPortRepository.delete(airport.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Аэропорт успешно удален");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный аэропорт не найден");
        }
    }
    @GetMapping()
    public @ResponseBody
    List<Airport> getAirports() {
        return airPortRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Object getAirport(HttpServletResponse response, @PathVariable Long id) {
        var airport = airPortRepository.findById(id);
        if (airport.isPresent()) {
            return airport.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данный аэропорт не найден");
        }
    }
    @PostMapping("{id}")
    public Object saveAirport(HttpServletResponse response, @RequestBody @Valid Airport airport, BindingResult bindingResult) {
        if (airport == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(airport.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = airPortRepository.findById(airport.getId()).get();
            correct.setName(airport.getName());
            correct.setName(airport.getName());
            correct.setAddress(airport.getAddress());
            correct.setCountry(airport.getCountry());
            correct.setShortName(airport.getShortName());
            airPortRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Аэропорт успешно обновлен");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

}
