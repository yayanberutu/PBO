/*
SQLyog Ultimate v8.55 
MySQL - 5.5.5-10.1.31-MariaDB : Database - mahasiswa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mahasiswa` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mahasiswa`;

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

insert  into `tmahasiswa`(`NIM`,`NAMA`,`DOB`,`EMAIL`) values ('11318058','Sarah','2000-07-20','sarah@gmail.com'),('11318077','Jen','2000-04-07','jen@gmail.com');

/*Table structure for table `tmatakuliah` */

DROP TABLE IF EXISTS `tmatakuliah`;

CREATE TABLE `tmatakuliah` (
  `ID` varchar(10) NOT NULL,
  `MATKUL` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmatakuliah` */

insert  into `tmatakuliah`(`ID`,`MATKUL`) values ('Daspro','Dasar Pemrograman'),('PAM','Pengembangan Aplikasi Mobile'),('PAP','Perancangan Antarmuka Pengguna'),('PBO','Pemrograman Berbasis Objek'),('SBD','Sistem Basis Data');

/*Table structure for table `tmhsmatkul` */

DROP TABLE IF EXISTS `tmhsmatkul`;

CREATE TABLE `tmhsmatkul` (
  `NIM` varchar(8) DEFAULT NULL,
  `MATKULID` varchar(10) DEFAULT NULL,
  KEY `MATKULID` (`MATKULID`),
  KEY `NIM` (`NIM`),
  CONSTRAINT `tmhsmatkul_ibfk_1` FOREIGN KEY (`MATKULID`) REFERENCES `tmatakuliah` (`ID`),
  CONSTRAINT `tmhsmatkul_ibfk_2` FOREIGN KEY (`NIM`) REFERENCES `tmahasiswa` (`NIM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmhsmatkul` */

insert  into `tmhsmatkul`(`NIM`,`MATKULID`) values ('11318058','PAP'),('11318077','PBO');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
