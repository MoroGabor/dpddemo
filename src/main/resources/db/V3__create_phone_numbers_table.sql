CREATE TABLE phone_numbers (
    id SERIAL PRIMARY KEY,
    phone_number VARCHAR(255) NOT NULL,
    person_id BIGINT,
    FOREIGN KEY (person_id) REFERENCES persons (id)
);
