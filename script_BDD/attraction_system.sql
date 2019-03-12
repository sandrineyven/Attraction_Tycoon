-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 22 fév. 2019 à 18:53
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `attraction_system`
--

-- --------------------------------------------------------

--
-- Structure de la table `carousel`
--

DROP TABLE IF EXISTS `carousel`;
CREATE TABLE IF NOT EXISTS `carousel` (
  `id_carousel` int(11) NOT NULL  AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `capacity_max` int(11),
  `id_zone` int(11),
  `is_open` char(1),
  `number_acces` int(11),
  `duration` int(11),
  `waiting_time` time,
  `state` varchar(255),
  PRIMARY KEY (`id_carousel`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `shop`
--

DROP TABLE IF EXISTS `shop`;
CREATE TABLE IF NOT EXISTS `shop` (
    `id_shop` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `id_zone` int(11) NOT NULL,

  PRIMARY KEY (`id_shop`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `id_staff` int(10) NOT NULL AUTO_INCREMENT,
  `id_user` int(10) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `labor_hours` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id_staff`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `staff_caroussel`
--

DROP TABLE IF EXISTS `staff_carousel`;
CREATE TABLE IF NOT EXISTS `staff_carousel` (
  `id_staff` int(11) NOT NULL,
  `id_carousel` int(11) NOT NULL,
  `date` date NOT NULL,
  `hour_begining` time NOT NULL,
  `hour_ending` time NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `staff_shop`
--

DROP TABLE IF EXISTS `staff_shop`;
CREATE TABLE IF NOT EXISTS `staff_shop` (
  `id_shop` int(11) NOT NULL,
  `id_staff` int(11) NOT NULL,
  `date` date NOT NULL,
  `hour_begining` time NOT NULL,
  `hour_ending` time NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
	`id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `zone`
--

DROP TABLE IF EXISTS `zone`;
CREATE TABLE IF NOT EXISTS `zone` (
  `id_zone` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id_zone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- --------------------------------------------------------

--
-- Insertion de donnees dans la table `zone`
--
INSERT INTO zone VALUES (1,"Entree");
INSERT INTO zone VALUES (2,"Ouest");
INSERT INTO zone VALUES (3,"Centre");
INSERT INTO zone VALUES (4,"Est");
INSERT INTO zone VALUES (5,"Nord");

INSERT INTO user (email, password, login) VALUES ("sandrine.yven@gmail.com", "coucou", "Sandrine Yven");
INSERT INTO user (email, password, login) VALUES ("alexandre.lebegue@edu.esiee.fr", "password", "Alexandre Lebegue");
INSERT INTO user (email, password, login) VALUES ("test@test.ca", "test", "Test");
INSERT INTO user (email, password, login) VALUES ("e.sanchez@test.ca", "test", "Emma C. Sanchez");
INSERT INTO user (email, password, login) VALUES ("l.snow@test.ca", "test", "Leonardo D. Snow");

INSERT INTO staff (id_user,name,salary,labor_hours,type,status) VALUES (1,"Sandrine Yven", 210,35,"Administrateur","A");
INSERT INTO staff (id_user,name,salary,labor_hours,type,status) VALUES (2,"Alexandre Lebegue", 210,35,"Administrateur","A");
INSERT INTO staff (id_user,name,salary,labor_hours,type,status) VALUES (4,"Emma C. Sanchez", 30,32,"Seller","A");
INSERT INTO staff (id_user,name,salary,labor_hours,type,status) VALUES (5,"Leonardo D. Snow", 25,20,"Cleaner","A");

INSERT INTO shop (name,type,id_zone) VALUES ("Hotdog", "Stand",1);
INSERT INTO shop (name,type,id_zone) VALUES ("Le Grill", "Restauration",2);
INSERT INTO shop (name,type,id_zone) VALUES ("Sunday", "Souvenirs",2);
INSERT INTO shop (name,type,id_zone) VALUES ("Glaces", "Stand",3);
INSERT INTO shop (name,type,id_zone) VALUES ("Drinks", "Stand",5);

INSERT INTO carousel (id_carousel, name, type, capacity_max, id_zone, is_open, number_acces, duration, waiting_time, state) VALUES
(1, 'Project Euromir', 'Sensations', 15, 5, '1', 0, '00:03:00', '00:06:00', 'A');