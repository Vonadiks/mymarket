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
('Prod_10', 100, 1),
('Prod_11', 110, 1),
('Prod_12', 120, 1),
('Prod_13', 130, 1),
('Prod_14', 140, 1),
('Prod_15', 150, 1),
('Prod_16', 160, 1),
('Prod_17', 170, 1),
('Prod_18', 180, 1),
('Prod_19', 190, 1),
('Prod_20', 200, 1);