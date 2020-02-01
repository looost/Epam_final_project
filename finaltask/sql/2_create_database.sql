CREATE DATABASE `serials_db`;

CREATE USER 'serials_user'@'localhost' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON `serials_db`.*
    TO 'serials_user'@'localhost'