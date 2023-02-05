CREATE DATABASE pizzeriadb;

USE pizzeriadb;

CREATE TABLE customer (
    id VARCHAR(40) NOT NULL PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(100),
    phoneNumber VARCHAR(100),
    password VARCHAR(255)
);

CREATE TABLE customer_address (
    id VARCHAR(40) NOT NULL PRIMARY KEY,
    streetAddress VARCHAR(255),
    city VARCHAR(100),
    zipCode VARCHAR(100),
    country VARCHAR(100),
    customerId INT,
    FOREIGN KEY (costumerId) REFERENCES customer(id)
);

CREATE TABLE product (
    id VARCHAR(40) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    price INT,
    categoryId INT,
    FOREIGN KEY (categoryId) REFERENCES product_category(id)
);

CREATE TABLE product_category (
    id VARCHAR(40) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT
);