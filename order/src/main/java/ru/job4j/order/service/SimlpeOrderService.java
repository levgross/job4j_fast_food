package ru.job4j.order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.order.repository.OrderRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimlpeOrderService implements OrderService {
    private final OrderRepository repository;
    @Override
    public Order createOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public Optional<Order> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public OrderStatus checkStatus(long orderId) {
        Optional<Order> orderOpt = findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException(String.format("Заказ с id=%d не зарегистрирован.", orderId));
        }
        return orderOpt.get().getOrderStatus();
    }
}
