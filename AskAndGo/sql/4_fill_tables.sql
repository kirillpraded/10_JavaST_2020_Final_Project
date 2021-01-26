INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('1', 'testUser1', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', '1');
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('2', 'testUser2', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', '1');
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('3', 'testUser3', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', '1');
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('4', 'testUser4', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', '1');


INSERT INTO `category` (`id`, `name`) VALUES ('1', 'Java');
INSERT INTO `category` (`id`, `parent_id`, `name`) VALUES ('2', '1', 'Collections');
INSERT INTO `category` (`id`, `parent_id`, `name`) VALUES ('3', '1', 'Multithreading');

INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('1', 'Collections', 'How many collections in java?', '2', '1', '0', '0');
INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('2', 'List', 'How many realizations of List in java?', '2', '2', '0', '0');
INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('3', 'Threads', 'How to create thread?', '3', '3', '0', '0');

INSERT INTO `tag` (`id`, `text`) VALUES ('1', 'collections');
INSERT INTO `tag` (`id`, `text`) VALUES ('2', 'list');
INSERT INTO `tag` (`id`, `text`) VALUES ('3', 'threads');

INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES ('1', '1');
INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES ('1', '2');
INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES ('2', '1');
INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES ('2', '2');
INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES ('3', '3');


INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('1', 'there are about 23 realizations', '2', '1', '0');
INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('2', 'about 8 realizations of set', '3', '2', '0');
INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('3', 'there are few ways, using Thread and Runnable', '1', '3', '0');
