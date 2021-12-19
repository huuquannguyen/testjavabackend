CREATE TABLE IF NOT EXISTS `testjavabackend`.`appuser` (
    `id` VARCHAR(8) NOT NULL,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `phone` VARCHAR(13) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `emoji` VARCHAR(255) NOT NULL,
    PRIMARY KEY(`id`)
    );

CREATE TABLE IF NOT EXISTS `testjavabackend`.`role`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `testjavabackend`.`user_role`(
    `user_id` VARCHAR(8) NOT NULL,
    `role_id` INT NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `appuser`(`id`),
    FOREIGN KEY (`role_id`) REFERENCES `role`(`id`),
    PRIMARY KEY (`user_id`, `role_id`)
);