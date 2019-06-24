CREATE TABLE IF NOT EXISTS AUTH_USERS
(
  id SERIAL PRIMARY KEY,
  username varchar(35) UNIQUE NOT NULL,
  password varchar(128) NOT NULL,
  enabled boolean
);

CREATE TABLE IF NOT EXISTS USER_ROLES
(
  user_role_id SERIAL PRIMARY KEY,
  username  varchar(35) NOT NULL,
  role varchar(35) NOT NULL,
  UNIQUE (username, role),
  FOREIGN KEY (username) REFERENCES AUTH_USERS (username)
);

CREATE TABLE IF NOT EXISTS WALLET
(
  id SERIAL PRIMARY KEY,
  username varchar(35) NOT NULL,
  currency varchar(15) NOT NULL,
  amount real,
  UNIQUE(username,currency),
  FOREIGN KEY (username) REFERENCES AUTH_USERS (username)
);

INSERT INTO AUTH_USERS (username, password, enabled)
VALUES ('admin', '{noop}admin', true);

INSERT INTO USER_ROLES (username, role) VALUES ('admin', 'ADMIN');
INSERT INTO USER_ROLES (username, role) VALUES ('admin', 'USER');
