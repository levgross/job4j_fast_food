package ru.job4j.dish.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dish.service.DishService;
import ru.job4j.domain.model.Dish;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dish")
@AllArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping
    public Dish add(@RequestBody Dish dish) {
        return dishService.addDish(dish);
    }

    @PutMapping
    public ResponseEntity<Void> edit(@RequestBody Dish dish) {
        boolean status = dishService.editDish(dish);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam long id) {
        boolean status = dishService.deleteDish(id);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @GetMapping("/getById")
    public ResponseEntity<Dish> getById(@RequestParam long id) {
        Optional<Dish> dishOpt = dishService.findDishById(id);
        if (dishOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .ok(dishOpt.get());
    }

    @GetMapping("/getAll")
    public List<Dish> getAll() {
        return dishService.findAllDishes();
    }
}
