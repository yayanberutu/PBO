/*
SQLyog Ultimate v8.55 
MySQL - 5.5.5-10.1.31-MariaDB : Database - maha
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`maha` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `maha`;

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

insert  into `tmahasiswa`(`NIM`,`NAMA`,`DOB`,`EMAIL`) values ('11318077','Jeno Lee','2000-04-23','jeno@gmail.com');

/*Table structure for table `tmatakuliah` */

DROP TABLE IF EXISTS `tmatakuliah`;

CREATE TABLE `tmatakuliah` (
  `ID_MATKUL` varchar(10) NOT NULL,
  `NAMA_MATKUL` varchar(200) NOT NULL,
  `SKS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_MATKUL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmatakuliah` */

insert  into `tmatakuliah`(`ID_MATKUL`,`NAMA_MATKUL`,`SKS`) values ('PAM','Pengembangan Aplikasi Mobile',3),('PAP','Perancangan Antarmuka Pengguna',3);

/*Table structure for table `tmkmhs` */

DROP TABLE IF EXISTS `tmkmhs`;

CREATE TABLE `tmkmhs` (
  `NIM` varchar(8) DEFAULT NULL,
  `ID_MATKUL` varchar(10) DEFAULT NULL,
  `NILAI_PRAK` int(11) DEFAULT NULL,
  `NILAI_KUIS` int(11) DEFAULT NULL,
  `NILAI_UTS` int(11) DEFAULT NULL,
  `NILAI_UAS` int(11) DEFAULT NULL,
  KEY `MATKULID` (`ID_MATKUL`),
  KEY `NIM` (`NIM`),
  CONSTRAINT `tmkmhs_ibfk_1` FOREIGN KEY (`ID_MATKUL`) REFERENCES `tmatakuliah` (`ID_MATKUL`),
  CONSTRAINT `tmkmhs_ibfk_2` FOREIGN KEY (`NIM`) REFERENCES `tmahasiswa` (`NIM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmkmhs` */

insert  into `tmkmhs`(`NIM`,`ID_MATKUL`,`NILAI_PRAK`,`NILAI_KUIS`,`NILAI_UTS`,`NILAI_UAS`) values ('11318077','PAM',88,88,88,88);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
