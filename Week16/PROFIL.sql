/*
SQLyog Ultimate v8.55 
MySQL - 5.5.5-10.4.6-MariaDB : Database - profil
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

/*Table structure for table `tdosen` */

DROP TABLE IF EXISTS `tdosen`;

CREATE TABLE `tdosen` (
  `NIDN` varchar(10) NOT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`NIDN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tdosen` */

insert  into `tdosen`(`NIDN`,`Nama`,`Email`) values ('113Ta','Teamsar','teamsar@del.ac.id'),('Tog113','Togu','togu@del.ac.id');

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

insert  into `tmahasiswa`(`NIM`,`NAMA`,`DOB`,`EMAIL`) values ('11318001','Palti Siregar','1999-12-28','if318001@students.del.ac.id'),('11318005','Samuel Ambarita','2019-12-30','samuel@gmail.com'),('11318066','Yosepri Disyandro Berutu','1998-09-05','yoseprib@gmail.com');

/*Table structure for table `tmatakuliah` */

DROP TABLE IF EXISTS `tmatakuliah`;

CREATE TABLE `tmatakuliah` (
  `ID_MATKUL` varchar(8) NOT NULL,
  `NAMA_MATKUL` varchar(30) NOT NULL,
  `SKS` int(11) DEFAULT NULL,
  `Semester` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`ID_MATKUL`),
  CONSTRAINT `TMatakuliah_FK` FOREIGN KEY (`ID_MATKUL`) REFERENCES `tdosen` (`NIDN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmatakuliah` */

/*Table structure for table `tmkmhs` */

DROP TABLE IF EXISTS `tmkmhs`;

CREATE TABLE `tmkmhs` (
  `NIM` varchar(8) DEFAULT NULL,
  `ID_MATKUL` varchar(8) DEFAULT NULL,
  `Nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tmkmhs` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
