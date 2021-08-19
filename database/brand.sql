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

 Date: 19/08/2021 10:30:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `name` varchar(20) DEFAULT NULL COMMENT '品牌名称',
  `sort` int(20) DEFAULT NULL COMMENT '品牌排序',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand
-- ----------------------------
BEGIN;
INSERT INTO `brand` VALUES (111, '华为', 23, '2021-07-28 13:50:32', NULL);
INSERT INTO `brand` VALUES (112, '小米', 55, '2021-07-28 14:01:51', NULL);
INSERT INTO `brand` VALUES (113, '苹果', 56, '2021-07-28 14:11:31', NULL);
INSERT INTO `brand` VALUES (114, '联想', 36, '2021-07-28 14:32:12', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
