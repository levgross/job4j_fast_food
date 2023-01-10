create table orders (
    id serial primary key not null,
    created timestamp not null,
    address varchar unique not null,
    sum int not null,
    customer_id int not null references customer(id),
    order_status varchar not null
);