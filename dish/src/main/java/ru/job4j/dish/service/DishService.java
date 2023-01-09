package ru.job4j.dish.service;

import ru.job4j.domain.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {
    Dish addDish(Dish dish);
    boolean editDish(Dish dish);
    boolean deleteDish(long id);
    Optional<Dish> findDishById(long id);
    List<Dish> findAllDishes();
}
