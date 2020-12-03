INSERT INTO `user` (`username`, `password`, `first_name`, `last_name`, `email`)
VALUES ('kirillpraded', '2522244', 'Kirill', 'Praded', 'kirillpraded@bk.ru');
INSERT INTO `role` (`role_name`)
VALUES ('ADMIN'),
       ('MODERATOR'),
       ('WRITER'),
       ('READER');
INSERT INTO `user_role` (`user_id`,`role_id`)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);
INSERT INTO `image_user` (`user_id`) VALUES (1);