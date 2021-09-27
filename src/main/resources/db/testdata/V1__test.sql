create table categories (
    id bigserial primary key,
    title varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
                       );
insert into categories (title) values ('Food');
create table products (
    id bigserial primary key,
    title varchar(255),
    price numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
                       );
insert into products (title, price, category_id)
values
('Prod_1', 10, 1),
('Prod_2', 20, 1),
('Prod_3', 30, 1),
('Prod_4', 40, 1),
('Prod_5', 50, 1),
('Prod_6', 60, 1),
('Prod_7', 70, 1),
('Prod_8', 80, 1),
('Prod_9', 90, 1),
('Prod_10', 100, 1);

create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
create table orders
(
    id         bigserial primary key,
    price      numeric(8, 2) not null,
    user_id    bigint references users (id),
    address    varchar(255),
    phone      varchar(32),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);


create table order_items
(
    id                bigserial primary key,
    price             numeric(8, 2) not null,
    price_per_product numeric(8, 2) not null,
    product_id        bigint references products (id),
    order_id          bigint references products (id),
    quantity          int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

create table statistic (
id bigserial primary key,
service varchar(255) unique,
duration bigint,
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp
);


insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

insert into orders (price, user_id, address, phone)
values
(100, 1, 'Volzhskaya 22', '223322'),
(100, 2, 'Novaya 33', '555555'),
(300, 1, 'Volzhskaya 22', '223322');