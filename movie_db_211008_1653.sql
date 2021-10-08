-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: movie_db
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `user_pw` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'아','비','이름','폰번호'),(2,'김','아','이름1','폰번호2'),(3,'tjoeun','1234','조은','010-2525-8282'),(4,'tjoeun2','12345','조안','010-6662-6277'),(5,'tjoeun1','1055','조흔','010-6624-2755'),(6,'갸','12','김','010-6264-2727'),(7,'tjoeun3','123456','조훈','010-6662-2485'),(8,'tjoeun77','1234567','조흥','010-2777-6657'),(9,'tjoeun4','12345678','조흐잉','010-2674-3456'),(10,'tjoeun5','134','김','010-6665-7777'),(11,'tjoeun6','6666','하','010-2755-2755'),(12,'tjoeun24','24','조','010-6276-2755'),(13,'tjoeun7','123456789','최신','010-7785-0966'),(14,'tjoeun22','22','김씨','010-6675-3377'),(15,'tjoeun55','555','김미미','010-2664-2666');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `actor` varchar(45) NOT NULL,
  `director` varchar(45) NOT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'007 노 타임 투 다이','액션','다니엘 크레이그, 라미 말렉, 라샤나 린치, 레아 세이두','캐리 후쿠나가'),(2,'보이스','범죄, 액션','변요한, 김무열, 김희원, 박명훈','김선, 김곡'),(3,'수색자','스릴러','송창의, 송영규, 장해송, 도은비, 김지웅, 김영재','김민섭'),(4,'기적','드라마','박정민, 이성민, 임윤아, 이수경','이장훈'),(5,'샹치와 텐 링즈의 전설','액션','시무 리우, 양조위, 아콰파나,장멍, 양자경','데스틴 크리튼'),(6,'스틸워터','범죄, 드라마, 스릴러','맷 데이먼, 아비게일 브레스린','토마스 맥카시');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `price` int NOT NULL,
  `payment_date` datetime NOT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,53,10000,'2021-10-07 00:00:00'),(2,4,10000,'2021-10-07 16:19:40'),(3,7,10000,'2021-10-07 16:35:05'),(4,3,10000,'2021-10-07 16:40:30'),(5,8,10000,'2021-10-07 16:41:43'),(6,9,10000,'2021-10-07 16:47:06'),(7,12,10000,'2021-10-07 17:10:24'),(8,0,10000,'2021-10-07 17:40:44'),(9,12,10000,'2021-10-08 11:12:37'),(10,3,10000,'2021-10-08 12:12:22'),(11,3,10000,'2021-10-08 12:52:52'),(12,3,10000,'2021-10-08 15:27:54'),(13,12,10000,'2021-10-08 15:29:57'),(14,3,10000,'2021-10-08 15:31:23'),(15,3,10000,'2021-10-08 15:36:07'),(16,14,10000,'2021-10-08 15:48:48'),(17,14,10000,'2021-10-08 15:49:21'),(18,12,10000,'2021-10-08 16:07:10'),(19,15,10000,'2021-10-08 16:40:57');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `seat_detail` varchar(45) NOT NULL,
  PRIMARY KEY (`seat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,'A'),(2,3,'A'),(3,5,'A'),(4,2,'A'),(5,4,'B'),(6,5,'E'),(7,6,'C'),(8,3,'D'),(9,6,'E'),(10,6,'A'),(11,6,'B'),(12,5,'D'),(13,3,'E'),(14,5,'C'),(15,2,'D'),(16,5,'S'),(17,1,'E'),(18,4,'A'),(19,6,'D');
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `seat_id` int NOT NULL,
  `payment_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `ticket_tot` int NOT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (2,1,4,2,1),(3,1,5,4,1),(4,1,6,6,1),(5,1,7,6,1),(6,1,8,3,1),(7,1,7,6,1),(8,10,4,6,1),(10,18,18,4,1);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-08 16:53:51
