DROP TABLE products IF EXISTS;

CREATE TABLE products
(
    id      INTEGER IDENTITY PRIMARY KEY,
    name    VARCHAR(255),
    protein DECIMAL(3, 1),
    carbs   DECIMAL(3, 1),
    fat     DECIMAL(3, 1)
);