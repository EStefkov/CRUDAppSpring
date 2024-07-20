-- V1__Create_users_table.sql
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(30) NOT NULL,
                       last_name VARCHAR(30) NOT NULL,
                       number VARCHAR(25) NOT NULL,
                       mail_address VARCHAR(255) NOT NULL,
                       birth_date DATE NOT NULL
);
