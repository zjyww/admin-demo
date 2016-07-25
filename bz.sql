/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : bz

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-21 20:53:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_station
-- ----------------------------
DROP TABLE IF EXISTS `t_station`;
CREATE TABLE `t_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '站点编码',
  `operatorCode` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '基站名称编码',
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '站点名称',
  `payType` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '付款类型',
  `electricity` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电表',
  `area` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属区县',
  `lastPay` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上笔支付之间',
  `endPay` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '结算截止时间',
  `transferName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '转账名称',
  `transferBank` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开户银行',
  `transferCount` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账号',
  `contractCode` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同编号',
  `contractStart` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同开始时间',
  `contractEnd` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同结束时间',
  `contractA` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同甲方',
  `contactMan` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人',
  `contactTelephone` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '练习电话',
  `price` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '协议单价',
  `payCircle` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付周期',
  `renewMan` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '续签人员',
  `ticketMan` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '取票人',
  `ticketTime` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rename` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否转名',
  `turnover` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否移交',
  `ctconfirm` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否有长摊需确认',
  `baseName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '基站名称',
  `remark` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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

-- ----------------------------
-- Table structure for t_sysuser
-- ----------------------------
DROP TABLE IF EXISTS `t_sysuser`;
CREATE TABLE `t_sysuser` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `salt` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `t_sysuser` VALUES (1, 'admin', 'DnRCv8zl5wN/C/bo5hJxWPsj5F0=', 'xdKmaAuC9hs=');
