--liquibase formatted sql

--changeset ayushchenko:1
CREATE TABLE IF NOT EXISTS administrator
(
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(128)       NOT NULL DEFAULT '{noop}1234',
    email       VARCHAR(50) UNIQUE NOT NULL,
    firstname   VARCHAR(50)        NOT NULL,
    lastname    VARCHAR(50)        NOT NULL,
    role        VARCHAR(50)        NOT NULL,
    created_at  TIMESTAMP                   DEFAULT NOW(),
    modified_at TIMESTAMP                   DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:2
CREATE TABLE IF NOT EXISTS country
(
    id          SERIAL PRIMARY KEY,
    code        VARCHAR(2) UNIQUE   NOT NULL,
    name        VARCHAR(255) UNIQUE NOT NULL,
    is_active   BOOLEAN   DEFAULT true,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:3
CREATE TABLE IF NOT EXISTS agent
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    phone       VARCHAR(20),
    commentary  TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:4
CREATE TABLE IF NOT EXISTS manufacturer
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50)                 NOT NULL UNIQUE,
    country_id  INT REFERENCES country (id) NOT NULL,
    address     VARCHAR(255),
    commentary  TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:5
CREATE TABLE IF NOT EXISTS warehouse
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) UNIQUE          NOT NULL,
    country_id  INT REFERENCES country (id) NOT NULL,
    address     VARCHAR(255), --NOT NULL
    commentary  TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64),
    UNIQUE (name, country_id)
);

--changeset ayushchenko:6
CREATE TABLE IF NOT EXISTS client
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(50)                       NOT NULL UNIQUE,
    full_name        VARCHAR(100),
    status_id        INT REFERENCES client_status (id) NOT NULL,
    business_type_id INT REFERENCES business_type (id) NOT NULL,
    industry_type_id INT REFERENCES industry_type (id) NOT NULL,
    address          VARCHAR(255),
    created_at       TIMESTAMP DEFAULT NOW(),
    modified_at      TIMESTAMP DEFAULT NOW(),
    created_by       VARCHAR(64),
    modified_by      VARCHAR(64)
);

--changeset ayushchenko:7
CREATE TABLE IF NOT EXISTS route
(
    id                        BIGSERIAL PRIMARY KEY,
    identification_number     VARCHAR(100)                     NOT NULL UNIQUE,
    status_id                 INT REFERENCES route_status (id) NOT NULL,
    transport_type            VARCHAR(20)                      NOT NULL, -- e.g., 'road', 'sea', 'air', 'railway'
    is_international          BOOLEAN   DEFAULT TRUE,                    -- flag for international/domestic; CAN BE REMOVED as you can fetch only those rows with different origin/destination countries;
    country_of_departure_id   INT REFERENCES country (id)      NOT NULL, -- Foreign key reference to 'countryId'
    country_of_destination_id INT REFERENCES country (id)      NOT NULL, -- Foreign key reference to 'countryId'
    customs_post              VARCHAR(50)                      NULL,     -- NULL for domestic routes
    departure_date            TIMESTAMP,                                 -- The scheduled departure date
    arrival_date              TIMESTAMP,                                 -- The scheduled arrival date
    created_at                TIMESTAMP DEFAULT NOW(),
    modified_at               TIMESTAMP DEFAULT NOW(),
    created_by                VARCHAR(64),
    modified_by               VARCHAR(64)
);

--changeset ayushchenko:8
CREATE TABLE IF NOT EXISTS road_transport
(
    id                   BIGINT PRIMARY KEY REFERENCES route (id),
    identifier           VARCHAR(50) NOT NULL UNIQUE,
    truck_plate_number   VARCHAR(20),
    trailer_plate_number VARCHAR(20),
    cmr                  VARCHAR(50),
    truck_type           VARCHAR(20) NOT NULL, -- e.g., '90_feet', '120_feet'
    movement_plan        TEXT,                 -- Plan for the movement of the truck
    comments             TEXT,                 -- Additional comments
    created_at           TIMESTAMP DEFAULT NOW(),
    modified_at          TIMESTAMP DEFAULT NOW(),
    created_by           VARCHAR(64),
    modified_by          VARCHAR(64)
);

--changeset ayushchenko:9
CREATE TABLE IF NOT EXISTS pick_up_point
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)                NOT NULL UNIQUE,
    country_id  INT REFERENCES country (id) NOT NULL,
    address     VARCHAR(255)                NOT NULL,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:10
CREATE TABLE IF NOT EXISTS shipment
(
    id                      BIGSERIAL PRIMARY KEY,
    status_id               INT REFERENCES shipment_status (id)    NOT NULL,
    client_id               INT REFERENCES client (id)             NOT NULL,
    priority_id             INT REFERENCES priority (id) DEFAULT 1 NOT NULL,
    route_id                BIGINT REFERENCES route (id),
    internal_comment        TEXT,
    client_comment          TEXT,
    warehouse_comment       TEXT,
    date_placed             TIMESTAMP,
    date_checked            TIMESTAMP,
    date_ready_dispatch     TIMESTAMP,
    date_reached_warehouse  TIMESTAMP,
    date_loading            TIMESTAMP,
    date_unloading          TIMESTAMP,

    -- Delivery Type and Address
    delivery_type           VARCHAR(20)                            NOT NULL, -- e.g., 'pick-up', 'door-to-door'
    date_confirmed_dispatch TIMESTAMP,
    pick_up_point_id        INT REFERENCES pick_up_point (id),
    destination_address     VARCHAR(255),

    -- client-provided information
    client_pcs              INT,
    client_volume_m3        FLOAT,
    client_weight_kg        FLOAT,
    shipment_type           VARCHAR(50)                            NOT NULL,
    shipment_description    TEXT,

    -- Verified warehouse information
    warehouse_pcs           INT,
    warehouse_volume_m3     FLOAT,
    warehouse_weight_kg     FLOAT,
    warehouse_diff_comment  TEXT,

    -- Origin and Destination
    country_of_departure    INT REFERENCES country (id)            NOT NULL,
    manufacturer_id         INT REFERENCES manufacturer (id)       NOT NULL,
    country_of_destination  INT REFERENCES country (id)            NOT NULL,

    -- Auditing
    created_at              TIMESTAMP                    DEFAULT NOW(),
    modified_at             TIMESTAMP                    DEFAULT NOW(),
    created_by              VARCHAR(64),
    modified_by             VARCHAR(64)
);

--changeset ayushchenko:11
CREATE TABLE IF NOT EXISTS shipment_status_history
(
    id          BIGSERIAL PRIMARY KEY,
    shipment_id BIGINT REFERENCES shipment (id),
    status_id   INT REFERENCES shipment_status (id),
    status_date TIMESTAMP DEFAULT NOW(),
    modified_by VARCHAR(64),
    comment     TEXT
);

--changeset ayushchenko:12
CREATE TABLE IF NOT EXISTS invoice
(
    id           BIGSERIAL PRIMARY KEY,
    shipment_id  BIGINT,
    total_amount DECIMAL(10, 2),
    currency     VARCHAR(3), -- ISO currency code (e.g., USD, EUR)
    status_id    BIGINT,
    issue_date   DATE,
    due_date     DATE,
    commentary   TEXT,

    created_at   TIMESTAMP DEFAULT NOW(),
    modified_at  TIMESTAMP DEFAULT NOW(),
    created_by   VARCHAR(64),
    modified_by  VARCHAR(64),

    FOREIGN KEY (shipment_id) REFERENCES shipment (id),
    FOREIGN KEY (status_id) REFERENCES invoice_status (id)
);

--changeset ayushchenko:13
CREATE TABLE IF NOT EXISTS payment
(
    id                BIGSERIAL PRIMARY KEY,
    client_id         INT,
    amount            DECIMAL(10, 2),
    currency          VARCHAR(3), -- ISO currency code
    payment_date_time TIMESTAMP,
    payment_method    VARCHAR(50),

    created_at        TIMESTAMP DEFAULT NOW(),
    modified_at       TIMESTAMP DEFAULT NOW(),
    created_by        VARCHAR(64),
    modified_by       VARCHAR(64),

    FOREIGN KEY (client_id) REFERENCES client (id)
);

--changeset ayushchenko:14
CREATE TABLE IF NOT EXISTS payment_invoice
(
    id               BIGSERIAL PRIMARY KEY,
    payment_id       BIGINT NOT NULL REFERENCES payment (id) ON DELETE CASCADE,
    invoice_id       BIGINT NOT NULL REFERENCES invoice (id) ON DELETE CASCADE,
    allocated_amount DECIMAL(10, 2),
    UNIQUE (payment_id, invoice_id)
);
