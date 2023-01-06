package ru.job4j.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    @Id
    @EqualsAndHashCode.Include
    private long id;
    private String number;
    private double discount;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
