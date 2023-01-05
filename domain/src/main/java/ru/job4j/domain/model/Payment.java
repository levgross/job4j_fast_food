package ru.job4j.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Payment {
    @EqualsAndHashCode.Include
    private long id;
    private LocalDateTime created = LocalDateTime.now();
    private Order order;
}
