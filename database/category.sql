/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 19/08/2021 10:30:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '类目id',
  `name` char(50) DEFAULT NULL COMMENT '类目名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级类目id',
  `level` int(5) DEFAULT NULL COMMENT '类目层级',
  `sort` int(5) DEFAULT NULL COMMENT '类目排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1032 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1023, '手机', 0, 1, 33, '2021-08-12 16:07:31', NULL);
INSERT INTO `category` VALUES (1024, '苹果', 1023, 2, 23, '2021-08-12 16:07:39', NULL);
INSERT INTO `category` VALUES (1025, 'iPhone13', 1024, 3, 89, '2021-08-12 16:07:48', NULL);
INSERT INTO `category` VALUES (1026, '图书', 0, 1, 23, '2021-08-12 16:08:57', NULL);
INSERT INTO `category` VALUES (1027, 'IT书籍', 1026, 2, 73, '2021-08-12 16:09:16', NULL);
INSERT INTO `category` VALUES (1028, 'Java程序设计', 1027, 3, 14, '2021-08-12 16:09:30', NULL);
INSERT INTO `category` VALUES (1029, 'AAA', 0, 1, 15, '2021-08-12 21:14:31', NULL);
INSERT INTO `category` VALUES (1030, 'BBB', 1029, 2, 18, '2021-08-12 21:14:39', NULL);
INSERT INTO `category` VALUES (1031, 'CCC', 1030, 3, 90, '2021-08-12 21:14:45', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
