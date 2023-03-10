package ru.job4j.notification.service;

import ru.job4j.domain.model.Notification;
import ru.job4j.domain.model.Order;

public interface NotificationService {
    Notification addNotification(Notification notification);
    Notification createOrderNotification(Order order);
}
