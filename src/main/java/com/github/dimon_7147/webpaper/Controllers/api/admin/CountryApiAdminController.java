package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Airline;
import com.github.dimon_7147.webpaper.Objects.Country;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.AirLineRepository;
import com.github.dimon_7147.webpaper.repos.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/countries")
public class CountryApiAdminController {
    @Autowired
    private CountryRepository countryRepository;

    @PutMapping()
    public @ResponseBody Object addCountry(HttpServletResponse response, @RequestBody Country country) {
        if (country.getName() == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Не указано название страны");
        }
        var country1 = new Country();
        country1.setName(country.getName());
        var k = countryRepository.save(country1);
        response.setStatus(200);
        return new ErrorInfo(200, "Страна успешно добавлена с id " + k.getId());
    }

    @DeleteMapping("{id}")
    public @ResponseBody Object removeCountry(HttpServletResponse response, @PathVariable Long id) {
        var country = countryRepository.findById(id);
        if (country.isPresent()) {
            countryRepository.delete(country.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Страна успешно удалена");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данная страна не найдена");
        }
    }
    @GetMapping()
    public @ResponseBody
    List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Object getCountry(HttpServletResponse response, @PathVariable Long id) {
        var country = countryRepository.findById(id);
        if (country.isPresent()) {
            return country.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Данная страна не найдена");
        }
    }
    @PostMapping("{id}")
    public Object saveCountry(HttpServletResponse response, @RequestBody @Valid Country country, BindingResult bindingResult) {
        if (country == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(country.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = countryRepository.findById(country.getId()).get();
            correct.setName(country.getName());
            countryRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Страна успешно обновлена");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

}
