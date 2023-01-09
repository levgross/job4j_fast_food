create table dish (
    id serial primary key not null,
    name varchar unique not null,
    description varchar,
    photo bytea not null,
    price int not null
);