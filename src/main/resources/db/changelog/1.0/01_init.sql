CREATE TABLE order_status
(
    id   INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

COMMENT ON TABLE order_status IS 'Order statuses';
COMMENT ON COLUMN order_status.id IS 'Unique identifier for the order status';
COMMENT ON COLUMN order_status.name IS 'Name of the order status';

CREATE TABLE "order"
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT         NOT NULL,
    name            VARCHAR(100)   NOT NULL,
    order_status_id INT            NOT NULL,
    cart_id         BIGINT         NOT NULL,
    total_price     NUMERIC(10, 2) NOT NULL,
    creation_date   TIMESTAMP      NOT NULL DEFAULT NOW(),
    update_date     TIMESTAMP      NOT NULL DEFAULT NOW(),
    is_archive      BOOLEAN        NOT NULL DEFAULT FALSE,
    CONSTRAINT order_status_id_fk FOREIGN KEY (order_status_id) REFERENCES order_status (id)
);

COMMENT ON TABLE "order" IS 'Order information';
COMMENT ON COLUMN "order".id IS 'Unique identifier for the order';
COMMENT ON COLUMN "order".user_id IS 'Identifier of the user who placed the order';
COMMENT ON COLUMN "order".name IS 'Name of the order';
COMMENT ON COLUMN "order".order_status_id IS 'Identifier of the order status';
COMMENT ON COLUMN "order".cart_id IS 'Identifier of the cart associated with the order';
COMMENT ON COLUMN "order".total_price IS 'Total price of the order';
COMMENT ON COLUMN "order".creation_date IS 'Date of creation';
COMMENT ON COLUMN "order".update_date IS 'Date of the last update';
COMMENT ON COLUMN "order".is_archive IS 'Archive status of the order';

CREATE TABLE order_product
(
    id         BIGINT PRIMARY KEY,
    order_id   BIGINT         NOT NULL,
    product_id BIGINT         NOT NULL,
    quantity   INT            NOT NULL DEFAULT 1,
    price      NUMERIC(10, 2) NOT NULL,
    CONSTRAINT order_id_fk FOREIGN KEY (order_id) REFERENCES "order" (id)
);

COMMENT ON TABLE order_product IS 'Links products to orders';
COMMENT ON COLUMN order_product.id IS 'Unique identifier for the order-product link';
COMMENT ON COLUMN order_product.order_id IS 'Identifier of the order';
COMMENT ON COLUMN order_product.product_id IS 'Identifier of the product';
COMMENT ON COLUMN order_product.quantity IS 'Quantity of the product in the order';
COMMENT ON COLUMN order_product.price IS 'Price of the product at the time of the order for 1 quantity';

CREATE TABLE order_status_history
(
    id            BIGSERIAL PRIMARY KEY,
    order_id      BIGINT    NOT NULL,
    old_status_id INT       NOT NULL,
    change_date   TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT order_id_fk FOREIGN KEY (order_id) REFERENCES "order" (id),
    CONSTRAINT old_status_id_fk FOREIGN KEY (old_status_id) REFERENCES order_status (id)
);

COMMENT ON TABLE order_status_history IS 'Stores order status change history';
COMMENT ON COLUMN order_status_history.id IS 'Unique identifier for the order status change record';
COMMENT ON COLUMN order_status_history.order_id IS 'Identifier of the order';
COMMENT ON COLUMN order_status_history.old_status_id IS 'Previous status of the order';
COMMENT ON COLUMN order_status_history.change_date IS 'Date of the status change';