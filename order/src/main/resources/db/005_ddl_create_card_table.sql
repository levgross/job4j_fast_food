create table card (
    id serial primary key not null,
    number varchar unique not null,
    discount double precision not null,
    customer_id int not null references customer(id)
);