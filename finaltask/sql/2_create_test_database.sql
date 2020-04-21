CREATE DATABASE `test_serials_db`;

CREATE USER 'testApplication'@'localhost' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON `test_serials_db`.*
    TO 'testApplication'@'localhost'