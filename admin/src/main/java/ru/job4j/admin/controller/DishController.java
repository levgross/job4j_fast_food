package ru.job4j.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.admin.service.DishAPIService;
import ru.job4j.domain.model.Dish;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class DishController {
    private final DishAPIService service;

    @GetMapping("dishes")
    public String dishes(Model model) {
        model.addAttribute("dishes", service.findAll());
        return "dishes";
    }

    @GetMapping("formAddDish")
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish(
                0, "Заполните название", "Заполните описание", null, 0
        ));
        return "addDish";
    }

    @PostMapping("createDish")
    public String createDish(@ModelAttribute Dish dish,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        dish.setPhoto(file.getBytes());
        service.addDish(dish);
        return "redirect:/dishes";
    }

    @GetMapping("formUpdateDish/{id}")
    public String formUpdateDish(Model model, @PathVariable("id") int id) {
        model.addAttribute("dish", service.findById(id));
        return "updateDish";
    }

    @PostMapping("updateDish")
    public String updateDish(@ModelAttribute Dish dish,
                                  @RequestParam("file")MultipartFile file) throws IOException {
        dish.setPhoto(file.getBytes());
        service.editDish(dish);
        return "redirect:/dishes";
    }

    @GetMapping("photoDish/{dishId}")
    public ResponseEntity<Resource> download(@PathVariable("dishId") Long dishId) {
        Optional<Dish> dishOpt = service.findById(dishId);
        if (dishOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        if (dishOpt.get().getPhoto() == null) {
            return ResponseEntity.ok()
                    .headers(new HttpHeaders())
                    .contentLength(1)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new ByteArrayResource(new byte[1]));
        } else {
            return ResponseEntity.ok()
                    .headers(new HttpHeaders())
                    .contentLength(dishOpt.get().getPhoto().length)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new ByteArrayResource(dishOpt.get().getPhoto()));
        }
    }
}
