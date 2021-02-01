CREATE DATABASE `askandgo_db` DEFAULT CHARACTER SET utf8;

CREATE USER 'person'@'localhost' IDENTIFIED BY 'kirillpraded';
GRANT SELECT,INSERT,UPDATE,DELETE
    ON askandgo_db.*
    TO 'CREATE USER ''person''@''localhost'' IDENTIFIED BY ''kirillpraded'';
GRANT SELECT,INSERT,UPDATE,DELETE
ON askandgo_db.*
TO ''person''@''localhost'';

GRANT SELECT,INSERT,UPDATE,DELETE
ON askandgo_db.*
TO ''person''@''localhost'';

FLUSH PRIVILEGES;'@'localhost';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON askandgo_db.*
    TO 'person'@'localhost';

FLUSH PRIVILEGES;