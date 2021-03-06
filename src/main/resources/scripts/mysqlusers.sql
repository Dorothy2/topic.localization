DROP DATABASE IF EXISTS db_example;
DROP USER IF EXISTS `dbadmin`@`%`;
DROP USER IF EXISTS `dbuser`@`%`;
CREATE DATABASE IF NOT EXISTS db_example CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `dbadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `db_example`.* TO `dbadmin`@`%`;
CREATE USER IF NOT EXISTS `bookuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `db_example`.* TO `dbuser`@`%`;
FLUSH PRIVILEGES;