DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.product_category;
DROP TABLE IF EXISTS public.supplier;

CREATE TABLE public.product (
    id serial NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    price FLOAT NOT NULL,
    currency VARCHAR(200) NOT NULL,
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

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_product_category_id FOREIGN KEY (product_category_id) REFERENCES public.product_category(id),
    ADD CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);
