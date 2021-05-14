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

 Date: 14/05/2021 18:02:47
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_award
-- ----------------------------
INSERT INTO `t_award` VALUES (1, 'A_0001', '特等奖', 1, 5, 5, 1, '', 'G_0001', '2021-05-13 16:21:56', '2021-05-13 16:21:56');
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
                               INDEX `fk_group2employee`(`group_id`) USING BTREE,
                               INDEX `employee_id`(`employee_id`) USING BTREE,
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
INSERT INTO `t_employee` VALUES (45, 'E_00010', '孟馨', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (46, 'E_00011', '宣孝栋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (47, 'E_00012', '危琬', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (48, 'E_00013', '桓钰行', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (49, 'E_00014', '苍莎', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (50, 'E_00015', '何义巧', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (51, 'E_00016', '尤士', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (52, 'E_00017', '胡被致', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (53, 'E_00018', '阮加亮', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (54, 'E_00019', '司钧', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (55, 'E_00020', '闻婕', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (56, 'E_00021', '邬易广', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (57, 'E_00022', '屈融', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (58, 'E_00023', '劳之', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (59, 'E_00024', '毕黛', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (60, 'E_00025', '杨钰栋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (61, 'E_00026', '盛滢', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (62, 'E_00027', '欧阳妹', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (63, 'E_00028', '端木友', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (64, 'E_00029', '仇翰', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (65, 'E_00030', '景都媛', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (66, 'E_00031', '衡孝娟', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (67, 'E_00032', '居都芳', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (68, 'E_00033', '武加致', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (69, 'E_00034', '都歌婵', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (70, 'E_00035', '江贝茗', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (71, 'E_00036', '伍冠', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (72, 'E_00037', '龙星', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (73, 'E_00038', '长孙信思', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (74, 'E_00039', '唐无钧', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (75, 'E_00040', '松淑', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (76, 'E_00041', '闻九璧', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (77, 'E_00042', '乔霞', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (78, 'E_00043', '乐忠文', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (79, 'E_00044', '关信', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (80, 'E_00045', '官欧霞', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (81, 'E_00046', '汪胜', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (82, 'E_00047', '解宁', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (83, 'E_00048', '叶东', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (84, 'E_00049', '项义柔', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (85, 'E_00050', '滕翰', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (86, 'E_00051', '游加江', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (87, 'E_00052', '单友俊', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (88, 'E_00053', '乐凯娟', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (89, 'E_00054', '家伊', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (90, 'E_00055', '令狐秋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (91, 'E_00056', '夏侯瑗', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (92, 'E_00057', '左育', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (93, 'E_00058', '乐莲', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (94, 'E_00059', '山被博', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (95, 'E_00060', '崔昭', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (96, 'E_00061', '乐菁', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (97, 'E_00062', '东方蓉', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (98, 'E_00063', '干露', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (99, 'E_00064', '屈平', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (100, 'E_00065', '钟礼飘', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (101, 'E_00066', '张被龙', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (102, 'E_00067', '屈孝纨', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (103, 'E_00068', '姜智薇', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (104, 'E_00069', '粱器桂', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (105, 'E_00070', '施竹', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (106, 'E_00071', '邬林', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (107, 'E_00072', '鲍裕', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (108, 'E_00073', '洪腾', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (109, 'E_00074', '澹台华', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (110, 'E_00075', '缪友广', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (111, 'E_00076', '徐信超', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (112, 'E_00077', '史馥', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (113, 'E_00078', '贝利', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (114, 'E_00079', '容都莉', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (115, 'E_00080', '顾艳', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (116, 'E_00081', '刁锦', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (117, 'E_00082', '东门家', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (118, 'E_00083', '柯好绍', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (119, 'E_00084', '谷发', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (120, 'E_00085', '邱栋', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (121, 'E_00086', '卢山', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (122, 'E_00087', '祁凯博', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (123, 'E_00088', '王歌可', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (124, 'E_00089', '沈好云', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (125, 'E_00090', '益马新', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (126, 'E_00091', '劳思', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (127, 'E_00092', '伍芝', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (128, 'E_00093', '汪信环', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (129, 'E_00094', '公冶伯', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (130, 'E_00095', '杭翰', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (131, 'E_00096', '洪卡舒', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (132, 'E_00097', '米刚', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (133, 'E_00098', '颜伊', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');
INSERT INTO `t_employee` VALUES (134, 'E_00099', '麻纨', '开发部', 'G_0001', '2021-05-10 17:37:32', '2021-05-10 17:37:32');

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
                              `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `employee_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `employee_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                              `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `fk_group2history`(`group_id`) USING BTREE,
                              INDEX `fk_award2history`(`award_id`) USING BTREE,
                              INDEX `fk_employee2history`(`employee_id`) USING BTREE,
                              CONSTRAINT `fk_award2history` FOREIGN KEY (`award_id`) REFERENCES `t_award` (`award_id`) ON DELETE SET NULL ON UPDATE CASCADE,
                              CONSTRAINT `fk_employee2history` FOREIGN KEY (`employee_id`) REFERENCES `t_employee` (`employee_id`) ON DELETE SET NULL ON UPDATE CASCADE,
                              CONSTRAINT `fk_group2history` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_history
-- ----------------------------
INSERT INTO `t_history` VALUES (1, 'G_0001', '安凯希斯', NULL, '张三', NULL, '特等奖', '2021-05-12 14:35:20');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
                          `id` bigint(0) NOT NULL AUTO_INCREMENT,
                          `job_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                          `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                          `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                          `employee_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                          `employee_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                          `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                          `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
                          `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                          PRIMARY KEY (`id`) USING BTREE,
                          INDEX `fk_group2history`(`group_id`) USING BTREE,
                          INDEX `fk_award2history`(`award_id`) USING BTREE,
                          INDEX `fk_employee2history`(`employee_id`) USING BTREE,
                          CONSTRAINT `t_job_ibfk_1` FOREIGN KEY (`award_id`) REFERENCES `t_award` (`award_id`) ON DELETE SET NULL ON UPDATE CASCADE,
                          CONSTRAINT `t_job_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE SET NULL ON UPDATE CASCADE,
                          CONSTRAINT `t_job_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `t_employee` (`employee_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES (1, 'J_0001', 'G_0001', '安凯希斯', 'E_0001', '张三', 'A_0001', '特等奖', '2021-05-14 16:55:14');
INSERT INTO `t_job` VALUES (6, 'J_0001', 'G_0001', '安凯希斯', 'E_0002', '李四', 'A_0001', '特等奖', '2021-05-14 16:57:01');

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
