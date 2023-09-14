-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: book_db
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `book_title` varchar(255) DEFAULT NULL,
  `cover_image` varchar(255) DEFAULT NULL,
  `created_at` bigint NOT NULL,
  `genres` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `pdf_link` varchar(255) DEFAULT NULL,
  `ratings` int NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `updated_at` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'author-1','test-1','/image/cover-image/image/abc.png',1694615858,'B',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',5,'test',0),(2,'author-1','test-2','/image/cover-image/image/abc.png',1694616077,'A',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',5,'test',0),(3,'author-1','test-3','/image/cover-image/image/abc.png',1694616086,'B',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',5,'test',0),(4,'author-2','test-4','/image/cover-image/image/abc.png',1694616109,'A',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',2,'test',0),(5,'author-2','test-5','/image/cover-image/image/abc.png',1694616135,'A',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',2,'test',0),(6,'author-2','test-6','/image/cover-image/image/abc.png',1694616148,'B',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',5,'test',0),(7,'author-3','test-7','/image/cover-image/image/abc.png',1694616179,'C',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',1,'test',0),(8,'author-3','test-8','/image/cover-image/image/abc.png',1694616194,'A',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',2,'test',0),(9,'author-3','test-9','/image/cover-image/image/abc.png',1694616209,'B',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',5,'test',0),(10,'author-4','test-10','/image/cover-image/image/abc.png',1694616286,'C',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',5,'test',0),(11,'author-4','test-11','/image/cover-image/image/abc.png',1694616296,'A',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',5,'test',0),(12,'author-4','test-12','/image/cover-image/image/abc.png',1694616304,'B',_binary '\0','/pdf/cover-pdf/pdf/a.pdf',5,'test',0);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `created_at` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ey8gegnanvybix5a025vepf4` (`book_id`),
  CONSTRAINT `FK1ey8gegnanvybix5a025vepf4` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJra2wiLCJpYXQiOjE2OTQ2MzYyMzEsImV4cCI6MTY5NDYzNjQxMX0.3lvDRhhdRKKf6gj-2wp9r7TK8pdg_bWcZ5qTFyGn8xg','BEARER',1),(2,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJra2wiLCJpYXQiOjE2OTQ2MzYyODAsImV4cCI6MTY5NDYzNjQ2MH0.v64g8BQc11V7-hVEvGE55mGB4GK39woPT1abeagRkGc','BEARER',1),(3,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJra2wiLCJpYXQiOjE2OTQ2MzYzMTksImV4cCI6MTY5NDYzNjQ5OX0.2IrCs7WXLqq6SgoS7wp7gjasp0B7Ai8N9ztJ-szLKw0','BEARER',1),(4,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJra2wiLCJpYXQiOjE2OTQ2MzYzMzUsImV4cCI6MTY5NDYzNjUxNX0.atOLBWS93q9xf4GG9pY7WdkfA3r7A8Q31lPGn13TZyI','BEARER',1),(5,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJra2wiLCJpYXQiOjE2OTQ2MzY2MDYsImV4cCI6MTY5NDYzNjc4Nn0.HR4cziGlxiW8_JaeVticEkK3t80hBy0lme-tsQVvH7k','BEARER',1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'$2a$10$MhklsUzbqBj1h2T7UPhZzuaG5psETs71dTazzOwWLPgW6b87qhXHW','USER','kkl');
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

-- Dump completed on 2023-09-14  3:08:24
