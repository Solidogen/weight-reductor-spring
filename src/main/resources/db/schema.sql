# drop database weightreductor;
# create database weightreductor;

ALTER DATABASE weightreductor CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE TABLE IF NOT EXISTS products
(
    id      INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    protein FLOAT NOT NULL,
    carbs   FLOAT NOT NULL,
    fat     FLOAT NOT NULL,
    company    VARCHAR(255),
    INDEX (name)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS weighings
(
    id     INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    weight FLOAT NOT NULL,
    date   DATE UNIQUE NOT NULL,
    INDEX (date)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS meals
(
    id   INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS products_with_weights
(
    id         INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id INT(4) UNSIGNED NOT NULL,
    meal_id    INT(4) UNSIGNED NOT NULL,
    weight     FLOAT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (meal_id) REFERENCES meals (id)
) engine = InnoDB;

