DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS supplier;


CREATE TABLE category
(
  category_id INT PRIMARY KEY,
  name varchar(40),
  department VARCHAR(40),
  description VARCHAR(255)

);

CREATE TABLE supplier
(
  supplier_id INT PRIMARY KEY,
  name VARCHAR(40)

);

CREATE TABLE product
(
  id varchar(36) PRIMARY KEY,
  name varchar(40),
  default_price VARCHAR(10),
  currency MONEY,
  description VARCHAR(255),
  category INT REFERENCES category(category_id),
  supplier INT REFERENCES supplier(supplier_id)
);