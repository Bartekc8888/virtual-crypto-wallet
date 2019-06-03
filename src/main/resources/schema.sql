CREATE TABLE IF NOT EXISTS USERS
(
  id int not null auto_increment unique,
  username varchar(35) unique,
  password varchar(65),
  firstName varchar(35),
  role varchar(35),
  enabled boolean
);

CREATE TABLE IF NOT EXISTS AUTHORITIES
(
  username  varchar(35),
  authority varchar(35)
)