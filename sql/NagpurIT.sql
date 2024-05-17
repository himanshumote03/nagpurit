-- Host: 127.0.0.1    Database: nagpurit
-- ------------------------------------------------------
-- Server version	8.0.31
-- ------------------------------------------------------

--
-- Database Name:
-- 
DROP DATABASE IF EXISTS nagpuritDB;
CREATE DATABASE IF NOT EXISTS nagpuritDB;
USE nagpuritDB;

--
-- Table structure for table `login_details`
--
DROP TABLE IF EXISTS `login_details`;
CREATE TABLE `login_details` (
    `id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `name` VARCHAR(255)  DEFAULT NULL,
    `email` VARCHAR(255) UNIQUE  DEFAULT NULL,
    `password` VARCHAR(255)  DEFAULT NULL,
    `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modified` DATETIME NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- INSERT INTO `login_details` (`id`, `name`, `email`, `password`) VALUES ('1', 'Himanshu', 'himanshumote@gmail.com', 'himanshu'), ('2', 'Varun', 'varun@gmail.com', 'varun'), ('3', 'Neha', 'neha@gmail.com','neha');


--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT PRIMARY KEY NOT NULL,
  `login_id` INT NOT NULL,
  `image` VARCHAR(512) DEFAULT NULL,
  `first_name` VARCHAR(255) DEFAULT NULL,
  `last_name` VARCHAR(255) DEFAULT NULL,
  `language` VARCHAR(15) DEFAULT NULL,
  `github_url` VARCHAR(512) DEFAULT NULL,
  `linkdin_url` VARCHAR(512) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `account_deletion_msg``
--
DROP TABLE IF EXISTS `account_deletion_msg`;
CREATE TABLE `account_deletion_msg` (
  `id` INT PRIMARY KEY NOT NULL,
  `login_id` INT NOT NULL,
  `message` VARCHAR(255) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `check_in`
--
DROP TABLE IF EXISTS `check_in`;
CREATE TABLE `check_in` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT NOT NULL,
  `project_name` VARCHAR(255) DEFAULT NULL,
  `task_description` VARCHAR(1000) DEFAULT NULL,
  `total_task` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
--   FOREIGN KEY (`login_id`) REFERENCES `login_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `check_out`
--
DROP TABLE IF EXISTS `check_out`;
CREATE TABLE `check_out` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `project_name` VARCHAR(255) DEFAULT NULL,
  `todays_task` VARCHAR(1000) DEFAULT NULL,
  `working_hours_report` VARCHAR(1000) DEFAULT NULL,
  `total_hours` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `fields`
--
DROP TABLE IF EXISTS `fields`;
CREATE TABLE `fields` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `description` VARCHAR(255) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `fields` (`id`, `description`) 
VALUES  ('1', 'Marketing And Design'), 
	    ('2', 'Software Development'),
        ('3', 'Data Analytics');


--
-- Table structure for table `fields_details`
--
DROP TABLE IF EXISTS `fields_details`;
CREATE TABLE `fields_details` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `field_id` INT DEFAULT NULL,
  `field_name` VARCHAR(512) NOT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`field_id`) REFERENCES `fields`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `fields_details` (`id`, `field_id`, `field_name`) 
VALUES  ('1', '1', 'Digital Marketing'), ('2', '1', 'Email Marketing'), ('3', '1', 'SEO Specialist'), 
		('4', '1', 'Marketing Analyst'), ('5', '1', 'UX Designer'), ('6', '1', 'UI Designer'),
		('7', '1', 'Web Designer'), ('8', '2', 'Web Developer'), ('9', '2', 'Back End Web Developer'), 
		('10', '2', 'Front End Web Developer'), ('11', '2', 'Full Stack Web Developer'), ('12', '2', 'MERN Full stack Developer'),
		('13', '2', 'Java developer'), ('14', '2', 'Python'), ('15', '3', 'Data Analyst'), 
		('16', '3', 'Data Science'), ('17', '3', 'SAP');


--
-- Table structure for table `career_goal`
--
DROP TABLE IF EXISTS `career_goal`;
CREATE TABLE `career_goal` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `current_goal` VARCHAR(255) DEFAULT NULL,
  `field_details_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`field_details_id`) REFERENCES `fields_details`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `course_categories`
--
DROP TABLE IF EXISTS `course_categories`;
CREATE TABLE `course_categories` (
  `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `course_type` VARCHAR(255) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
  `modified` DATETIME NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `course_categories` (`id`, `course_type`) 
VALUES  ('1', 'Development '), ('2', 'Business'), 
		('3', 'IT & Software'), ('4', 'Offline Productivity'), 
        ('5', 'Design'), ('6', 'Marketing'), 
        ('7', 'Teaching & Academic');


--
-- Table structure for table `instructor`
--
DROP TABLE IF EXISTS `instructor`;
CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,  
  `image` Varchar(512) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `designation` VARCHAR(512) DEFAULT NULL,
  `total_students` INT DEFAULT NULL,
  `description` VARCHAR(1000) DEFAULT NULL,
  `github_url` VARCHAR(512) DEFAULT NULL,
  `linkdin_url` VARCHAR(512) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `courses`
--
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `course_category_id` INT DEFAULT NULL,
  `course_title` VARCHAR(255) DEFAULT NULL,
  `description_title` VARCHAR(512) DEFAULT NULL, 
  `description_content` VARCHAR(1000) DEFAULT NULL,
  `duration` DOUBLE DEFAULT NULL,
  `image` VARCHAR(512) NOT NULL,
  `language` VARCHAR(100) NOT NULL,
  `sub_title` VARCHAR(255) DEFAULT NULL,
  `cost` INT DEFAULT NULL,
  `course_outcome` VARCHAR(255) DEFAULT NULL,
  `instructor_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`course_category_id`) REFERENCES `course_categories`(`id`),
--   FOREIGN KEY (`instructor_id`) REFERENCES `instructor`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `section`
--
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `course_id` INT DEFAULT NULL,
  `section_name` VARCHAR(255) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `videos`
--
DROP TABLE IF EXISTS `videos`;
CREATE TABLE `videos` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `section_id` INT DEFAULT NULL,
  `title` VARCHAR(255) DEFAULT NULL,
  `type` VARCHAR(45) DEFAULT NULL,
  `video_url` VARCHAR(512) DEFAULT NULL,
  `duration` VARCHAR(45) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`section_id`) REFERENCES `section`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `my_courses`
--
DROP TABLE IF EXISTS `my_courses`;
CREATE TABLE `my_courses` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `course_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `wishlist`
--
DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE `wishlist` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `course_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `cart`
--
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `course_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `favourite`
--
DROP TABLE IF EXISTS `favourite`;
CREATE TABLE `favourite` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `course_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `installment_plan`
--
DROP TABLE IF EXISTS `installment_plan`;
CREATE TABLE `installment_plan` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `no_of_months` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
  `modified` DATETIME NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `installment_plan` (`id`, `no_of_months`) VALUES ('1', '3'), ('2', '4'), ('3', '5');


--
-- Table structure for table `transactions`
--
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `amount` INT DEFAULT NULL,
  `transaction_id` VARCHAR(255) DEFAULT NULL,
  `status` VARCHAR(45) DEFAULT NULL,
  `payment_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `installment_plan_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`installment_plan_id`) REFERENCES `installment_plan`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `course_reviews`
--
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `course_id` INT DEFAULT NULL,
  `ratings` DOUBLE DEFAULT NULL,
  `message` VARCHAR(1024) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `course_ratings`
--
DROP TABLE IF EXISTS `course_ratings`;
CREATE TABLE `course_ratings` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `course_id` INT DEFAULT NULL,
  `ratings` DOUBLE DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `instructor_reviews`
--
DROP TABLE IF EXISTS `instructor_reviews`;
CREATE TABLE `instructor_reviews` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `instructor_id` INT DEFAULT NULL,
  `ratings` DOUBLE DEFAULT NULL,
  `message` VARCHAR(1024) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`instructor_id`) REFERENCES `instructor`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `instructor ratings`
--
DROP TABLE IF EXISTS `instructor_ratings`;
CREATE TABLE `instructor_ratings` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `instructor_id` INT DEFAULT NULL,
  `ratings` DOUBLE DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
--   FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
--   FOREIGN KEY (`instructor_id`) REFERENCES `instructor`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



ALTER TABLE `instructor_reviews`
ADD `ratings` DOUBLE DEFAULT NULL;


