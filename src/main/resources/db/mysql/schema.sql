CREATE TABLE IF NOT EXISTS products
(
    id      INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255),
    protein FLOAT(3, 1),
    carbs   FLOAT(3, 1),
    fat     FLOAT(3, 1),
    INDEX (name)
) engine = InnoDB;