package ru.job4j.notification.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Notification;
import ru.job4j.domain.model.Order;
import ru.job4j.notification.repository.NotificationRepository;

@Service
@AllArgsConstructor
public class SimpleNotificationService implements NotificationService {
    private final NotificationRepository notificationRepository;
    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification createOrderNotification(Order order) {
        Notification notification = new Notification();
        notification.setOrder(order);
        notification.setText(String.format(
                "Получен заказ №%d на сумму %d руб. Его статус %s.",
                order.getId(),
                order.getSum(),
                order.getOrderStatus())
        );
        return notification;
    }
}
