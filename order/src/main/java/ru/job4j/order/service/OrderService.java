package ru.job4j.order.service;

import ru.job4j.domain.model.Card;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.Status;

public interface OrderService {
    Order createOrder(Order order);
    void buyCard(Card card);
    Status checkStatus(long orderId);
}
