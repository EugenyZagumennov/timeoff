CREATE DATABASE `timeoff` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE  timeoff;

CREATE TABLE `users` (
  `uuid` binary(16) NOT NULL,
  `login` varchar(100) NOT NULL,
  `name` varchar(200) NOT NULL,
  `regdate` timestamp NOT NULL,
  `password` binary(16) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;