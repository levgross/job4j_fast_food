package ru.job4j.kitchen.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Notification;
import ru.job4j.domain.model.Order;
import ru.job4j.domain.model.OrderStatus;
import ru.job4j.kitchen.repository.NotificationRepository;

import static org.apache.kafka.common.utils.Utils.sleep;

@Service
@AllArgsConstructor
public class SimpleNotificationService implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final KafkaTemplate<Integer, Order> template;
    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification createOrderNotification(Order order) {
        Notification notification = new Notification();
        notification.setOrder(order);
        notification.setText(String.format(
                "Заказ №%d на сумму %d руб. Статус - %s.",
                order.getId(),
                order.getSum(),
                order.getOrderStatus())
        );
        return notification;
    }

    @Override
    public void preorder(Order order) {
        Notification notification = createOrderNotification(order);
        addNotification(notification);
        sleep(60_000);
        order.setOrderStatus(OrderStatus.COOKED);
        if (order.getOrderStatus() != OrderStatus.COOKED) {
            order.setOrderStatus(OrderStatus.CANCELED);
        }
        template.send("cooked_order", order);
    }
}
