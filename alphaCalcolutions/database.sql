DROP DATABASE IF EXISTS `alpha_calcolutions`;
CREATE DATABASE IF NOT EXISTS `alpha_calcolutions`;
USE `alpha_calcolutions`;

--
-- Table structure for table `user_title`
--

DROP TABLE IF EXISTS `user_title`;
CREATE TABLE `user_title` (
  `user_title_id` int NOT NULL AUTO_INCREMENT,
  `user_title` varchar(100) NOT NULL,
  PRIMARY KEY (`user_title_id`),
  UNIQUE KEY `employee_title_UNIQUE` (`user_title`)
) ENGINE=InnoDB;

--
-- Dumping data for table `user_title`
--

INSERT INTO `user_title` VALUES (6,'Business Intelligence Analyst'),(7,'Computer System Engineer'),(5,'Database Administrator'),(4,'Front End Developer'),(1,'Junior Developer'),(2,'Senior Developer'),(3,'Web Developer');

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_first_name` varchar(100) NOT NULL,
  `user_last_name` varchar(100) NOT NULL,
  `user_title_id` int NOT NULL,
  `user_hourly_salary` int NOT NULL,
  `user_username` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `is_admin` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_username_UNIQUE` (`user_username`),
  KEY `employee_title_id_idx` (`user_title_id`),
  CONSTRAINT `user_title_id` FOREIGN KEY (`user_title_id`) REFERENCES `user_title` (`user_title_id`)
) ENGINE=InnoDB; 


--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,'Frank','Jensen',1,150,'Frje','Frje',0),(2,'Simone','Sørensen',1,165,'Sisø','Sisø',0),(3,'Henning','Hansen',1,180,'Heha','Heha',0),(4,'Christoffer','Andersen',2,260,'Chan','Chan',0),(5,'Jens','Thomsen',2,290,'Jeth','Jeth',0),(6,'Sigurd','Hansen',2,240,'Siha','Siha',0),(7,'Charlotte','Karlsen',1,155,'Chka','Chka',0),(8,'Nicklas','Graversen',2,275,'Nigr','Nigr',0),(9,'Nicki','Larsen',1,195,'Nila','Nila',0),(10,'Bent','Trane',2,300,'Betr','Betr',0),(11,'Rikke','Jensen',3,175,'Rije','Rije',0),(12,'Anne','Magnussen',6,410,'Anma','Anma',0),(13,'Ayo','Johansen',3,155,'Ayjo','Ayjo',0),(14,'Jimbo','Gotteson',6,430,'Jigo','Jigo',0),(15,'Mohammed','Madsen',3,175,'Moma','Moma',0),(16,'Chan','Lee',6,405,'Chle','Chle',0),(17,'Yoko','Yakuma',4,120,'Yoya','Yoya',0),(18,'Pashki','Lambadur',7,520,'Pala','Pala',0),(19,'Ali','Ababwa',4,135,'Alab','Alab',0),(20,'Inge','Jacobsen',7,530,'Inja','Inja',0),(21,'Robin','Petersen',4,145,'Rope','Rope',0),(22,'Jackie','Killemose',5,390,'Jaki','Jaki',0),(23,'Kim','Olsen',5,280,'Kiol','Kiol',0);

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int NOT NULL AUTO_INCREMENT,
  `project_title` varchar(100) NOT NULL,
  `project_leader_id` int DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `project_leader_id_idx` (`project_leader_id`),
  CONSTRAINT `project_leader_id` FOREIGN KEY (`project_leader_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB;

--
-- Dumping data for table `project`
--
INSERT INTO `project` VALUES (1,'Nordea - Integration af mobilepay til huskøb',10),(2,'Sundhedsstyrelsen - Patientlog',5);

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `task_title` varchar(100) NOT NULL,
  `task_leader_id` int DEFAULT NULL,
  `task_start_date` date NOT NULL,
  `task_end_date` date NOT NULL,
  `project_id` int NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `task_leader_id_idx` (`task_leader_id`),
  KEY `project_id_idx` (`project_id`),
  CONSTRAINT `task_leader_id` FOREIGN KEY (`task_leader_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `task_project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE
) ENGINE=InnoDB;

--
-- Dumping data for table `task`
--

INSERT INTO `task` VALUES (1,'Create Analysis',14,'2020-12-09','2020-12-14',2),(2,'Create riskanalysis',16,'2020-12-09','2020-12-10',1),(3,'Implement data logic',1,'2020-12-10','2020-12-11',1),(4,'Use analysis to find market',14,'2020-12-14','2020-12-16',2),(5,'Create MVC architecture',10,'2020-12-09','2020-12-10',1),(6,'Create HTML pages',11,'2020-12-11','2020-12-16',1),(7,'Setup system',5,'2020-12-09','2020-12-17',2),(8,'System test',3,'2020-12-17','2020-12-22',2),(9,'H2Database Test',10,'2020-12-21','2020-12-23',1),(10,'SWOT analysis',1,'2020-12-10','2020-12-11',1),(11,'Implement data logic',5,'2020-12-22','2020-12-25',2),(12,'Style HTML',11,'2020-12-29','2020-12-31',2),(13,'Style Html pages',10,'2020-12-16','2020-12-18',1),(14,'Create API',10,'2020-12-10','2020-12-15',1);

--
-- Table structure for table `task_has_dependency`
--

DROP TABLE IF EXISTS `task_has_dependency`;
CREATE TABLE `task_has_dependency` (
  `dependant_task_id` int NOT NULL,
  `dependency_task_id` int NOT NULL,
  PRIMARY KEY (`dependant_task_id`,`dependency_task_id`),
  KEY `dependency_task_id_idx` (`dependency_task_id`),
  CONSTRAINT `dependant_task_id` FOREIGN KEY (`dependant_task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `dependency_task_id` FOREIGN KEY (`dependency_task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE
) ENGINE=InnoDB;

--
-- Dumping data for table `task_has_dependency`
--
INSERT INTO `task_has_dependency` VALUES (4,1),(10,2),(6,3),(3,5),(14,5),(13,6),(8,7),(11,7),(11,8);


--
-- Table structure for table `user_has_project`
--

DROP TABLE IF EXISTS `user_has_project`;
CREATE TABLE `user_has_project` (
  `user_id` int NOT NULL,
  `project_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`),
  KEY `project_id_idx` (`project_id`),
  CONSTRAINT `project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB;

--
-- Dumping data for table `user_has_project`
--
INSERT INTO `user_has_project` VALUES (1,1),(3,1),(10,1),(11,1),(16,1),(18,1),(2,2),(3,2),(5,2),(9,2),(11,2),(14,2),(20,2);

--
-- Table structure for table `user_has_task`
--

DROP TABLE IF EXISTS `user_has_task`;
CREATE TABLE `user_has_task` (
  `user_id` int NOT NULL,
  `task_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`task_id`),
  KEY `employee_has_task_task_id_idx` (`task_id`),
  CONSTRAINT `employee_has_task_employee_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `employee_has_task_task_id` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE
) ENGINE=InnoDB;

--
-- Dumping data for table `user_has_task`
--

INSERT INTO `user_has_task` VALUES (2,1),(14,1),(16,2),(1,3),(9,4),(14,4),(10,5),(18,5),(11,6),(2,7),(5,7),(2,8),(3,8),(9,8),(10,9),(1,10),(16,10),(5,11),(11,11),(20,11),(11,12),(1,13),(10,13),(1,14),(3,14),(10,14);

