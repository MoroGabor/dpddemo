CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    post_code INT NOT NULL,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    house_number INT NOT NULL,
    person_id BIGINT,
    FOREIGN KEY (person_id) REFERENCES persons (id)
);
