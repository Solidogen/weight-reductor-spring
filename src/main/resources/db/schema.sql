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
    date   DATE,
    INDEX (date)
) engine = InnoDB;
