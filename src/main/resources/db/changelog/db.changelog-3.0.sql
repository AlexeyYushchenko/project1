--liquibase formatted sql

--changeset ayushchenko:1
CREATE TABLE IF NOT EXISTS client_status_localization
(
    client_status_id INT REFERENCES client_status (id) ON DELETE CASCADE,
    language_code    VARCHAR(5),
    localized_name   VARCHAR(255) NOT NULL,
    PRIMARY KEY (client_status_id, language_code)
);

--changeset ayushchenko:2
CREATE TABLE shipment_status_localization
(
    shipment_status_id INT REFERENCES shipment_status (id) ON DELETE CASCADE,
    language_code      VARCHAR(5),
    localized_name     VARCHAR(255),
    PRIMARY KEY (shipment_status_id, language_code)
);

--changeset ayushchenko:3
CREATE TABLE IF NOT EXISTS country_localization
(
    country_id     INT REFERENCES country (id) ON DELETE CASCADE,
    language_code  VARCHAR(50),
    localized_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (country_id, language_code)
);

--changeset ayushchenko:4
CREATE TABLE IF NOT EXISTS business_type_localization
(
    business_type_id      INT REFERENCES business_type (id) ON DELETE CASCADE,
    language_code         VARCHAR(5),
    localized_name        VARCHAR(255) NOT NULL,
    localized_description TEXT,
    PRIMARY KEY (business_type_id, language_code)
);

--changeset ayushchenko:5
CREATE TABLE IF NOT EXISTS industry_type_localization
(
    industry_type_id      INT REFERENCES industry_type (id) ON DELETE CASCADE,
    language_code         VARCHAR(5),
    localized_name        VARCHAR(255) NOT NULL,
    localized_description TEXT,
    PRIMARY KEY (industry_type_id, language_code)
);

--changeset ayushchenko:6
CREATE TABLE IF NOT EXISTS priority_localization
(
    priority_id           INT REFERENCES priority (id) ON DELETE CASCADE,
    language_code         VARCHAR(5),
    localized_name        VARCHAR(255) NOT NULL,
    localized_description TEXT,
    PRIMARY KEY (priority_id, language_code)
);

--changeset ayushchenko:7
CREATE TABLE IF NOT EXISTS pick_up_point_localization
(
    pick_up_point_id INT REFERENCES pick_up_point (id) ON DELETE CASCADE,
    language_code    VARCHAR(5),
    localized_name   VARCHAR(255) NOT NULL,
    localized_address TEXT,
    PRIMARY KEY (pick_up_point_id, language_code)
);

--changeset ayushchenko:8
CREATE TABLE IF NOT EXISTS invoice_status_localization
(
    invoice_status_id INT REFERENCES invoice_status (id) ON DELETE CASCADE,
    language_code     VARCHAR(5),
    localized_name    VARCHAR(255) NOT NULL,
    PRIMARY KEY (invoice_status_id, language_code)
);

--changeset ayushchenko:9
CREATE TABLE IF NOT EXISTS route_status_localization
(
    route_status_id INT REFERENCES route_status (id) ON DELETE CASCADE,
    language_code     VARCHAR(5),
    localized_name    VARCHAR(255) NOT NULL,
    PRIMARY KEY (route_status_id, language_code)
);

--changeset ayushchenko:10
CREATE TABLE IF NOT EXISTS service_type_localization
(
    service_type_id      INT REFERENCES service_type (id) ON DELETE CASCADE,
    language_code         VARCHAR(5),
    localized_name        VARCHAR(255) NOT NULL,
    localized_description TEXT,
    PRIMARY KEY (service_type_id, language_code)
);

--changeset ayushchenko:11
CREATE TABLE IF NOT EXISTS payment_type_localization
(
    payment_type_id      INT REFERENCES payment_type (id) ON DELETE CASCADE,
    language_code         VARCHAR(5),
    localized_name        VARCHAR(255) NOT NULL,
    localized_description TEXT,
    PRIMARY KEY (payment_type_id, language_code)
);