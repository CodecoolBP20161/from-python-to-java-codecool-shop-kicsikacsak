DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS supplier;


CREATE TABLE category
(
  category_id VARCHAR(36) PRIMARY KEY,
  name varchar(40),
  department VARCHAR(40),
  description VARCHAR(255)

);

CREATE TABLE supplier
(
  supplier_id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(40)

);

CREATE TABLE product
(
  id varchar(36) PRIMARY KEY,
  name varchar(40),
  default_price FLOAT(10),
  currency VARCHAR(5),
  description VARCHAR(255),
  category varchar(40) REFERENCES category(category_id),
  supplier varchar(40) REFERENCES supplier(supplier_id)
);