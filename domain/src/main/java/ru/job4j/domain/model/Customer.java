package ru.job4j.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Customer {
    @EqualsAndHashCode.Include
    private long id;
    private String username;
    private String password;
    private String phone;
    private String email;
}
