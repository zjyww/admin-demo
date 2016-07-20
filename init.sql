/*
Navicat MySQL Data Transfer

Source Server         : bz
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : bz

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-07-21 01:25:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_station
-- ----------------------------
DROP TABLE IF EXISTS `t_station`;
CREATE TABLE `t_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'վ�����',
  `operatorCode` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��վ���Ʊ���',
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'վ������',
  `payType` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��������',
  `electricity` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '���',
  `area` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��������',
  `lastPay` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '�ϱ�֧��֮��',
  `endPay` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '�����ֹʱ��',
  `transferName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ת������',
  `transferBank` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��������',
  `transferCount` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '�˺�',
  `contractCode` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��ͬ���',
  `contractStart` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��ͬ��ʼʱ��',
  `contractEnd` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��ͬ����ʱ��',
  `contractA` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��ͬ�׷�',
  `contactMan` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��ϵ��',
  `contactTelephone` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��ϰ�绰',
  `price` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Э�鵥��',
  `payCircle` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '֧������',
  `renewMan` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��ǩ��Ա',
  `ticketMan` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ȡƱ��',
  `ticketTime` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rename` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '�Ƿ�ת��',
  `turnover` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '�Ƿ��ƽ�',
  `ctconfirm` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '�Ƿ��г�̯��ȷ��',
  `baseName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '��վ����',
  `remark` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31000 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



/*
Navicat MySQL Data Transfer

Source Server         : bz
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : bz

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-07-21 01:25:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sysuser
-- ----------------------------
DROP TABLE IF EXISTS `t_sysuser`;
CREATE TABLE `t_sysuser` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




INSERT INTO `t_sysuser` VALUES (1, 'admin', 'DnRCv8zl5wN/C/bo5hJxWPsj5F0=', 'xdKmaAuC9hs=');
