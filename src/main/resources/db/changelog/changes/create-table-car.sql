--liquibase formatted sql

--changeset ilga:1

CREATE TABLE car
(
    car_id INTEGER PRIMARY KEY,
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year_of_manufacture INTEGER NOT NULL,
    vin_number VARCHAR(255) NOT NULL,
    registration_number VARCHAR(255) NOT NULL
);

CREATE SEQUENCE car_sequence START WITH 1 INCREMENT BY 1;