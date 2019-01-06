CREATE DATABASE `timeoff` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE  timeoff;

CREATE TABLE `departments` (
  `uuid` binary(16) NOT NULL,
  `createDate` datetime(6) NOT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tasks` (
  `uuid` binary(16) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `string_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `timerecord` (
  `uuid` binary(16) NOT NULL,
  `hours` double NOT NULL,
  `timestamp` bigint(20) NOT NULL,
  `tasks_uuid` binary(16) NOT NULL,
  `users_uuid` binary(16) NOT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKk27aejroogrmual5s4hrujkbq` (`tasks_uuid`),
  KEY `FK1nf2pfqos01r31k3437le6md2` (`users_uuid`),
  CONSTRAINT `FK1nf2pfqos01r31k3437le6md2` FOREIGN KEY (`users_uuid`) REFERENCES `users` (`uuid`),
  CONSTRAINT `FKk27aejroogrmual5s4hrujkbq` FOREIGN KEY (`tasks_uuid`) REFERENCES `tasks` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `uuid` binary(16) NOT NULL,
  `login` varchar(100) NOT NULL,
  `name` varchar(200) NOT NULL,
  `password` tinyblob NOT NULL,
  `regDate` datetime(6) NOT NULL,
  `departments_uuid` binary(16) NOT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKp5561swmtji614bx6vhk1yp6i` (`departments_uuid`),
  CONSTRAINT `FKp5561swmtji614bx6vhk1yp6i` FOREIGN KEY (`departments_uuid`) REFERENCES `departments` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;