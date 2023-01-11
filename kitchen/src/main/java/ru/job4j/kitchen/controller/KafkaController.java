package ru.job4j.kitchen.controller;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.domain.model.Notification;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.kitchen.service.SimpleNotificationService;

import static org.apache.kafka.common.utils.Utils.sleep;

@Component
@AllArgsConstructor
public class KafkaController {
    private final SimpleNotificationService service;
    private final KafkaTemplate<Integer, Order> template;

    @KafkaListener(topics = {"preorder"})
    public void onApiCallCreateOrder(ConsumerRecord<Long, Order> input) {
        Order order = input.value();
        Notification notification = service.createOrderNotification(order);
        service.addNotification(notification);
        sleep(60_000);
        order.setOrderStatus(OrderStatus.COOKED);
        if (order.getOrderStatus() != OrderStatus.COOKED) {
            order.setOrderStatus(OrderStatus.CANCELED);
        }
        template.send("cooked_order", order);
    }
}
