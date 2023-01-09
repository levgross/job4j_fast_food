package ru.job4j.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.model.Customer;
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
    public ResponseEntity<Void> buyCard(@RequestParam("cardId") long cardId,
                                  HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        boolean status = customer != null;
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @GetMapping("/status/{id}")
    public Status checkStatus(@PathVariable long id) {
        return orderService.checkStatus(id);
    }
}
