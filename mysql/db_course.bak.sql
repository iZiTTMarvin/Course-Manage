/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : db_course

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 27/05/2024 01:25:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course_academic
-- ----------------------------
DROP TABLE IF EXISTS `course_academic`;
CREATE TABLE `course_academic`  (
                                    `academic_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                    `academic_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                    `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                    `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                    `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                    `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                    PRIMARY KEY (`academic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学院' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_academic
-- ----------------------------
INSERT INTO `course_academic` VALUES (1, '信息技术学院', '龚秀英', '2024-05-23 00:51:17', '', NULL, 0, 1, NULL);
INSERT INTO `course_academic` VALUES (2, '工程技术学院', '龚秀英', '2024-05-23 00:55:00', '', NULL, 0, 1, NULL);
INSERT INTO `course_academic` VALUES (3, '体育学院', '龚秀英', '2024-05-23 00:55:25', '', NULL, 0, 1, NULL);
INSERT INTO `course_academic` VALUES (4, '艺术学院', '龚秀英', '2024-05-23 00:55:31', '', NULL, 0, 1, NULL);
INSERT INTO `course_academic` VALUES (5, '教育学院', '龚秀英', '2024-05-23 00:55:37', '', NULL, 0, 1, NULL);
INSERT INTO `course_academic` VALUES (6, '医学院', '龚秀英', '2024-05-23 00:55:43', '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_classroom
-- ----------------------------
DROP TABLE IF EXISTS `course_classroom`;
CREATE TABLE `course_classroom`  (
                                     `classroom_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                     `classroom_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                     `classroom_room` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教室',
                                     `classroom_site` int NULL DEFAULT NULL COMMENT '座位',
                                     `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                     `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                     `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                     `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                     `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                     `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                     `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                     PRIMARY KEY (`classroom_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '教室' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_classroom
-- ----------------------------
INSERT INTO `course_classroom` VALUES (1, '经管楼', '401', 50, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_classroom` VALUES (2, '经管楼', '402', 50, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_classroom` VALUES (3, '经管楼', '403', 50, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_classroom` VALUES (4, '经管楼', '404', 50, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_classroom` VALUES (5, '经管楼', '101', 300, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_classroom` VALUES (6, '经管楼', '102', 300, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_classroom` VALUES (7, '经管楼', '103', 300, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_classroom` VALUES (8, '经管楼', '104', 300, '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_grade
-- ----------------------------
DROP TABLE IF EXISTS `course_grade`;
CREATE TABLE `course_grade`  (
                                 `grade_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                 `grade_user` int NULL DEFAULT NULL COMMENT '用户@',
                                 `grade_list` int NULL DEFAULT NULL COMMENT '课程@',
                                 `grade_score` int NULL DEFAULT NULL COMMENT '分数',
                                 `grade_grade` int NULL DEFAULT NULL COMMENT '学分',
                                 `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                 `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                 `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                 `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生成绩' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_grade
-- ----------------------------

-- ----------------------------
-- Table structure for course_list
-- ----------------------------
DROP TABLE IF EXISTS `course_list`;
CREATE TABLE `course_list`  (
                                `list_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `list_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                `list_grade` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学分',
                                `list_type` int NULL DEFAULT NULL COMMENT '类型@',
                                `list_profess` int NULL DEFAULT NULL COMMENT '专业@',
                                `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`list_id`) USING BTREE,
                                INDEX `course_list_course_type_type_id_fk`(`list_type` ASC) USING BTREE,
                                CONSTRAINT `course_list_course_type_type_id_fk` FOREIGN KEY (`list_type`) REFERENCES `course_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_list
-- ----------------------------
INSERT INTO `course_list` VALUES (1, '新时代中国特色社会主义思想', '0.5', 1, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_list` VALUES (2, '软件工程导论', '0.5', 1, 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_list` VALUES (3, 'SSM开发实战', '0.5', 1, 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_list` VALUES (4, '毛泽东思想', '0.5', 1, 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_list` VALUES (5, '软件体系架构', '0.5', 2, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_list` VALUES (6, '演讲与口才', '0.2', 3, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_list` VALUES (7, '创新创业', '0.2', 3, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_list` VALUES (8, '写作指导', '0.2', 3, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_list` VALUES (9, '大学生就业指导', '0.2', 3, NULL, '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_menu
-- ----------------------------
DROP TABLE IF EXISTS `course_menu`;
CREATE TABLE `course_menu`  (
                                `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `menu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                `menu_icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
                                `menu_keypath` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标识符',
                                `menu_ranks` int NULL DEFAULT NULL COMMENT '排序',
                                `menu_parent` int NULL DEFAULT NULL COMMENT '父级',
                                `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_menu
-- ----------------------------
INSERT INTO `course_menu` VALUES (1, '用户管理', 'Compass', '1', 1, 17, '', NULL, '龚秀英', '2024-05-23 15:39:03', 0, 1, NULL);
INSERT INTO `course_menu` VALUES (2, '角色管理', 'Setting', '2', 2, 17, '', NULL, '龚秀英', '2024-05-23 15:39:05', 0, 1, NULL);
INSERT INTO `course_menu` VALUES (3, '菜单管理', 'Clock', '3', 3, 17, '', NULL, '龚秀英', '2024-05-23 15:39:17', 0, 1, NULL);
INSERT INTO `course_menu` VALUES (4, '课程类型', 'CircleClose', '4', 4, 19, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (5, '课程目录', 'Operation', '5', 5, 19, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (6, '选课开设', 'Crop', '6', 6, 19, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (7, '作息管理', 'PieChart', '7', 7, 17, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (8, '选课中心', 'Open', '8', 14, 20, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (9, '我的课表', 'Finished', '9', 15, 20, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (10, '我的信息', 'ChatDotSquare', '10', 16, 20, '', NULL, '龚秀英', '2024-05-22 00:37:29', 0, 1, NULL);
INSERT INTO `course_menu` VALUES (11, '教室管理', 'Search', '11', 12, 18, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (12, '班级管理', 'MuteNotification', '12', 10, 18, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (13, '专业管理', 'Odometer', '13', 9, 18, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (14, '学院管理', 'Bell', '14', 8, 18, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (15, '成绩管理', 'Discount', '15', 13, 19, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (16, '班级课表', 'ChatLineSquare', '16', 11, 19, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (17, '系统管理', 'Odometer', '17', 1, 0, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (18, '学院管理', 'CircleClose', '18', 2, 0, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (19, '课程管理', 'Open', '19', 3, 0, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_menu` VALUES (20, '个人中心', 'FullScreen', '20', 4, 0, '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_profess
-- ----------------------------
DROP TABLE IF EXISTS `course_profess`;
CREATE TABLE `course_profess`  (
                                   `profess_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                   `profess_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                   `profess_academic` int NULL DEFAULT NULL COMMENT '学院@',
                                   `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                   `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                   `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                   `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                   `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                   `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                   `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                   PRIMARY KEY (`profess_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '专业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_profess
-- ----------------------------
INSERT INTO `course_profess` VALUES (1, '软件工程', 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_profess` VALUES (2, '大数据', 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_profess` VALUES (3, '人工智能', 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_profess` VALUES (4, '计算机科学', 1, '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_rm
-- ----------------------------
DROP TABLE IF EXISTS `course_rm`;
CREATE TABLE `course_rm`  (
                              `rm_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                              `rm_role` int NULL DEFAULT NULL COMMENT '角色',
                              `rm_menu` int NULL DEFAULT NULL COMMENT '菜单',
                              `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                              `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                              `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                              `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                              `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                              `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                              PRIMARY KEY (`rm_id`) USING BTREE,
                              INDEX `course_rm_course_role_role_id_fk`(`rm_role` ASC) USING BTREE,
                              INDEX `course_rm_course_menu_menu_id_fk`(`rm_menu` ASC) USING BTREE,
                              CONSTRAINT `course_rm_course_menu_menu_id_fk` FOREIGN KEY (`rm_menu`) REFERENCES `course_menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                              CONSTRAINT `course_rm_course_role_role_id_fk` FOREIGN KEY (`rm_role`) REFERENCES `course_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_rm
-- ----------------------------
INSERT INTO `course_rm` VALUES (1, 1, 1, '', NULL, '龚秀英', '2024-05-26 02:56:45', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (2, 1, 2, '', NULL, '龚秀英', '2024-05-26 02:56:45', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (3, 1, 3, '', NULL, '龚秀英', '2024-05-26 02:56:45', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (4, 1, 4, '', NULL, '龚秀英', '2024-05-26 03:02:29', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (5, 1, 5, '', NULL, '龚秀英', '2024-05-26 02:56:24', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (6, 1, 6, '', NULL, '龚秀英', '2024-05-26 02:56:24', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (7, 1, 7, NULL, NULL, '龚秀英', '2024-05-26 02:56:45', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (8, 1, 8, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (9, 1, 9, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (10, 1, 10, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (11, 1, 11, '', NULL, '龚秀英', '2024-05-26 02:56:39', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (12, 1, 12, '', NULL, '龚秀英', '2024-05-26 02:56:39', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (13, 1, 13, '', NULL, '龚秀英', '2024-05-26 02:56:39', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (14, 1, 14, '', NULL, '龚秀英', '2024-05-26 02:56:39', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (15, 1, 15, '', NULL, '龚秀英', '2024-05-26 02:56:24', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (16, 1, 16, '', NULL, '龚秀英', '2024-05-26 02:56:24', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (17, 1, 17, '', NULL, '龚秀英', '2024-05-26 02:56:45', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (18, 1, 18, '', NULL, '龚秀英', '2024-05-26 02:56:39', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (19, 1, 19, '', NULL, '龚秀英', '2024-05-26 03:02:29', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (20, 1, 20, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (21, 2, 1, '', NULL, '', NULL, 1, 1, NULL);
INSERT INTO `course_rm` VALUES (22, 2, 2, '', NULL, '', NULL, 1, 1, NULL);
INSERT INTO `course_rm` VALUES (23, 2, 3, '', NULL, '', NULL, 1, 1, NULL);
INSERT INTO `course_rm` VALUES (24, 2, 4, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (25, 2, 5, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (26, 2, 6, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (27, 2, 7, '', NULL, '', '2024-05-26 02:47:47', 1, 1, NULL);
INSERT INTO `course_rm` VALUES (28, 2, 8, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (29, 2, 9, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (30, 2, 10, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (31, 2, 11, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (32, 2, 12, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (33, 2, 13, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (34, 2, 14, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (35, 2, 15, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (36, 2, 16, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (37, 2, 17, '', NULL, '', NULL, 1, 1, NULL);
INSERT INTO `course_rm` VALUES (38, 2, 18, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (39, 2, 19, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (40, 2, 20, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (41, 3, 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (42, 3, 2, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (43, 3, 3, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (44, 3, 4, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (45, 3, 5, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (46, 3, 6, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (47, 3, 7, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (48, 3, 8, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (49, 3, 9, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (50, 3, 10, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (51, 3, 11, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (52, 3, 12, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (53, 3, 13, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (54, 3, 14, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (55, 3, 15, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (56, 3, 16, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (57, 3, 17, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (58, 3, 18, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (59, 3, 19, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_rm` VALUES (60, 3, 20, '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_role
-- ----------------------------
DROP TABLE IF EXISTS `course_role`;
CREATE TABLE `course_role`  (
                                `role_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_role
-- ----------------------------
INSERT INTO `course_role` VALUES (1, '学生', '', NULL, '龚秀英', '2024-05-22 00:27:11', 0, 1, NULL);
INSERT INTO `course_role` VALUES (2, '教师', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_role` VALUES (3, '管理员', '', NULL, '龚秀英', '2024-05-22 00:27:29', 0, 1, NULL);

-- ----------------------------
-- Table structure for course_schedule
-- ----------------------------
DROP TABLE IF EXISTS `course_schedule`;
CREATE TABLE `course_schedule`  (
                                    `schedule_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                    `schedule_team` int NULL DEFAULT NULL COMMENT '班级@',
                                    `schedule_time` int NULL DEFAULT NULL COMMENT '时间@',
                                    `schedule_week` int NULL DEFAULT NULL COMMENT '星期（1~7)',
                                    `schedule_list` int NULL DEFAULT NULL COMMENT '课程@',
                                    `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                    `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                    `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                    `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                    PRIMARY KEY (`schedule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '班级课表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for course_table
-- ----------------------------
DROP TABLE IF EXISTS `course_table`;
CREATE TABLE `course_table`  (
                                 `table_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                 `table_time` int NULL DEFAULT NULL COMMENT '时间@',
                                 `table_week` int NULL DEFAULT NULL COMMENT '星期（1~7)',
                                 `table_teacher` int NULL DEFAULT NULL COMMENT '老师@',
                                 `table_classroom` int NULL DEFAULT NULL COMMENT '教室@',
                                 `table_list` int NULL DEFAULT NULL COMMENT '课程@',
                                 `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                 `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                 `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                 `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`table_id`) USING BTREE,
                                 INDEX `course_table_course_time_time_id_fk`(`table_time` ASC) USING BTREE,
                                 INDEX `course_table_course_list_list_id_fk`(`table_list` ASC) USING BTREE,
                                 CONSTRAINT `course_table_course_list_list_id_fk` FOREIGN KEY (`table_list`) REFERENCES `course_list` (`list_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                 CONSTRAINT `course_table_course_time_time_id_fk` FOREIGN KEY (`table_time`) REFERENCES `course_time` (`time_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_table
-- ----------------------------
INSERT INTO `course_table` VALUES (1, 1, 2, 2, 1, 9, '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_team
-- ----------------------------
DROP TABLE IF EXISTS `course_team`;
CREATE TABLE `course_team`  (
                                `team_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `team_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                `team_profess` int NULL DEFAULT NULL COMMENT '所属专业',
                                `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`team_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '班级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_team
-- ----------------------------
INSERT INTO `course_team` VALUES (1, 'B2101', 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_team` VALUES (2, 'B2102', 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_team` VALUES (3, 'B2103', 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_team` VALUES (4, 'B2104', 1, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_team` VALUES (5, 'B2101', 2, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_team` VALUES (6, 'B2102', 2, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_team` VALUES (7, 'B2103', 2, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_team` VALUES (8, 'B2104', 2, '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_time
-- ----------------------------
DROP TABLE IF EXISTS `course_time`;
CREATE TABLE `course_time`  (
                                `time_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `time_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                `time_start` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开始时间',
                                `time_end` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '结束时间',
                                `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`time_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_time
-- ----------------------------
INSERT INTO `course_time` VALUES (1, '上午第一节课', '08:30:00', '09:15:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (2, '上午第二节课', '09:20:00', '10:05:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (3, '上午第三节课', '10:25:00', '11:10:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (4, '上午第四节课', '11:15:00', '12:00:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (5, '下午第一节课', '14:00:00', '14:45:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (6, '下午第二节课', '14:50:00', '15:35:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (7, '下午第三节课', '15:40:00', '16:25:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (8, '晚上第一节课', '19:00:00', '19:45:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (9, '晚上第二节课', '19:50:00', '20:35:00', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_time` VALUES (10, '晚上第三节课', '20:40:00', '21:25:00', '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_type
-- ----------------------------
DROP TABLE IF EXISTS `course_type`;
CREATE TABLE `course_type`  (
                                `type_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `type_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_type
-- ----------------------------
INSERT INTO `course_type` VALUES (1, '必修课', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_type` VALUES (2, '选修课', '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_type` VALUES (3, '公开课', '', NULL, '', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for course_user
-- ----------------------------
DROP TABLE IF EXISTS `course_user`;
CREATE TABLE `course_user`  (
                                `user_id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `user_pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
                                `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
                                `user_gender` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
                                `user_birth` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生日',
                                `user_acc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
                                `user_pwd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
                                `user_role` int NULL DEFAULT NULL COMMENT '角色@',
                                `user_start` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入学年份',
                                `user_academic` int NULL DEFAULT NULL COMMENT '学院',
                                `user_profess` int NULL DEFAULT NULL COMMENT '专业',
                                `user_team` int NULL DEFAULT NULL COMMENT '班级',
                                `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                `undock` int NOT NULL DEFAULT 0 COMMENT '伪删除',
                                `state` int NOT NULL DEFAULT 1 COMMENT '状态',
                                `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`user_id`) USING BTREE,
                                INDEX `course_user_course_role_role_id_fk`(`user_role` ASC) USING BTREE,
                                CONSTRAINT `course_user_course_role_role_id_fk` FOREIGN KEY (`user_role`) REFERENCES `course_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_user
-- ----------------------------
INSERT INTO `course_user` VALUES (1, NULL, '严致远', '男', '2019-09-01', '1', '1', 1, '2008-02-20', NULL, NULL, NULL, '', NULL, '龚秀英', '2024-05-21 21:34:15', 0, 1, NULL);
INSERT INTO `course_user` VALUES (2, NULL, '袁璐', '女', '2016-03-11', '2', '2', 2, '2018-09-15', NULL, NULL, NULL, '', NULL, '龚秀英', '2024-05-21 21:33:17', 0, 1, NULL);
INSERT INTO `course_user` VALUES (3, NULL, '龚秀英', '女', '2004-02-06', '3', '3', 3, '2016-08-20', NULL, NULL, NULL, '', NULL, '龚秀英', '2024-05-21 21:34:17', 0, 1, NULL);
INSERT INTO `course_user` VALUES (4, NULL, '陈宇宁', '男', '2004-12-20', '2423953096', '4hwt2SvDAq', 1, '2010-11-23', NULL, NULL, NULL, '', NULL, '龚秀英', '2024-05-21 21:25:32', 1, 1, NULL);
INSERT INTO `course_user` VALUES (5, NULL, '吴璐', '女', '2010-07-27', '2891014954', 'WfoDd6qM11', 1, '2015-10-21', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (6, NULL, '罗宇宁', '男', '2017-10-26', '4573136541', 'ClaWm1kDZa', 1, '2019-03-25', NULL, NULL, NULL, '', NULL, '', NULL, 0, 0, NULL);
INSERT INTO `course_user` VALUES (7, NULL, '龙晓明', '男', '2009-02-15', '2087765210', 'XVNWVjmJfp', 1, '2003-02-23', NULL, NULL, NULL, '', NULL, '', NULL, 0, 0, NULL);
INSERT INTO `course_user` VALUES (8, NULL, '任晓明', '男', '2007-01-23', '8810684593', 'woMIPndwYV', 1, '2013-10-15', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (9, NULL, '梁宇宁', '男', '2011-05-13', '7516395185', 'DEvnH0IvOu', 1, '2023-08-08', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (10, NULL, '黎宇宁', '男', '2000-12-25', '2564026136', 'VazpV85Qjf', 1, '2002-02-22', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (11, NULL, '吕子异', '男', '2006-01-01', '4452230279', 'kXV3Yl31lA', 1, '2007-09-25', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (12, NULL, '高晓明', '男', '2006-04-26', '2137237039', 'wmupfoBVxw', 1, '2015-02-04', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (13, NULL, '陶云熙', '男', '2017-10-20', '8900566424', 'mxKkzdwGuV', 1, '2009-03-17', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (14, NULL, '李子韬', '男', '2016-12-01', '6141498869', '0avnBloXbK', 1, '2022-05-04', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (15, NULL, '蔡秀英', '女', '2019-03-11', '2200300385', 'CsHGw2C3Fv', 1, '2011-09-24', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (16, NULL, '高睿', '男', '2009-12-07', '4221433669', '9TE6bAvn0a', 1, '2017-03-25', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (17, NULL, '高詩涵', '女', '2005-04-09', '1916038903', 'cXEfSzkDXn', 1, '2007-08-10', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (18, NULL, '方晓明', '男', '2006-07-07', '8878580115', 'UAPxIVFZIT', 1, '2001-05-25', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (19, NULL, '范詩涵', '女', '2012-03-23', '2088811897', '2swGyeNoXM', 1, '2001-09-29', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (20, NULL, '王晓明', '男', '2005-01-14', '0831904718', 'hWK8UWZM8s', 1, '2001-03-20', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (21, NULL, '方晓明', '男', '2011-02-16', '4683178755', 'oYnQryxMCr', 1, '2004-03-14', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (22, NULL, '廖詩涵', '女', '2001-11-01', '2825687593', 'ApV5Vxi9G9', 1, '2004-10-31', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (23, NULL, '钟睿', '男', '2000-04-26', '7645719932', 'NjvKwNIgib', 1, '2012-12-20', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (24, NULL, '袁岚', '女', '2004-01-29', '2685044310', 'CjtG0Mdwqc', 1, '2014-06-21', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (25, NULL, '龚岚', '女', '2021-08-23', '9969818963', 'wEzVLdbBLw', 1, '2018-04-16', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (26, NULL, '史云熙', '男', '2005-09-12', '2507399716', 'Xyw0xTYB3l', 1, '2000-10-19', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (27, NULL, '魏震南', '男', '2010-07-10', '4104924060', 'L8ur6CzvfD', 1, '2013-03-19', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (28, NULL, '陆云熙', '男', '2005-03-17', '8468057539', 'jbtxju5yPd', 1, '2018-07-10', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (29, NULL, '黎致远', '男', '2005-07-19', '0530032686', '8wDZM2tknQ', 1, '2019-12-19', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (30, NULL, '冯安琪', '女', '2004-03-13', '6315343600', 'Tqn8oqVe3d', 1, '2008-12-05', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (31, NULL, '唐杰宏', '男', '2019-08-04', '5006930121', 'QFHFkOnsfz', 1, '2006-08-04', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (32, NULL, '蔡子异', '男', '2023-01-24', '8174022044', 'rMjaAnvawb', 1, '2022-11-06', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (33, NULL, '邹云熙', '男', '2010-08-29', '7616174726', 'V0APGrAvbk', 1, '2015-09-21', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (34, NULL, '何睿', '男', '2010-11-17', '7643258096', 'PPlPMIca5M', 1, '2018-07-11', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (35, NULL, '胡秀英', '女', '2011-03-03', '3834133463', '30uUJ4xeEw', 1, '2020-11-15', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (36, NULL, '侯子异', '男', '2021-10-01', '2072229177', 'DSHthliTLr', 1, '2018-02-08', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (37, NULL, '黎璐', '女', '2023-08-11', '8567651998', 'aDyyvcjn3V', 1, '2014-01-19', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (38, NULL, '梁璐', '女', '2010-04-15', '0010788510', 'ftr5WEp9gt', 1, '2002-09-06', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (39, NULL, '贺嘉伦', '男', '2006-12-04', '6798958022', 'yIeKsFxZhg', 1, '2001-02-12', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (40, NULL, '陆璐', '女', '2022-05-10', '7550109504', 'PpVae2YpYe', 2, '2016-04-26', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (41, NULL, '卢致远', '男', '2023-06-11', '4642823106', '4Mmsaco2rm', 2, '2021-09-10', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (42, NULL, '傅震南', '男', '2005-03-06', '4938960513', 'EAoSb1ThjG', 2, '2022-01-31', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (43, NULL, '石晓明', '男', '2014-06-18', '0310150314', 'KZTKzH3fcG', 2, '2001-12-25', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (44, NULL, '卢嘉伦', '男', '2013-04-06', '4885999884', 'HU1io2IpZP', 2, '2008-06-18', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (45, NULL, '黎睿', '男', '2017-02-11', '9342778343', 'i1rWOsDTV9', 2, '2016-10-15', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (46, NULL, '于宇宁', '男', '2015-01-05', '5709547126', 'FFKd8sDwUR', 2, '2016-09-14', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (47, NULL, '韩杰宏', '男', '2005-03-15', '6875291411', 'p2iEnKvx0U', 2, '2017-08-15', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (48, NULL, '史詩涵', '女', '2023-11-02', '0253769728', 'h8LkpwbtFt', 2, '2020-06-04', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (49, NULL, '严嘉伦', '男', '2017-12-27', '5954555062', 'BeV5H9y2bq', 2, '2013-12-13', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);
INSERT INTO `course_user` VALUES (50, NULL, '萧晓明', '男', '2023-02-18', '4614149194', 'xfsznBUf6j', 2, '2017-07-02', NULL, NULL, NULL, '', NULL, '', NULL, 0, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
