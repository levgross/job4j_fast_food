package ru.job4j.order.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.order.repository.OrderRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimlpeOrderService implements OrderService {
    private final OrderRepository repository;
    private KafkaTemplate<Integer, Order> template;

    @Override
    public Order createOrder(Order order) {
        Order savedOrder = repository.save(order);
        template.send("new-orders", savedOrder);
        template.send("preorder", savedOrder);
        return savedOrder;
    }

    @Override
    public boolean edit(Order order) {
        if (!repository.existsById(order.getId())) {
            return false;
        }
        repository.save(order);
        return true;
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
