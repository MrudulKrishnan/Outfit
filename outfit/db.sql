/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 8.0.22 : Database - outfit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`outfit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `outfit`;

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add log entry',1,'add_logentry'),
(2,'Can change log entry',1,'change_logentry'),
(3,'Can delete log entry',1,'delete_logentry'),
(4,'Can view log entry',1,'view_logentry'),
(5,'Can add permission',2,'add_permission'),
(6,'Can change permission',2,'change_permission'),
(7,'Can delete permission',2,'delete_permission'),
(8,'Can view permission',2,'view_permission'),
(9,'Can add group',3,'add_group'),
(10,'Can change group',3,'change_group'),
(11,'Can delete group',3,'delete_group'),
(12,'Can view group',3,'view_group'),
(13,'Can add user',4,'add_user'),
(14,'Can change user',4,'change_user'),
(15,'Can delete user',4,'delete_user'),
(16,'Can view user',4,'view_user'),
(17,'Can add content type',5,'add_contenttype'),
(18,'Can change content type',5,'change_contenttype'),
(19,'Can delete content type',5,'delete_contenttype'),
(20,'Can view content type',5,'view_contenttype'),
(21,'Can add session',6,'add_session'),
(22,'Can change session',6,'change_session'),
(23,'Can delete session',6,'delete_session'),
(24,'Can view session',6,'view_session'),
(25,'Can add designers_table',7,'add_designers_table'),
(26,'Can change designers_table',7,'change_designers_table'),
(27,'Can delete designers_table',7,'delete_designers_table'),
(28,'Can view designers_table',7,'view_designers_table'),
(29,'Can add designs_tables',8,'add_designs_tables'),
(30,'Can change designs_tables',8,'change_designs_tables'),
(31,'Can delete designs_tables',8,'delete_designs_tables'),
(32,'Can view designs_tables',8,'view_designs_tables'),
(33,'Can add login_table',9,'add_login_table'),
(34,'Can change login_table',9,'change_login_table'),
(35,'Can delete login_table',9,'delete_login_table'),
(36,'Can view login_table',9,'view_login_table'),
(37,'Can add manufacturer_table',10,'add_manufacturer_table'),
(38,'Can change manufacturer_table',10,'change_manufacturer_table'),
(39,'Can delete manufacturer_table',10,'delete_manufacturer_table'),
(40,'Can view manufacturer_table',10,'view_manufacturer_table'),
(41,'Can add materials_table',11,'add_materials_table'),
(42,'Can change materials_table',11,'change_materials_table'),
(43,'Can delete materials_table',11,'delete_materials_table'),
(44,'Can view materials_table',11,'view_materials_table'),
(45,'Can add user_table',12,'add_user_table'),
(46,'Can change user_table',12,'change_user_table'),
(47,'Can delete user_table',12,'delete_user_table'),
(48,'Can view user_table',12,'view_user_table'),
(49,'Can add tailorshop_table',13,'add_tailorshop_table'),
(50,'Can change tailorshop_table',13,'change_tailorshop_table'),
(51,'Can delete tailorshop_table',13,'delete_tailorshop_table'),
(52,'Can view tailorshop_table',13,'view_tailorshop_table'),
(53,'Can add rating_table',14,'add_rating_table'),
(54,'Can change rating_table',14,'change_rating_table'),
(55,'Can delete rating_table',14,'delete_rating_table'),
(56,'Can view rating_table',14,'view_rating_table'),
(57,'Can add orderinfo_table',15,'add_orderinfo_table'),
(58,'Can change orderinfo_table',15,'change_orderinfo_table'),
(59,'Can delete orderinfo_table',15,'delete_orderinfo_table'),
(60,'Can view orderinfo_table',15,'view_orderinfo_table'),
(61,'Can add custom_desgin_table',16,'add_custom_desgin_table'),
(62,'Can change custom_desgin_table',16,'change_custom_desgin_table'),
(63,'Can delete custom_desgin_table',16,'delete_custom_desgin_table'),
(64,'Can view custom_desgin_table',16,'view_custom_desgin_table'),
(65,'Can add complaint_table',17,'add_complaint_table'),
(66,'Can change complaint_table',17,'change_complaint_table'),
(67,'Can delete complaint_table',17,'delete_complaint_table'),
(68,'Can view complaint_table',17,'view_complaint_table'),
(69,'Can add assign_table',18,'add_assign_table'),
(70,'Can change assign_table',18,'change_assign_table'),
(71,'Can delete assign_table',18,'delete_assign_table'),
(72,'Can view assign_table',18,'view_assign_table'),
(73,'Can add chat',19,'add_chat'),
(74,'Can change chat',19,'change_chat'),
(75,'Can delete chat',19,'delete_chat'),
(76,'Can view chat',19,'view_chat');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user` */

insert  into `auth_user`(`id`,`password`,`last_login`,`is_superuser`,`username`,`first_name`,`last_name`,`email`,`is_staff`,`is_active`,`date_joined`) values 
(1,'pbkdf2_sha256$260000$NFbbwKqBnIMHReyuffreC3$3XyJ+hXI0MN3imjyOK7Rz66+g8xVirmNqWI21JlYNcA=','2024-03-16 04:15:29.459462',1,'admin','','','admin@gmail.com',1,1,'2024-03-11 12:14:06.878702');

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(1,'admin','logentry'),
(3,'auth','group'),
(2,'auth','permission'),
(4,'auth','user'),
(5,'contenttypes','contenttype'),
(18,'royaloutfit','assign_table'),
(19,'royaloutfit','chat'),
(17,'royaloutfit','complaint_table'),
(16,'royaloutfit','custom_desgin_table'),
(7,'royaloutfit','designers_table'),
(8,'royaloutfit','designs_tables'),
(9,'royaloutfit','login_table'),
(10,'royaloutfit','manufacturer_table'),
(11,'royaloutfit','materials_table'),
(15,'royaloutfit','orderinfo_table'),
(14,'royaloutfit','rating_table'),
(13,'royaloutfit','tailorshop_table'),
(12,'royaloutfit','user_table'),
(6,'sessions','session');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'contenttypes','0001_initial','2023-12-26 20:26:00.778274'),
(2,'auth','0001_initial','2023-12-26 20:26:34.321008'),
(3,'admin','0001_initial','2023-12-26 20:26:40.905319'),
(4,'admin','0002_logentry_remove_auto_add','2023-12-26 20:26:41.058678'),
(5,'admin','0003_logentry_add_action_flag_choices','2023-12-26 20:26:41.175017'),
(6,'contenttypes','0002_remove_content_type_name','2023-12-26 20:26:43.141306'),
(7,'auth','0002_alter_permission_name_max_length','2023-12-26 20:26:45.162547'),
(8,'auth','0003_alter_user_email_max_length','2023-12-26 20:26:45.684875'),
(9,'auth','0004_alter_user_username_opts','2023-12-26 20:26:45.869945'),
(10,'auth','0005_alter_user_last_login_null','2023-12-26 20:26:47.409856'),
(11,'auth','0006_require_contenttypes_0002','2023-12-26 20:26:47.593581'),
(12,'auth','0007_alter_validators_add_error_messages','2023-12-26 20:26:47.713380'),
(13,'auth','0008_alter_user_username_max_length','2023-12-26 20:26:50.020184'),
(14,'auth','0009_alter_user_last_name_max_length','2023-12-26 20:26:51.408032'),
(15,'auth','0010_alter_group_name_max_length','2023-12-26 20:26:51.740245'),
(16,'auth','0011_update_proxy_permissions','2023-12-26 20:26:51.909880'),
(17,'auth','0012_alter_user_first_name_max_length','2023-12-26 20:26:53.860701'),
(18,'royaloutfit','0001_initial','2023-12-26 20:27:44.946723'),
(19,'sessions','0001_initial','2023-12-26 20:27:45.634060'),
(20,'royaloutfit','0002_chat','2024-01-08 05:41:52.384632');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('2r0wpbeuqcgptphm590sxhwaypedc04b','eyJsaWQiOjExfQ:1rkFrX:pOfSiSFgNFv3uXMSp40CcKxPOfpIa_tDq4vgBlrKQYE','2024-03-27 04:05:07.748528'),
('8q48rpx8s71fjogb8ybejwoa0qi7oek0','.eJyrVsrJTFGyMtFRSoHS-flKVsY6SskgLpDOB4sb1wIA65cLOA:1rhlsU:7Mxlg6b32V8s5lnbxEFyHt2Jpk3Fr0RbrN898SGbm0w','2024-03-20 07:39:50.110626'),
('ezsml8mz952400hv6z4tqarjrdomjs4b','eyJsaWQiOjcsImRpZCI6Miwib28iOjQsIm9kaWQiOjR9:1rjwbf:DC5SxOk7ca5VOmVUe6dmCCJprZ_dJz0SH41qlWBH0V8','2024-03-26 07:31:27.726435'),
('i6myfnqftggkw2hnb52z05tf3j8ovsqi','eyJsaWQiOjgsImNpZCI6MywiZGlkIjoyLCJvbyI6Mn0:1rSEXt:KIy-ljuruVTljo1Un2Bwp4Bm9XHHXI7wu0R9yASTJxo','2024-02-06 11:02:21.245932'),
('nm1jmx42kg0gyqd9me4kecrfsam551py','eyJtX2ltYWdlIjoiL21lZGlhL3N0cmVldC5qcGciLCJsaWQiOjExfQ:1rk0v0:Wmp3nKzJfyP2dYa_0bLHB3JQlTeY2XnXfuU5Qw851LY','2024-03-26 12:07:42.637090'),
('q6crfd5dtq0s18a4mv45kw4fb4a7ysqd','eyJsaWQiOjExLCJkaWQiOjR9:1rjjF1:DVHlM3dA8lwNIxsiGeEfdLmgs07v4fkE_1U18sjMYx8','2024-03-25 17:15:11.935350');

/*Table structure for table `royaloutfit_assign_table` */

DROP TABLE IF EXISTS `royaloutfit_assign_table`;

CREATE TABLE `royaloutfit_assign_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `status` varchar(100) NOT NULL,
  `orderinfo_id` bigint NOT NULL,
  `tailorshop_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_assign_t_orderinfo_id_10c8810c_fk_royaloutf` (`orderinfo_id`),
  KEY `royaloutfit_assign_t_tailorshop_id_bde0eab4_fk_royaloutf` (`tailorshop_id`),
  CONSTRAINT `royaloutfit_assign_t_orderinfo_id_10c8810c_fk_royaloutf` FOREIGN KEY (`orderinfo_id`) REFERENCES `royaloutfit_orderinfo_table` (`id`),
  CONSTRAINT `royaloutfit_assign_t_tailorshop_id_bde0eab4_fk_royaloutf` FOREIGN KEY (`tailorshop_id`) REFERENCES `royaloutfit_tailorshop_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_assign_table` */

insert  into `royaloutfit_assign_table`(`id`,`date`,`status`,`orderinfo_id`,`tailorshop_id`) values 
(2,'2024-02-22','pending',2,3),
(3,'2024-02-22','completed',2,2),
(4,'2024-02-22','on processing ',4,2),
(5,'2024-03-12','pending',6,3);

/*Table structure for table `royaloutfit_chat` */

DROP TABLE IF EXISTS `royaloutfit_chat`;

CREATE TABLE `royaloutfit_chat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `msg` longtext NOT NULL,
  `date` date NOT NULL,
  `fromid_id` bigint NOT NULL,
  `toid_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_chat_fromid_id_801528df_fk_royaloutf` (`fromid_id`),
  KEY `royaloutfit_chat_toid_id_47cd239f_fk_royaloutfit_login_table_id` (`toid_id`),
  CONSTRAINT `royaloutfit_chat_fromid_id_801528df_fk_royaloutf` FOREIGN KEY (`fromid_id`) REFERENCES `royaloutfit_login_table` (`id`),
  CONSTRAINT `royaloutfit_chat_toid_id_47cd239f_fk_royaloutfit_login_table_id` FOREIGN KEY (`toid_id`) REFERENCES `royaloutfit_login_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_chat` */

insert  into `royaloutfit_chat`(`id`,`msg`,`date`,`fromid_id`,`toid_id`) values 
(46,'m to d','2024-03-11',17,12),
(47,'d t m','2024-03-11',12,17),
(48,'m t d','2024-03-11',17,12),
(49,'m t d1','2024-03-11',17,12),
(50,'dilu t mru','2024-03-11',12,17),
(51,'hi anas','2024-03-11',17,13),
(52,'','2024-03-11',13,17),
(53,'hi mru','2024-03-11',13,17),
(54,'hi gengan','2024-03-11',17,11),
(55,'hi','2024-03-11',11,17),
(56,'it\'s me designer abc','2024-03-11',11,17),
(57,'hi','2024-03-12',12,3),
(58,'hi','2024-03-12',12,17),
(59,'h','2024-03-12',17,4),
(60,'me mru','2024-03-13',17,11),
(61,'mru','2024-03-13',17,13),
(62,'hello','2024-03-13',17,11),
(63,'hiiii','2024-03-13',11,17),
(64,'hi','2024-03-14',17,13),
(65,'hello mr','2024-03-14',12,17),
(66,'hi dliu','2024-03-14',17,12),
(67,'hi ans','2024-03-14',17,13),
(68,'hiiiii','2024-03-15',17,12),
(69,'hiii iii','2024-03-15',12,17),
(70,'hieeeee','2024-03-15',17,13),
(71,'hiiii','2024-03-15',13,17),
(72,'hi','2024-03-16',20,12),
(73,'hii','2024-03-16',12,20);

/*Table structure for table `royaloutfit_complaint_table` */

DROP TABLE IF EXISTS `royaloutfit_complaint_table`;

CREATE TABLE `royaloutfit_complaint_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `complaint` longtext NOT NULL,
  `Date` date NOT NULL,
  `reply` varchar(100) NOT NULL,
  `USER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_complain_USER_id_15fc479d_fk_royaloutf` (`USER_id`),
  CONSTRAINT `royaloutfit_complain_USER_id_15fc479d_fk_royaloutf` FOREIGN KEY (`USER_id`) REFERENCES `royaloutfit_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_complaint_table` */

insert  into `royaloutfit_complaint_table`(`id`,`complaint`,`Date`,`reply`,`USER_id`) values 
(1,'hgfds','2024-01-08','ok',1),
(2,'bbjb','2024-01-08','done',1),
(3,'help','2024-02-14','hi',1),
(4,'new comp','2024-03-08','waiting',1),
(5,'ber','2024-03-08','waiting',1),
(6,'g','2024-03-11','waiting',2),
(7,'new','2024-03-15','waiting',2),
(8,'new','2024-03-15','waiting',2),
(9,'comp shyam','2024-03-16','waiting',4);

/*Table structure for table `royaloutfit_custom_desgin_table` */

DROP TABLE IF EXISTS `royaloutfit_custom_desgin_table`;

CREATE TABLE `royaloutfit_custom_desgin_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `status` varchar(100) NOT NULL,
  `design_details` longtext NOT NULL,
  `DESIGNER_id` bigint NOT NULL,
  `USER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_custom_d_DESIGNER_id_409b8bda_fk_royaloutf` (`DESIGNER_id`),
  KEY `royaloutfit_custom_d_USER_id_f89a356f_fk_royaloutf` (`USER_id`),
  CONSTRAINT `royaloutfit_custom_d_DESIGNER_id_409b8bda_fk_royaloutf` FOREIGN KEY (`DESIGNER_id`) REFERENCES `royaloutfit_designers_table` (`id`),
  CONSTRAINT `royaloutfit_custom_d_USER_id_f89a356f_fk_royaloutf` FOREIGN KEY (`USER_id`) REFERENCES `royaloutfit_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_custom_desgin_table` */

insert  into `royaloutfit_custom_desgin_table`(`id`,`date`,`status`,`design_details`,`DESIGNER_id`,`USER_id`) values 
(2,'2024-01-23','Accepted','fdsa',2,1),
(3,'2024-02-14','pending','dfg',2,1),
(4,'2024-03-13','Accepted','this is new design ',4,2),
(5,'2024-03-13','pending','costom second ',4,2),
(6,'2024-03-16','pending','c design ',2,4),
(7,'2024-03-16','pending','nnnn',2,2);

/*Table structure for table `royaloutfit_designers_table` */

DROP TABLE IF EXISTS `royaloutfit_designers_table`;

CREATE TABLE `royaloutfit_designers_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Gender` varchar(90) NOT NULL,
  `Place` varchar(100) NOT NULL,
  `Post` varchar(100) NOT NULL,
  `Pin` int NOT NULL,
  `Phone` bigint NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Experience` varchar(100) NOT NULL,
  `Certificate` varchar(100) NOT NULL,
  `LOGIN_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_designer_LOGIN_id_55761dce_fk_royaloutf` (`LOGIN_id`),
  CONSTRAINT `royaloutfit_designer_LOGIN_id_55761dce_fk_royaloutf` FOREIGN KEY (`LOGIN_id`) REFERENCES `royaloutfit_login_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_designers_table` */

insert  into `royaloutfit_designers_table`(`id`,`Name`,`Gender`,`Place`,`Post`,`Pin`,`Phone`,`Email`,`Experience`,`Certificate`,`LOGIN_id`) values 
(2,'ajesh','male','chalodi','irigallur',999999,7994607601,'ansppp@gmail.com','2 year','IMG_4636_o8h4gjt.jpeg',4),
(4,'rengan','male','chalodi','irigallur',676314,79946456,'ansppp@gmail.com','2 year','IMG_4636_8uYxIIQ.jpeg',11);

/*Table structure for table `royaloutfit_designs_tables` */

DROP TABLE IF EXISTS `royaloutfit_designs_tables`;

CREATE TABLE `royaloutfit_designs_tables` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL,
  `design` varchar(100) NOT NULL,
  `discription` longtext NOT NULL,
  `DESIGNER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_designs__DESIGNER_id_c9620096_fk_royaloutf` (`DESIGNER_id`),
  CONSTRAINT `royaloutfit_designs__DESIGNER_id_c9620096_fk_royaloutf` FOREIGN KEY (`DESIGNER_id`) REFERENCES `royaloutfit_designers_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_designs_tables` */

insert  into `royaloutfit_designs_tables`(`id`,`type`,`design`,`discription`,`DESIGNER_id`) values 
(4,'type1','street4.jpg','description',2);

/*Table structure for table `royaloutfit_login_table` */

DROP TABLE IF EXISTS `royaloutfit_login_table`;

CREATE TABLE `royaloutfit_login_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(90) NOT NULL,
  `type` varchar(90) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_login_table` */

insert  into `royaloutfit_login_table`(`id`,`username`,`password`,`type`) values 
(1,'admin','123','Coordinator'),
(2,'ab','123','Rejected'),
(3,'uvss','u','user'),
(4,'ds','111','blocked'),
(7,'abcd','123','manufacturer'),
(8,'manu','222','Blocked'),
(11,'abc123','123','designers'),
(12,'tdilu','t1','tailershop'),
(13,'tanas','t1','tailershop'),
(14,'qwe','567','Blocked'),
(15,'love','0987','manufacturer'),
(17,'umru','u','user'),
(18,'mru','12','pending'),
(19,'jyo','123','user'),
(20,'shyam','123','user');

/*Table structure for table `royaloutfit_manufacturer_table` */

DROP TABLE IF EXISTS `royaloutfit_manufacturer_table`;

CREATE TABLE `royaloutfit_manufacturer_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Gender` varchar(90) NOT NULL,
  `Place` varchar(100) NOT NULL,
  `Post` varchar(100) NOT NULL,
  `Pin` int NOT NULL,
  `Phone` bigint NOT NULL,
  `License` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Photo` varchar(100) NOT NULL,
  `LOGIN_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_manufact_LOGIN_id_2ac267f5_fk_royaloutf` (`LOGIN_id`),
  CONSTRAINT `royaloutfit_manufact_LOGIN_id_2ac267f5_fk_royaloutf` FOREIGN KEY (`LOGIN_id`) REFERENCES `royaloutfit_login_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_manufacturer_table` */

insert  into `royaloutfit_manufacturer_table`(`id`,`Name`,`Gender`,`Place`,`Post`,`Pin`,`Phone`,`License`,`Email`,`Photo`,`LOGIN_id`) values 
(1,'ab','m','lk','po',8655,8765,'mbv','gg','jh',2),
(4,'answif','m','chalodi','irigallur',676314,7994607601,'IMG_4636_gmAfXqj.jpeg','ansppp@gmail.com','IMG_4636_F7aXEqA.jpeg',7),
(5,'answif','m','chalodi','irigallur',676314,7994607601,'IMG_4636_X5UvlSN.jpeg','ansppp@gmail.com','IMG_4636_qfeMRPe.jpeg',8),
(6,'dilu','m','clt','cclt',675433,79946456,'IMG_4636_v7I9tc6.jpeg','ansppp@gmail.com','IMG_4636_nx13uU8.jpeg',14),
(7,'fathima','f','malappuram','malappuram',676343,8965433456,'IMG_4636_eJO9L2j.jpeg','mrtk07860@gmail.com','street.jpg',15),
(8,'mru','m','kozhikode','kakodi',673611,8129068160,'gym2.jpg','awh@gmail.com','gym.jpg',18);

/*Table structure for table `royaloutfit_materials_table` */

DROP TABLE IF EXISTS `royaloutfit_materials_table`;

CREATE TABLE `royaloutfit_materials_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `material` varchar(200) NOT NULL,
  `photo` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `stock` bigint NOT NULL,
  `details` longtext NOT NULL,
  `manufacture_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_material_manufacture_id_761fde3a_fk_royaloutf` (`manufacture_id`),
  CONSTRAINT `royaloutfit_material_manufacture_id_761fde3a_fk_royaloutf` FOREIGN KEY (`manufacture_id`) REFERENCES `royaloutfit_manufacturer_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_materials_table` */

insert  into `royaloutfit_materials_table`(`id`,`material`,`photo`,`price`,`stock`,`details`,`manufacture_id`) values 
(2,'silk','street.jpg',654,2,'tharoola',5),
(4,'plastuic','street1.jpg',456,4,'ummhh',5);

/*Table structure for table `royaloutfit_orderinfo_table` */

DROP TABLE IF EXISTS `royaloutfit_orderinfo_table`;

CREATE TABLE `royaloutfit_orderinfo_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `amount` double NOT NULL,
  `status` varchar(100) NOT NULL,
  `details` longtext NOT NULL,
  `DESIGNS_id` bigint NOT NULL,
  `USER_id` bigint NOT NULL,
  `materials_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_orderinf_DESIGNS_id_d58ef9f2_fk_royaloutf` (`DESIGNS_id`),
  KEY `royaloutfit_orderinf_USER_id_e8615e7e_fk_royaloutf` (`USER_id`),
  KEY `royaloutfit_orderinf_materials_id_6e771cc8_fk_royaloutf` (`materials_id`),
  CONSTRAINT `royaloutfit_orderinf_DESIGNS_id_d58ef9f2_fk_royaloutf` FOREIGN KEY (`DESIGNS_id`) REFERENCES `royaloutfit_designs_tables` (`id`),
  CONSTRAINT `royaloutfit_orderinf_materials_id_6e771cc8_fk_royaloutf` FOREIGN KEY (`materials_id`) REFERENCES `royaloutfit_materials_table` (`id`),
  CONSTRAINT `royaloutfit_orderinf_USER_id_e8615e7e_fk_royaloutf` FOREIGN KEY (`USER_id`) REFERENCES `royaloutfit_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_orderinfo_table` */

insert  into `royaloutfit_orderinfo_table`(`id`,`date`,`amount`,`status`,`details`,`DESIGNS_id`,`USER_id`,`materials_id`) values 
(2,'2024-01-23',3222,'fffff','gfds',4,1,2),
(3,'2024-02-24',1000,'okey','okey',4,1,2),
(4,'2024-02-13',5600,'pending','ohhh',4,1,2),
(5,'2024-03-08',0,'','ff',4,1,2),
(6,'2024-03-08',0,'','ff',4,1,2),
(7,'2024-03-08',0,'','40in',4,1,4),
(8,'2024-03-08',0,'pending','ghgh',4,1,2),
(9,'2024-03-15',0,'pending','ghh',4,2,2),
(10,'2024-03-16',0,'pending','2',4,4,2),
(11,'2024-03-16',0,'pending','1',4,4,4),
(12,'2024-03-16',0,'pending','',4,4,4),
(13,'2024-03-16',0,'pending','1',4,4,2),
(14,'2024-03-16',0,'pending','hhh',4,4,4);

/*Table structure for table `royaloutfit_rating_table` */

DROP TABLE IF EXISTS `royaloutfit_rating_table`;

CREATE TABLE `royaloutfit_rating_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Reviews` longtext NOT NULL,
  `rating` double NOT NULL,
  `Date` date NOT NULL,
  `USER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_rating_t_USER_id_d4678cf8_fk_royaloutf` (`USER_id`),
  CONSTRAINT `royaloutfit_rating_t_USER_id_d4678cf8_fk_royaloutf` FOREIGN KEY (`USER_id`) REFERENCES `royaloutfit_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_rating_table` */

insert  into `royaloutfit_rating_table`(`id`,`Reviews`,`rating`,`Date`,`USER_id`) values 
(1,'good',4,'2024-01-06',1),
(2,'hmm',3,'2024-02-14',1),
(3,'new review',2,'2024-03-08',1),
(4,'hgf',1.5,'2024-03-08',1),
(5,'revvvv',1,'2024-03-15',2),
(6,'rev shyam',2,'2024-03-16',4);

/*Table structure for table `royaloutfit_tailorshop_table` */

DROP TABLE IF EXISTS `royaloutfit_tailorshop_table`;

CREATE TABLE `royaloutfit_tailorshop_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Place` varchar(100) NOT NULL,
  `Post` varchar(100) NOT NULL,
  `Pin` int NOT NULL,
  `Phone` bigint NOT NULL,
  `License` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Photo` varchar(100) NOT NULL,
  `LOGIN_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_tailorsh_LOGIN_id_25927bc6_fk_royaloutf` (`LOGIN_id`),
  CONSTRAINT `royaloutfit_tailorsh_LOGIN_id_25927bc6_fk_royaloutf` FOREIGN KEY (`LOGIN_id`) REFERENCES `royaloutfit_login_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_tailorshop_table` */

insert  into `royaloutfit_tailorshop_table`(`id`,`Name`,`Place`,`Post`,`Pin`,`Phone`,`License`,`Email`,`Photo`,`LOGIN_id`) values 
(2,'dilu','dfghjk','fghk',99999,79946456,'IMG_4636_51XJDpv.jpeg','ansppp@gmail.com','IMG_4636_0WLIDNK.jpeg',12),
(3,'answif','chalodi','trew',984,7994607601,'IMG_4636_HSex4fW.jpeg','ansppp@gmail.com','IMG_4636_SOKQqwO.jpeg',13);

/*Table structure for table `royaloutfit_user_table` */

DROP TABLE IF EXISTS `royaloutfit_user_table`;

CREATE TABLE `royaloutfit_user_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Gender` varchar(90) NOT NULL,
  `Place` varchar(100) NOT NULL,
  `Post` varchar(100) NOT NULL,
  `Pin` int NOT NULL,
  `Phone` bigint NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Photo` varchar(100) NOT NULL,
  `LOGIN_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `royaloutfit_user_tab_LOGIN_id_4e9db843_fk_royaloutf` (`LOGIN_id`),
  CONSTRAINT `royaloutfit_user_tab_LOGIN_id_4e9db843_fk_royaloutf` FOREIGN KEY (`LOGIN_id`) REFERENCES `royaloutfit_login_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `royaloutfit_user_table` */

insert  into `royaloutfit_user_table`(`id`,`Name`,`Gender`,`Place`,`Post`,`Pin`,`Phone`,`Email`,`Photo`,`LOGIN_id`) values 
(1,'vss','m','fggg','gddd',2345,5688478,'xddd','street1.jpg',3),
(2,'mru','Male','p','h',5,7,'n','street1.jpg',17),
(3,'jythish','Male','kozhikode','palath',673611,9876542190,'jythish@gmail.com','Screenshot_2022-10-19-07-55-11-05_40deb401b9ffe8e1df2f1cc5ba480b12.jpg',19),
(4,'shaym','MALE','Kozhikode ','kizhakkummury',673611,8129068165,'sh@gmail.com','Screenshot_2022-10-19-07-55-11-05_40deb401b9ffe8e1df2f1cc5ba480b12_hltxeCv.jpg',20);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
