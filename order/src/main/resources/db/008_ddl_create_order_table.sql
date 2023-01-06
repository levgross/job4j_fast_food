create table order (
    id serial primary key not null,
    created timestamp not null,
    address varchar unique not null,
    sum int not null,
    status varchar not null,
    customer_id int not null references customer(id)
);