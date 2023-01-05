package ru.job4j.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Dish {
    @EqualsAndHashCode.Include
    private long id;
    private String name;
    private String description;
    private byte[] photo;
    private BigDecimal price;
}
