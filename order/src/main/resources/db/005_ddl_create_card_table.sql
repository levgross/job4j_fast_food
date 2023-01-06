create table card (
    id serial primary key not null,
    number varchar unique not null,
    discount double not null,
    customer_id int not null references customer(id)
);