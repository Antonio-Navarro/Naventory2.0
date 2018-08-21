CREATE DATABASE  IF NOT EXISTS `naventory_com` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `naventory_com`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: naventory_com
-- ------------------------------------------------------
-- Server version	8.0.11

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
  `email` varchar(255) DEFAULT NULL,
  `sku` varchar(255) DEFAULT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_alerta`),
  KEY `sku` (`sku`),
  KEY `email` (`email`),
  CONSTRAINT `alerta_stock_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `alerta_stock_ibfk_2` FOREIGN KEY (`sku`) REFERENCES `producto` (`sku`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerta_stock`
--

LOCK TABLES `alerta_stock` WRITE;
/*!40000 ALTER TABLE `alerta_stock` DISABLE KEYS */;
INSERT INTO `alerta_stock` VALUES (1,'expo@naventory.es','WD10EZEX','WD blue',5,'Unidades'),(2,'expo@naventory.es','75E250B','Samsung 850 Evo',3,'Unidades');
/*!40000 ALTER TABLE `alerta_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idcat` int(4) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nomcat` varchar(255) DEFAULT NULL,
  `desc` text,
  `obser` text,
  PRIMARY KEY (`idcat`),
  KEY `email` (`email`),
  CONSTRAINT `FK_email` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (35,'expo@naventory.es','Procesadores','Categoría de procesadores','Principalmente AMD o INTEL'),(36,'expo@naventory.es','Discos duros','Categorías de discos duros','WD, Samsung, Thoshiba, etc'),(37,'expo@naventory.es','RAM','Categoría para la memoria RAM','G.Skill, Kingston, Samsung, etc.');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id_chat` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `mensaje` text,
  `foto_perfil` varchar(255) DEFAULT 'img/foto_null.jpg',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_chat`),
  KEY `email` (`email`),
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'expo@naventory.es','Usando Naventory para gestionar el inventario','img/foto_null.jpg','2016-01-18 09:39:49');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nombre_com` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `tel1` int(11) DEFAULT NULL,
  `tel2` int(11) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `iva` int(11) DEFAULT '21',
  `fecha_alta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `observaciones` text,
  PRIMARY KEY (`id_cliente`),
  KEY `email` (`email`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'anavarrodelamor@gmail.com','Pepe Sevilla','Jose sanchez sevilla','77759826T','calle alcla','Mula','Murcia',30040,'España',661124098,661124098,'pepe@asir.com',NULL,21,'2016-01-12 12:56:23',''),(46,'expo@naventory.es','Jose Espín','Jose Espín Guirao','77731921J','Gran vía','Cehegin','Murcia',30021,'España',616371594,616371594,'jose.espin18@gmail.com',0,21,'2015-05-17 12:52:34','niguna'),(47,'expo@naventory.es','Navarro','Antonio Javier Navarro','77759826T','Pasico 45','Calasparra','Murcia',30420,'España',630783145,968746309,'anavarrodelamor@gmail.com',0,21,'2015-06-16 12:53:31','yo'),(48,'expo@naventory.es','Martínez','Cafetería Martínez','B12321231','Calle Miguel Hernandez','Calasparra','Murcia',30420,'España',968231656,968231656,'matinez@gmail.com',0,21,'2015-07-15 12:54:49',''),(49,'expo@naventory.es','Digio','Digio soluciones digitales','B23123112','Campus Espinado','Murcia','Murcia',30009,'España',912789324,912789324,'digio@info.es',0,21,'2015-07-17 12:55:51',''),(50,'expo@naventory.es','Valera','Daniel Valera Reina','48123783T','Gran vía','Caravaca','Murcia',30430,'España',664047942,664047942,'valera@gmail.com',0,21,'2015-06-18 12:58:08',''),(51,'expo@naventory.es','Carlos','Carlos López Toro','77829231H','Avenida de la constitución','Caravaca','Murcia',30430,'España',666777888,666777888,'carlos.toro@gmail.com',0,21,'2015-06-18 12:59:06',''),(52,'expo@naventory.es','Guille','Guillermo Sevilla','48123212P','Aguas saladas','Cehegin','Murcia',30410,'Epsaña',762123121,762123121,'guille@gmail.com',0,21,'2015-08-16 13:00:30',''),(53,'expo@naventory.es','Inforges','Inforges SL','B32123123','Juan Carlos i','Murcia','Murcia',30009,'España',902923241,902923241,'inforges@gmail.com',0,21,'2015-08-16 13:01:45',''),(54,'expo@naventory.es','Damian','Damian Moya','777892313','Barrio nuevo Pozo','Calasparra','Murcia',30420,'España',666123891,666123891,'damianmoyaperez@gmail.com',0,21,'2015-08-17 13:02:52',''),(55,'expo@naventory.es','Piku','Juan Sanchez de Paco','77731921J','Calle Canteras','Calasparra','Murcai',30420,'España',666123891,968746309,'piku@gmail.com',0,21,'2015-08-17 13:03:49',''),(56,'expo@naventory.es','Depau','Punto de informática','B33231232','Calle espartero','Calasparra','Murcia',30420,'España',666123891,968746309,'depau@gmail.com',0,21,'2015-06-17 13:04:29','');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `id_comp` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `nombre_proveedor` varchar(255) DEFAULT NULL,
  `id_prod` varchar(255) NOT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `factura` varchar(255) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `iva` int(11) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_comp`),
  KEY `email` (`email`,`id_proveedor`,`id_prod`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_prod` (`id_prod`),
  CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (14,'expo@naventory.es',18,'Westerm Digital','WD10EZEX','WD blue','2015-04-17 13:15:45','23122H12',5,'Unidades',54,0,21,326.7,''),(15,'expo@naventory.es',19,'Sasmsung','75E250B','Samsung 850 Evo','2015-05-17 13:16:15','HS12312',5,'Unidades',108,0,26,680.4,'ninguna'),(16,'expo@naventory.es',21,'Intel','BX8064I','Intel Core I7-4790K','2015-06-17 13:21:59','JH231H',2,'Unidades',335,0,21,810.7,'');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejemplo`
--

DROP TABLE IF EXISTS `ejemplo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejemplo` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `fecha_alta` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `enum_ejemplo` varchar(45) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplo`
--

LOCK TABLES `ejemplo` WRITE;
/*!40000 ALTER TABLE `ejemplo` DISABLE KEYS */;
INSERT INTO `ejemplo` VALUES (1,'ANTONIO','2018-07-14 16:53:24','HOLA',2.3);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_pago`
--

LOCK TABLES `forma_pago` WRITE;
/*!40000 ALTER TABLE `forma_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `forma_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identificacion`
--

DROP TABLE IF EXISTS `identificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identificacion` (
  `id_ident` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_ident`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identificacion`
--

LOCK TABLES `identificacion` WRITE;
/*!40000 ALTER TABLE `identificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `identificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `de` varchar(255) DEFAULT NULL,
  `hacia` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `mensaje` text,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
INSERT INTO `mensaje` VALUES (25,'antonio-navarro19@hotmail.com','antonio-navarro19@hotmail.com','Antonio','navarro','askdljf adklsñ \n','2015-06-02 17:10:00'),(33,'anavarrodelamor@gmail.com','anavarrodelamor@gmail.com','Antonio Javier ','Navarro del Amor','asdfasd','2015-06-02 17:19:18'),(35,'anavarrodelamor@gmail.com','antonio-navarro19@hotmail.com','Antonio Javier ','Navarro del Amor','asdkjfsalkdfjl-asd','2015-06-02 17:19:59'),(39,'anavarrodelamor@gmail.com','antonio-navarro19@hotmail.com','Antonio Javier ','Navarro del Amor','jdfkljaskld\n','2015-06-02 17:21:55'),(40,'anavarrodelamor@gmail.com','antonio-navarro19@hotmail.com','Antonio Javier ','Navarro del Amor','dsljfalñsdka','2015-06-02 17:22:06'),(41,'anavarrodelamor@gmail.com','anavarrodelamor@gmail.com','Antonio Javier ','Navarro del Amor','sdlkñjfalsdk\n','2015-06-02 17:22:25'),(45,'anavarrodelamor@gmail.com','anavarrodelamor@gmail.com','Antonio Javier ','Navarro del Amor','lkasjdflñasd','2015-06-03 09:36:56'),(47,'anavarrodelamor@gmail.com','anavarrodelamor@gmail.com','Antonio Javier ','Navarro del Amor','asdf','2015-06-03 11:12:47'),(48,'anavarrodelamor@gmail.com','anavarrodelamor@gmail.com','Antonio','Navarro','esto es una prueba','2015-06-15 20:46:40'),(49,'expo@naventory.es','anavarrodelamor@gmail.com','Antonio Javier','Navarro del Amor','Hola estamos realizando el qa de Naventory\n','2015-06-17 11:24:00'),(50,'anavarrodelamor@gmail.com','expo@naventory.es','Antonio Javier','Navarro del Amor','Suerte con la exposicion','2015-06-17 11:24:26'),(51,'anavarrodelamor@gmail.com','expo@naventory.com','Antonio Javier','Navarro del Amor','¿Podrías realizar un presupuesto para la empresa Digio?','2016-01-18 10:06:01'),(52,'anavarrodelamor@gmail.com','expo@naventory.es','Antonio Javier','Navarro del Amor','¿Podrías realizar un presupuesto para la empresa Digio?','2016-01-18 10:07:14'),(53,'anavarrodelamor@gmail.com','expo@naventory.es','Antonio Javier','Navarro del Amor','Acuérdese de realizar la copia de seguridad del último mes','2016-01-18 10:07:34');
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novedad`
--

DROP TABLE IF EXISTS `novedad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novedad` (
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `novedad` text CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novedad`
--

LOCK TABLES `novedad` WRITE;
/*!40000 ALTER TABLE `novedad` DISABLE KEYS */;
INSERT INTO `novedad` VALUES (NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> samsung </b><span class=\"pull-right text-muted small\"><em>2015-06-05 - 14:21:50</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> monitores </b><span class=\"pull-right text-muted small\"><em>2015-06-05 - 14:22:03</em></span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-user fa-fw\"></i>&nbspSe ha creado una nuevo usuario llamado <b> oscar@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-05 - 14:26:31</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> papiro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-05 - 14:27:45</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 12:39:39</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 12:43:33</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Apple store </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:00:25</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> samsung </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:01:25</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria dolores </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:02:01</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cerrajería Navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:03:07</em>\r\n                                    </span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> portátiles </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:03:54</em></span>','0000-00-00 00:00:00'),(NULL,'<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> moviles </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:04:14</em></span>','0000-00-00 00:00:00'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> trigger </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:13:00</em></span>','0000-00-00 00:00:00'),('naventory@qa.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> trigger </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:14:44</em></span>','0000-00-00 00:00:00'),('naventory@qa.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> trigger qa </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 13:22:27</em></span>','0000-00-00 00:00:00'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 14:03:34</em></span>','0000-00-00 00:00:00'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 14:10:19</em></span>','0000-00-00 00:00:00'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> adsfasd </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 14:12:04</em></span>','2015-06-06 12:12:04'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> fasdfasdfas </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 14:12:06</em></span>','2015-06-06 12:12:06'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> ultima </b><span class=\"pull-right text-muted small\"><em>2015-06-06 - 14:12:09</em></span>','2015-06-06 12:12:09'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> librería lavador </b><span class=\"pull-right text-muted small\"><em>2015-06-08 - 10:37:04</em>\r\n                                    </span>','2015-06-08 08:37:04'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-08 - 19:00:15</em>\r\n                                    </span>','2015-06-08 17:00:15'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> juvasa </b><span class=\"pull-right text-muted small\"><em>2015-06-08 - 22:47:24</em>\r\n                                    </span>','2015-06-08 20:47:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria </b><span class=\"pull-right text-muted small\"><em>2015-06-09 - 10:53:59</em>\r\n                                    </span>','2015-06-09 08:53:59'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan Núñez </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 09:27:19</em>\r\n                                    </span>','2015-06-10 07:27:19'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Samsung Corporative </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 12:45:30</em>\r\n                                    </span>','2015-06-10 10:45:30'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 12:45:59</em>\r\n                                    </span>','2015-06-10 10:45:59'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> samsung </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 12:50:53</em>\r\n                                    </span>','2015-06-10 10:50:53'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> samsung </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 12:53:01</em>\r\n                                    </span>','2015-06-10 10:53:01'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> casio sl </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 13:03:24</em>\r\n                                    </span>','2015-06-10 11:03:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Daniel Valera Reina </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 14:45:51</em>\r\n                                    </span>','2015-06-10 12:45:51'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Apple </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 15:03:58</em>\r\n                                    </span>','2015-06-10 13:03:58'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> danone </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 18:48:06</em>\r\n                                    </span>','2015-06-10 16:48:06'),('m.-dolores@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> m.-dolores@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 20:32:55</em>\r\n                                    </span>','2015-06-10 18:32:55'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> proveedor </b><span class=\"pull-right text-muted small\"><em>2015-06-10 - 20:38:41</em>\r\n                                    </span>','2015-06-10 18:38:41'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan sachez fernandez </b><span class=\"pull-right text-muted small\"><em>2015-06-11 - 13:08:16</em>\r\n                                    </span>','2015-06-11 11:08:16'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-11 - 13:08:28</em>\r\n                                    </span>','2015-06-11 11:08:28'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-11 - 13:08:47</em>\r\n                                    </span>','2015-06-11 11:08:47'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-13 - 18:37:55</em>\r\n                                    </span>','2015-06-13 16:37:55'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> portátiles-samsung </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:19</em></span>','2015-06-14 09:10:19'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> moviles </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:19</em></span>','2015-06-14 09:10:19'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> trigger </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:19</em></span>','2015-06-14 09:10:19'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:19</em></span>','2015-06-14 09:10:19'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:19</em></span>','2015-06-14 09:10:19'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> adsfasd </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:19</em></span>','2015-06-14 09:10:19'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> fasdfasdfas </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:19</em></span>','2015-06-14 09:10:19'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> ultima </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:19</em></span>','2015-06-14 09:10:19'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> portátiles-samsung </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:46</em></span>','2015-06-14 09:10:46'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> moviles </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:46</em></span>','2015-06-14 09:10:46'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> trigger </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:46</em></span>','2015-06-14 09:10:46'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:46</em></span>','2015-06-14 09:10:46'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:46</em></span>','2015-06-14 09:10:46'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> adsfasd </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:46</em></span>','2015-06-14 09:10:46'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> fasdfasdfas </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:46</em></span>','2015-06-14 09:10:46'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> ultima </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:10:46</em></span>','2015-06-14 09:10:46'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Apple </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:14:49</em>\r\n                                    </span>','2015-06-14 09:14:49'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> danone </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:14:49</em>\r\n                                    </span>','2015-06-14 09:14:49'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria dolores navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cerrajería Navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> juvasa </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan Núñez </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Daniel Valera Reina </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan sachez fernandez </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 11:22:23</em>\r\n                                    </span>','2015-06-14 09:22:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria dolores navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cerrajería Navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> juvasa </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan Núñez </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Daniel Valera Reina </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan sachez fernandez </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 12:07:29</em>\r\n                                    </span>','2015-06-14 10:07:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 14:53:58</em>\r\n                                    </span>','2015-06-14 12:53:58'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 14:57:21</em>\r\n                                    </span>','2015-06-14 12:57:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:33:29</em>\r\n                                    </span>','2015-06-14 14:33:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> portátiles-samsung </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:38:21</em></span>','2015-06-14 14:38:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> moviles </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:38:21</em></span>','2015-06-14 14:38:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> trigger </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:38:21</em></span>','2015-06-14 14:38:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:38:21</em></span>','2015-06-14 14:38:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:38:21</em></span>','2015-06-14 14:38:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> adsfasd </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:38:21</em></span>','2015-06-14 14:38:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> fasdfasdfas </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:38:21</em></span>','2015-06-14 14:38:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> ultima </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:38:21</em></span>','2015-06-14 14:38:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:39:48</em>\r\n                                    </span>','2015-06-14 14:39:48'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:51:38</em>\r\n                                    </span>','2015-06-14 14:51:38'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 16:53:48</em>\r\n                                    </span>','2015-06-14 14:53:48'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> asdfasd </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 17:04:29</em></span>','2015-06-14 15:04:29'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 18:12:20</em>\r\n                                    </span>','2015-06-14 16:12:20'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 18:41:01</em>\r\n                                    </span>','2015-06-14 16:41:01'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 18:43:05</em>\r\n                                    </span>','2015-06-14 16:43:05'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 18:44:38</em>\r\n                                    </span>','2015-06-14 16:44:38'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 18:46:38</em>\r\n                                    </span>','2015-06-14 16:46:38'),('m.-dolores@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> m.-dolores@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 19:07:17</em>\r\n                                    </span>','2015-06-14 17:07:17'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 19:12:12</em>\r\n                                    </span>','2015-06-14 17:12:12'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 19:15:31</em>\r\n                                    </span>','2015-06-14 17:15:31'),('jose18022012@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> jose18022012@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 19:18:42</em>\r\n                                    </span>','2015-06-14 17:18:42'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 19:21:39</em>\r\n                                    </span>','2015-06-14 17:21:39'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 19:23:09</em>\r\n                                    </span>','2015-06-14 17:23:09'),('asturx@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> asturx@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 21:15:46</em>\r\n                                    </span>','2015-06-14 19:15:46'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:33:44</em>\r\n                                    </span>','2015-06-14 20:33:44'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> portátiles-samsung </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:34:56</em></span>','2015-06-14 20:34:56'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> moviles </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:34:56</em></span>','2015-06-14 20:34:56'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> trigger </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:34:56</em></span>','2015-06-14 20:34:56'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:34:56</em></span>','2015-06-14 20:34:56'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:34:56</em></span>','2015-06-14 20:34:56'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> adsfasd </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:34:56</em></span>','2015-06-14 20:34:56'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> fasdfasdfas </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:34:56</em></span>','2015-06-14 20:34:56'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> ultima </b><span class=\"pull-right text-muted small\"><em>2015-06-14 - 22:34:56</em></span>','2015-06-14 20:34:56'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria dolores navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cerrajería Navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> juvasa </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan Núñez </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Daniel Valera Reina </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan sachez fernandez </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 10:50:50</em>\r\n                                    </span>','2015-06-15 08:50:50'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Apple </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 12:31:49</em>\r\n                                    </span>','2015-06-15 10:31:49'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> danone </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 12:31:49</em>\r\n                                    </span>','2015-06-15 10:31:49'),('anavarrodelamor@gmasil.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmasil.com </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 20:02:01</em>\r\n                                    </span>','2015-06-15 18:02:01'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 20:25:21</em>\r\n                                    </span>','2015-06-15 18:25:21'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Mediar Markt </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 20:47:53</em>\r\n                                    </span>','2015-06-15 18:47:53'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Ordenadores </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 20:47:59</em></span>','2015-06-15 18:47:59'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> prueba </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 20:53:33</em></span>','2015-06-15 18:53:33'),(NULL,'<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Mediar Markt </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 20:54:00</em>\r\n                                    </span>','2015-06-15 18:54:00'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> OPEL </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 20:56:41</em>\r\n                                    </span>','2015-06-15 18:56:41'),('anavarrodelamor@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Mediar Markt </b><span class=\"pull-right text-muted small\"><em>2015-06-15 - 20:58:25</em>\r\n                                    </span>','2015-06-15 18:58:25'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cerrajería Navarro, S.L. </b><span class=\"pull-right text-muted small\"><em>2015-06-16 - 09:12:29</em>\r\n                                    </span>','2015-06-16 07:12:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> OPEL </b><span class=\"pull-right text-muted small\"><em>2015-06-16 - 09:28:04</em>\r\n                                    </span>','2015-06-16 07:28:04'),('anavarrodelamor@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Hierros del noroeste </b><span class=\"pull-right text-muted small\"><em>2015-06-16 - 09:43:01</em>\r\n                                    </span>','2015-06-16 07:43:01'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Vigas </b><span class=\"pull-right text-muted small\"><em>2015-06-16 - 10:19:58</em></span>','2015-06-16 08:19:58'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Tubos </b><span class=\"pull-right text-muted small\"><em>2015-06-16 - 10:22:27</em></span>','2015-06-16 08:22:27'),('oscarmica@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> oscarmica@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-16 - 12:31:47</em>\r\n                                    </span>','2015-06-16 10:31:47'),('expo@naventory.es','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> expo@naventory.es </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:37:40</em>\r\n                                    </span>','2015-06-17 10:37:40'),('expo@naventory.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Procesadores </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:39:23</em></span>','2015-06-17 10:39:23'),('expo@naventory.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Discos duros </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:40:00</em></span>','2015-06-17 10:40:00'),('expo@naventory.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> RAM </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:40:21</em></span>','2015-06-17 10:40:21'),('expo@naventory.es','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Westerm Digital </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:43:53</em>\r\n                                    </span>','2015-06-17 10:43:53'),('expo@naventory.es','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Sasmsung </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:46:53</em>\r\n                                    </span>','2015-06-17 10:46:53'),('expo@naventory.es','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Toshiba </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:49:05</em>\r\n                                    </span>','2015-06-17 10:49:05'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cerrajería Navarro, S.L. </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:50:56</em>\r\n                                    </span>','2015-06-17 10:50:56'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Jose Espín Guirao </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:52:34</em>\r\n                                    </span>','2015-06-17 10:52:34'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio Javier Navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:53:31</em>\r\n                                    </span>','2015-06-17 10:53:31'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cafetería Martínez </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:54:49</em>\r\n                                    </span>','2015-06-17 10:54:49'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Digio soluciones digitales </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:55:51</em>\r\n                                    </span>','2015-06-17 10:55:51'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Daniel Valera Reina </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:58:08</em>\r\n                                    </span>','2015-06-17 10:58:08'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Carlos López Toro </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 12:59:06</em>\r\n                                    </span>','2015-06-17 10:59:06'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Guillermo Sevilla </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:00:30</em>\r\n                                    </span>','2015-06-17 11:00:30'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Inforges SL </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:01:45</em>\r\n                                    </span>','2015-06-17 11:01:45'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Damian Moya </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:02:52</em>\r\n                                    </span>','2015-06-17 11:02:52'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan Sanchez de Paco </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:03:49</em>\r\n                                    </span>','2015-06-17 11:03:49'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Punto de informática </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:04:29</em>\r\n                                    </span>','2015-06-17 11:04:29'),('expo@naventory.es','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Intel </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:11:22</em>\r\n                                    </span>','2015-06-17 11:11:22'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:30:51</em>\r\n                                    </span>','2015-06-17 11:30:51'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Portátiles </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:31:54</em></span>','2015-06-17 11:31:54'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> moviles </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:31:54</em></span>','2015-06-17 11:31:54'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> trigger </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:31:54</em></span>','2015-06-17 11:31:54'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> alerta </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:31:54</em></span>','2015-06-17 11:31:54'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> ultima </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:31:54</em></span>','2015-06-17 11:31:54'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Ordenadores </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:31:54</em></span>','2015-06-17 11:31:54'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Tubos </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:31:54</em></span>','2015-06-17 11:31:54'),('anavarrodelamor@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Apple </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:32:12</em>\r\n                                    </span>','2015-06-17 11:32:12'),('anavarrodelamor@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Mediar Markt </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:32:12</em>\r\n                                    </span>','2015-06-17 11:32:12'),('anavarrodelamor@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Hierros del noroeste </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:32:12</em>\r\n                                    </span>','2015-06-17 11:32:12'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria dolores navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:23</em>\r\n                                    </span>','2015-06-17 11:33:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:23</em>\r\n                                    </span>','2015-06-17 11:33:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> juvasa </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:23</em>\r\n                                    </span>','2015-06-17 11:33:23'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Maria </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:24</em>\r\n                                    </span>','2015-06-17 11:33:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan Núñez </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:24</em>\r\n                                    </span>','2015-06-17 11:33:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio navarro SL </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:24</em>\r\n                                    </span>','2015-06-17 11:33:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Daniel Valera Reina </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:24</em>\r\n                                    </span>','2015-06-17 11:33:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan sachez fernandez </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:24</em>\r\n                                    </span>','2015-06-17 11:33:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:24</em>\r\n                                    </span>','2015-06-17 11:33:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> antonio navarro sl </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:24</em>\r\n                                    </span>','2015-06-17 11:33:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> OPEL </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:33:24</em>\r\n                                    </span>','2015-06-17 11:33:24'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:38:50</em>\r\n                                    </span>','2015-06-17 11:38:50'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:39:52</em>\r\n                                    </span>','2015-06-17 11:39:52'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-17 - 13:40:24</em>\r\n                                    </span>','2015-06-17 11:40:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-22 - 14:12:22</em>\r\n                                    </span>','2015-06-22 14:12:22'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-22 - 17:18:11</em>\r\n                                    </span>','2015-06-22 17:18:11'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-22 - 17:22:40</em>\r\n                                    </span>','2015-06-22 17:22:40'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> asdfasdfasd </b><span class=\"pull-right text-muted small\"><em>2015-06-22 - 17:23:30</em></span>','2015-06-22 17:23:30'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-23 - 09:50:00</em>\r\n                                    </span>','2015-06-23 09:50:00'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-27 - 16:41:48</em>\r\n                                    </span>','2015-06-27 16:41:48'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-06-29 - 22:09:38</em>\r\n                                    </span>','2015-06-29 22:09:38'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> pprueba </b><span class=\"pull-right text-muted small\"><em>2015-07-21 - 14:55:24</em></span>','2015-07-21 14:55:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Móviles </b><span class=\"pull-right text-muted small\"><em>2015-10-01 - 21:32:28</em></span>','2015-10-01 21:32:28'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Móviles </b><span class=\"pull-right text-muted small\"><em>2015-10-01 - 21:32:29</em></span>','2015-10-01 21:32:29'),('anavarrodelamor@gmail.com','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Móviles </b><span class=\"pull-right text-muted small\"><em>2015-10-01 - 21:33:03</em></span>','2015-10-01 21:33:03'),('anavarrodelamor@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Apple Inc. </b><span class=\"pull-right text-muted small\"><em>2015-10-01 - 21:37:26</em>\r\n                                    </span>','2015-10-01 21:37:26'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Bernardino Abenza Moya </b><span class=\"pull-right text-muted small\"><em>2015-10-01 - 21:42:04</em>\r\n                                    </span>','2015-10-01 21:42:04'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cerrajería Navarro </b><span class=\"pull-right text-muted small\"><em>2015-10-05 - 17:09:21</em>\r\n                                    </span>','2015-10-05 17:09:21'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan Núñez Martínez </b><span class=\"pull-right text-muted small\"><em>2015-10-05 - 17:12:25</em>\r\n                                    </span>','2015-10-05 17:12:25'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-12-21 - 19:21:50</em>\r\n                                    </span>','2015-12-21 19:21:50'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-12-21 - 19:28:40</em>\r\n                                    </span>','2015-12-21 19:28:40'),('anavarrodelamor@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> anavarrodelamor@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-12-21 - 19:31:59</em>\r\n                                    </span>','2015-12-21 19:31:59'),('jossevilla1993@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> jossevilla1993@gmail.com </b><span class=\"pull-right text-muted small\"><em>2015-12-22 - 09:20:55</em>\r\n                                    </span>','2015-12-22 09:20:55'),('antonio-navarro19@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> antonio-navarro19@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2015-12-22 - 09:52:24</em>\r\n                                    </span>','2015-12-22 09:52:24'),('anavarrodelamor@gmail.com','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Jose sanchez sevilla </b><span class=\"pull-right text-muted small\"><em>2016-01-12 - 12:56:23</em>\r\n                                    </span>','2016-01-12 12:56:23'),('ipo@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> ipo@gmail.com </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:03:56</em>\r\n                                    </span>','2016-01-17 18:03:56'),('cerrajerianavarro@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> cerrajerianavarro@gmail.com </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:15:39</em>\r\n                                    </span>','2016-01-17 18:15:39'),('barborrengo@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> barborrengo@gmail.com </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:16:21</em>\r\n                                    </span>','2016-01-17 18:16:21'),('ccaballero@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> ccaballero@gmail.com </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:17:17</em>\r\n                                    </span>','2016-01-17 18:17:17'),('recambiosmiquel@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> recambiosmiquel@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:18:12</em>\r\n                                    </span>','2016-01-17 18:18:12'),('jsusnavarro@gmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> jsusnavarro@gmail.com </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:19:10</em>\r\n                                    </span>','2016-01-17 18:19:10'),('ipo@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Apple Inc. </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:28:48</em>\r\n                                    </span>','2016-01-17 18:28:48'),('ipo@gmail.com','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Samsung Electronics </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:30:26</em>\r\n                                    </span>','2016-01-17 18:30:26'),('expo@naventory.es','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> expo@naventory.es </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:34:40</em>\r\n                                    </span>','2016-01-17 18:34:40'),('expo@naventory.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Procesadores </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:05</em></span>','2016-01-17 18:37:05'),('expo@naventory.es','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Sasmsung </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:18</em>\r\n                                    </span>','2016-01-17 18:37:18'),('expo@naventory.es','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Toshiba </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:18</em>\r\n                                    </span>','2016-01-17 18:37:18'),('expo@naventory.es','<i class=\"fa fa-truck fa-fw\"></i>&nbspSe ha creado una nuevo proveedo llamado <b> Intel </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:18</em>\r\n                                    </span>','2016-01-17 18:37:18'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Jose Espín Guirao </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Antonio Javier Navarro </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Cafetería Martínez </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Digio soluciones digitales </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Daniel Valera Reina </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Carlos López Toro </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Guillermo Sevilla </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Inforges SL </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Damian Moya </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Juan Sanchez de Paco </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-street-view fa-fw\"></i>Se ha creado una nuevo cliente llamado<b> Punto de informática </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:37:35</em>\r\n                                    </span>','2016-01-17 18:37:35'),('expo@naventory.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Procesadores </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:41:27</em></span>','2016-01-17 18:41:27'),('expo@naventory.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> Discos duros </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:44:23</em></span>','2016-01-17 18:44:23'),('expo@naventory.es','<i class=\"fa fa-archive fa-fw\"></i>&nbspSe ha creado una nueva categoria llamada <b> RAM </b><span class=\"pull-right text-muted small\"><em>2016-01-17 - 18:44:23</em></span>','2016-01-17 18:44:23'),('an@hotmail.com','<i class=\"fa fa-user fa-fw\"></i>&nbspNuevo usuario llamado <b> an@hotmail.com </b><span class=\"pull-right text-muted small\"><em>2016-01-28 - 12:02:08</em>\r\n                                    </span>','2016-01-28 12:02:08');
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
  `email` varchar(255) NOT NULL,
  `id_cat` int(11) NOT NULL,
  `nomcat` varchar(255) DEFAULT 'Sin categoría',
  `id_proveedor` int(11) NOT NULL,
  `nomprov` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `unidad` varchar(255) DEFAULT NULL,
  `coste` float DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_min` int(11) DEFAULT NULL,
  `observaciones` text,
  PRIMARY KEY (`sku`),
  KEY `email` (`email`),
  KEY `id_cat` (`id_cat`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`id_cat`) REFERENCES `categoria` (`idcat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('75E250B','expo@naventory.es',36,'Discos duros',19,'Sasmsung','Samsung 850 Evo','SSD 250GB SATA 3','Unidades',100,108,3,5,''),('BX8064I','expo@naventory.es',35,'Procesadores',21,'Intel','Intel Core I7-4790K','4 nucleos a 4.0GHz','Unidades',320,335,97,33,''),('WD10EZEX','expo@naventory.es',36,'Discos duros',18,'Westerm Digital','WD blue','1TB sata 3','Unidades',50,54,5,10,'WD de 1 TB'),('WD10EZRX','expo@naventory.es',36,'Discos duros',18,'Westerm Digital','WD Green','1TB SATA 3','Unidades',48,53,50,25,'');
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
  `email` varchar(255) NOT NULL,
  `nombre_com` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `cp` int(5) DEFAULT NULL,
  `tel1` int(9) DEFAULT NULL,
  `tel2` int(9) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_prov`),
  KEY `email` (`email`),
  CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'ipo@gmail.com','Apple','Apple Inc.','1 Infinite Loop','Cupertino','California','Estados Unidos',12499,902302343,902302343,'apple@gmail.com'),(2,'ipo@gmail.com','Samsung','Samsung Electronics','Samsung Town','Seul','Seul','Corea del Surl',11190,902444666,902444666,'samsung@gmail.com'),(19,'expo@naventory.es','Samsung','Sasmsung','Nam-gu','Daegu','Corea del Sur','Corea del Sur',32121,2147483647,923123121,'samsung@samsung.es'),(20,'expo@naventory.es','Toshiba','Toshiba','Gaien-nishi Dori','Minato','Tokio','Japon',12312,991291231,912391231,'thosiba@thosiba.es'),(21,'expo@naventory.es','Intel','Intel','Mountain view','Mountain View','California','Estados Unidos',12321,920123645,920123645,'intel@info.es');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `fecha_alta` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `foto_perf` varchar(255) DEFAULT 'img/foto_null.jpg',
  `activo` char(1) DEFAULT 'N',
  `admin` char(1) DEFAULT 'N',
  `TOKEN` varchar(255) DEFAULT NULL,
  `token_pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('an@hotmail.com','$2y$12$SvICYKAAbE8g5HWz3/8LeeNri0j6YOrjgQkV8acI4omrkGL3O8CMS','Antonio','Perez','Saluble S.L','2016-01-28 12:02:08','img/foto_null.jpg','N','N','361ddd3aa9f5c93bcc0071992aa578a0',NULL),('anavarrodelamor@gmail.com','$2y$12$KtzRvifv6AC76eI00auqa.OslLblWC0.sof6PIm9edWCXusijjRW6','Antonio Javier','Navarro del Amor','Aseguramiento de calidad','2015-07-15 19:31:59','img/foto_null.jpg','Y','Y','9c0d844b04436127c308529683e02252',''),('antonio-navarro19@hotmail.com','$2y$12$qBlJ7FdaJ2kflMZJmU5aSuIagajPI9L0AP3aWEppBDRRFwmku162m','Antonio Javier','Navarro del Amor','Sin empresa','2015-12-22 09:52:24','img/foto_null.jpg','Y','Y',NULL,NULL),('barborrengo@gmail.com','$2y$12$K1PeI63Fl/7jWHpNBUCF7uYDYlFCOmvNXp3Td7Vaa0w.zFUk8wD3G','Antonio','Canovas','Bar El Borrego','2016-01-17 18:16:21','img/foto_null.jpg','N','N','1cc19bce608a08debd9b64854fd6a783',NULL),('ccaballero@gmail.com','$2y$12$Dg.pKtWNJlrhj03hAnbt5uOtEfAHUUORE4eERV/Bt096qjB1ZWSei','Antonio','Caballero Martinez','Comercial Caballero','2015-11-09 18:17:17','img/foto_null.jpg','N','N','7fda0a21332df13337a692f7debc2799',NULL),('cerrajerianavarro@gmail.com','$2y$12$MA72hdxIqBd5vqOnMxvgsumX6DlybkOEEG7U7nAieKDT2eYVNtU9a','Cerrajeria','Navarro','Cerrajeria Navarro, S.L.','2015-11-11 18:15:39','img/foto_null.jpg','N','N','63b094ab6725605903d9c41d794388b1',NULL),('expo@naventory.es','$2y$12$aJH/UWvRvCp8l8pE/hJcAeNKcAUmyor676.JRVbBIXmnvu7nsauqG','Interfaz','Persona-Maquina','Bar moral','2016-01-17 18:34:40','img/foto_null.jpg','Y','N','ee793d27b87948a2f955db20ea0312e5',NULL),('ipo@gmail.com','$2y$12$CGbuGg3Wz8A0XN/FWW5hTe3ttsQavaDnVOug3oijxVWLobdMS.tD2','Interfaz','Persona-Maquina','Interfaz Persona-Maquina','2016-01-17 18:03:56','img/foto_null.jpg','Y','N','d3063e66bc45e921010ccbeb5fc70fc6',NULL),('jossevilla1993@gmail.com','$2y$12$zrJ4qiAf47J.kMRqp3R4SufiLQ1pLbInmiuUyy.LztMxZInfWl6pa','José','Sánchez','Wences','2015-12-22 09:20:55','img/foto_null.jpg','Y','N','123f09edbc66acc0e2b9eebcf21874ec',''),('jsusnavarro@gmail.com','$2y$12$Xj6rY6SeWZ9N61Y0RHpHaeQXOWlMCjNEmKZutGHl1/1crGeSkzxaK','Jesús','Navarro Moreno','Aluminio y Acero Inox. Jesus Navarro','2015-09-18 18:19:10','img/foto_null.jpg','N','N','89ee44ba2073e07d913635ffa5f14aea',NULL),('recambiosmiquel@hotmail.com','$2y$12$BtXKh.f7Dk/BSuDtZuvxXeqqP9PDiqA09xY2VqaMEB3Ab/w2YNCcq','Jose Bartolome','Sanchez Martinez','Auto-Recambios Miquel','2016-01-15 18:18:12','img/foto_null.jpg','N','N','9658ce228f654c8feb6ab0a6647e256a',NULL);
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
  `email` varchar(255) NOT NULL,
  `id_prod` varchar(255) NOT NULL,
  `nombre_prod` varchar(255) DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  `nombre_cliente` varchar(255) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcion` varchar(255) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `forma_pago` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `iva` int(11) DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`id_vent`),
  KEY `email` (`email`),
  KEY `id_prod` (`id_prod`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `producto_fk` FOREIGN KEY (`id_prod`) REFERENCES `producto` (`sku`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (98,'expo@naventory.es','75E250B',NULL,46,'Jose Espín Guirao','2015-05-17 13:14:25','',1,'Unidades',108,'transferencia',0,21,130.68),(99,'expo@naventory.es','WD10EZRX',NULL,47,'Antonio Javier Navarro','2015-06-17 13:14:49','',2,'Unidades',53,'contado',5,21,122.96),(100,'expo@naventory.es','BX8064I',NULL,48,'Cafetería Martínez','2015-06-17 13:22:45','',3,'Unidades',335,'transferencia',0,21,1216.05),(101,'expo@naventory.es','75E250B',NULL,49,'Digio soluciones digitales','2015-06-17 13:27:36','',2,'Unidades',108,'contado',0,21,261.36),(102,'expo@naventory.es','BX8064I','Intel Core I7-4790K',49,'Digio soluciones digitales','2016-01-18 09:39:14','',4,'Unidades',335,'tarjeta de credito/debito',0,21,1621.4),(103,'expo@naventory.es','WD10EZEX','WD blue',53,'Inforges SL','2016-01-18 10:12:51','',21,'Unidades',54,'tarjeta de credito/debito',0,21,1372.14),(104,'expo@naventory.es','WD10EZEX','WD blue',48,'Cafetería Martínez','2016-01-18 10:22:55','',3,'Unidades',54,'contado',0,21,196.02),(105,'expo@naventory.es','75E250B','Samsung 850 Evo',50,'Daniel Valera Reina','2016-01-18 10:23:07','',1,'Unidades',108,'transferencia',0,21,130.68),(106,'expo@naventory.es','75E250B','Samsung 850 Evo',49,'Digio soluciones digitales','2016-01-18 10:40:29','4 unidades de discos SSD para empresa Digio',4,'Unidades',108,'tarjeta de credito/debito',0,21,522.72),(107,'expo@naventory.es','75E250B','Samsung 850 Evo',48,'Cafetería Martínez','2016-01-20 12:56:09','',17,'Unidades',108,'contado',0,21,2221.56),(108,'expo@naventory.es','WD10EZEX','WD blue',47,'Antonio Javier Navarro','2016-01-20 18:46:48','',1,'Unidades',54,'contado',0,21,65.34);
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

-- Dump completed on 2018-07-16 18:00:07
