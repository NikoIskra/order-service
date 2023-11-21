CREATE SEQUENCE IF NOT EXISTS order_id_seq
    START 1
    INCREMENT 1
    MINVALUE 1
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS item_id_seq
    START 1
    INCREMENT 1
    MINVALUE 1
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS sub_item_id_seq
    START 1
    INCREMENT 1
    MINVALUE 1
    CACHE 1;

CREATE TABLE IF NOT EXISTS orders
(
    id bigint NOT NULL DEFAULT nextval('order_id_seq'),
    order_number character varying(10) NOT NULL,
    provider_id uuid NOT NULL,
    client_id uuid NOT NULL,
    comment character varying(512),
    total_price_cents integer NOT NULL,
    client_contact character varying(512) NOT NULL,
    delivery_address character varying(512),
    stage character varying(12) NOT NULL,
    status character varying(12) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT unique_order_number UNIQUE (order_number)
);

CREATE TABLE IF NOT EXISTS order_item
(
    id bigint NOT NULL DEFAULT nextval('item_id_seq'),
    order_id bigint,
    provider_item_id bigint NOT NULL,
    quantity integer NOT NULL,
    price_cents integer NOT NULL,
    CONSTRAINT order_item_pkey PRIMARY KEY (id),
    CONSTRAINT order_id_fk FOREIGN KEY (order_id)
        REFERENCES orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_sub_item
(
    id bigint NOT NULL DEFAULT nextval('sub_item_id_seq'),
    order_item_id bigint,
    provider_sub_item_id bigint NOT NULL,
    quantity integer NOT NULL,
    price_cents integer NOT NULL,
    CONSTRAINT sub_item_pkey PRIMARY KEY (id),
    CONSTRAINT fk_item_id FOREIGN KEY (order_item_id)
        REFERENCES order_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_transition_log
(
    id character varying(24) NOT NULL,
    order_id bigint NOT NULL,
    comment character varying(512),
    total_price_cents integer NOT NULL,
    client_contact character varying(512) NOT NULL,
    delivery_address character varying(512),
    stage character varying(12) NOT NULL,
    complete_status character varying(10) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    CONSTRAINT order_transition_log_pkey PRIMARY KEY (id),
    CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)