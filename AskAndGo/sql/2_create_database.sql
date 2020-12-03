CREATE DATABASE `askandgo_db` DEFAULT CHARACTER SET utf8;

CREATE USER 'kirill'@'localhost' IDENTIFIED BY 'kirillpraded';
GRANT SELECT,INSERT,UPDATE,DELETE
ON askandgo_db.*
TO 'kirill'@'localhost';

GRANT SELECT,INSERT,UPDATE,DELETE
ON askandgo_db.*
TO 'kirill'@'%';

FLUSH PRIVILEGES;