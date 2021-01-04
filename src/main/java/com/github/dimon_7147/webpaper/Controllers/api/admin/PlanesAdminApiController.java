package com.github.dimon_7147.webpaper.Controllers.api.admin;

import com.github.dimon_7147.webpaper.Controllers.ControllerUtils;
import com.github.dimon_7147.webpaper.Objects.Plane;
import com.github.dimon_7147.webpaper.Objects.api.ErrorInfo;
import com.github.dimon_7147.webpaper.repos.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/planes")
public class PlanesAdminApiController {
    @Autowired
    private PlaneRepository planeRepository;

    @PutMapping()
    public @ResponseBody
    Object addPlane(HttpServletResponse response, @RequestBody Plane plane) {
        if (plane.getName() == null) {
            response.setStatus(400);
            return new ErrorInfo(400, "Не указано название самолета");
        }
        var plane1 = new Plane();
        plane1.setName(plane.getName());
        var k = planeRepository.save(plane1);
        response.setStatus(200);
        return new ErrorInfo(200, "Самолет успешно добавлен с id " + k.getId());
    }

    @DeleteMapping("{id}")
    public @ResponseBody Object removePlane(HttpServletResponse response, @PathVariable Long id) {
        var plane = planeRepository.findById(id);
        if (plane.isPresent()) {
            planeRepository.delete(plane.get());
            response.setStatus(200);
            return new ErrorInfo(200, "Самолет успешно удален");
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Даннаый самолет не найден");
        }
    }
    @GetMapping()
    public @ResponseBody
    List<Plane> getPlanes() {
        return planeRepository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Object getPlane(HttpServletResponse response, @PathVariable Long id) {
        var plane = planeRepository.findById(id);
        if (plane.isPresent()) {
            return plane.get();
        } else {
            response.setStatus(404);
            return new ErrorInfo(404, "Даннаый самолет не найден");
        }
    }
    @PostMapping("{id}")
    public Object savePlane(HttpServletResponse response, @RequestBody @Valid Plane plane, BindingResult bindingResult) {
        if (plane == null) {
            System.out.println("400");
            response.setStatus(400);
            return new ErrorInfo(400, "Неверно сформированный запрос");
        } else {
            System.out.println(plane.toString());
        }
        if (!bindingResult.hasErrors()) {
            var correct = planeRepository.findById(plane.getId()).get();
            correct.setName(plane.getName());
            planeRepository.save(correct);
            response.setStatus(200);
            return new ErrorInfo(200, "Самолет успешно обновлен");
        } else {
            return ControllerUtils.getErrors(bindingResult);
        }
    }

}
