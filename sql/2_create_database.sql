CREATE DATABASE `serials_db`;

CREATE USER 'application'@'localhost' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON `serials_db`.*
    TO 'application'@'localhost'