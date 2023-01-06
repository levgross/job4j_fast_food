package ru.job4j.order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.model.Card;

public interface CardRepository extends CrudRepository<Card, Long> {
}
