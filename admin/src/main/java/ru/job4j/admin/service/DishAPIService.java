package ru.job4j.admin.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.model.Dish;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:application.properties")
@AllArgsConstructor
public class DishAPIService {

    @Value("${dish-api-url}")
    private String url;

    private final RestTemplate client;

    public Dish addDish(Dish dish) {
        return client.postForEntity(
                url, dish, Dish.class
        ).getBody();
    }

    public boolean editDish(Dish dish) {
        return client.exchange(
                String.format("%s?id=%d", url, dish.getId()),
                HttpMethod.PUT,
                new HttpEntity<>(dish),
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean deleteDish(long id) {
        return client.exchange(
                String.format("%s?id=%d", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public Optional<Dish> findById(long id) {
        return Optional.ofNullable(client.getForEntity(
                String.format("%s/getById?id=%d", url, id),
                Dish.class
        ).getBody());
    }

    public List<Dish> findAll() {
        return getList(String.format(
                "%s/getAll", url
        ));
    }

    private List<Dish> getList(String url) {
        List<Dish> body = client.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Dish>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }
}
