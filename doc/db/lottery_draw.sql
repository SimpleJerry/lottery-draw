/*
 Navicat Premium Data Transfer

 Source Server         : lacal_root
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : lottery_draw

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 23/06/2021 11:23:19
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
                            INDEX `group_id`(`group_id`) USING BTREE,
                            CONSTRAINT `fk_group2admin` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', 'admin', 'G_0001', 'Jerry', '15800719512');

-- ----------------------------
-- Table structure for t_award
-- ----------------------------
DROP TABLE IF EXISTS `t_award`;
CREATE TABLE `t_award`  (
                            `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '奖品id',
                            `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '奖品名称',
                            `once_quantity` int(0) NOT NULL COMMENT '单次抽取数',
                            `total_quantity` int(0) NOT NULL COMMENT '奖品总数',
                            `remain_quantity` int(0) NOT NULL COMMENT '奖品剩余数',
                            `priority` int(0) NOT NULL COMMENT '优先级',
                            `img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片路径',
                            `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '组织id',
                            `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                            `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `award_id`(`award_id`) USING BTREE,
                            INDEX `group_id`(`group_id`) USING BTREE,
                            CONSTRAINT `fk_group2award` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_award
-- ----------------------------
INSERT INTO `t_award` VALUES (1, 'A_0001', '特等奖', 1, 5, 0, 1, '', 'G_0001', '2021-05-17 15:16:56', '2021-05-17 15:16:56');
INSERT INTO `t_award` VALUES (2, 'A_0002', '一等奖', 2, 10, 10, 2, '', 'G_0001', '2021-05-13 16:21:58', '2021-05-13 16:21:58');
INSERT INTO `t_award` VALUES (3, 'A_0003', '二等奖', 3, 15, 15, 3, '', 'G_0001', '2021-05-13 16:22:00', '2021-05-13 16:22:00');
INSERT INTO `t_award` VALUES (4, 'A_0004', '三等奖', 5, 20, 20, 4, '', 'G_0001', '2021-05-13 16:22:02', '2021-05-13 16:22:02');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee`  (
                               `id` bigint(0) NOT NULL AUTO_INCREMENT,
                               `employee_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
                               `employee_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
                               `department` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
                               `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
                               `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                               `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `employee_id`(`employee_id`) USING BTREE,
                               INDEX `group_id`(`group_id`) USING BTREE,
                               CONSTRAINT `fk_group2employee` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 135 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES (35, 'E_0000', '郭咏', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (36, 'E_0001', '东门秋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (37, 'E_0002', '卜和', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (38, 'E_0003', '程玉凡', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (39, 'E_0004', '焦策', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (40, 'E_0005', '束友霞', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (41, 'E_0006', '花林', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (42, 'E_0007', '谷姬', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (43, 'E_0008', '沈岚', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (44, 'E_0009', '蔡九保', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (45, 'E_0010', '孟馨', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (46, 'E_0011', '宣孝栋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (47, 'E_0012', '危琬', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (48, 'E_0013', '桓钰行', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (49, 'E_0014', '苍莎', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (50, 'E_0015', '何义巧', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (51, 'E_0016', '尤士', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (52, 'E_0017', '胡被致', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (53, 'E_0018', '阮加亮', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (54, 'E_0019', '司钧', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (55, 'E_0020', '闻婕', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (56, 'E_0021', '邬易广', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (57, 'E_0022', '屈融', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (58, 'E_0023', '劳之', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (59, 'E_0024', '毕黛', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (60, 'E_0025', '杨钰栋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (61, 'E_0026', '盛滢', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (62, 'E_0027', '欧阳妹', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (63, 'E_0028', '端木友', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (64, 'E_0029', '仇翰', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (65, 'E_0030', '景都媛', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (66, 'E_0031', '衡孝娟', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (67, 'E_0032', '居都芳', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (68, 'E_0033', '武加致', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (69, 'E_0034', '都歌婵', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (70, 'E_0035', '江贝茗', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (71, 'E_0036', '伍冠', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (72, 'E_0037', '龙星', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (73, 'E_0038', '长孙信思', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (74, 'E_0039', '唐无钧', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (75, 'E_0040', '松淑', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (76, 'E_0041', '闻九璧', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (77, 'E_0042', '乔霞', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (78, 'E_0043', '乐忠文', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (79, 'E_0044', '关信', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (80, 'E_0045', '官欧霞', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (81, 'E_0046', '汪胜', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (82, 'E_0047', '解宁', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (83, 'E_0048', '叶东', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (84, 'E_0049', '项义柔', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (85, 'E_0050', '滕翰', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (86, 'E_0051', '游加江', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (87, 'E_0052', '单友俊', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (88, 'E_0053', '乐凯娟', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (89, 'E_0054', '家伊', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (90, 'E_0055', '令狐秋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (91, 'E_0056', '夏侯瑗', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (92, 'E_0057', '左育', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (93, 'E_0058', '乐莲', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (94, 'E_0059', '山被博', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (95, 'E_0060', '崔昭', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (96, 'E_0061', '乐菁', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (97, 'E_0062', '东方蓉', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (98, 'E_0063', '干露', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (99, 'E_0064', '屈平', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (100, 'E_0065', '钟礼飘', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (101, 'E_0066', '张被龙', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (102, 'E_0067', '屈孝纨', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (103, 'E_0068', '姜智薇', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (104, 'E_0069', '粱器桂', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (105, 'E_0070', '施竹', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (106, 'E_0071', '邬林', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (107, 'E_0072', '鲍裕', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (108, 'E_0073', '洪腾', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (109, 'E_0074', '澹台华', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (110, 'E_0075', '缪友广', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (111, 'E_0076', '徐信超', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (112, 'E_0077', '史馥', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (113, 'E_0078', '贝利', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (114, 'E_0079', '容都莉', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (115, 'E_0080', '顾艳', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (116, 'E_0081', '刁锦', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (117, 'E_0082', '东门家', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (118, 'E_0083', '柯好绍', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (119, 'E_0084', '谷发', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (120, 'E_0085', '邱栋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (121, 'E_0086', '卢山', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (122, 'E_0087', '祁凯博', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (123, 'E_0088', '王歌可', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (124, 'E_0089', '沈好云', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (125, 'E_0090', '益马新', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (126, 'E_0091', '劳思', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (127, 'E_0092', '伍芝', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (128, 'E_0093', '汪信环', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (129, 'E_0094', '公冶伯', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (130, 'E_0095', '杭翰', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (131, 'E_0096', '洪卡舒', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (132, 'E_0097', '米刚', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (133, 'E_0098', '颜伊', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (134, 'E_0099', '麻纨', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');

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
                              `job_id` bigint(0) NOT NULL,
                              `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `employee_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `group_id`(`group_id`) USING BTREE,
                              INDEX `award_id`(`award_id`) USING BTREE,
                              INDEX `employee_id`(`employee_id`) USING BTREE,
                              INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_history
-- ----------------------------
INSERT INTO `t_history` VALUES (1, 1394187297934675968, 'G_0001', 'E_0067', 'A_0001', '2021-06-22 16:35:34');
INSERT INTO `t_history` VALUES (2, 1394187297934675968, 'G_0001', 'E_0002', 'A_0001', '2021-06-22 16:35:35');
INSERT INTO `t_history` VALUES (3, 1394187297934675968, 'G_0001', 'E_0077', 'A_0001', '2021-06-22 16:35:36');
INSERT INTO `t_history` VALUES (4, 1394187297934675968, 'G_0001', 'E_0096', 'A_0001', '2021-06-22 16:35:36');
INSERT INTO `t_history` VALUES (5, 1394187297934675968, 'G_0001', 'E_0050', 'A_0001', '2021-06-22 16:35:45');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
                          `id` bigint(0) NOT NULL AUTO_INCREMENT,
                          `job_id` bigint(0) NOT NULL,
                          `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                          `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                          PRIMARY KEY (`id`) USING BTREE,
                          INDEX `group_id`(`group_id`) USING BTREE,
                          INDEX `job_id`(`job_id`) USING BTREE,
                          CONSTRAINT `fk_group2job` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES (1, 1394187297934675968, 'G_0001', '2021-06-22 16:23:00');
INSERT INTO `t_job` VALUES (2, 3279865135109358901, 'G_0001', '2021-06-22 16:34:35');

-- ----------------------------
-- Table structure for t_job_result
-- ----------------------------
DROP TABLE IF EXISTS `t_job_result`;
CREATE TABLE `t_job_result`  (
                                 `id` bigint(0) NOT NULL AUTO_INCREMENT,
                                 `job_id` bigint(0) NOT NULL DEFAULT '',
                                 `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                                 `employee_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                                 `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                                 `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `fk_group2history`(`group_id`) USING BTREE,
                                 INDEX `fk_award2history`(`award_id`) USING BTREE,
                                 INDEX `fk_employee2history`(`employee_id`) USING BTREE,
                                 CONSTRAINT `fk_group2job_result` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_job_result
-- ----------------------------
INSERT INTO `t_job_result` VALUES (1, 1394187297934675968, 'G_0001', 'E_0067', 'A_0001', '2021-06-22 16:23:25');
INSERT INTO `t_job_result` VALUES (2, 1394187297934675968, 'G_0001', 'E_0002', 'A_0001', '2021-06-22 16:23:28');
INSERT INTO `t_job_result` VALUES (3, 1394187297934675968, 'G_0001', 'E_0077', 'A_0001', '2021-06-22 16:23:29');
INSERT INTO `t_job_result` VALUES (4, 1394187297934675968, 'G_0001', 'E_0096', 'A_0001', '2021-06-22 16:23:30');
INSERT INTO `t_job_result` VALUES (5, 1394187297934675968, 'G_0001', 'E_0050', 'A_0001', '2021-06-22 16:23:31');
INSERT INTO `t_job_result` VALUES (6, 3279865135109358901, 'G_0001', 'E_0077', 'A_0001', '2021-06-22 16:24:29');

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
