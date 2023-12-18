--liquibase formatted sql

--changeset ayushchenko:1
ALTER SEQUENCE shipment_id_seq RESTART WITH 50000;

--changeset ayushchenko:2
CREATE UNIQUE INDEX administrator_email_lower_idx ON administrator (LOWER(email));

--changeset ayushchenko:3
CREATE UNIQUE INDEX administrator_username_lower_idx ON administrator (LOWER(username));
