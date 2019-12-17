CREATE TABLE expense (
    id SERIAL PRIMARY KEY,
    amount NUMERIC NOT NULL,
    category VARCHAR,
    version BIGINT NOT NULL
);
CREATE INDEX category_idx ON expense(category);
