CREATE TABLE `user`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `username`      VARCHAR(20)  NOT NULL UNIQUE,
    `password`      CHAR(60)     NOT NULL,
    `first_name`    VARCHAR(50)  NOT NULL,
    `last_name`     VARCHAR(50)  NOT NULL,
    `email`         VARCHAR(50)  NOT NULL,
    /*
      0 - ADMINISTRATOR,
      1 - MODERATOR,
      2 - WRITER,
      3 - READER.
     */
    `role`          TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2, 3)),
    `profile_image` VARCHAR(255) NOT NULL DEFAULT "default_user.jpg",
    `reg_date`      TIMESTAMP    NOT NULL DEFAULT NOW(),
    CONSTRAINT `PK_user` PRIMARY KEY (`id`)
);

CREATE TABLE `category`
(
    `id`        BIGINT      NOT NULL AUTO_INCREMENT,
    `parent_id` BIGINT, #fk дает ли fk возможность добавлять null
    `name`      VARCHAR(50) NOT NULL,
    CONSTRAINT `PK_category` PRIMARY KEY (`id`),
    CONSTRAINT `FK_category` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `CAT_name_pid` UNIQUE (`name`, `parent_id`)
);

CREATE TABLE `question`
(
    `id`                      BIGINT       NOT NULL AUTO_INCREMENT,
    `title`                   VARCHAR(50)  NOT NULL,
    `text`                    VARCHAR(500) NOT NULL,
    `category_id`             BIGINT       NOT NULL,
    `user_id`                 BIGINT       NOT NULL,
    `ask_date`                TIMESTAMP    NOT NULL DEFAULT NOW(),
    `question_image`          CHAR(36),
    `is_closed`               TINYINT(1)   NOT NULL DEFAULT 0 CHECK (`is_closed` IN (0, 1)),
    `contains_correct_answer` TINYINT(1)   NOT NULL DEFAULT 0 CHECK (`contains_correct_answer` IN (0, 1)),
    CONSTRAINT `PK_question` PRIMARY KEY (`id`),
    CONSTRAINT `FK_question_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `FK_question_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `answer`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `text`        VARCHAR(500) NOT NULL,
    `user_id`     BIGINT       NOT NULL,
    `question_id` BIGINT       NOT NULL,
    `answer_date` TIMESTAMP    NOT NULL DEFAULT NOW(),
    `is_correct`  TINYINT(1)   NOT NULL DEFAULT 0 CHECK (`is_correct` IN (0, 1)),
    CONSTRAINT `PK_answer` PRIMARY KEY (`id`),
    CONSTRAINT `FK_answer_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `FK_answer_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `tag`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `text` VARCHAR(32) NOT NULL UNIQUE,
    CONSTRAINT `PK_tag` PRIMARY KEY (`id`)
);


CREATE TABLE `question_tag`
(
    `question_id` BIGINT NOT NULL,
    `tag_id`      BIGINT NOT NULL,
    CONSTRAINT `FK_question_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `FK_tag_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `UQ_qid_tid` UNIQUE (`question_id`, `tag_id`)
);



