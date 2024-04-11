BEGIN;


CREATE TABLE IF NOT EXISTS public.bottoms
(
    bottom_id serial NOT NULL,
    flavor character varying(100) COLLATE pg_catalog."default" NOT NULL,
    price integer NOT NULL,
    CONSTRAINT bottoms_pkey PRIMARY KEY (bottom_id)
    );

CREATE TABLE IF NOT EXISTS public."order"
(
    order_id serial NOT NULL,
    total_price integer NOT NULL,
    orderline_amount integer NOT NULL,
    user_id integer NOT NULL,
    user_name character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT order_pkey PRIMARY KEY (order_id)
    );

CREATE TABLE IF NOT EXISTS public.orderline
(
    orderline_id serial NOT NULL,
    order_id integer NOT NULL,
    total_price integer NOT NULL,
    topping_id integer NOT NULL,
    bottom_id integer NOT NULL,
    amount integer NOT NULL,
    CONSTRAINT orderline_pkey PRIMARY KEY (orderline_id)
    );

CREATE TABLE IF NOT EXISTS public.toppings
(
    topping_id serial NOT NULL,
    flavor character varying(100) COLLATE pg_catalog."default" NOT NULL,
    price integer NOT NULL,
    CONSTRAINT toppings_pkey PRIMARY KEY (topping_id)
    );

CREATE TABLE IF NOT EXISTS public.users
(
    user_id serial NOT NULL,
    user_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    user_password character varying(50) COLLATE pg_catalog."default" NOT NULL,
    user_wallet integer,
    user_role character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT unique_user_name UNIQUE (user_name)
    );

ALTER TABLE IF EXISTS public."order"
    ADD CONSTRAINT fk_orders_user FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.orderline
    ADD CONSTRAINT fkey_bottoms_orderline FOREIGN KEY (bottom_id)
    REFERENCES public.bottoms (bottom_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.orderline
    ADD CONSTRAINT fkey_toppings_orderline FOREIGN KEY (topping_id)
    REFERENCES public.toppings (topping_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.orderline
    ADD CONSTRAINT fkey_order_orders FOREIGN KEY (order_id)
    REFERENCES public."order" (order_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

INSERT INTO public.bottoms (flavor, price)
VALUES ('Chocolate', 5.00),
       ('Vanilla', 5.00),
       ('Nutmeg', 5.00),
       ('Pistachio', 6.00),
       ('Almond', 7.00);

INSERT INTO public.toppings (flavor, price)
VALUES ('Chocolate', 5.00),
       ('Blueberry', 5.00),
       ('Raspberry', 5.00),
       ('Crispy', 6.00),
       ('Strawberry', 6.00),
       ('Rum/Raisin', 7.00),
       ('Orange', 8.00),
       ('Lemon', 8.00),
       ('Blue cheese', 9.00);

INSERT INTO users (user_name, user_password, user_wallet, user_role)
VALUES
    ('chad', '1234', 600.00, 'admin'),
    ('gary', '1234', 200.00, 'user'),
    ('jeff', 'admin', 500.00, 'user');
END;