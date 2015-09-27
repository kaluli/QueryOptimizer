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
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
INSERT INTO `consulta` VALUES (1,'SELECT * FROM City;',0.00005,1,46,'2015-09-26 17:19:59'),(2,'SELECT * FROM City;',0.00263,1,46,'2015-09-26 17:20:52'),(3,'SELECT * FROM City;',0.002748,1,46,'2015-09-26 17:20:54'),(4,'SELECT * FROM City;',0.00265,1,46,'2015-09-26 17:20:55'),(5,'SELECT * FROM City;',0.002114,1,46,'2015-09-26 17:20:56'),(6,'SELECT * FROM City;',0.003059,1,46,'2015-09-26 17:20:57'),(7,'SELECT * FROM City;',0.00218,1,46,'2015-09-26 17:20:58'),(8,'SELECT * FROM City;',0.002827,1,46,'2015-09-26 17:20:59'),(9,'SELECT * FROM City;',0.003212,1,46,'2015-09-26 17:21:49'),(10,'SELECT * FROM City;',0.003089,1,46,'2015-09-26 17:21:51'),(11,'SELECT * FROM City;',0.00544,1,46,'2015-09-26 17:21:52'),(12,'SELECT * FROM City;',0.004383,1,46,'2015-09-26 17:21:53'),(13,'SELECT * FROM City;',0.004755,1,46,'2015-09-26 17:21:54'),(14,'SELECT * FROM City;',0.002682,1,46,'2015-09-26 17:21:55'),(15,'SELECT * FROM City;',0.002791,1,46,'2015-09-26 17:21:55'),(16,'SELECT * FROM City;',0.00201,1,46,'2015-09-26 17:21:56'),(17,'SELECT * FROM City;',0.002378,1,46,'2015-09-26 17:21:57'),(18,'SELECT * FROM City;',0.002983,1,46,'2015-09-26 17:21:58'),(19,'SELECT * FROM City;',0.002691,1,46,'2015-09-26 17:21:58'),(20,'SELECT * FROM City;',0.000205,1,46,'2015-09-26 17:24:02'),(21,'SELECT * FROM City;',0.000225,1,46,'2015-09-26 17:24:14'),(22,'SELECT * FROM City;',0.000139,1,46,'2015-09-26 17:24:15'),(23,'SELECT * FROM City;',0.000326,1,46,'2015-09-26 17:24:16'),(24,'SELECT * FROM City;',0.000141,1,46,'2015-09-26 17:24:17'),(25,'SELECT * FROM City;',0.000145,1,46,'2015-09-26 17:24:18'),(26,'SELECT * FROM City;',0.000294,1,46,'2015-09-26 17:24:19'),(27,'SELECT * FROM City;',0.002746,1,46,'2015-09-26 17:24:59'),(28,'SELECT * FROM City;',0.003069,1,46,'2015-09-26 17:25:01'),(29,'SELECT * FROM City;',0.002093,1,46,'2015-09-26 17:25:02'),(30,'SELECT * FROM City;',0.001541,1,46,'2015-09-26 17:25:03'),(31,'SELECT * FROM City;',0.003648,1,46,'2015-09-26 17:25:04'),(32,'SELECT * FROM City;',0.003497,1,46,'2015-09-26 17:25:05'),(33,'SELECT * FROM City;',0.003342,1,46,'2015-09-26 17:25:06'),(34,'SELECT * FROM City;',0.002465,1,46,'2015-09-26 17:25:35'),(35,'SELECT * FROM City;',0.003626,1,46,'2015-09-26 17:25:37'),(36,'SELECT * FROM City;',0.004285,1,46,'2015-09-26 17:25:38'),(37,'SELECT * FROM City;',0.002561,1,46,'2015-09-26 17:26:08'),(38,'SELECT * FROM City;',0.003808,1,46,'2015-09-26 17:26:10'),(39,'SELECT * FROM City;',0.002661,1,46,'2015-09-26 17:26:11'),(40,'SELECT * FROM City;',0.001737,1,46,'2015-09-26 17:26:12'),(41,'SELECT * FROM City;',0.001979,1,46,'2015-09-26 17:26:14'),(42,'SELECT * FROM City;',0.002333,1,46,'2015-09-26 17:26:15'),(43,'SELECT * FROM City;',0.002091,1,46,'2015-09-26 17:26:16'),(44,'SELECT * FROM City;',0.002741,1,46,'2015-09-26 17:26:52'),(45,'SELECT * FROM City;',0.00294,1,46,'2015-09-26 17:26:54'),(46,'SELECT * FROM City;',0.00342,1,46,'2015-09-26 17:26:56'),(47,'SELECT * FROM City;',0.002758,1,46,'2015-09-26 17:27:07'),(48,'SELECT * FROM City;',0.00365,1,46,'2015-09-26 17:27:09'),(49,'SELECT * FROM City;',0.002813,1,46,'2015-09-26 17:27:11'),(50,'SELECT * FROM City;',0.003244,1,46,'2015-09-26 17:27:13'),(51,'SELECT * FROM City;',0.003224,1,46,'2015-09-26 17:28:40'),(52,'SELECT * FROM City;',0.003709,1,46,'2015-09-26 17:28:45'),(53,'SELECT * FROM City;',0.002736,1,46,'2015-09-26 17:28:46'),(54,'SELECT * FROM City;',0.002666,1,46,'2015-09-26 17:28:48'),(55,'SELECT * FROM City;',0.003776,1,46,'2015-09-26 17:28:50'),(56,'SELECT * FROM City;',0.005319,1,46,'2015-09-26 17:30:01'),(57,'SELECT * FROM City;',0.001888,1,46,'2015-09-26 17:30:06'),(58,'SELECT * FROM City;',0.003006,1,46,'2015-09-26 17:30:09'),(59,'SELECT * FROM City;',0.002354,1,46,'2015-09-26 17:30:11'),(60,'SELECT * FROM City;',0.00381,1,46,'2015-09-26 17:30:13'),(61,'SELECT * FROM City;',0.002639,1,46,'2015-09-26 17:30:14'),(62,'SELECT * FROM City;',0.002819,1,46,'2015-09-26 17:30:16'),(63,'SELECT * FROM City;',0.003554,1,46,'2015-09-26 17:30:18'),(64,'SELECT * FROM City;',0.002612,1,46,'2015-09-26 17:31:05'),(65,'SELECT * FROM City;',0.004383,1,46,'2015-09-26 17:31:08'),(66,'SELECT * FROM City;',0.00291,1,46,'2015-09-26 17:31:09'),(67,'SELECT * FROM City;',0.002397,1,46,'2015-09-26 17:31:11'),(68,'SELECT * FROM City;',0.00236,1,46,'2015-09-26 17:31:13'),(69,'SELECT * FROM City;',0.002793,1,46,'2015-09-26 17:31:15'),(70,'SELECT * FROM City;',0.002882,1,46,'2015-09-26 17:32:52'),(71,'SELECT * FROM City;',0.00224,1,46,'2015-09-26 17:32:54'),(72,'SELECT * FROM City;',0.002631,1,46,'2015-09-26 17:32:56'),(73,'SELECT * FROM City;',0.002554,1,46,'2015-09-26 17:32:58'),(74,'SELECT * FROM City;',0.005237,1,46,'2015-09-26 17:33:18'),(75,'SELECT * FROM City;',0.002333,1,46,'2015-09-26 17:33:20'),(76,'SELECT * FROM City;',0.003661,1,46,'2015-09-26 17:33:22'),(77,'SELECT * FROM City;',0.002683,1,46,'2015-09-26 17:34:10'),(78,'SELECT * FROM City;',0.00268,1,46,'2015-09-26 17:34:14'),(79,'SELECT * FROM City;',0.081199,1,46,'2015-09-26 17:46:01'),(80,'SELECT * FROM City;',0.002468,1,46,'2015-09-26 17:46:05'),(81,'SELECT * FROM City;',0.004656,1,46,'2015-09-26 17:46:08'),(82,'SELECT * FROM City;',0.002045,1,46,'2015-09-26 17:46:11'),(83,'SELECT * FROM City;',0.00264,1,46,'2015-09-26 17:46:13'),(84,'SELECT * FROM City;',0.002848,1,46,'2015-09-26 17:46:15'),(85,'SELECT * FROM City;',0.002972,1,46,'2015-09-26 17:46:17'),(86,'SELECT * FROM City;',0.003838,1,46,'2015-09-26 17:46:49'),(87,'SELECT * FROM City;',0.00303,1,46,'2015-09-26 17:46:54'),(88,'SELECT * FROM City;',0.002341,1,46,'2015-09-26 17:47:21'),(89,'SELECT * FROM City;',0.002674,1,46,'2015-09-26 17:47:25'),(90,'SELECT * FROM City;',0.001566,1,46,'2015-09-26 17:47:28'),(91,'SELECT * FROM City;',0.00461,1,46,'2015-09-26 17:47:33'),(92,'SELECT * FROM City;',NULL,1,46,'2015-09-26 17:49:01'),(93,'SELECT * FROM City;',NULL,1,46,'2015-09-26 17:49:47'),(94,'SELECT * FROM City;',NULL,1,46,'2015-09-26 17:49:52'),(95,'SELECT * FROM City;',0.00013,1,46,'2015-09-26 17:51:25'),(96,'SELECT * FROM City;',0.000047,1,46,'2015-09-26 17:51:27'),(97,'SELECT * FROM City;',0.000091,1,46,'2015-09-26 17:51:28'),(98,'SELECT * FROM City;',0.000043,1,46,'2015-09-26 17:51:29'),(99,'SELECT * FROM City;',0.000073,1,46,'2015-09-26 17:52:00'),(100,'SELECT * FROM City;',0.00004,1,46,'2015-09-26 17:52:02'),(101,'SELECT * FROM City;',0.000078,1,46,'2015-09-26 17:52:03'),(102,'SELECT * FROM City;',0.000094,1,46,'2015-09-26 17:52:21'),(103,'SELECT * FROM City;',0.000043,1,46,'2015-09-26 17:52:23'),(104,'SELECT * FROM City;',0.000048,1,46,'2015-09-26 18:30:02'),(105,'SELECT * FROM City;',0.000047,1,46,'2015-09-26 18:43:38'),(106,'SELECT * FROM City;',0.000099,1,46,'2015-09-26 18:51:25'),(107,'SELECT * FROM City;',0.000077,1,46,'2015-09-26 18:57:03'),(108,'SELECT * FROM City;',NULL,1,46,'2015-09-26 19:00:24'),(109,'SELECT * FROM City;',NULL,1,46,'2015-09-26 19:00:51'),(110,'SELECT * FROM City;',NULL,1,46,'2015-09-26 19:01:39'),(111,'SELECT * FROM City;',0.000091,1,46,'2015-09-26 19:02:28'),(112,'SELECT * FROM City;',0.000056,1,46,'2015-09-26 19:06:16'),(113,'SELECT * FROM City;',0.002456,1,46,'2015-09-26 19:06:45'),(114,'SELECT * FROM City;',0.00304,1,46,'2015-09-26 19:06:48'),(115,'SELECT * FROM City;',0.004753,1,46,'2015-09-26 19:06:50'),(116,'SELECT * FROM City;',0.003881,1,46,'2015-09-26 19:06:52'),(117,'SELECT * FROM City;',0.002844,1,46,'2015-09-26 19:06:53'),(118,'SELECT * FROM City;',0.002564,1,46,'2015-09-26 19:06:54'),(119,'SELECT * FROM City;',0.002571,1,46,'2015-09-26 19:06:56'),(120,'SELECT * FROM City;',0.002669,1,46,'2015-09-26 19:07:00'),(121,'SELECT * FROM City;',0.002022,1,46,'2015-09-26 19:07:02'),(122,'SELECT * FROM City;',0.003515,1,46,'2015-09-26 19:07:04'),(123,'SELECT * FROM City;',0.001766,1,46,'2015-09-26 19:07:07'),(124,'SELECT * FROM City;',0.004497,1,46,'2015-09-26 19:07:09'),(125,'SELECT * FROM City;',0.003539,1,46,'2015-09-26 19:07:11'),(126,'SELECT * FROM City;',0.005079,1,46,'2015-09-26 19:07:12'),(127,'SELECT * FROM City;',0.003105,1,46,'2015-09-26 19:07:14'),(128,'SELECT * FROM City;',0.00338,1,46,'2015-09-26 19:07:15'),(129,'SELECT * FROM City;',0.002499,1,46,'2015-09-26 19:07:15'),(130,'SELECT * FROM City;',0.003114,1,46,'2015-09-26 19:07:16'),(131,'SELECT * FROM City;',0.001542,1,46,'2015-09-26 19:07:18'),(132,'SELECT * FROM City;',0.005232,1,46,'2015-09-26 19:07:20'),(133,'SELECT * FROM City;',0.00361,1,46,'2015-09-26 19:07:23'),(134,'SELECT * FROM City;',0.002828,1,46,'2015-09-26 19:07:24'),(135,'SELECT * FROM City;',0.003138,1,46,'2015-09-26 19:07:26'),(136,'SELECT * FROM City;',0.003129,1,46,'2015-09-26 19:07:28'),(137,'SELECT * FROM City;',0.002574,1,46,'2015-09-26 19:08:15'),(138,'SELECT * FROM City;',0.001646,1,46,'2015-09-26 19:08:17'),(139,'SELECT * FROM City;',0.003729,1,46,'2015-09-26 19:08:19'),(140,'SELECT * FROM City;',0.003655,1,46,'2015-09-26 19:08:20'),(141,'SELECT * FROM City;',0.004356,1,46,'2015-09-26 19:08:22'),(142,'SELECT * FROM City;',0.002536,1,46,'2015-09-26 19:08:23'),(143,'SELECT * FROM City;',0.00266,1,46,'2015-09-26 19:08:24'),(144,'SELECT * FROM City;',0.002938,1,46,'2015-09-26 19:08:26'),(145,'SELECT * FROM City;',0.004007,1,46,'2015-09-26 19:08:27'),(146,'SELECT * FROM City;',0.003778,1,46,'2015-09-26 19:08:28'),(147,'SELECT * FROM City;',0.003592,1,46,'2015-09-26 19:08:29'),(148,'SELECT * FROM City;',0.001735,1,46,'2015-09-26 19:08:31'),(149,'SELECT * FROM City;',0.002164,1,46,'2015-09-26 19:08:32'),(150,'SELECT * FROM City;',0.002358,1,46,'2015-09-26 19:08:33'),(151,'SELECT * FROM City;',0.002879,1,46,'2015-09-26 19:08:34'),(152,'SELECT * FROM City;',0.002224,1,46,'2015-09-26 19:08:35'),(153,'SELECT * FROM City;',0.004792,1,46,'2015-09-26 19:08:36'),(154,'SELECT * FROM City;',0.003979,1,46,'2015-09-26 19:08:39'),(155,'SELECT * FROM City;',0.002692,1,46,'2015-09-26 19:08:40'),(156,'SELECT * FROM City;',0.002564,1,46,'2015-09-26 19:08:41'),(157,'SELECT * FROM City;',0.001989,1,46,'2015-09-26 19:08:42'),(158,'SELECT * FROM City;',0.002931,1,46,'2015-09-26 19:08:43'),(159,'SELECT * FROM City;',0.003575,1,46,'2015-09-26 19:08:44'),(160,'SELECT * FROM City;',0.003829,1,46,'2015-09-26 19:08:45'),(161,'SELECT * FROM City;',0.002667,1,46,'2015-09-26 19:09:54'),(162,'SELECT * FROM City;',0.002687,1,46,'2015-09-26 19:09:55'),(163,'SELECT * FROM City;',0.004486,1,46,'2015-09-26 19:09:56'),(164,'SELECT * FROM City;',0.003595,1,46,'2015-09-26 19:09:58'),(165,'SELECT * FROM City;',0.002803,1,46,'2015-09-26 19:09:59'),(166,' SELECT * FROM City;',0.002643,1,46,'2015-09-26 19:12:14'),(167,' SELECT * FROM City;',0.001565,1,46,'2015-09-26 19:12:16'),(168,' SELECT * FROM City;',0.002644,1,46,'2015-09-26 19:12:18'),(169,' SELECT * FROM City;',0.003419,1,46,'2015-09-26 19:12:19'),(170,' SELECT * FROM City;',0.063128,1,46,'2015-09-26 19:21:36'),(171,' SELECT * FROM City;',0.000124,1,46,'2015-09-26 19:21:39'),(172,' SELECT * FROM City;',0.000091,1,46,'2015-09-26 19:21:41'),(173,' SELECT * FROM City;',0.000126,1,46,'2015-09-26 19:21:42'),(174,' SELECT * FROM City;',0.000123,1,46,'2015-09-26 19:22:09'),(175,' SELECT * FROM City;',0.00013,1,46,'2015-09-26 19:22:33'),(176,'SELECT * FROM City;',0.016758,1,46,'2015-09-26 19:22:48'),(177,'SELECT * FROM City;',0.002694,1,46,'2015-09-26 19:22:50'),(178,'SELECT * FROM City;',0.003633,1,46,'2015-09-26 19:22:52'),(179,'SELECT * FROM City;',0.002454,1,46,'2015-09-26 19:22:53'),(180,'SELECT * FROM City;',0.002999,1,46,'2015-09-26 19:22:54'),(181,'SELECT * FROM City;',0.00168,1,46,'2015-09-26 19:22:55'),(182,' SELECT * FROM City;',0.002834,1,46,'2015-09-26 19:24:00'),(183,' SELECT * FROM City;',0.002556,1,46,'2015-09-26 19:24:02'),(184,' SELECT * FROM City;',0.006835,1,46,'2015-09-26 19:24:04'),(185,' SELECT * FROM City;',0.001609,1,46,'2015-09-26 19:24:05'),(186,' SELECT * FROM City;',0.005262,1,46,'2015-09-26 19:24:06'),(187,' SELECT * FROM City;',0.003458,1,46,'2015-09-26 19:24:07'),(188,' SELECT * FROM City;',0.003435,1,46,'2015-09-26 19:24:08'),(189,' SELECT * FROM City;',0.001975,1,46,'2015-09-26 19:24:10'),(190,' SELECT * FROM City;',0.002128,1,46,'2015-09-26 19:24:11'),(191,' SELECT * FROM City;',0.003829,1,46,'2015-09-26 19:24:50'),(192,' SELECT * FROM City;',0.004389,1,46,'2015-09-26 19:24:51'),(193,' SELECT * FROM City;',0.002416,1,46,'2015-09-26 19:24:52'),(194,' SELECT * FROM City;',0.003663,1,46,'2015-09-26 19:24:53'),(195,' SELECT * FROM City;',0.002712,1,46,'2015-09-26 19:26:54'),(196,' SELECT * FROM City;',0.002497,1,46,'2015-09-26 19:27:05'),(197,' SELECT * FROM City;',0.003637,1,46,'2015-09-26 19:27:07'),(198,' SELECT * FROM City;',0.002597,1,46,'2015-09-26 19:28:25'),(199,' SELECT * FROM City;',0.002747,1,46,'2015-09-26 19:28:27'),(200,' SELECT * FROM City;',0.001531,1,46,'2015-09-26 19:28:29'),(201,' SELECT * FROM City;',0.003333,1,46,'2015-09-26 19:28:30'),(202,' SELECT * FROM City;',0.002902,1,46,'2015-09-26 19:28:32'),(203,' SELECT * FROM City;',0.004777,1,46,'2015-09-26 19:28:39'),(204,'SELECT * FROM City;',0.049954,1,46,'2015-09-26 19:39:41'),(205,'SELECT * FROM City;',0.002205,1,46,'2015-09-26 19:39:48'),(206,'SELECT * FROM City;',0.004129,1,46,'2015-09-26 19:39:49'),(207,'SELECT * FROM City;',0.002654,1,46,'2015-09-26 19:39:50'),(208,'SELECT * FROM City;',0.002537,1,46,'2015-09-26 19:39:51'),(209,'SELECT * FROM City;',0.003538,1,46,'2015-09-26 19:39:52'),(210,'SELECT * FROM City;',0.002592,1,46,'2015-09-26 19:39:53'),(211,'SELECT * FROM City;',0.002186,1,46,'2015-09-26 19:39:54'),(212,'SELECT * FROM City;',0.003615,1,46,'2015-09-26 19:39:55'),(213,'SELECT * FROM City;',0.002561,1,46,'2015-09-26 19:39:56'),(214,'SELECT * FROM City;',0.002914,1,46,'2015-09-26 19:41:45'),(215,'SELECT * FROM City;',0.000117,1,46,'2015-09-26 19:41:47'),(216,'SELECT * FROM City;',0.00015,1,46,'2015-09-26 19:41:49'),(217,'SELECT * FROM City;',0.000172,1,46,'2015-09-26 19:41:51'),(218,'SELECT * FROM City;',0.000086,1,46,'2015-09-26 19:41:52'),(219,'SELECT * FROM City;',0.000156,1,46,'2015-09-26 19:41:55'),(220,'SELECT * FROM City;',0.000117,1,46,'2015-09-26 19:41:58'),(221,'SELECT * FROM City;',0.000109,1,46,'2015-09-26 19:42:00'),(222,'SELECT * FROM City;',0.000142,1,46,'2015-09-26 19:42:01'),(223,'SELECT * FROM City;',0.002701,1,46,'2015-09-26 19:43:24'),(224,'SELECT * FROM City;',0.003739,1,46,'2015-09-26 19:43:28'),(225,'SELECT * FROM City;',0.002609,1,46,'2015-09-26 19:43:30'),(226,'SELECT * FROM City;',0.003189,1,46,'2015-09-26 19:43:31'),(227,'SELECT * FROM City;',0.002998,1,46,'2015-09-26 19:43:32'),(228,'SHOW TABLES; ',0.000246,1,47,'2015-09-26 19:47:49'),(229,'SELECT * FROM employees',0.521066,1,47,'2015-09-26 19:47:58'),(230,'SELECT * FROM employees',0.260898,1,47,'2015-09-26 19:48:37'),(231,'SELECT * FROM employees',0.258808,1,47,'2015-09-26 19:50:17'),(232,'SELECT * from employees;',0.255681,1,47,'2015-09-26 19:53:09'),(233,'SELECT * from employees;',0.220843,1,47,'2015-09-26 19:53:48'),(234,'SELECT * from employees;',0.241339,1,47,'2015-09-26 19:55:18'),(235,'SELECT * from employees;',0.305767,1,47,'2015-09-26 19:56:11'),(236,'SELECT * from employees;',0.258907,1,47,'2015-09-26 19:56:44'),(237,'SELECT * FROM City;',0.108577,1,46,'2015-09-27 15:46:18'),(238,'SELECT * FROM City;',0.002563,1,46,'2015-09-27 15:46:26'),(239,'SELECT * FROM City;',0.003028,1,46,'2015-09-27 15:46:29'),(240,'SELECT * FROM City;',0.003304,1,46,'2015-09-27 15:47:50'),(241,'SELECT * FROM City;',0.028722,1,46,'2015-09-27 18:15:43'),(242,'SELECT * FROM City;',0.017038,1,46,'2015-09-27 19:07:02'),(243,'SELECT * FROM City;',0.022657,1,46,'2015-09-27 19:18:33'),(244,'SELECT * FROM City;',0.017594,1,46,'2015-09-27 19:21:05'),(245,'SELECT * FROM City;',0.002695,1,46,'2015-09-27 19:21:28'),(246,'SELECT * FROM City;',0.003896,1,46,'2015-09-27 19:21:35'),(247,'SELECT * FROM City;',0.003758,1,46,'2015-09-27 19:21:41'),(248,'SELECT * FROM City;',0.004455,1,46,'2015-09-27 19:21:45'),(249,'SELECT * FROM City;',0.002126,1,46,'2015-09-27 19:21:49'),(250,'SELECT * FROM City;',0.003598,1,46,'2015-09-27 19:21:57'),(251,'SELECT * FROM City;',0.004237,1,46,'2015-09-27 19:22:02'),(252,'SELECT * FROM City;',0.004013,1,46,'2015-09-27 19:22:07'),(253,'SELECT * FROM City;',0.001619,1,46,'2015-09-27 19:22:11'),(254,'SELECT * FROM City;',0.002659,1,46,'2015-09-27 19:22:16'),(255,'SELECT * FROM City;',0.002603,1,46,'2015-09-27 19:22:21'),(256,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.002741,1,46,'2015-09-27 19:22:58'),(257,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.002155,1,46,'2015-09-27 19:23:04'),(258,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.003314,1,46,'2015-09-27 19:23:09'),(259,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.001907,1,46,'2015-09-27 19:23:15'),(260,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.003507,1,46,'2015-09-27 19:23:20'),(261,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.00208,1,46,'2015-09-27 19:23:24'),(262,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.002829,1,46,'2015-09-27 19:23:29'),(263,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.004351,1,46,'2015-09-27 19:23:33'),(264,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.002156,1,46,'2015-09-27 19:23:37'),(265,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.004964,1,46,'2015-09-27 19:23:40'),(266,'SELECT ID,Name,CountryCode,District,Population FROM City;',0.003923,1,46,'2015-09-27 19:23:45');
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mahout`
--

DROP TABLE IF EXISTS `mahout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mahout` (
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `time` double DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mahout`
--

LOCK TABLES `mahout` WRITE;
/*!40000 ALTER TABLE `mahout` DISABLE KEYS */;
/*!40000 ALTER TABLE `mahout` ENABLE KEYS */;
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

-- Dump completed on 2015-09-27 19:32:23