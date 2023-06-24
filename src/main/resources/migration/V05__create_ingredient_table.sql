CREATE TABLE IF NOT EXISTS ingredient
(
    id   VARCHAR(4)  NOT NULL,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10)     NOT NULL,
    CONSTRAINT pk_ingredient PRIMARY KEY (id)
);