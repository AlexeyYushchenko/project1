--liquibase formatted sql

--changeset ayushchenko:1
CREATE TABLE client_status
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) UNIQUE,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:2
CREATE TABLE shipment_status
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:3
CREATE TABLE IF NOT EXISTS invoice_status
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:4
CREATE TABLE IF NOT EXISTS business_type
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:5
CREATE TABLE IF NOT EXISTS industry_type
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:6
CREATE TABLE IF NOT EXISTS route_status
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:7
CREATE TABLE IF NOT EXISTS priority
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:8
CREATE TABLE IF NOT EXISTS service_type
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:9
CREATE TABLE IF NOT EXISTS payment_type
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

