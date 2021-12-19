
INSERT IGNORE INTO `testjavabackend`.`appuser`
    (`id`,`username`, `password`, `email`, `phone`, `address`, `emoji`)
    VALUES ('aAbcdE98', 'quan', '$2a$12$9fTSjDlyCHqFYiDpx7LfF.5GJPUqZhSaZnax/ofeX2MHhMKIkPSKi', 'huuquan@gmail.com', '012-3582-3498', '123, qt apt, xyz str, dp dist, hn', 'photos/user/defaultEmoji');

INSERT IGNORE INTO `testjavabackend`.`role`(`name`) VALUES ('role_user');
INSERT IGNORE INTO `testjavabackend`.`role`(`name`) VALUES ('role_admin');

INSERT IGNORE INTO `testjavabackend`.`user_role` VALUES ('aAbcdE98',2);
