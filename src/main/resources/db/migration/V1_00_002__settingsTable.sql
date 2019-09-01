use gdpr;

CREATE TABLE IF NOT EXISTS settings(
n_id INT(10) NOT NULL AUTO_INCREMENT,
n_user_id INT(10) NOT NULL,
n_alt_view INT(1) NULL,
n_linear INT(1) NULL,
n_open_on_click INT(1) NULL,
v_header_color VARCHAR(255) NULL,
v_theme_color VARCHAR(255) NULL,
v_side_color VARCHAR(255) NULL,
n_theme INT(1) NULL,
PRIMARY KEY (n_id)
);

ALTER TABLE settings
ADD CONSTRAINT fk_user FOREIGN KEY (n_user_id) REFERENCES users(n_id);

ALTER TABLE settings
ADD UNIQUE INDEX ix_user_id (n_user_id);