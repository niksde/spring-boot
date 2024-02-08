DROP SCHEMA IF EXISTS `graphqltutorial`;

CREATE SCHEMA `graphqltutorial`;

USE `graphqltutorial`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
`id` int(11) NOT NULL auto_increment,
`street` varchar(100) NOT NULL,
`city` varchar(45) NOT NULL,
primary key (`id`)
) ENGINE=InnoDB auto_increment=5 default charset=latin1;

LOCK tables `address` WRITE;

INSERT INTO `address` VALUES
(1,'Happy Street', 'Delhi'),
(2,'2nd Street', 'Mumbai'),
(3,'3rd Street', 'Delhi'),
(4,'any Street', 'Mumbai');

unlock tables;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
`id` int(11) NOT NULL auto_increment,
`first_name` varchar(50) NOT null,
`last_name` varchar(50) default null,
`email` varchar(30) default null,
`address_id` int(11) DEFAULT NULL,
primary key(`id`),
  KEY `FK_ADDRESS_idx` (`address_id`),
  CONSTRAINT `FK_ADDRESS` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB auto_increment=5 default charset=latin1;

LOCK TABLES `student` WRITE;

insert INTO `student` values 
(1,'John', 'Smith','john@gmail.com',1),
(2,'Virat', 'Dave','virat@gmail.com',2),
(3,'Steve', 'Martin','steve@gmail.com',3),
(4,'Sachin', 'Kumar','sachin@gmail.com',4);

unlock tables;

DROP TABLES IF EXISTS `marks`;

CREATE TABLE `marks` (
`id` int(11) NOT NULL auto_increment,
`subject_name` varchar(45) DEFAULT null,
`marks_obtained` double DEFAULT null,
`student_id` int(11) default null,
primary key (`id`),
KEY `FK_STUDENT_idx` (`student_id`),
constraint `FK_STUDENT` foreign key(`student_id`) references `student` (`id`) 
ON delete NO ACTION ON update NO ACTION 
) ENGINE=InnoDB auto_increment=5 default charset=latin1;

LOCK TABLES `marks` WRITE;

INSERT INTO `marks` VALUES
(1, 'JAVA', 80, 1),
(2, 'MySQL', 70, 1),
(3, 'JAVA', 80, 2),
(4, 'MySQL', 70, 2),
(5, 'MongoDB', 70, 2),
(6, 'MySQL', 70, 3),
(7, 'MongoDB', 70, 3),
(8, 'Java', 60, 4),
(9, 'MongoDB', 50, 4);

UNLOCK TABLES;

SET FOREIGN_KEY_CHECKS = 1;


