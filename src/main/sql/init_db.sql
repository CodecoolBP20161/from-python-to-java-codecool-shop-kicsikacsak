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
  name varchar(40),
  default_price VARCHAR(255),
  currency VARCHAR(255),
  description VARCHAR(255),
  category INT REFERENCES categories(category_id),
  supplier INT REFERENCES supplier(supplier_id)
);
