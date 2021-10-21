-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: railway
-- ------------------------------------------------------
-- Server version	8.0.26

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
  `uname` varchar(45) NOT NULL,
  `pass` varchar(45) DEFAULT NULL,
  `sno` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sno`,`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('abc','123',1),('admin','0000',2),('group1','1234',3);
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
  `name` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `seatno` int DEFAULT NULL,
  `coach` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `timestamp` timestamp(6) NOT NULL,
  `dot` date DEFAULT NULL,
  `sno` int NOT NULL AUTO_INCREMENT,
  `tnum` int NOT NULL,
  PRIMARY KEY (`sno`,`tnum`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chart`
--

LOCK TABLES `chart` WRITE;
/*!40000 ALTER TABLE `chart` DISABLE KEYS */;
INSERT INTO `chart` VALUES (21531,'dummy',23,'M',3,'First AC','confirmed','2021-10-16 23:25:23.143000','2021-12-31',1,3000),(21532,'Manoj',78,'M',4,'First AC','cancel','2021-10-17 04:05:48.682000','2021-12-31',2,3000),(21533,'virat',36,'F',5,'First AC','cancel','2021-10-17 04:10:01.741000','2021-12-31',3,3000),(21534,'dinesh',68,'M',6,'First AC','confirmed','2021-10-17 04:13:48.156000','2021-12-31',4,3000),(21535,'brijesh',76,'M',7,'First AC','confirmed','2021-10-17 04:20:13.813000','2021-12-31',5,3000),(21536,'lokesh',23,'O',8,'First AC','confirmed','2021-10-17 04:21:50.310000','2021-12-31',6,3000),(21537,'naman',54,'M',9,'First AC','confirmed','2021-10-17 04:23:12.825000','2021-12-31',7,3000),(21538,'abc',34,'M',10,'First AC','confirmed','2021-10-17 04:25:20.626000','2021-12-31',8,3000),(21538,'RTY',65,'F',11,'First AC','confirmed','2021-10-17 04:25:20.626000','2021-12-31',9,3000),(21539,'ghf',55,'T',12,'First AC','confirmed','2021-10-17 04:28:26.945000','2021-12-31',10,3000),(21539,'tre',44,'M',13,'First AC','confirmed','2021-10-17 04:28:26.945000','2021-12-31',11,3000),(21540,'popo',34,'M',14,'First AC','confirmed','2021-10-17 04:32:06.222000','2021-12-01',12,4000),(21540,'opop',43,'F',15,'First AC','confirmed','2021-10-17 04:32:06.222000','2021-12-01',13,4000),(21541,'toto',22,'M',16,'First AC','cancel','2021-10-17 04:38:21.419000','2021-12-01',14,4000),(21541,'roro',22,'F',17,'First AC','cancel','2021-10-17 04:38:21.419000','2021-12-01',15,4000),(21542,'amar',12,'M',18,'Second AC','confirmed','2021-10-17 06:52:09.149000','2021-12-31',16,3000),(21542,'akbar',45,'F',19,'Second AC','confirmed','2021-10-17 06:52:09.149000','2021-12-31',17,3000),(21542,'anthony',34,'T',20,'Second AC','confirmed','2021-10-17 06:52:09.149000','2021-12-31',18,3000),(21543,'sang_wu',59,'F',21,'First AC','confirmed','2021-10-17 14:00:11.713000','2021-12-01',19,4000),(21544,'tarun',23,'M',22,'First AC','cancel','2021-10-17 15:24:43.808000','2021-12-01',20,4000),(21545,'Abhishek',21,'M',23,'First AC','cancel','2021-10-18 05:35:17.229000','2022-02-01',21,7000),(21545,'Atishay',21,'M',24,'First AC','cancel','2021-10-18 05:35:17.229000','2022-02-01',22,7000),(21545,'Neeraj',25,'M',25,'First AC','cancel','2021-10-18 05:35:17.229000','2022-02-01',23,7000),(21545,'Nikhil',25,'M',26,'First AC','cancel','2021-10-18 05:35:17.229000','2022-02-01',24,7000),(21545,'Yash',22,'M',27,'First AC','cancel','2021-10-18 05:35:17.229000','2022-02-01',25,7000),(21545,'Raman',23,'M',28,'First AC','cancel','2021-10-18 05:35:17.229000','2022-02-01',26,7000);
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
  `tname` varchar(45) DEFAULT NULL,
  `seats` int DEFAULT NULL,
  `bp` varchar(45) DEFAULT NULL,
  `dp` varchar(45) DEFAULT NULL,
  `fAC` int DEFAULT NULL,
  `sAC` int DEFAULT NULL,
  `tAC` int DEFAULT NULL,
  `sc` int DEFAULT NULL,
  `doj` date DEFAULT NULL,
  `dtime` varchar(45) DEFAULT NULL,
  `atime` varchar(45) DEFAULT NULL,
  `sno` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sno`,`tnum`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (3000,'dakshin_express',277,'NGP','HYB',456,345,234,123,'2021-12-31','17:35:00','08:30:00',1),(4000,'mumbai_express',392,'NGP','CSMT',567,456,345,234,'2021-12-01','16:00:00','12:00:00',2),(5000,'rajdhani_express',400,'NGP','DEL',900,800,700,600,'2021-12-16','20:00:00','13:00:00',3),(5000,'duronto_express',400,'NGP','DEL',900,800,700,600,'2021-12-16','20:00:00','13:00:00',4),(6000,'vistadome',600,'DEL','MUM',777,666,555,444,'2021-11-30','20:00:00','13:00:00',5),(7000,'group1_express',294,'NGP','GOA',999,888,777,666,'2022-04-06','20:00:00','18:00:00',6);
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uname` varchar(50) NOT NULL,
  `pass` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `g` varchar(45) DEFAULT NULL,
  `timestamp` timestamp(6) NULL DEFAULT NULL,
  `sno` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sno`,`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('abc','123',16,'M','2021-10-16 18:13:03.624000',1),('xyz','321',34,'F','2021-10-16 18:32:25.230000',2),('fgh','456',18,'M','2021-10-17 15:31:45.320000',3),('team1','team1',23,'M','2021-10-18 05:44:59.091000',4);
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

-- Dump completed on 2021-10-21 21:22:04
