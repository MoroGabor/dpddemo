CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_place VARCHAR(255),
    birth_date DATE,
    mother_name VARCHAR(255),
    social_security_number INT,
    tax_number INT,
    email VARCHAR(255),
    deleted BOOLEAN,
    version BIGINT NOT NULL DEFAULT 1
);
