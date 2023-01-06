create table dish (
    id serial primary key not null,
    name varchar unique not null,
    description varchar,
    photo bytea,
    price int not null
);