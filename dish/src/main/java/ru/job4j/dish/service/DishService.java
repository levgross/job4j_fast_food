package ru.job4j.dish.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Dish;

import java.util.List;
import java.util.Optional;

@Service
public interface DishService {
    Optional<Dish> addDish(Dish dish);
    Optional<Dish> editDish(Dish dish);
    boolean deleteDish(Dish dish);
    Optional<Dish> findDishById(int id);
    List<Dish> findAllDishes();
}
