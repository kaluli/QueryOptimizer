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
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `engine` varchar(30) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (13,1,'wizepost_active','jdbc:mysql://127.0.0.1:3306/wizepost_active','wizepost_active.sql',NULL,'2015-09-01 19:37:43'),(31,1,'atla_db','jdbc:mysql://127.0.0.1:3306/atla_db','atla_db.sql',NULL,'2015-09-02 00:12:41'),(45,1,'sakila','jdbc:mysql://127.0.0.1:3306/sakila','sakila-data.sql',NULL,'2015-09-14 19:01:01'),(46,1,'world','jdbc:mysql://127.0.0.1:3306/world','world.sql',NULL,'2015-09-14 19:01:22'),(47,1,'employees','jdbc:mysql://127.0.0.1:3306/employees','employees',NULL,'2015-09-26 19:01:22');
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consulta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `query` text,
  `time` double DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idconfig` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
INSERT INTO `consulta` VALUES (1,'SELECT * FROM City;',0.097787,1,46,'2015-09-29 12:33:52'),(2,'Select Name, CountryCode, District From City;',0.00126,1,46,'2015-09-29 12:34:52'),(3,' Select Name, CountryCode, District From City;',0.001988,1,46,'2015-09-29 12:39:27'),(4,' Select Name, CountryCode, District From City;',0.001559,1,46,'2015-09-29 12:39:52'),(5,'SELECT * FROM City;',0.002567,1,46,'2015-09-29 12:44:21'),(6,'SELECT * FROM City;',0.00341,1,46,'2015-09-29 12:45:32'),(7,'SELECT * FROM City;',0.01774,1,46,'2015-09-29 12:51:52'),(8,'SELECT * FROM City;',0.117345,1,46,'2015-09-29 12:52:55'),(9,'SELECT * FROM City;',0.014953,1,46,'2015-09-29 13:00:33'),(10,'Select Name, CountryCode, District From City;',0.001674,1,46,'2015-09-29 13:02:27'),(11,'SELECT * FROM City;',0.003804,1,46,'2015-09-29 13:03:38'),(12,'Select Name, CountryCode, District From City;',0.002439,1,46,'2015-09-29 13:04:15'),(13,'Select Name, CountryCode, District From City;',0.001788,1,46,'2015-09-29 13:04:21'),(14,'Select Name, CountryCode, District From City;',0.001674,1,46,'2015-09-29 13:04:25'),(15,'Select Name, CountryCode, District From City;',0.002524,1,46,'2015-09-29 13:04:29'),(16,'Select Name, CountryCode, District From City;',0.002178,1,46,'2015-09-29 13:06:29'),(17,'Select Name, CountryCode, District From City;',0.001974,1,46,'2015-09-29 13:10:13'),(18,'SELECT * FROM City;',0.054879,1,46,'2015-10-01 15:23:10'),(19,'SELECT * FROM City;',0.003466,1,46,'2015-10-01 15:25:37'),(20,'Select Name, CountryCode, District From City;',0.392392,1,46,'2015-10-01 15:42:05'),(21,'Select Name, CountryCode, District From City;',0.002433,1,46,'2015-10-01 15:43:50'),(22,'Select Name, CountryCode, District From City;',0.00179,1,46,'2015-10-01 15:44:43'),(23,'Select Name, CountryCode, District From City;',0.002519,1,46,'2015-10-01 15:44:57'),(24,'SELECT * FROM City;',0.040461,1,46,'2015-10-01 15:51:28'),(25,'SELECT * FROM City;',0.002689,1,46,'2015-10-01 15:51:50'),(26,'Select Name, CountryCode, District From City;',0.026259,1,46,'2015-10-01 17:04:30'),(27,'Select Name, CountryCode, District From City;',0.035485,1,46,'2015-10-01 17:05:00'),(28,'Select Name, CountryCode, District From City;',0.001803,1,46,'2015-10-01 17:06:25'),(29,'Select Name, CountryCode, District From City;',0.001754,1,46,'2015-10-01 17:06:56'),(30,'SELECT * FROM City;',0.022809,1,46,'2015-10-01 17:22:50'),(31,'SELECT * FROM City;',0.003772,1,46,'2015-10-01 17:23:21'),(32,'SHOW TABLES; ',0.196181,1,47,'2015-10-01 17:24:13'),(33,'SHOW TABLES; ',0.014829,1,47,'2015-10-01 17:27:47'),(34,'SHOW TABLES; ',0.011942,1,46,'2015-10-01 20:52:26'),(35,'SHOW TABLES; ',0.066121,1,46,'2015-10-01 20:54:00'),(36,'SHOW TABLES; ',0.000236,1,46,'2015-10-01 20:54:28'),(37,'SHOW TABLES; ',0.000247,1,46,'2015-10-01 20:54:33'),(38,'SHOW TABLES; ',0.000289,1,46,'2015-10-01 20:54:38');
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `query` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'SELECT * FROM %table%','2015-10-01 17:22:50'),(2,'SELECT %keyfields% FROM %table%;','2015-10-01 17:22:50'),(3,'SELECT * FROM %table% WHERE %conditions%;','2015-10-01 17:22:50'),(4,'SELECT %keyfields% FROM %table% WHERE %conditions%;','2015-10-01 17:22:50'),(5,'SELECT * FROM %table% INNER JOIN %table2% ON %conditions% WHERE %conditions%;','2015-10-01 17:22:50'),(6,'SELECT %keyfields% FROM %table% INNER JOIN %table2% ON %conditions% WHERE %conditions%;','2015-10-01 17:22:50'),(7,'SELECT * FROM %table% WHERE %SELECT * FROM% ','2015-10-01 17:22:50');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranking`
--

DROP TABLE IF EXISTS `ranking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ranking` (
  `item_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ranking` float NOT NULL,
  `time_average` double DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`item_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranking`
--

LOCK TABLES `ranking` WRITE;
/*!40000 ALTER TABLE `ranking` DISABLE KEYS */;
INSERT INTO `ranking` VALUES (1,1,5,0.3,'2015-10-01 17:22:50'),(1,2,4,0.2,'2015-10-01 17:22:50');
/*!40000 ALTER TABLE `ranking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profiles`
--

DROP TABLE IF EXISTS `user_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `nacimiento` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profiles`
--

LOCK TABLES `user_profiles` WRITE;
/*!40000 ALTER TABLE `user_profiles` DISABLE KEYS */;
INSERT INTO `user_profiles` VALUES (1,1,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_profiles` ENABLE KEYS */;
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
  `password` varchar(8) NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'kalu','kalu',NULL);
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

-- Dump completed on 2015-10-01 21:33:02
