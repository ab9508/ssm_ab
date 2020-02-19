/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : bumen

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-02-18 20:08:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `shop`
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO shop VALUES ('1', '1号店');
INSERT INTO shop VALUES ('2', '2号店');
INSERT INTO shop VALUES ('3', '3号店');
