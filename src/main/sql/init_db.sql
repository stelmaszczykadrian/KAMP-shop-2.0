DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.product_category;
DROP TABLE IF EXISTS public.supplier;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.authorities;

CREATE TABLE public.product (
    id serial NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    price FLOAT NOT NULL,
    currency VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    product_category_id integer NOT NULL,
    supplier_id integer NOT NULL
);

CREATE TABLE public.product_category (
    id serial NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    department VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL
);

CREATE TABLE public.supplier (
    id serial NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL

);

CREATE TABLE public.users (
      id serial NOT NULL PRIMARY KEY,
      name VARCHAR(200) NOT NULL,
      email VARCHAR(200) NOT NULL,
      password VARCHAR(200) NOT NULL,
      enabled INT NOT NULL DEFAULT 1,
      authority VARCHAR(50) NOT NULL
);
-- CREATE TABLE public.authorities (
--                             id serial NOT NULL PRIMARY KEY,
--                             name VARCHAR(50) NOT NULL,
--                              authority VARCHAR(50) NOT NULL
-- );

-- CREATE UNIQUE INDEX ix_auth_username
--     on authorities (name ,authority);
-- create table authorities (
--                              name VARCHAR(50) not null,
--                              authority VARCHAR(50) not null,
--                              constraint fk_authorities_users foreign key(name) references users(name)
-- );


INSERT INTO product (name, price, currency, description, product_category_id, supplier_id)
VALUES ('Amazon Fire', 49.9, 'USD','Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.',1, 1),
       ('Lenovo IdeaPad Miix 700', 479, 'USD','Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.',1, 2),
       ('Amazon Fire HD 8', 89, 'USD','Amazon latest Fire HD 8 tablet is a great value for media consumption.',1, 1),
       ('SAMSUNG QE43Q67B', 2000, 'USD','Television',2, 3),
       ('APPLE iPhone 14 Pro', 3000, 'USD','Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.',3, 4)
;

INSERT INTO product_category (name, department, description)
VALUES ('Tablet', 'Media', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.'),
       ('Television', 'Media', 'A system for converting visual images (with sound) into electrical signals, transmitting them by radio or other means, and displaying them electronically on a screen.'),
       ('Mobile Phone', 'Media', 'Portable device for connecting to a telecommunications network in order to transmit and receive voice, video, or other data.')
;

INSERT INTO users (name, email, password, enabled, authority)
VALUES ('a', 'a', '$e0804$Gv0z3n4ujRCBES/uOnuH2OrDDv+MciIaiTkhR7osNMGO0jqqRfQ6Ffw8qSX34SJeKk19DlR7mvW3++2nZsWbCw==$M9zK0oLzuXvYKraIdGZYw3weoho0GRVtDQVS1FcDYQI=',1, 'ROLE_USER')
;
-- INSERT INTO authorities (name, authority)
-- values ('a', 'ROLE_USER');



ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_product_category_id FOREIGN KEY (product_category_id) REFERENCES public.product_category(id),
    ADD CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);

