package ru.job4j.dish.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.dish.repository.DishRepository;
import ru.job4j.domain.model.Dish;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleDishService implements DishService {
    private final DishRepository dishRepository;

    @Override
    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    @Transactional
    public boolean editDish(Dish dish) {
        if (!dishRepository.existsById(dish.getId())) {
            return false;
        }
        dishRepository.save(dish);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteDish(long id) {
        if (!dishRepository.existsById(id)) {
            return false;
        }
        dishRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Dish> findDishById(long id) {
        return dishRepository.findById(id);
    }

    @Override
    public List<Dish> findAllDishes() {
        return (List) dishRepository.findAll();
    }
}
