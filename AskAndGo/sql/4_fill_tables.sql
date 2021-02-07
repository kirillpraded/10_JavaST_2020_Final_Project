INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('1', 'admin01', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', '0');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('2', 'admin02', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'evgeniy', 'alanov', 'evgeny@bk.ru', '0');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('3', 'admin03', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'den', 'azarkin', 'den@bk.ru', '0');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('4', 'moder01', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'nikita', 'korol', 'nikita@bk.ru', '1');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('5', 'user01', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'dima', 'maslow', 'dima@bk.ru', '2');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('6', 'user02', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'dmitry', 'shatalov', 'shatalov@bk.ru', '2');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('7', 'user03', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'alex', 'marvin', 'marvin@bk.ru', '2');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('8', 'user04', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'sasha', 'kotov', 'kotov@bk.ru', '2');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('9', 'user05', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'artem', 'dimchenko', 'dimch@bk.ru', '2');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('10', 'user06', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'anatoly', 'lombax', 'lombax@bk.ru', '3');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('11', 'user07', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'alexey', 'club', 'alexeyclub@bk.ru', '2');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('12', 'user08', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'mike', 'vasin', 'vasin@bk.ru', '3');
INSERT INTO `askandgo_db`.`user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES ('13', 'user09', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'roman', 'urov', 'romurov@bk.ru', '3');
/*
 пароль у всех пользователей  - password
 */

INSERT INTO `askandgo_db`.`category` (`id`, `name`) VALUES ('1', 'Java');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('2', '1', 'Java SE');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('3', '1', 'Java EE');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('4', '2', 'Collections');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('5', '2', 'Concurrency');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('6', '2', 'Memory');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('7', '3', 'Servlets');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('8', '3', 'JSP');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('9', '3', 'HTTP protocol with Java EE');
INSERT INTO `askandgo_db`.`category` (`id`, `name`) VALUES ('10', 'C++');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('11', '10', 'For begginers');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('12', '10', 'WinAPI');
INSERT INTO `askandgo_db`.`category` (`id`, `name`) VALUES ('13', 'C#');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('14', '13', '.NET');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('15', '13', 'Windows Forms');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('16', '13', 'LINQ');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('17', '13', 'Basics');
INSERT INTO `askandgo_db`.`category` (`id`, `name`) VALUES ('18', 'Python');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('19', '18', 'For begginers');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('20', '18', 'Django');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('21', '18', 'Graphics');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('22', '18', 'Flask');
INSERT INTO `askandgo_db`.`category` (`id`, `parent_id`, `name`) VALUES ('23', '18', 'PyGame');


INSERT INTO `askandgo_db`.`question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('1', 'ConcurrentHashMap', 'Can anyone explain how the ConcurrentHashMap works', '4', '5', '0', '0');
INSERT INTO `askandgo_db`.`question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('2', 'Interface Collection', 'which collections implement the interface Collection?', '4', '6', '1', '1');
INSERT INTO `askandgo_db`.`question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('3', 'Lock in java.util.concurrent', 'How works lock in Java?', '5', '7', '0', '1');
INSERT INTO `askandgo_db`.`question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('4', 'Request and Response', 'What is HttpServletRequest and HttpServletResponse?', '7', '8', '0', '0');
INSERT INTO `askandgo_db`.`question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('5', 'Filters', 'What are servlet filters for?', '7', '7', '1', '1');
INSERT INTO `askandgo_db`.`question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES ('6', 'Memory model', 'what memory areas are there in java?', '6', '10', '1', '1');



INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('1', 'java');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('2', 'memory');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('3', 'model');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('5', 'concurrency');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('6', 'heap');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('7', 'stack');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('8', 'locks');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('9', 'reentrantlock');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('10', 'request');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('11', 'response');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('12', 'filters');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('13', 'http');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('14', 'concurrenthashmap');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('15', 'collection');
INSERT INTO `askandgo_db`.`tag` (`id`, `text`) VALUES ('16', 'servlets');


INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('1', '1');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('1', '5');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('1', '14');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('1', '15');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('2', '1');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('2', '15');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('3', '5');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('3', '8');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('3', '9');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('4', '13');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('4', '1');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('4', '10');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('4', '11');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('2', '2');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('4', '16');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('5', '1');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('5', '10');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('5', '11');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('5', '13');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('5', '16');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('6', '1');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('6', '2');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('6', '3');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('6', '6');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('6', '7');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('6', '15');
INSERT INTO `askandgo_db`.`question_tag` (`question_id`, `tag_id`) VALUES ('5', '12');


INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('1', 'For your needs, use ConcurrentHashMap. It allows concurrent modification of the Map from several threads without the need to block them. Collections.synchronizedMap(map) creates a blocking Map which will degrade performance, albeit ensure consistency (if used properly).', '6', '1', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('2', 'The scalability issues for Hashtable are present in exactly the same way in Collections.synchronizedMap(Map) - they use very simple synchronization, which means that only one thread can access the map at the same time.', '7', '1', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('3', 'ConcurrentHashMap is preferred when you can use it - though it requires at least Java 5.', '8', '1', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('4', 'The Collection interface is the foundation upon which the collections framework is built. It declares the core methods that all collections will have. These methods are summarized in the following table.', '8', '2', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('5', 'Set List Queue', '7', '2', '1');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('6', 'you can check it yourself by opening the Collections interface and pressing CTRL + H', '5', '2', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('7', 'Locks hepls to make your code thread-safe, using methods lock() and unlock', '5', '3', '1');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('8', 'javax.servlet.http.HttpServletResponse Is specific to an HTTP protocol. Additional convenience methods provide support for: Cookie manipulation . HTTP headers and status codes URL rewriting used for session management. javax.servlet.http.HttpServletRequest  Is specific to an HTTP protocol. HttpServletRequest extends ServletRequest Provides additional useful methods related HTTP requests.', '10', '4', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('9', 'You may use them for filtering requests, as u wish.', '9', '5', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('10', 'Фильтры в Java позволяют вмешатся в обработку запроса как до передачи запроса в jsp-страницу, так и после. ', '11', '5', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('11', 'You can make your application secured', '12', '5', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('12', 'filters serve to process requests before they get into the servlet', '13', '5', '1');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('13', 'Depends on java version!', '11', '6', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('14', 'in java 1.7 - Heap and Stack', '12', '6', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('15', 'but what concurnes java 8 - Stack. Heap. MetaSpace', '13', '6', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('16', 'only three in java 1.8.', '9', '6', '0');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('17', 'There are three areas:Heap, Stack and Metaspace. Heap keeps all objects. It\'s much more than stack. Every thread has it\'s own stack. Stack is LIFO. it keeps methods, links, local primitive variables. Metaspace keeps all metainfo about classes.', '8', '6', '1');
INSERT INTO `askandgo_db`.`answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES ('18', 'you may ask google!', '7', '6', '0');
