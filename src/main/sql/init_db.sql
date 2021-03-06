DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS supplier;


CREATE TABLE categories
(
  category_id INT PRIMARY KEY NOT NULL,
  name varchar(255),
  department VARCHAR(255),
  description VARCHAR(255)

);

CREATE TABLE supplier
(
  supplier_id INT PRIMARY KEY NOT NULL,
  name VARCHAR(255)

);

CREATE TABLE product
(
  id SERIAL PRIMARY KEY NOT NULL,
  name varchar(40),
  default_price VARCHAR(255),
  currency VARCHAR(255),
  description VARCHAR(255),
  category INT REFERENCES categories(category_id),
  supplier INT REFERENCES supplier(supplier_id)
);

CREATE TABLE webshopuser
(
  id            SERIAL PRIMARY KEY NOT NULL,
  username      VARCHAR(40) UNIQUE,
  email         VARCHAR(255) UNIQUE ,
  password         VARCHAR(255)
--   country VARCHAR(255),
--   zipcode VARCHAR(10),
--   phone VARCHAR(20),
--   firstname VARCHAR(25),
--   lastname VARCHAR(25),
--   address VARCHAR(255)


);
