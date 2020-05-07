CREATE TABLE IF NOT EXISTS products
(
    id      INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255),
    protein FLOAT,
    carbs   FLOAT,
    fat     FLOAT,
    INDEX (name)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS weighings
(
    id     INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    weight FLOAT,
    date   DATE UNIQUE,
    INDEX (date)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS meals
(
    id   INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date DATE
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS products_with_weights
(
    id         INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id INT(4) UNSIGNED NOT NULL,
    meal_id    INT(4) UNSIGNED NOT NULL,
    weight     FLOAT,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (meal_id) REFERENCES meals (id)
) engine = InnoDB;

