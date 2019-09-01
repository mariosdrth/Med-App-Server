/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure and data.
 */

/*
 * Structure
 */

CREATE database IF NOT EXISTS gdpr;
use gdpr;

CREATE TABLE IF NOT EXISTS patients(
n_id INT(10) NOT NULL AUTO_INCREMENT,
v_name VARBINARY(255) NOT NULL,
v_surname VARBINARY(255) NOT NULL,
v_father_name VARBINARY(255) NULL,
v_mother_name VARBINARY(255) NULL,
n_sex INT(1) NOT NULL CHECK(1 or 2),
v_afm VARBINARY(255) NOT NULL,
v_amka VARBINARY(255) NULL,
v_birth_date VARBINARY(255) NULL,
PRIMARY KEY (n_id)
);

ALTER TABLE patients
ADD UNIQUE INDEX ix_afm (v_afm);

CREATE TABLE IF NOT EXISTS users(
n_id INT(10) NOT NULL AUTO_INCREMENT,
v_username VARBINARY(255) NOT NULL,
v_password VARBINARY(255) NOT NULL,
v_name VARBINARY(255) NULL,
v_surname VARBINARY(255) NULL,
v_email VARBINARY(255) NULL,
n_user_role_id INT(10) NOT NULL,
PRIMARY KEY (n_id)
);

ALTER TABLE users
ADD UNIQUE INDEX ix_username (v_username);

CREATE TABLE IF NOT EXISTS user_roles(
n_id INT(10) NOT NULL AUTO_INCREMENT,
v_description VARBINARY(255) NOT NULL,
PRIMARY KEY (n_id)
);

ALTER TABLE user_roles
ADD UNIQUE INDEX ix_description (v_description);

ALTER TABLE users
ADD CONSTRAINT fk_user_role FOREIGN KEY (n_user_role_id) REFERENCES user_roles(n_id);

INSERT INTO user_roles (v_description)
VALUES (AES_ENCRYPT('admin', UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')));

INSERT INTO users (v_username, v_password, n_user_role_id)
VALUES (AES_ENCRYPT('admin', UNHEX('F423AB67C4FAB98FE45B61F06B4D7DE2')), AES_ENCRYPT('!Adm1NqWe@#', UNHEX
('F423AB67C4FAB98FE45B61F06B4D7DE2')), 1);

ALTER TABLE patients
ADD COLUMN v_tel VARBINARY(255) NULL,
ADD COLUMN v_cell VARBINARY(255) NULL,
ADD COLUMN v_email VARBINARY(255) NULL,
ADD COLUMN v_address VARBINARY(255) NULL,
ADD COLUMN v_comments VARBINARY(255) NULL;

ALTER TABLE users
ADD COLUMN v_tel VARBINARY(255) NULL;

CREATE TABLE IF NOT EXISTS sessions(
n_id INT(10) NOT NULL AUTO_INCREMENT,
n_patient_id INT(10) NOT NULL,
v_session_date VARBINARY(255) NOT NULL,
v_session_id VARBINARY(255) NULL,
v_comments VARBINARY(255) NULL,
v_patient_name VARBINARY(255) NOT NULL,
PRIMARY KEY (n_id),
CONSTRAINT fk_session_patient FOREIGN KEY (n_patient_id) REFERENCES patients(n_id)
);

CREATE TABLE IF NOT EXISTS connections(
n_id INT(10) NOT NULL AUTO_INCREMENT,
v_ip VARBINARY(255) NOT NULL,
v_con_date VARBINARY(255) NOT NULL,
v_country VARBINARY(255) NULL,
v_is_mobile VARBINARY(255) NULL,
v_coordinates VARBINARY(255) NULL,
PRIMARY KEY (n_id)
);