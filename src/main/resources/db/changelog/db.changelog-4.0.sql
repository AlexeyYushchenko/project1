--liquibase formatted sql

--changeset ayushchenko:1
ALTER SEQUENCE shipment_id_seq RESTART WITH 50000;