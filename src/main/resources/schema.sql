CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DOUBLE PRECISION NOT NULL
);

CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE booking (
                         id SERIAL PRIMARY KEY,
                         customer_id INTEGER NOT NULL,
                         product_id INTEGER NOT NULL,
                         booking_date TIMESTAMP NOT NULL,
                         FOREIGN KEY (customer_id) REFERENCES customer(id),
                         FOREIGN KEY (product_id) REFERENCES product(id)
);
