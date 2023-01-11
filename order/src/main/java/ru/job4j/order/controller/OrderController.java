package ru.job4j.order.controller;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.model.Customer;
import ru.job4j.domain.model.Dish;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.order.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private KafkaTemplate<Integer, Order> template;

    /**
     * Пока поставил заглушку, чтобы можно было просто постманом проверить работу
     */
    @PostMapping("/")
    public Order createOrder() {
        Order order = new Order();
        Customer customer = new Customer();
        Dish dish = new Dish();
        dish.setId(1);
        customer.setId(1);
        order.setCustomer(customer);
        order.setDishes(List.of(dish));
        order.setSum(999);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setAddress("Address");
        Order savedOrder = orderService.createOrder(order);
        template.send("new-orders", savedOrder);
        template.send("preorder", savedOrder);
        return savedOrder;
    }

    @GetMapping("/status/{id}")
    public OrderStatus checkStatus(@PathVariable long id) {
        return orderService.checkStatus(id);
    }

    @KafkaListener(topics = {"cooked_order"})
    public void onApiCallCreateOrder(ConsumerRecord<Long, Order> input) {
        Order order = input.value();
        Optional<Order> orderOpt = orderService.findById(order.getId());
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException(String.format("Заказ с id=%d не зарегистрирован.", order.getId()));
        }
        orderService.edit(order);
    }
}
