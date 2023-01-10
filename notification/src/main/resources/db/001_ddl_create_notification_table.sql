create table notification (
    id serial primary key not null,
    created timestamp not null,
    text varchar not null,
    order_id int not null references orders(id)
);