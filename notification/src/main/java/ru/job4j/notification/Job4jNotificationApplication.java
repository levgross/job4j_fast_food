package ru.job4j.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"ru.job4j.domain.model"})
@ComponentScan(basePackages = {"ru.job4j.domain.model", "ru.job4j.notification"})
public class Job4jNotificationApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Job4jNotificationApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Job4jNotificationApplication.class, args);
    }
}
