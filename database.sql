CREATE DATABASE  IF NOT EXISTS `GomConCept` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `GomConCept`;
-- MySQL dump 10.13  Distrib 8.0.42, for macos15 (arm64)
--
-- Host: localhost    Database: GomConCept
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `ChiTietSanPham`
--

DROP TABLE IF EXISTS `ChiTietSanPham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChiTietSanPham` (
  `MaChiTiet` int NOT NULL AUTO_INCREMENT,
  `MaSanPham` int NOT NULL,
  `KichThuoc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ChieuCao` int DEFAULT NULL,
  `ChieuRong` int DEFAULT NULL,
  `GiaTien` decimal(12,0) DEFAULT NULL,
  `SoLuongTrongKho` int DEFAULT NULL,
  PRIMARY KEY (`MaChiTiet`),
  KEY `FK_CTSP_SP_idx` (`MaSanPham`),
  CONSTRAINT `FK_CTSP_SP` FOREIGN KEY (`MaSanPham`) REFERENCES `SanPham` (`MaSanPham`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChiTietSanPham`
--

LOCK TABLES `ChiTietSanPham` WRITE;
/*!40000 ALTER TABLE `ChiTietSanPham` DISABLE KEYS */;
INSERT INTO `ChiTietSanPham` VALUES (1,1,'S',20,20,139000,0),(2,1,'M',22,20,159000,0),(3,1,'L',25,30,200000,0),(4,4,'S',20,20,129000,3),(5,4,'M',22,20,139000,1);
/*!40000 ALTER TABLE `ChiTietSanPham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NguoiDung`
--

DROP TABLE IF EXISTS `NguoiDung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NguoiDung` (
  `MaNguoiDung` int NOT NULL AUTO_INCREMENT,
  `HoVaTen` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MatKhau` text,
  `VaiTro` varchar(45) NOT NULL,
  PRIMARY KEY (`MaNguoiDung`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NguoiDung`
--

LOCK TABLES `NguoiDung` WRITE;
/*!40000 ALTER TABLE `NguoiDung` DISABLE KEYS */;
INSERT INTO `NguoiDung` VALUES (1,'Trần Công Hậu','thau1298@gmail.com','$2a$10$gERFkaWLWtmwslKCQHxu1uphABK5pMbnpkrIlbbAYn7RHLle0tHdS','HaiBaTrung'),(2,'Quân Nguyễn Bảo','nbquaan@gmail.com',NULL,'NgoaiHaiBaTrung');
/*!40000 ALTER TABLE `NguoiDung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SanPham`
--

DROP TABLE IF EXISTS `SanPham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SanPham` (
  `MaSanPham` int NOT NULL AUTO_INCREMENT,
  `TenSanPham` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `GiaTienCoBan` decimal(12,0) NOT NULL,
  `MoTa` text NOT NULL,
  `HinhAnhHienThi` text NOT NULL,
  `HinhAnh2` text,
  `HinhAnh3` text,
  `HinhAnh4` text,
  `TinhTrang` bit(1) NOT NULL COMMENT '0 - Còn, 1 - Hết',
  `NgayThemSanPham` datetime DEFAULT NULL,
  `Slug` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MaSanPham`),
  UNIQUE KEY `Slug_UNIQUE` (`Slug`),
  KEY `idx_Slug` (`Slug`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SanPham`
--

LOCK TABLES `SanPham` WRITE;
/*!40000 ALTER TABLE `SanPham` DISABLE KEYS */;
INSERT INTO `SanPham` VALUES (1,'Bình gốm men xôi',1139000,'Vẻ ngoài sần sùi trông đẹp','https://scontent.fsgn2-6.fna.fbcdn.net/v/t39.30808-6/512097047_122122919660844036_8473380086021434765_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=833d8c&_nc_ohc=b2JzoXth0aQQ7kNvwG7rsau&_nc_oc=AdlxvOyIB-J0vgsRcar_g6CctXLhVntgnvEUkUTWkUCHYwXKUqNymHQtNMwFPoNwIw39NVOC3lt8P6VZ5wOG-pJK&_nc_zt=23&_nc_ht=scontent.fsgn2-6.fna&_nc_gid=xirqU171cc9QOjlH3XVDAA&oh=00_AfOou31Cr45z2e5I2LnIez0ElTTrd0OnKuoHZMhlgc9VBw&oe=686DB02A',NULL,NULL,NULL,_binary '',NULL,'binh-gom-men-xoi'),(2,'Bình sọc ',150000,'Vẻ ngoài vằn','https://scontent.fsgn2-6.fna.fbcdn.net/v/t39.30808-6/504344173_122118885014844036_3727232274862954149_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=833d8c&_nc_ohc=pDeSjca-IngQ7kNvwHgnFN1&_nc_oc=AdldFY6vmRLkHamBbjhWcwoxtDfbw5D2mPws8kJMkWD-MoGvL8a3ICD4vbke6ZeMtkTrmLT2Ha3uFaD-2uoLGVkx&_nc_zt=23&_nc_ht=scontent.fsgn2-6.fna&_nc_gid=OEK_DI8j31-Do3bU8K8TZA&oh=00_AfO2yDPKZejH1S4DwS77AAvjchLH1UUq80i_3D6RpvNEvQ&oe=686BF9C3',NULL,NULL,NULL,_binary '\0',NULL,'binh-soc'),(3,'Bình tinh tế',139000,'Vẻ ngoài vằn','https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/500263680_122115576800844036_4736186736638036363_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=127cfc&_nc_ohc=uXsyd-Z7DmoQ7kNvwH5q-Cn&_nc_oc=AdmxfCg9qQwD5hbEFFW8v58C0jxHAwhL0pyyNolfcy4c4AU9J8ma_U3zSfCxz0ddf6zJ8xJ9Jlg6zH9nMaT2OZ9E&_nc_zt=23&_nc_ht=scontent.fsgn2-4.fna&_nc_gid=6UfMg2dGcOSvteduFBUCeA&oh=00_AfN36g_wzeB0FUWVowBAH1ZsHXt1tihKXXmzIE758j-7fw&oe=686C0980',NULL,NULL,NULL,_binary '\0',NULL,'binh-tinh-te'),(4,'Bình uống rượu',100000,'vẻ ngoài vằn','https://scontent.fsgn2-3.fna.fbcdn.net/v/t39.30808-6/500387622_122115569540844036_1521388500162612029_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=127cfc&_nc_ohc=IiPSDcwkyY8Q7kNvwHqR5hj&_nc_oc=Adm-kv3yHhZ3n2mSvKm_VrjPu-iMu9EjjFJge-0V9989pZVJb4kToeQ2jtLLEagHxmwFeQ8fPhRnDBaQ__oHBAdN&_nc_zt=23&_nc_ht=scontent.fsgn2-3.fna&_nc_gid=mWcAJ3PNjyVi9eEMld1lEw&oh=00_AfNDtP6x9g-3kTo3D6Ib53uznYHyeXWCJ_5RoETTzZ7pUA&oe=686BFE3E','https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/500525057_122115569588844036_5710604549000544789_n.jpg?stp=dst-jpg_s552x414_tt6&_nc_cat=101&ccb=1-7&_nc_sid=714c7a&_nc_ohc=Vcx-PsWkXKsQ7kNvwGgeh3X&_nc_oc=Adn3fTTrScw0iuoNjtzIgM3OSbJSd6fXlYIRA35chwD2phzTn_zAVwCV4aV3qHn76K7hYbMh-paGJITnyyT0Bb0G&_nc_zt=23&_nc_ht=scontent.fsgn2-4.fna&_nc_gid=CUTrVdOSTfhPBHMZEN2H7A&oh=00_AfP0E7Wd_vOlIWgCzeG5qsyjIzV1GRo-k4R7cVN7rlifRw&oe=686C7ECC','https://scontent.fsgn2-9.fna.fbcdn.net/v/t39.30808-6/500276008_122115569558844036_626690972304544098_n.jpg?stp=dst-jpg_s552x414_tt6&_nc_cat=103&ccb=1-7&_nc_sid=714c7a&_nc_ohc=i2WGA6ji4ugQ7kNvwFajd6A&_nc_oc=Adnqr0O2ACMfGhDVfv0lO1lyxSnJ7Ih73aaEdmclOs9-lbkdw5EvWegvA3mCK1zU6nHI93sLODSket-8z67JulkO&_nc_zt=23&_nc_ht=scontent.fsgn2-9.fna&_nc_gid=0LFlAFTB-Mx0UWFwRAwDkw&oh=00_AfMETm_3Gy4mKZVaX6_hdvrkFiqE6bnH7iBwDKrCyQbffA&oe=686C6580','https://scontent.fsgn2-6.fna.fbcdn.net/v/t39.30808-6/501036347_122115569546844036_369675742444724936_n.jpg?stp=dst-jpg_s552x414_tt6&_nc_cat=111&ccb=1-7&_nc_sid=714c7a&_nc_ohc=1U75utypBq0Q7kNvwHSx2W7&_nc_oc=AdnkD_IcElimbp0_6kA8IKJ833_5xUlbUDb4Zi2eB5DqXYkJB2KrGkRBkajpBaIJbEdQmnfbtKahS2rOQcl2obEs&_nc_zt=23&_nc_ht=scontent.fsgn2-6.fna&_nc_gid=0LFlAFTB-Mx0UWFwRAwDkw&oh=00_AfPXYi-QVkKlK_E-K5srLvOmHh1WrB37AHO1HNT6ksJCwQ&oe=686C6A54',_binary '\0',NULL,'binh-uong-ruou'),(5,'Bình 3',120000,'đẹp','https://scontent.fsgn2-9.fna.fbcdn.net/v/t39.30808-6/500274513_122114595674844036_5586388290730182784_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=833d8c&_nc_ohc=C8clsdv5ExMQ7kNvwHNrbzM&_nc_oc=AdkFQLFHIugnl_o7oDl4A2ACtgoe3Tm67i7KGxYpQpexBdeh3ORj0LIRGZdIi3Eg42UUi01X8dcUm02Wg2xJrBTt&_nc_zt=23&_nc_ht=scontent.fsgn2-9.fna&_nc_gid=ERtHM3VfvDmwcxTtNelMWw&oh=00_AfP_Sqrvf6u44xElk3v7ca6Qy_5xaA5DyxqJz5BklPFALQ&oe=686C0B36',NULL,NULL,NULL,_binary '\0','2025-07-03 00:00:00','binh-3'),(7,'Bình 4',130000,'đẹp','https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/500273434_122114596682844036_4639395023331278302_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=833d8c&_nc_ohc=z7nAlX9LGysQ7kNvwFHKDBB&_nc_oc=Adm4g0Rjqwd8qJ6MLPT2aP_Woq6b82VER-0oimmreuEeQLAhK4LiopPXVNu0CA6tmKyo4QTx4nrhgcu2y8CrLIec&_nc_zt=23&_nc_ht=scontent.fsgn2-4.fna&_nc_gid=Qcc-Y3BMEgjMRt_gd_FMog&oh=00_AfPue8LLJeXK8twkl1rwO1QmOey_TdSlTnzEEgYibc6x-g&oe=686C1AF3',NULL,NULL,NULL,_binary '','2025-07-03 00:00:00','binh-4'),(8,'Bình 5',140000,'đẹp luôn','https://scontent.fsgn2-7.fna.fbcdn.net/v/t39.30808-6/500320448_122114594546844036_7108944947913199825_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=833d8c&_nc_ohc=nMBe3Vfd8d4Q7kNvwHkJK42&_nc_oc=Adm89bD-g9GcO8tnKP4_RTpwRVk_v171_MahDiO54yIFnI_hNFxjCrVju14v0_oS5g4&_nc_zt=23&_nc_ht=scontent.fsgn2-7.fna&_nc_gid=Rnh5j6ip6TS9HUD3PaqKJg&oh=00_AfMa5n-70grpcJP_Q6gvNDHFEbD8jQviOPzixpaTeaSYvQ&oe=686C100B',NULL,NULL,NULL,_binary '','2025-07-03 00:00:00','binh-5'),(9,'Bình 6',150000,'không đẹp','https://scontent.fsgn2-9.fna.fbcdn.net/v/t39.30808-6/500358312_122114593814844036_6899202136586803759_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=833d8c&_nc_ohc=-xaVmqQcDJEQ7kNvwGP561g&_nc_oc=AdlwHRVTTKgIcjGAVnM74pJpTtcJ9SqGLOBGpsoBneuqq-juOrF5O9Vf9atCVG2t2Es&_nc_zt=23&_nc_ht=scontent.fsgn2-9.fna&_nc_gid=a-JbR1lkEFzwUD-aaPRwqg&oh=00_AfMqvKkT-IITRsRCADl6JNDJEwmv3-QULoKrKBiubXKd0A&oe=686C19EE',NULL,NULL,NULL,_binary '','2025-07-03 00:00:00','binh-6'),(10,'Bình 7',160000,'Đẹp không','https://scontent.fsgn2-3.fna.fbcdn.net/v/t39.30808-6/499571545_122113957016844036_9061428180612092418_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=833d8c&_nc_ohc=kWTg0RB-RZkQ7kNvwHn0-iU&_nc_oc=AdnosUr6pIuHUJ-YDpo1UHo38ydYYf3uc2htztEfgqQh9pTTWvoSlmNPIKfDZIgJ1cA&_nc_zt=23&_nc_ht=scontent.fsgn2-3.fna&_nc_gid=9r8PO-JFSy9SIkGR7EApzQ&oh=00_AfOXkcWZrPK2ObSr4IpnzOc4K0CMQtN09xtMbysuPh0cuQ&oe=686C26CF',NULL,NULL,NULL,_binary '\0','2025-07-03 00:00:00','binh-7'),(11,'Bình 8',170000,'hihi','https://scontent.fsgn2-9.fna.fbcdn.net/v/t39.30808-6/499379921_122113957568844036_6662054803272038177_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=833d8c&_nc_ohc=8_92u41LbAsQ7kNvwF9fQRS&_nc_oc=Adnxl65DiqcD-QqsY7Fg2pP89mGBNL1bGkQj-7gutNKcR63Zdst-iRTXRMfhmUggN9wCUAYx19dhVR-AHVulxZLB&_nc_zt=23&_nc_ht=scontent.fsgn2-9.fna&_nc_gid=oMqxv2NQpyalnz4807YbVA&oh=00_AfMJR6goSTW5teBTQMmPRxsVUfuloS8JJ4V_CTFsbvKTkA&oe=686C071A',NULL,NULL,NULL,_binary '\0','2025-07-03 08:19:10','binh-8');
/*!40000 ALTER TABLE `SanPham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-09 20:02:54
