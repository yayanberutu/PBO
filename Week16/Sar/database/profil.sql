/*
SQLyog Ultimate v8.55 
MySQL - 5.5.5-10.1.31-MariaDB : Database - profil
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`profil` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `profil`;

/*Table structure for table `tmahasiswa` */

DROP TABLE IF EXISTS `tmahasiswa`;

CREATE TABLE `tmahasiswa` (
  `NIM` varchar(8) NOT NULL,
  `NAMA` varchar(30) NOT NULL,
  `DOB` date NOT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`NIM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmahasiswa` */

insert  into `tmahasiswa`(`NIM`,`NAMA`,`DOB`,`EMAIL`) values ('11318058','Sarah Tampubolon','2000-07-20','sarah@gmail.com'),('11318076','Jaemin','2001-09-20','jaem@gmail.com'),('11318077','Jeno','2000-04-20','jeno@gmail.com'),('11318099','Sarah','2000-07-20','sarag@gmail.com'),('11319077','Kiki','2000-11-30','kiki@gmail.com'),('1138077','Jeno Lee','2019-12-10','jeno@gmail.com');

/*Table structure for table `tmatakuliah` */

DROP TABLE IF EXISTS `tmatakuliah`;

CREATE TABLE `tmatakuliah` (
  `ID_MATKUL` varchar(30) NOT NULL,
  `NAMA_MATKUL` varchar(100) DEFAULT NULL,
  `SKS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_MATKUL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmatakuliah` */

insert  into `tmatakuliah`(`ID_MATKUL`,`NAMA_MATKUL`,`SKS`) values ('ENG','English III',2),('PAP','Perancangan Antarmuka Pengguna',3),('PBO','Pemrograman Berbasis Objek',3);

/*Table structure for table `tmkmhs` */

DROP TABLE IF EXISTS `tmkmhs`;

CREATE TABLE `tmkmhs` (
  `NIM` varchar(8) DEFAULT NULL,
  `ID_MATKUL` varchar(30) DEFAULT NULL,
  `Nilai` varchar(5) DEFAULT NULL,
  KEY `NIM` (`NIM`),
  KEY `ID_MATKUL` (`ID_MATKUL`),
  CONSTRAINT `tmkmhs_ibfk_1` FOREIGN KEY (`NIM`) REFERENCES `tmahasiswa` (`NIM`),
  CONSTRAINT `tmkmhs_ibfk_2` FOREIGN KEY (`ID_MATKUL`) REFERENCES `tmatakuliah` (`ID_MATKUL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmkmhs` */

insert  into `tmkmhs`(`NIM`,`ID_MATKUL`,`Nilai`) values ('11318058','PAP','77'),('11318058','PBO','88'),('11318077','ENG','98'),('11318077','PAP','99');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
