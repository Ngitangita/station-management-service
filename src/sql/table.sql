
DROP DATABASE IF EXISTS service_sation_management;
CREATE DATABASE service_sation_management;

\c  service_sation_management;

CREATE TABLE IF NOT EXISTS stations
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(150) NOT NULL,
    address TEXT,
    max_volume_gasoline DECIMAL(10, 2) NOT NULL,
    max_volume_diesel DECIMAL(10, 2) NOT NULL,
    max_volume_petrol DECIMAL(10, 2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS products
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    quantity DECIMAL(10, 2)
    );

CREATE TABLE IF NOT EXISTS transactions
(
    id  SERIAL PRIMARY KEY,
    station_id  INT REFERENCES stations (id),
    product_id  INT REFERENCES products (id),
    type   VARCHAR(50) NOT NULL,
    quantity DECIMAL(10, 2),
    amount DECIMAL(10, 2) NOT NULL,
    date_transaction TIMESTAMP   NOT NULL,
    );

