CREATE DATABASE  IF NOT EXISTS `tesis` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tesis`;
-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: tesis
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `configuraciones`
--

DROP TABLE IF EXISTS `configuraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuraciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `engine` varchar(30) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuraciones`
--

LOCK TABLES `configuraciones` WRITE;
/*!40000 ALTER TABLE `configuraciones` DISABLE KEYS */;
INSERT INTO `configuraciones` VALUES (13,10,'wizepost_active','jdbc:mysql://127.0.0.1:3306/wizepost_active','wizepost_active.sql',NULL,'2015-09-01 19:37:43'),(31,10,'atla_db','jdbc:mysql://127.0.0.1:3306/atla_db','atla_db.sql',NULL,'2015-09-02 00:12:41'),(45,10,'sakila','jdbc:mysql://127.0.0.1:3306/sakila','sakila-data.sql',NULL,'2015-09-14 19:01:01'),(46,1,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-09-14 19:01:22'),(47,10,'employees','jdbc:mysql://127.0.0.1:3306/employees','employees',NULL,'2015-09-26 19:01:22'),(48,2,'world','jdbc:mysql://127.0.0.1:3306/employees','world.sql',NULL,'2015-10-04 14:25:43'),(56,3,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-04 19:23:07'),(58,1,'employees','jdbc:mysql://127.0.0.1:3306/employees','employees.sql',NULL,'2015-10-09 02:00:35'),(59,2,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35'),(60,3,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35'),(61,4,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35'),(62,5,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35'),(63,6,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35'),(64,7,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35'),(65,8,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35'),(66,9,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35'),(67,10,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-10-09 02:00:35');
/*!40000 ALTER TABLE `configuraciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultas`
--

DROP TABLE IF EXISTS `consultas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `query` text,
  `time` double DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idconfig` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultas`
--

LOCK TABLES `consultas` WRITE;
/*!40000 ALTER TABLE `consultas` DISABLE KEYS */;
INSERT INTO `consultas` VALUES (1,'SELECT * FROM City;',0.002351,10,67,'2015-10-09 18:43:31'),(2,'SELECT * FROM City;',0.002697,10,67,'2015-10-09 18:52:19'),(3,'SELECT * FROM City;',0.002666,10,67,'2015-10-09 18:54:29'),(4,'SELECT * FROM City;',0.002746,10,67,'2015-10-09 18:59:23'),(5,'SELECT * FROM City;',0.004389,10,67,'2015-10-09 19:01:04'),(6,'SELECT * FROM Country inner join CountryLanguage on Country.Code = CountryLanguage.CountryCode;',0.003099,10,67,'2015-10-09 19:02:48'),(7,'SELECT * FROM City;',0.003705,10,67,'2015-10-09 19:03:17'),(8,'SELECT ID, Name, CountryCode, District, Population FROM City;',0.003676,10,67,'2015-10-09 19:04:25'),(9,'SELECT * FROM City;',0.002608,10,67,'2015-10-09 19:08:38'),(10,'SELECT * FROM City;',0.002693,10,67,'2015-10-09 19:10:25'),(11,'SELECT ID, Name, CountryCode, District, Population FROM City;',0.003416,10,67,'2015-10-09 19:12:01'),(12,'SELECT ID, Name, CountryCode, District, Population FROM City;',0.002084,1,67,'2015-10-09 19:13:21'),(13,' select * from City',0.002857,1,46,'2015-10-09 19:14:04'),(14,'SELECT * FROM City;',0.001544,1,46,'2015-10-09 19:15:10'),(15,'SELECT * FROM City;',0.003795,1,46,'2015-10-09 19:16:29'),(16,'select ID, Name, CountryCode, District, Population from City; ',0.005049,1,46,'2015-10-09 19:17:16'),(17,'SELECT * FROM City;',0.030319,1,46,'2015-10-09 19:50:31'),(18,'SELECT * FROM City;',0.001539,1,46,'2015-10-09 19:56:00'),(19,'SELECT * FROM City;',0.002,1,46,'2015-10-09 20:04:43'),(20,'SELECT * FROM City;',0.001957,1,46,'2015-10-09 20:06:20'),(21,'SELECT * FROM City;',0.001748,1,46,'2015-10-09 20:08:02'),(22,'SELECT * FROM City;',0.001869,1,46,'2015-10-09 20:09:37'),(23,'SELECT * FROM City;',0.002604,1,46,'2015-10-09 20:12:34'),(24,'SELECT * FROM City;',0.00275,1,46,'2015-10-09 20:15:22'),(25,'SELECT * FROM City;',0.003532,1,46,'2015-10-09 20:19:09'),(26,'select ID, Name, CountryCode, District, Population from City;',0.002877,1,46,'2015-10-09 20:20:47'),(27,'select ID, Name, CountryCode, District, Population from City;',0.002654,1,46,'2015-10-09 20:23:05'),(28,'select ID, Name, CountryCode, District, Population from City;',0.003632,1,46,'2015-10-09 20:24:19'),(29,'select ID, Name, CountryCode, District, Population from City;',0.003132,1,46,'2015-10-09 20:25:39'),(30,'SELECT * FROM City;',0.050604,1,46,'2015-10-16 11:11:08');
/*!40000 ALTER TABLE `consultas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `query` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'select * from %table%','2015-10-01 00:00:00'),(2,'select %keyfields% from %table%;','2015-10-01 00:00:00'),(3,'select * from %table% where %conditions%;','2015-10-01 00:00:00'),(4,'select %keyfields% from %table% where %conditions%;','2015-10-01 00:00:00'),(5,'select * from %table% inner join %table2% on %conditions% where %conditions%;','2015-10-01 00:00:00'),(6,'select %keyfields% from %table% inner join %table2% on %table.id = table2.id%;','2015-10-01 00:00:00'),(7,'select %keyfields% from %table% inner join %table2% on %table.id = table2.id% where %conditions%;','2015-10-01 00:00:00'),(8,'select * from %table% inner join %table2% on %conditions%;','2015-10-01 00:00:00'),(9,'select * from %table% where % select * from% ;','2015-10-01 00:00:00'),(12,'select * from %table% group by %conditions%;','2015-10-01 00:00:00'),(13,'select %keyfields% from %table% group by %conditions%;','2015-10-01 00:00:00'),(14,'select * from %table% left join %table2% on (%table.id% = %table2.id%);','2015-10-01 00:00:00'),(15,'select * from %table% left join (%table2% cross join %table3%) on (%table2.id% = %table.id% and %table3.id% = %table.id)','2015-10-01 00:00:00'),(16,'select %keyfields% from %table% left join %table2% on (%table.id% = %table2.id%);','2015-10-01 00:00:00'),(17,'select * from %table% right join %table2% on (%table.id% = %table2.id%);','2015-10-01 00:00:00'),(18,'select %keyfields% from %table% right join %table2% on (%table.id% = %table2.id%);','2015-10-01 00:00:00'),(19,'select %keyfields% from %table% inner join %table2% on %table.id = table2.id% where %conditions% group by %conditions%','2015-10-01 00:00:00'),(20,'select * from %table% inner join %table2% on %table.id = table2.id% where %conditions% group by %conditions%',NULL);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rankings`
--

DROP TABLE IF EXISTS `rankings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rankings` (
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `time` double DEFAULT NULL,
  `ranking` float NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rankings`
--

LOCK TABLES `rankings` WRITE;
/*!40000 ALTER TABLE `rankings` DISABLE KEYS */;
INSERT INTO `rankings` VALUES (1,1,0.006405799999999999,1,'2015-10-16 11:11:08'),(2,1,0.002955,1,'2015-10-16 11:11:08'),(2,2,0.002709,5,'2015-10-16 11:11:08'),(3,1,0.002527,5,'2015-10-16 11:11:08'),(3,2,0.005608,1,'2015-10-16 11:11:08'),(4,1,0.002653,1,'2015-10-16 11:11:08'),(4,2,0.002495,5,'2015-10-16 11:11:08'),(5,1,0.001904,5,'2015-10-16 11:11:08'),(5,2,0.002539,1,'2015-10-16 11:11:08'),(6,1,0.004798,1,'2015-10-16 11:11:08'),(6,2,0.002118,5,'2015-10-16 11:11:08'),(7,1,0.002689,1,'2015-10-16 11:11:08'),(7,2,0.001547,5,'2015-10-16 11:11:08'),(8,1,0.002562,5,'2015-10-16 11:11:08'),(8,2,0.002578,1,'2015-10-16 11:11:08'),(9,1,0.002546,5,'2015-10-16 11:11:08'),(9,2,0.00335,1,'2015-10-16 11:11:08'),(10,1,0.002578,1,'2015-10-16 11:11:08'),(10,2,0.001581,5,'2015-10-16 11:11:08'),(11,1,0.004353,1,'2015-10-16 11:11:08'),(11,2,0.001877,5,'2015-10-16 11:11:08'),(12,1,0.002388,5,'2015-10-16 11:11:08'),(12,2,0.002705,1,'2015-10-16 11:11:08'),(13,1,0.002398,1,'2015-10-16 11:11:08'),(13,2,0.001747,5,'2015-10-16 11:11:08'),(14,1,0.003708,1,'2015-10-16 11:11:08'),(14,2,0.002894,5,'2015-10-16 11:11:08'),(15,1,0.006208,1,'2015-10-16 11:11:08'),(15,2,0.001536,5,'2015-10-16 11:11:08'),(16,1,0.00264,5,'2015-10-16 11:11:08'),(16,2,0.004799,1,'2015-10-16 11:11:08'),(17,1,0.002118,1,'2015-10-16 11:11:08'),(17,2,0.001753,5,'2015-10-16 11:11:08'),(18,1,0.004777,1,'2015-10-16 11:11:08'),(18,2,0.002405,5,'2015-10-16 11:11:08'),(19,1,0.00472,1,'2015-10-16 11:11:08'),(19,2,0.002469,5,'2015-10-16 11:11:08'),(20,1,0.002802,5,'2015-10-16 11:11:08'),(20,2,0.002894,1,'2015-10-16 11:11:08'),(21,1,0.001764,5,'2015-10-16 11:11:08'),(21,2,0.002259,1,'2015-10-16 11:11:08'),(22,1,0.004038,1,'2015-10-16 11:11:08'),(22,2,0.002924,5,'2015-10-16 11:11:08'),(23,1,0.002566,1,'2015-10-16 11:11:08'),(23,2,0.002563,5,'2015-10-16 11:11:08'),(24,1,0.004998,1,'2015-10-16 11:11:08'),(24,2,0.003625,5,'2015-10-16 11:11:08'),(25,1,0.00196,5,'2015-10-16 11:11:08'),(25,2,0.002786,1,'2015-10-16 11:11:08'),(26,1,0.003475,1,'2015-10-16 11:11:08'),(26,2,0.003143,5,'2015-10-16 11:11:08'),(27,1,0.003909,1,'2015-10-16 11:11:08'),(27,2,0.002904,5,'2015-10-16 11:11:08'),(28,1,0.00257,5,'2015-10-16 11:11:08'),(28,2,0.002865,1,'2015-10-16 11:11:08'),(29,1,0.001915,5,'2015-10-16 11:11:08'),(29,2,0.002785,1,'2015-10-16 11:11:08'),(30,1,0.002586,1,'2015-10-16 11:11:08'),(30,2,0.002349,5,'2015-10-16 11:11:08'),(31,1,0.002566,5,'2015-10-16 11:11:08'),(31,2,0.002905,1,'2015-10-16 11:11:08'),(32,1,0.002137,5,'2015-10-16 11:11:08'),(32,2,0.002363,1,'2015-10-16 11:11:08'),(33,1,0.003255,1,'2015-10-16 11:11:08'),(33,2,0.002617,5,'2015-10-16 11:11:08'),(34,1,0.004519,1,'2015-10-16 11:11:08'),(34,2,0.002949,5,'2015-10-16 11:11:08'),(35,1,0.003197,1,'2015-10-16 11:11:08'),(35,2,0.001726,5,'2015-10-16 11:11:08'),(36,1,0.001586,1,'2015-10-16 11:11:08'),(36,2,0.001517,5,'2015-10-16 11:11:08'),(37,1,0.002631,5,'2015-10-16 11:11:08'),(37,2,0.003561,1,'2015-10-16 11:11:08'),(38,1,0.006119,1,'2015-10-16 11:11:08'),(38,2,0.001503,5,'2015-10-16 11:11:08'),(39,1,0.00573,1,'2015-10-16 11:11:08'),(39,2,0.002378,5,'2015-10-16 11:11:08'),(40,1,0.002381,5,'2015-10-16 11:11:08'),(40,2,0.005344,1,'2015-10-16 11:11:08'),(41,1,0.002527,5,'2015-10-16 11:11:08'),(41,2,0.002888,1,'2015-10-16 11:11:08'),(42,1,0.0037,1,'2015-10-16 11:11:08'),(42,2,0.001876,5,'2015-10-16 11:11:08'),(43,1,0.004779,1,'2015-10-16 11:11:08'),(43,2,0.002471,5,'2015-10-16 11:11:08'),(44,1,0.002285,5,'2015-10-16 11:11:08'),(44,2,0.002598,1,'2015-10-16 11:11:08'),(45,1,0.002633,1,'2015-10-16 11:11:08'),(45,2,0.002448,5,'2015-10-16 11:11:08'),(46,1,0.003905,1,'2015-10-16 11:11:08'),(46,2,0.002951,5,'2015-10-16 11:11:08'),(47,1,0.007657,1,'2015-10-16 11:11:08'),(47,2,0.0018,5,'2015-10-16 11:11:08'),(48,1,0.002857,1,'2015-10-16 11:11:08'),(48,2,0.002501,5,'2015-10-16 11:11:08'),(49,1,0.001764,5,'2015-10-16 11:11:08'),(49,2,0.00442,1,'2015-10-16 11:11:08'),(50,1,0.003677,1,'2015-10-16 11:11:08'),(50,2,0.002408,5,'2015-10-16 11:11:08'),(51,1,0.004234,1,'2015-10-16 11:11:08'),(51,2,0.001552,5,'2015-10-16 11:11:08'),(52,1,0.002711,5,'2015-10-16 11:11:08'),(52,2,0.004377,1,'2015-10-16 11:11:08'),(53,1,0.004041,1,'2015-10-16 11:11:08'),(53,2,0.002428,5,'2015-10-16 11:11:08'),(54,1,0.004555,1,'2015-10-16 11:11:08'),(54,2,0.003109,5,'2015-10-16 11:11:08'),(55,1,0.001981,5,'2015-10-16 11:11:08'),(55,2,0.007133,1,'2015-10-16 11:11:08'),(56,1,0.005075,1,'2015-10-16 11:11:08'),(56,2,0.001963,5,'2015-10-16 11:11:08'),(57,1,0.004484,1,'2015-10-16 11:11:08'),(57,2,0.001509,5,'2015-10-16 11:11:08'),(58,1,0.004873,1,'2015-10-16 11:11:08'),(58,2,0.002545,5,'2015-10-16 11:11:08'),(59,1,0.002769,5,'2015-10-16 11:11:08'),(59,2,0.00392,1,'2015-10-16 11:11:08'),(60,1,0.00327,1,'2015-10-16 11:11:08'),(60,2,0.002029,5,'2015-10-16 11:11:08'),(61,1,0.00413,1,'2015-10-16 11:11:08'),(61,2,0.003287,5,'2015-10-16 11:11:08'),(62,1,0.003358,5,'2015-10-16 11:11:08'),(62,2,0.005396,1,'2015-10-16 11:11:08'),(63,1,0.003796,1,'2015-10-16 11:11:08'),(63,2,0.002371,5,'2015-10-16 11:11:08'),(64,1,0.006659,1,'2015-10-16 11:11:08'),(64,2,0.002955,5,'2015-10-16 11:11:08'),(65,1,0.004897,1,'2015-10-16 11:11:08'),(65,2,0.003145,5,'2015-10-16 11:11:08'),(66,1,0.00497,1,'2015-10-16 11:11:08'),(66,2,0.002526,5,'2015-10-16 11:11:08'),(67,1,0.004009,1,'2015-10-16 11:11:08'),(67,2,0.001556,5,'2015-10-16 11:11:08'),(68,1,0.002658,1,'2015-10-16 11:11:08'),(68,2,0.002551,5,'2015-10-16 11:11:08'),(69,1,0.001594,1,'2015-10-16 11:11:08'),(69,2,0.001579,5,'2015-10-16 11:11:08'),(70,1,0.001601,5,'2015-10-16 11:11:08'),(70,2,0.004937,1,'2015-10-16 11:11:08'),(71,1,0.002892,5,'2015-10-16 11:11:08'),(71,2,0.003681,1,'2015-10-16 11:11:08'),(72,1,0.002194,5,'2015-10-16 11:11:08'),(72,2,0.005712,1,'2015-10-16 11:11:08'),(73,1,0.003742,1,'2015-10-16 11:11:08'),(73,2,0.003015,5,'2015-10-16 11:11:08'),(74,1,0.003434,1,'2015-10-16 11:11:08'),(74,2,0.003135,5,'2015-10-16 11:11:08'),(75,1,0.004205,1,'2015-10-16 11:11:08'),(75,2,0.001768,5,'2015-10-16 11:11:08'),(76,1,0.001928,5,'2015-10-16 11:11:08'),(76,2,0.003736,1,'2015-10-16 11:11:08'),(77,1,0.00332,5,'2015-10-16 11:11:08'),(77,2,0.003476,1,'2015-10-16 11:11:08'),(78,1,0.003994,1,'2015-10-16 11:11:08'),(78,2,0.002146,5,'2015-10-16 11:11:08'),(79,1,0.003043,5,'2015-10-16 11:11:08'),(79,2,0.003577,1,'2015-10-16 11:11:08'),(80,1,0.002353,5,'2015-10-16 11:11:08'),(80,2,0.002427,1,'2015-10-16 11:11:08'),(81,1,0.002205,5,'2015-10-16 11:11:08'),(81,2,0.003185,1,'2015-10-16 11:11:08'),(82,1,0.002063,5,'2015-10-16 11:11:08'),(82,2,0.003635,1,'2015-10-16 11:11:08'),(83,1,0.002701,1,'2015-10-16 11:11:08'),(83,2,0.001572,5,'2015-10-16 11:11:08'),(84,1,0.002674,5,'2015-10-16 11:11:08'),(84,2,0.002674,1,'2015-10-16 11:11:08'),(85,1,0.00267,5,'2015-10-16 11:11:08'),(85,2,0.002691,1,'2015-10-16 11:11:08'),(86,1,0.00267,1,'2015-10-16 11:11:08'),(86,2,0.002662,5,'2015-10-16 11:11:08'),(87,1,0.004608,1,'2015-10-16 11:11:08'),(87,2,0.003638,5,'2015-10-16 11:11:08'),(88,1,0.003483,1,'2015-10-16 11:11:08'),(88,2,0.002505,5,'2015-10-16 11:11:08'),(89,1,0.003173,1,'2015-10-16 11:11:08'),(89,2,0.002904,5,'2015-10-16 11:11:08'),(90,1,0.002389,5,'2015-10-16 11:11:08'),(90,2,0.003448,1,'2015-10-16 11:11:08'),(91,1,0.004512,1,'2015-10-16 11:11:08'),(91,2,0.001828,5,'2015-10-16 11:11:08'),(92,1,0.004436,1,'2015-10-16 11:11:08'),(92,2,0.003594,5,'2015-10-16 11:11:08'),(93,1,0.001953,5,'2015-10-16 11:11:08'),(93,2,0.00252,1,'2015-10-16 11:11:08'),(94,1,0.002909,5,'2015-10-16 11:11:08'),(94,2,0.003,1,'2015-10-16 11:11:08'),(95,1,0.005316,1,'2015-10-16 11:11:08'),(95,2,0.002431,5,'2015-10-16 11:11:08'),(96,1,0.002807,5,'2015-10-16 11:11:08'),(96,2,0.003742,1,'2015-10-16 11:11:08'),(97,1,0.006045,1,'2015-10-16 11:11:08'),(97,2,0.003272,5,'2015-10-16 11:11:08'),(98,1,0.002939,5,'2015-10-16 11:11:08'),(98,2,0.00297,1,'2015-10-16 11:11:08'),(99,1,0.004021,1,'2015-10-16 11:11:08'),(99,2,0.002374,5,'2015-10-16 11:11:08');
/*!40000 ALTER TABLE `rankings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nacimiento` datetime NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'kalu','116393ddd9a3e3049659d05e97ed02d6','karina','pangaro','kaluli@gmail.com','1987-05-20 00:00:00',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-17  0:23:07
