/*
 Navicat Premium Data Transfer

 Source Server         : local_jerry
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : lottery_draw

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 13/05/2021 16:35:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_admin2group`(`group_id`) USING BTREE,
  CONSTRAINT `fk_admin2group` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', 'admin', 'G_0001', 'Jerry', '15800719512');

-- ----------------------------
-- Table structure for t_award
-- ----------------------------
DROP TABLE IF EXISTS `t_award`;
CREATE TABLE `t_award`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `once_quantity` int(0) NOT NULL,
  `total_quantity` int(0) NOT NULL,
  `remain_quantity` int(0) NOT NULL,
  `priority` int(0) NOT NULL,
  `img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_group2award`(`group_id`) USING BTREE,
  INDEX `award_id`(`award_id`) USING BTREE,
  CONSTRAINT `fk_group2award` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_award
-- ----------------------------
INSERT INTO `t_award` VALUES (1, 'A_0001', '特等奖', 1, 5, 5, 1, '', 'G_0001', '2021-05-13 16:21:56', '2021-05-13 16:21:56');
INSERT INTO `t_award` VALUES (2, 'A_0002', '一等奖', 2, 10, 10, 2, '', 'G_0001', '2021-05-13 16:21:58', '2021-05-13 16:21:58');
INSERT INTO `t_award` VALUES (3, 'A_0003', '二等奖', 3, 15, 15, 3, '', 'G_0001', '2021-05-13 16:22:00', '2021-05-13 16:22:00');
INSERT INTO `t_award` VALUES (4, 'A_0004', '三等奖', 5, 20, 20, 4, '', 'G_0001', '2021-05-13 16:22:02', '2021-05-13 16:22:02');

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES (1, 'G_0001', '安凯希斯', '2021-05-10 17:37:56', '2021-05-10 17:37:56');
INSERT INTO `t_group` VALUES (2, 'G_0002', '腾讯', '2021-05-12 15:21:35', '2021-05-12 15:21:35');

-- ----------------------------
-- Table structure for t_history
-- ----------------------------
DROP TABLE IF EXISTS `t_history`;
CREATE TABLE `t_history`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `history_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_group2history`(`group_id`) USING BTREE,
  INDEX `fk_award2history`(`award_id`) USING BTREE,
  INDEX `fk_user2history`(`user_id`) USING BTREE,
  CONSTRAINT `fk_award2history` FOREIGN KEY (`award_id`) REFERENCES `t_award` (`award_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_group2history` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_user2history` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_history
-- ----------------------------
INSERT INTO `t_history` VALUES (1, 'H_0001', 'G_0001', '安凯希斯', 'U_0001', '张三', NULL, '特等奖', '2021-05-12 14:35:20');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `department` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_group2user`(`group_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_group2user` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'U_0001', '张三', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_user` VALUES (2, 'U_0002', '李四', '摸鱼部', 'G_0002', '2021-05-12 15:22:06', '2021-05-12 15:22:06');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '测试', 'password');

SET FOREIGN_KEY_CHECKS = 1;
