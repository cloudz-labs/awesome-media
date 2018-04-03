-- accounts
DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  username varchar(255) NOT NULL,
  password varchar(255)
);

-- categories
DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  id varchar(255) NOT NULL,
  name varchar(255)
);

-- contents
DROP TABLE IF EXISTS contents;
CREATE TABLE contents (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category varchar(255),
  grade double NOT NULL,
  has_episodes boolean NOT NULL,
  poster varchar(255),
  rate varchar(255),
  reg_date varchar(255),
  runtime int(11) NOT NULL,
  stillcut varchar(255),
  summary varchar(255),
  title varchar(255),
  video varchar(255),
  view int(11) NOT NULL,
  year int(11) NOT NULL
);

-- episodes
DROP TABLE IF EXISTS episodes;
CREATE TABLE episodes (
  content varchar(255) NOT NULL,
  episode int(11) NOT NULL,
  season int(11) NOT NULL,
  poster varchar(255),
  reg_date varchar(255),
  runtime int(11) NOT NULL,
  summary varchar(255),
  title varchar(255),
  video varchar(255)
);

-- profiles
DROP TABLE IF EXISTS profiles;
CREATE TABLE profiles (
  id varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  name varchar(255)
);

-- promotions
DROP TABLE IF EXISTS promotions;
CREATE TABLE promotions (
  category varchar(255) NOT NULL,
  content int(11) NOT NULL
);