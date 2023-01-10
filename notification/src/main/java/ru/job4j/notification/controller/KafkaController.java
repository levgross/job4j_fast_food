package ru.job4j.notification.controller;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.domain.model.Notification;
import ru.job4j.domain.model.Order;
import ru.job4j.notification.service.SimpleNotificationService;

@Component
@AllArgsConstructor
public class KafkaController {
    private final SimpleNotificationService service;

    @KafkaListener(topics = {"new-orders"})
    public void onApiCallCreateOrder(ConsumerRecord<Long, Order> input) {
        Order order = input.value();
        Notification notification = service.createOrderNotification(order);
        service.addNotification(notification);
    }
}
