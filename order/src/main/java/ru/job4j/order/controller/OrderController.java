package ru.job4j.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.Status;
import ru.job4j.order.service.OrderService;

import javax.servlet.http.HttpSession;

@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PostMapping("/card")
    public void buyCard(@RequestParam("cardId") long cardId,
                        HttpSession httpSession) {
    }

    @GetMapping("/status/{id}")
    public Status checkStatus(@PathVariable long id) {
        return orderService.checkStatus(id);
    }
}
