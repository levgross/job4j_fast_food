create table customer (
    id serial primary key not null,
    username varchar unique not null,
    password varchar not null,
    phone varchar unique not null,
    email varchar unique,
    card_id int references card(id)
);