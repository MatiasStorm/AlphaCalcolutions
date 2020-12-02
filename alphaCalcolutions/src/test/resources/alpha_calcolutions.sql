DROP DATABASE `alpha_calcolutions`;
CREATE DATABASE `alpha_calcolutions`;
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
);
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
);

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int NOT NULL AUTO_INCREMENT,
  `project_title` varchar(100) NOT NULL,
  `project_start_date` date NOT NULL,
  `project_end_date` date NOT NULL,
  `project_leader_id` int NOT NULL,
  PRIMARY KEY (`project_id`),
  KEY `project_leader_id_idx` (`project_leader_id`),
  CONSTRAINT `project_leader_id` FOREIGN KEY (`project_leader_id`) REFERENCES `user` (`user_id`)
);

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `task_title` varchar(100) NOT NULL,
  `task_leader_id` int NOT NULL,
  `task_start_date` date NOT NULL,
  `task_end_date` date NOT NULL,
  `project_id` int NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `task_leader_id_idx` (`task_leader_id`),
  CONSTRAINT `task_leader_id` FOREIGN KEY (`task_leader_id`) REFERENCES `user` (`user_id`)
) ;

--
-- Table structure for table `task_has_dependency`
--

DROP TABLE IF EXISTS `task_has_dependency`;
CREATE TABLE `task_has_dependency` (
  `dependant_task_id` int NOT NULL,
  `dependency_task_id` int NOT NULL,
  PRIMARY KEY (`dependant_task_id`,`dependency_task_id`),
  KEY `dependency_task_id_idx` (`dependency_task_id`),
  CONSTRAINT `dependant_task_id` FOREIGN KEY (`dependant_task_id`) REFERENCES `task` (`task_id`),
  CONSTRAINT `dependency_task_id` FOREIGN KEY (`dependency_task_id`) REFERENCES `task` (`task_id`)
);


--
-- Table structure for table `user_has_project`
--

DROP TABLE IF EXISTS `user_has_project`;
CREATE TABLE `user_has_project` (
  `user_id` int NOT NULL,
  `project_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`),
  KEY `project_id_idx` (`project_id`),
  CONSTRAINT `project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

--
-- Table structure for table `user_has_task`
--

DROP TABLE IF EXISTS `user_has_task`;
CREATE TABLE `user_has_task` (
  `employee_id` int NOT NULL,
  `task_id` int NOT NULL,
  PRIMARY KEY (`employee_id`,`task_id`),
  KEY `employee_has_task_task_id_idx` (`task_id`),
  CONSTRAINT `employee_has_task_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `employee_has_task_task_id` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
);

INSERT INTO `user_title`(user_title)
VALUES ('Computer Science');
INSERT INTO `user_title`(user_title)
VALUES ('Graphical designer');

INSERT INTO `user`(user_first_name, user_last_name, user_title_id, user_hourly_salary, user_username, user_password)
VALUES ('First_name', 'Last_name', 1, 120, 'username', 'password');

INSERT INTO `user`(user_first_name, user_last_name, user_title_id, user_hourly_salary, user_username, user_password)
VALUES ('Jørgen', 'Jensen', 2, 120, 'username1', 'password');

INSERT INTO `project`(project_title, project_start_date, project_end_date, project_leader_id)
VALUES ('Title', '2020-12-12', '2020-12-24', 1) ;

INSERT INTO `user_has_project`(user_id, project_id)
VALUES (1, 1);

INSERT INTO `user_has_project`(user_id, project_id)
VALUES (2, 1);
