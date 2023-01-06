create table order_dishes (
    id serial primary key not null,
    order_id int not null references order(id),
    dish_id int not null references dish(id)
);