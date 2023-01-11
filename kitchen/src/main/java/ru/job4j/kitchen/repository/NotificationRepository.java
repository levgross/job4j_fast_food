package ru.job4j.kitchen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
