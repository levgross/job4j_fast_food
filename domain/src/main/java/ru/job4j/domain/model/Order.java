package ru.job4j.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @EqualsAndHashCode.Include
    private long id;
    private LocalDateTime created = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String address;
    @OneToMany
    @JoinTable(name = "order_dishes",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "dish_id") })
    private List<Dish> dishes = new ArrayList<>();
    private int sum;
    @Enumerated(EnumType.STRING)
    private Status status;
}
