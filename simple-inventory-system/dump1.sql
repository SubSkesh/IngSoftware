-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: inventorydb
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (9,'Fagiolino',2,2.00,0),(10,'Oscar Costanzelli',2,3.00,5),(11,'fii',33,1.00,5),(12,'ewewq',23232,23232.00,15),(13,'ewewq',23232,23232.00,15),(15,'s2213',12,121.00,16),(17,'Tire',2,11.00,17),(20,'Fagiolino',1,3.00,14);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'coccolino','$2a$10$HDd9qlGIfpvT7Q06poq...7Vfs16WQhilit8zt7vy6JxjU.76mULG','USER'),(2,'dd','$2a$10$bxbaSCnj2CL.cnipEBrmieTPYS4jMZth8jxl5F0FzIpYcOuPzOsni','USER'),(3,'ci','$2a$10$.ZRoBPNcy/9p0lMNRqngdetvoMJHLPHUaCtVvLfTOqvQrzX9M.03G','USER'),(4,'porco','$2a$10$W5TjsOl9QYb754KK/VnFZeRH/I336U//dh.xAAUH/MS49lvbxRJHK','USER'),(5,'figa','$2a$10$BByoSefn7Azol11E0FBDIOxCbo0j0gtjYUArunkPExt/aOz2Vm3Zy','USER'),(6,'tu','$2a$10$1q2ahvtMisOk/oIupTPI/.yp63SkbLVEnB3foLjMQAzQwCQ3.cbN6','USER'),(7,'mona','$2a$10$y/.AqAftYiuf/fpV2SwB5Or.TUYzOyyJupGVoBI5oeHuBx5PUkQjW','USER'),(8,'trombo','$2a$10$Gk1nB68.Dx9P7hQkkPjizOjP3Y3HYHXO/Vm1eh1z./Fc8rIGL4PT.','USER'),(9,'popo','$2a$10$ZRFT3ox8dSBBgB9dPAje2OknhJCpcgb5oZjn8DG8LsU.wWCk4iY9i','USER'),(10,'ddsd','$2a$10$iXaf7boN.wVY.vPF5uGFTelGfWruNZtSf8YqaOyHtjV7mOJo7auh.','USER'),(11,'lipo','$2a$10$SG2UNzbEgj2qIqpxsf819.meLXvXb1jOEwJ/ZXhdYs.SHHrJRnKMK','USER'),(12,'lollo','$2a$10$eX6gshGMRAWZ2JOytkN4k.72GJ3OITP2le3mVHxHS7bneDZcg2RVi','USER'),(13,'loris','$2a$10$dYkvMCn/oRSWZENIMZwIJuXajKByRDiS0GIN8eneTXC6LJPoPtK8O','USER'),(14,'oscar','$2a$10$PP3nhy.UNJ3cPHj83aqdauhTLNtIKDdUivdMDrwKaXzlqiP7kxhrC','USER'),(15,'dios','$2a$10$ncyD.hRLSdb45iIiypWbY.5R..xApn3/7MgUk1MvDDnT5IRyu5az.','USER'),(16,'dsdsadsadsa','$2a$10$g8Sq6rLzUmnWbVrok6ya9ecwizXhRT34jtGoHAHkeYqxrOybp.n.W','USER'),(17,'Micolo','$2a$10$886ZZtONwqrjBirLAQvYxusS7GgtyLYcduIKMMYJLs.CAVqhaxdeS','USER');
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

-- Dump completed on 2024-10-16 14:45:30
