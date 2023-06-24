CREATE TABLE taco_ingredients
(
    taco_id        BIGINT       NOT NULL,
    ingredients_id VARCHAR(255) NOT NULL
);

ALTER TABLE taco_ingredients
    ADD FOREIGN KEY(ingredients_id) REFERENCES ingredient (id);

ALTER TABLE taco_ingredients
    ADD CONSTRAINT fk_taco_ingredients FOREIGN KEY (taco_id) REFERENCES taco (id);