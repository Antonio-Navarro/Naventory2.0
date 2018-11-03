-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: naventory_com
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `alerta_stock`
--

DROP TABLE IF EXISTS `alerta_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alerta_stock` (
  `id_alerta` int(11) NOT NULL AUTO_INCREMENT,
  `sku` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_alerta`),
  KEY `FK4xr47yyws7t8hll3mfrxmeusl` (`sku`),
  KEY `FKq28ky2bflt15j1x0e3uc7ar77` (`email`),
  CONSTRAINT `FK4xr47yyws7t8hll3mfrxmeusl` FOREIGN KEY (`sku`) REFERENCES `producto` (`sku`) ON DELETE CASCADE,
  CONSTRAINT `FKq28ky2bflt15j1x0e3uc7ar77` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerta_stock`
--

LOCK TABLES `alerta_stock` WRITE;
/*!40000 ALTER TABLE `alerta_stock` DISABLE KEYS */;
INSERT INTO `alerta_stock` VALUES (4,'444','anavarrodelamor@gmail.com');
/*!40000 ALTER TABLE `alerta_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idcat` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nomcat` varchar(255) DEFAULT NULL,
  `obser` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcat`),
  KEY `FKhsbtvtoboeu9a2rec85r5u4i6` (`email`),
  CONSTRAINT `FKhsbtvtoboeu9a2rec85r5u4i6` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'','procesadores','','anavarrodelamor@gmail.com'),(4,'','holi','','anavarrodelamor@gmail.com');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `novedades_categoria` AFTER INSERT ON `categoria` FOR EACH ROW insert into novedad (email,novedad) values (new.email,concat('<i class="fa fa-archive fa-fw"></i>&nbsp;Se ha creado una nueva categoria llamada <b> ',new.nomcat,' </b><span class="pull-right text-muted small"><em>',curdate(),' - ',curtime(),'</em></span>')) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fecha_alta` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `iva` int(11) DEFAULT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nombre_com` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `tel1` int(11) DEFAULT NULL,
  `tel2` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `FKbyhb77vjypfdxn8k1u1yhvkd2` (`email`),
  CONSTRAINT `FKbyhb77vjypfdxn8k1u1yhvkd2` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'MURCIA','cgg@gmail.com',30009,NULL,'Calle santa Marta 4 - Escalera A - 6B','2018-10-24 21:16:55',NULL,NULL,'Antonio Javier','del Amor',NULL,'España','Murcia',123123123,NULL,'anavarrodelamor@gmail.com'),(2,'MURCIA','cgg@gmail.com',30009,NULL,'Calle santa Marta 4 - Escalera A - 6B','2018-10-25 09:23:48',NULL,NULL,'Francisco Jose','Comercial Garcia',NULL,'España','Murcia',630783145,NULL,'anavarrodelamor@gmail.com'),(3,'MURCIA','cgg@gmail.com',30009,NULL,'Calle santa Marta 4 - Escalera A - 6B','2018-10-25 09:23:48',NULL,NULL,'Juan','Comercial Garcia',NULL,'España','Murcia',630783145,NULL,'anavarrodelamor@gmail.com'),(4,'MURCIA','cgg@gmail.com',30009,NULL,'Calle santa Marta 4 - Escalera A - 6B','2018-01-21 10:23:48',NULL,NULL,'Eduardo','Comercial Garcia',NULL,'España','Murcia',630783145,NULL,'anavarrodelamor@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `novedades_clientes` AFTER INSERT ON `cliente` FOR EACH ROW insert into novedad (email,novedad) values (new.email,concat('<i class="fa fa-street-view fa-fw"></i>Se ha creado una nuevo cliente llamado<b> ',new.nombre,' </b><span class="pull-right text-muted small"><em>',curdate(),' - ',curtime(),'</em>
                                    </span>')) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `id_comp` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `factura` varchar(255) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `iva` float DEFAULT NULL,
  `nombre_prod` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL,
  `id_prod` varchar(255) DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_comp`),
  KEY `FKtpr29k6y6l9e0ebd8b063or0h` (`email`),
  KEY `FKnbvy434i3nwiu6dk07dvpvlwj` (`id_prod`),
  KEY `FKo158ix00ljn91uet4xv15fq7o` (`id_proveedor`),
  CONSTRAINT `FKnbvy434i3nwiu6dk07dvpvlwj` FOREIGN KEY (`id_prod`) REFERENCES `producto` (`sku`) ON DELETE SET NULL,
  CONSTRAINT `FKo158ix00ljn91uet4xv15fq7o` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_prov`) ON DELETE SET NULL,
  CONSTRAINT `FKtpr29k6y6l9e0ebd8b063or0h` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (5,100,'',0,'123','2018-10-24 21:31:04',21,'sdf',10,1210,'Unidades',NULL,1,'anavarrodelamor@gmail.com'),(6,1,'',0,'1233','2018-10-24 21:35:55',21,'intel i5',100,121,'Unidades','444',1,'anavarrodelamor@gmail.com');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejemplo`
--

DROP TABLE IF EXISTS `ejemplo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejemplo` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ENUM_EJEMPLO` varchar(255) DEFAULT NULL,
  `FECHA_ALTA` datetime DEFAULT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `VALOR` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplo`
--

LOCK TABLES `ejemplo` WRITE;
/*!40000 ALTER TABLE `ejemplo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ejemplo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_pago`
--

DROP TABLE IF EXISTS `forma_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forma_pago` (
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pago`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_pago`
--

LOCK TABLES `forma_pago` WRITE;
/*!40000 ALTER TABLE `forma_pago` DISABLE KEYS */;
INSERT INTO `forma_pago` VALUES (1,'Transferencia'),(2,'Contado'),(3,'Tarjeta de crédito');
/*!40000 ALTER TABLE `forma_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novedad`
--

DROP TABLE IF EXISTS `novedad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novedad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `novedad` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novedad`
--

LOCK TABLES `novedad` WRITE;
/*!40000 ALTER TABLE `novedad` DISABLE KEYS */;
INSERT INTO `novedad` VALUES (1,'anavarrodelamor@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbsp;Se ha creado una nuevo proveedo llamado <b> samsung </b><span class=\"pull-right text-muted smal\"><em>2015-06-05 - 14:21:50</em>','2018-11-11 23:00:00'),(2,'anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbsp;Se ha creado una nueva categoria llamada <b> holi </b><span class=\"pull-right text-muted small\"><em>2018-10-27 - 19:44:58</em></span>','2018-10-27 17:44:58');
/*!40000 ALTER TABLE `novedad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `sku` varchar(255) NOT NULL,
  `coste` float DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_min` int(11) DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL,
  `id_cat` int(11) DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sku`),
  KEY `FKb9x42g38lkylfwmj2lhllhesi` (`id_cat`),
  KEY `FKkinjnx6sxv6kf9s6i21ttfnfo` (`id_proveedor`),
  KEY `FK5lmdffi9wxkqphbmmsvx88kkg` (`email`),
  CONSTRAINT `FK5lmdffi9wxkqphbmmsvx88kkg` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`),
  CONSTRAINT `FKb9x42g38lkylfwmj2lhllhesi` FOREIGN KEY (`id_cat`) REFERENCES `categoria` (`idcat`),
  CONSTRAINT `FKkinjnx6sxv6kf9s6i21ttfnfo` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_prov`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('444',100,'producto nuevo','intel i5','',200,46,60,'Unidades',1,1,'anavarrodelamor@gmail.com');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `id_prov` int(11) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nombre_com` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `tel1` int(11) DEFAULT NULL,
  `tel2` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_prov`),
  KEY `FK634jvqr6uws7bgv749ulni1rv` (`email`),
  CONSTRAINT `FK634jvqr6uws7bgv749ulni1rv` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'MURCIA','cgg@gmail.com',30009,'Calle santa Marta 4 - Escalera A - 6B','Antonio Javier','del Amor','España','Murcia',64312312,NULL,'anavarrodelamor@gmail.com');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `novedades_proveedor` AFTER INSERT ON `proveedor` FOR EACH ROW insert into novedad (email,novedad) values (new.email,concat('<i class="fa fa-truck fa-fw"></i>&nbspSe ha creado una nuevo proveedor llamado <b> ',new.nombre,' </b><span class="pull-right text-muted small"><em>',curdate(),' - ',curtime(),'</em>
                                    </span>')) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `email` varchar(255) NOT NULL,
  `activo` varchar(1) DEFAULT 'Y',
  `administrador` varchar(1) DEFAULT 'N',
  `apellido` varchar(255) DEFAULT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `fecha_alta` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `foto_perf` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('aj2@aj.es','Y','Y','del Amor','TFG','2018-10-25 20:49:26',NULL,'Antonio Javier','$2a$12$qrGg1rXQlQa642xCxN8XQuE8xQ0nomql3lIVYA/LjTkuLH91sfbiG',NULL,NULL),('aj@aj.es','Y','N','del Amor','TFG','2018-10-23 20:49:26',NULL,'Antonio Javier','$2a$12$qrGg1rXQlQa642xCxN8XQuE8xQ0nomql3lIVYA/LjTkuLH91sfbiG',NULL,NULL),('anavarrodelamor@gmail.com','Y','Y','del Amor','TFG','2018-10-24 20:49:26',NULL,'Antonio Javier','$2a$12$qrGg1rXQlQa642xCxN8XQuE8xQ0nomql3lIVYA/LjTkuLH91sfbiG',NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `id_vent` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `iva` float DEFAULT NULL,
  `nombre_prod` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `forma_pago` int(11) DEFAULT NULL,
  `id_prod` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_vent`),
  KEY `FKlodcalyagjbta88cddpehw1ks` (`forma_pago`),
  KEY `FK401bdn7lmsebra4xy5ck7fi9j` (`email`),
  KEY `FK3g2nu60jixeh9ujhn24udumbe` (`id_prod`),
  KEY `FKsor2qmi3thao7a8or49vlohp9` (`id_cliente`),
  CONSTRAINT `FK3g2nu60jixeh9ujhn24udumbe` FOREIGN KEY (`id_prod`) REFERENCES `producto` (`sku`) ON DELETE SET NULL,
  CONSTRAINT `FK401bdn7lmsebra4xy5ck7fi9j` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`),
  CONSTRAINT `FKlodcalyagjbta88cddpehw1ks` FOREIGN KEY (`forma_pago`) REFERENCES `forma_pago` (`id_pago`),
  CONSTRAINT `FKsor2qmi3thao7a8or49vlohp9` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (2,60,'',0,'2018-10-24 21:32:18',21,'sdf',23,1669.8,'Unidades',1,2,NULL,'anavarrodelamor@gmail.com'),(3,1,'',0,'2018-09-25 15:51:10',21,'intel i5',200,242,'Unidades',1,2,'444','anavarrodelamor@gmail.com'),(4,4,'',0,'2018-10-25 21:02:16',21,'intel i5',200,968,'Unidades',1,3,'444','anavarrodelamor@gmail.com');
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-30 18:27:46
