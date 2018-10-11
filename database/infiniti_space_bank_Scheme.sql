
DROP SCHEMA IF EXISTS `infiniti_space_bank`;

CREATE SCHEMA `infiniti_space_bank`;

use `infiniti_space_bank`;

SET FOREIGN_KEY_CHECKS = 0;

-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: infiniti_space_bank
-- ------------------------------------------------------
-- Server version	5.6.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `dob` DATE DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` DOUBLE(16,4) DEFAULT NULL, 
  `customer_id` int(11) DEFAULT NULL,
 
  PRIMARY KEY (`id`),
  
  CONSTRAINT `FK_CUSTOMER` 
  FOREIGN KEY (`customer_id`) 
  REFERENCES `customer` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Table structure for table `transaction_`
--
DROP TABLE IF EXISTS `transaction_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_tab` (
  `id` BINARY(16) NOT NULL,
  `transaction_type` varchar(45) DEFAULT NULL,
  `amount` DOUBLE(16,4) DEFAULT NULL, 
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remaining` DOUBLE(16,4) DEFAULT NULL, 
  `account_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  CONSTRAINT `FK_account` 
  FOREIGN KEY (`account_id`) 
  REFERENCES `account` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

  
LOCK TABLES `customer` WRITE;

INSERT INTO `customer` VALUES 
	(1,'David','Adams','david@gmail.com','1155 guy st','Montreal', 'Canada', '438-924-8529','1984-02-14'),
	(2,'John','Doe','john@gmail.com','1155 guy st','Montreal','Canada', '438-924-8529', '1984-02-14'),
	(3,'Ajay','Rao','ajay@gmail.com','1155 guy st','Montreal','Canada', '438-924-8529', '1984-02-14');

UNLOCK TABLES;

LOCK TABLES `account` WRITE;

INSERT INTO `account` VALUES 
	(1,100,1),
	(2,100,2),
	(3,100,3);

UNLOCK TABLES;

SET FOREIGN_KEY_CHECKS = 1;