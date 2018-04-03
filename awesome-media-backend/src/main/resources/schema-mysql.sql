-- accounts
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) ,
  PRIMARY KEY (`username`)
);

-- categories
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) ,
  PRIMARY KEY (`id`)
);

-- contents
DROP TABLE IF EXISTS `contents`;
CREATE TABLE `contents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `grade` double NOT NULL,
  `has_episodes` bit(1) NOT NULL,
  `poster` varchar(255) DEFAULT NULL,
  `rate` varchar(255) DEFAULT NULL,
  `reg_date` varchar(255) DEFAULT NULL,
  `runtime` int(11) NOT NULL,
  `stillcut` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `view` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- episodes
DROP TABLE IF EXISTS `episodes`;
CREATE TABLE `episodes` (
  `content` varchar(255) NOT NULL,
  `episode` int(11) NOT NULL,
  `season` int(11) NOT NULL,
  `poster` varchar(255) DEFAULT NULL,
  `reg_date` varchar(255) DEFAULT NULL,
  `runtime` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`content`,`episode`,`season`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- profiles
DROP TABLE IF EXISTS `profiles`;
CREATE TABLE `profiles` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `name` varchar(255) ,
  PRIMARY KEY (`id`,`username`)
);

-- promotions
DROP TABLE IF EXISTS `promotions`;
CREATE TABLE `promotions` (
  `category` varchar(255) NOT NULL,
  `content` int(11) NOT NULL,
  PRIMARY KEY (`category`)
);