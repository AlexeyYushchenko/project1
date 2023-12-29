--liquibase formatted sql

--changeset ayushchenko:1
ALTER SEQUENCE shipment_id_seq RESTART WITH 50000;

--changeset ayushchenko:2
CREATE UNIQUE INDEX administrator_email_lower_idx ON administrator (LOWER(email));

--changeset ayushchenko:3
CREATE UNIQUE INDEX administrator_username_lower_idx ON administrator (LOWER(username));

--changeset ayushchenko:4
CREATE UNIQUE INDEX agent_username_lower_idx ON agent (LOWER(name));

--changeset ayushchenko:5
CREATE UNIQUE INDEX business_type_name_lower_idx ON business_type (lower(name));

--changeset ayushchenko:6
CREATE UNIQUE INDEX industry_type_name_lower_idx ON industry_type (lower(name));

--changeset ayushchenko:7
CREATE UNIQUE INDEX client_status_name_lower_idx ON client_status (lower(name));

--changeset ayushchenko:8
CREATE UNIQUE INDEX shipment_status_name_lower_idx ON shipment_status (lower(name));

--changeset ayushchenko:9
CREATE UNIQUE INDEX invoice_status_name_lower_idx ON invoice_status (lower(name));

--changeset ayushchenko:10
CREATE UNIQUE INDEX priority_name_lower_idx ON priority (lower(name));

--changeset ayushchenko:11
CREATE UNIQUE INDEX country_code_upper_idx ON country (UPPER(code));

--changeset ayushchenko:12
CREATE UNIQUE INDEX country_name_upper_idx ON country (UPPER(name));

--changeset ayushchenko:13
CREATE UNIQUE INDEX manufacturer_name_lower_idx ON manufacturer (lower(name));

--changeset ayushchenko:14
CREATE UNIQUE INDEX warehouse_name_lower_idx ON warehouse (lower(name));

--changeset ayushchenko:15
CREATE UNIQUE INDEX pick_up_point_name_lower_idx ON pick_up_point (lower(name));

--changeset ayushchenko:16
CREATE UNIQUE INDEX road_transport_identifier_lower_idx ON road_transport (lower(identifier));








