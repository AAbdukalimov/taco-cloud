CREATE TABLE IF NOT EXISTS Ingredient
(
    id   VARCHAR(4)  NOT NULL,
    name VARCHAR(25) NOT NULL,
    type INTEGER,
    CONSTRAINT pk_ingredient PRIMARY KEY (id)
);

drop table Ingredient_Ref;
drop table Ingredient;

CREATE TABLE IF NOT EXISTS Ingredient_Ref
(
    ingredient VARCHAR(4) NOT NULL,
    taco       BIGINT     NOT NULL,
    taco_key   BIGINT     NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(50) NOT NULL,
    taco_order     BIGINT      NOT NULL,
    taco_order_key BIGINT      NOT NULL,
    created_at     TIMESTAMP   NOT NULL
);

create table if not exists Taco_Order
(
    id              BIGSERIAL PRIMARY KEY,
    delivery_Name   VARCHAR(50) NOT NULL,
    delivery_Street VARCHAR(50) NOT NULL,
    delivery_City   VARCHAR(50) NOT NULL,
    delivery_State  VARCHAR(2)  NOT NULL,
    delivery_Zip    VARCHAR(10) NOT NULL,
    cc_number       VARCHAR(16) NOT NULL,
    cc_expiration   VARCHAR(5)  NOT NULL,
    cc_cvv          VARCHAR(3)  NOT NULL,
    placed_at       TIMESTAMP   NOT NULL
);

ALTER TABLE Taco
    ADD FOREIGN KEY (taco_order) references Taco_Order (id);

ALTER TABLE Ingredient_Ref
    ADD FOREIGN KEY (ingredient) references Ingredient (id);

insert into Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP'),
       ('COTO', 'Corn Tortilla', 'WRAP'),
       ('GRBF', 'Ground Beef', 'PROTEIN'),
       ('CARN', 'Carnitas', 'PROTEIN'),
       ('TMTO', 'Diced Tomatoes', 'VEGGIES'),
       ('LETC', 'Lettuce', 'VEGGIES'),
       ('CHED', 'Cheddar', 'CHEESE'),
       ('JACK', 'Monterrey Jack', 'CHEESE'),
       ('SLSA', 'Salsa', 'SAUCE'),
       ('SRCR', 'Sour Cream', 'SAUCE');