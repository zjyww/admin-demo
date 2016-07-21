/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : bz

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-21 20:53:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_station_img
-- ----------------------------
DROP TABLE IF EXISTS `t_station_img`;
CREATE TABLE `t_station_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(2) NOT NULL COMMENT '类型 1合同 2凭证',
  `path` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `stationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
