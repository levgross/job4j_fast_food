package ru.job4j.dish.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.model.Dish;

public interface DishRepository extends CrudRepository<Dish, Long> {
}