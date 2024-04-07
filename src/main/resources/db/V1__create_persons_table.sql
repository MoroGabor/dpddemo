CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_place VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    mother_name VARCHAR(255),
    social_security_number VARCHAR(50) NOT NULL,
    tax_number VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    version BIGINT DEFAULT 1 NOT NULL
);
