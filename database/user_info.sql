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

 Date: 19/08/2021 10:31:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id（主键）',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `avatar` varchar(300) DEFAULT NULL COMMENT '用户头像',
  `role` char(50) DEFAULT NULL COMMENT '用户角色',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态，1表示正常，0表示暂停',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100037 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (100030, 'admin', '$2a$10$VKWjuiphM8GivL5QY.gIduqaULMUx/oVCX0f4oN4cEkZBt8JMhDJW', 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 'ROLE_admin', 1, '2021-07-16 10:35:02', NULL);
INSERT INTO `user_info` VALUES (100034, 'wang', '$2a$10$c6cG76ZFrLUqIIhzEgNzweh5.sQHSB0fljEW0u1f5vyjAYwRt.sDO', 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', '普通用户', 1, '2021-07-16 12:01:41', '2021-07-16 12:11:57');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
