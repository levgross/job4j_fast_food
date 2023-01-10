package ru.job4j.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.order.service.OrderService;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private KafkaTemplate<Integer, Order> template;

    @PostMapping("/")
    public Order createOrder() {
        Order savedOrder = orderService.createOrder(new Order());
        template.send("new-orders", savedOrder);
        return savedOrder;
    }

    @GetMapping("/status/{id}")
    public OrderStatus checkStatus(@PathVariable long id) {
        return orderService.checkStatus(id);
    }
}
