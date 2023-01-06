package ru.job4j.order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
