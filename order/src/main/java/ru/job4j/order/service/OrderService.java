package ru.job4j.order.service;

import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;

import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    Optional<Order> findById(long id);
    OrderStatus checkStatus(long orderId);
}
