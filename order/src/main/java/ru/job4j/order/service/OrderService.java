package ru.job4j.order.service;

import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;

public interface OrderService {
    Order createOrder(Order order);
    OrderStatus checkStatus(long orderId);
}
