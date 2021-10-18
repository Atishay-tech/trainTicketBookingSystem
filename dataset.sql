-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: railway_database
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `sno` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  PRIMARY KEY (`sno`),
  UNIQUE KEY `aname_UNIQUE` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'ABC','abc123'),(2,'admin','pass');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chart`
--

DROP TABLE IF EXISTS `chart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chart` (
  `pnr` int DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `seatno` int DEFAULT NULL,
  `coach` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `dot` date DEFAULT NULL,
  `sno` int NOT NULL AUTO_INCREMENT,
  `tnum` int DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chart`
--

LOCK TABLES `chart` WRITE;
/*!40000 ALTER TABLE `chart` DISABLE KEYS */;
INSERT INTO `chart` VALUES (21256,'abc',12,'m',1,'c','cancel','2021-10-15 10:37:46','2021-10-01',1,1000),(21257,'a',10,'m',2,'First AC','cancel','2021-10-16 15:12:36','2000-05-01',2,1000),(21258,'v',13,'m',3,'Sleeper Coach','cancel','2021-10-16 15:17:14','2000-05-01',3,100),(21278,'x',18,'f',5,'First AC','cancel','2021-10-17 08:52:44','2000-05-01',5,100),(21310,'abc',12,'m',6,'First AC','confirmed','2021-10-17 13:09:53','2000-05-01',6,100),(21259,'df',30,'f',7,'First AC','cancel','2021-10-17 13:27:41','2000-05-01',7,100),(21300,'a',15,'m',8,'Second AC','confirmed','2021-10-17 14:52:30','2000-05-01',8,100),(21301,'ann',12,'g',9,'Sleeper Coach','confirmed','2021-10-18 07:57:46','2000-05-11',9,100),(21302,'qw',23,'f',10,'Second AC','confirmed','2021-10-18 08:04:41','2000-05-11',10,100),(21303,'hello',21,'f',11,'First AC','confirmed','2021-10-18 08:07:57','2000-05-11',11,100);
/*!40000 ALTER TABLE `chart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train` (
  `tnum` int NOT NULL,
  `tname` varchar(100) DEFAULT NULL,
  `seats` int DEFAULT NULL,
  `bp` varchar(100) DEFAULT NULL,
  `dp` varchar(100) DEFAULT NULL,
  `fAC` int DEFAULT NULL,
  `sAC` int DEFAULT NULL,
  `tAC` int DEFAULT NULL,
  `sc` int DEFAULT NULL,
  `doj` date DEFAULT NULL,
  `dtime` varchar(100) DEFAULT NULL,
  `atime` varchar(100) DEFAULT NULL,
  `sno` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sno`),
  UNIQUE KEY `tnum_UNIQUE` (`tnum`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (100,'hundred',71,'a','b',10,10,80,1,'2000-05-11','20:10:10','20:10:10',1),(1000,'x',200,'delhi','gkp',100,80,60,40,'2021-01-01','12:00:00','00:00:00',3);
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uname` varchar(100) DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `g` varchar(100) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sno` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sno`),
  UNIQUE KEY `uname_UNIQUE` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('xyz','xyz',20,'m','2021-10-17 19:10:18',1),('abc','abc',20,'M','2021-10-17 19:10:18',5),('atishay','atishay',21,'m','2021-10-17 19:25:44',6),('hello','hello',26,'f','2021-10-18 07:34:33',7),('h','h',40,'M','2021-10-18 08:01:56',8);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-18 15:34:57
