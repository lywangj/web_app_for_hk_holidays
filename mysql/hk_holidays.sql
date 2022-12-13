CREATE DATABASE hk_holidays;

USE `hk_holidays`;

CREATE TABLE `holidays` (
  `holiday_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(45) NOT NULL,
  `dtstart` varchar(45) NOT NULL,
  `dtend` varchar(45) NOT NULL,
  `summary` varchar(80) NOT NULL,
  PRIMARY KEY (`holiday_id`)
);