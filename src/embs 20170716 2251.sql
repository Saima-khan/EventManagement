-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema eps
--

CREATE DATABASE IF NOT EXISTS eps;
USE eps;

--
-- Definition of table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `userid` varchar(20) NOT NULL,
  `userpass` varchar(20) NOT NULL,
  `userrole` varchar(25) NOT NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`userid`,`userpass`,`userrole`) VALUES 
 ('clk01','sam','clerk'),
 ('clk02','joey','clerk'),
 ('clk03','neha','clerk'),
 ('clk04','sonali','clerk'),
 ('clk06','rehan','clerk'),
 ('saima','12345','manager');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


--
-- Definition of table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `clientid` varchar(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(60) NOT NULL,
  `phonenum` longtext NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY  (`clientid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`clientid`,`name`,`address`,`phonenum`,`email`) VALUES 
 ('c001','srish','mahangar','123456789','srish@gmail.com'),
 ('c002','sonal','balda colony,nishatganj','8505849229','sonal2@gmail.com'),
 ('c003','saif','malitola,basti','1235768980','saif@gmail.com'),
 ('c005','Jai','rajnagar,ghaziabad','319847964','jai@gmail.com');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;


--
-- Definition of table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `eventid` varchar(20) NOT NULL,
  `eventname` varchar(45) NOT NULL,
  `charge` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` (`eventid`,`eventname`,`charge`) VALUES 
 ('e001','rannn',400),
 ('e002','epoque',1000),
 ('e004','techno',900),
 ('e005','Treasure Hunt',400);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;


--
-- Definition of table `eventbooking`
--

DROP TABLE IF EXISTS `eventbooking`;
CREATE TABLE `eventbooking` (
  `bookid` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `venueid` varchar(45) NOT NULL,
  `eventid` varchar(20) NOT NULL,
  `clientid` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY  (`bookid`),
  KEY `FK_eventbooking_1` (`venueid`),
  KEY `FK_eventbooking_2` (`eventid`),
  KEY `FK_eventbooking_3` (`clientid`),
  CONSTRAINT `FK_eventbooking_1` FOREIGN KEY (`venueid`) REFERENCES `venue` (`venueid`),
  CONSTRAINT `FK_eventbooking_2` FOREIGN KEY (`eventid`) REFERENCES `event` (`eventid`),
  CONSTRAINT `FK_eventbooking_3` FOREIGN KEY (`clientid`) REFERENCES `client` (`clientid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eventbooking`
--

/*!40000 ALTER TABLE `eventbooking` DISABLE KEYS */;
INSERT INTO `eventbooking` (`bookid`,`date`,`venueid`,`eventid`,`clientid`,`status`) VALUES 
 ('b007','2017-07-20','v008','e005','c003','Booked'),
 ('b009','2017-07-19','v002','e005','c005','Booked'),
 ('b011','2017-07-16','v004','e002','c003','Booked'),
 ('b012','2017-07-16','v007','e004','c005','Booked'),
 ('b013','2017-07-24','v007','e004','c002','Booked'),
 ('b014','2017-07-21','v010','e004','c005','Booked'),
 ('b015','2017-07-18','v009','e004','c003','Booked'),
 ('b016','2017-07-17','v004','e002','c001','Booked'),
 ('b018','2017-07-18','v001','e002','c001','Booked');
/*!40000 ALTER TABLE `eventbooking` ENABLE KEYS */;


--
-- Definition of table `place`
--

DROP TABLE IF EXISTS `place`;
CREATE TABLE `place` (
  `placeid` varchar(45) NOT NULL default '',
  `placename` varchar(45) NOT NULL,
  PRIMARY KEY  (`placeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `place`
--

/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` (`placeid`,`placename`) VALUES 
 ('p001','Mahanagar'),
 ('p002','Hazratganj'),
 ('p003','Charbagh'),
 ('p004','Lekhraj'),
 ('p005','Gomtinagar'),
 ('p006','Kapurthala'),
 ('p007','chinhut');
/*!40000 ALTER TABLE `place` ENABLE KEYS */;


--
-- Definition of table `venue`
--

DROP TABLE IF EXISTS `venue`;
CREATE TABLE `venue` (
  `venueid` varchar(45) NOT NULL,
  `venuename` varchar(45) NOT NULL,
  `status` varchar(20) NOT NULL,
  `placeid` varchar(45) NOT NULL,
  PRIMARY KEY  (`venueid`),
  KEY `FK_venue_1` (`placeid`),
  CONSTRAINT `FK_venue_1` FOREIGN KEY (`placeid`) REFERENCES `place` (`placeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venue`
--

/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` (`venueid`,`venuename`,`status`,`placeid`) VALUES 
 ('v001','ritz','Booked','p001'),
 ('v002','royal cafe','Booked','p002'),
 ('v003','Mr Brown','Not Booked','p002'),
 ('v004','Kitchen','Not Booked','p004'),
 ('v005','Taj Hotel','Not Booked','p005'),
 ('v006','Kalika Hut','Not Booked','p005'),
 ('v007','Dominos','Booked','p006'),
 ('v008','babians','Booked','p004'),
 ('v009','mocha','Booked','p005'),
 ('v010','bikanerwala','Booked','p001');
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
