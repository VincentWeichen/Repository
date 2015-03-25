/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : vaultman

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2014-11-18 13:50:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `releasetime` datetime DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of bulletin
-- ----------------------------

-- ----------------------------
-- Table structure for bulletinfile
-- ----------------------------
DROP TABLE IF EXISTS `bulletinfile`;
CREATE TABLE `bulletinfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bulletinsn` int(11) DEFAULT NULL,
  `filepath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of bulletinfile
-- ----------------------------

-- ----------------------------
-- Table structure for controlltime
-- ----------------------------
DROP TABLE IF EXISTS `controlltime`;
CREATE TABLE `controlltime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `organizationcode` varchar(30) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of controlltime
-- ----------------------------
INSERT INTO `controlltime` VALUES ('1', null, '1', '1', '1', '1');

-- ----------------------------
-- Table structure for controlltimedetail
-- ----------------------------
DROP TABLE IF EXISTS `controlltimedetail`;
CREATE TABLE `controlltimedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `controlltimeid` int(11) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `organizationcode` varchar(30) DEFAULT NULL,
  `weekday` varchar(30) DEFAULT NULL,
  `begindate` varchar(30) DEFAULT NULL,
  `enddate` varchar(30) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of controlltimedetail
-- ----------------------------
INSERT INTO `controlltimedetail` VALUES ('1', '1', '1', '1', '1', null, '00:00', '08:00', '1');
INSERT INTO `controlltimedetail` VALUES ('4', '1', '1', '2', '1', null, '0:10', '0:20', '1');
INSERT INTO `controlltimedetail` VALUES ('5', '1', '1', '3', '1', null, '0:30', '0:40', '1');
INSERT INTO `controlltimedetail` VALUES ('6', '1', '2', '1', '1', null, '0:30', '0:40', '1');

-- ----------------------------
-- Table structure for controlltimeercisedate
-- ----------------------------
DROP TABLE IF EXISTS `controlltimeercisedate`;
CREATE TABLE `controlltimeercisedate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `controlltimeid` int(11) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `organizationcode` varchar(30) DEFAULT NULL,
  `weekday` varchar(30) DEFAULT NULL,
  `begindate` varchar(30) DEFAULT NULL,
  `enddate` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of controlltimeercisedate
-- ----------------------------
INSERT INTO `controlltimeercisedate` VALUES ('3', '1', '1', '1', '1,2,3', null, null, '1');
INSERT INTO `controlltimeercisedate` VALUES ('4', '1', '1', '1', '4', null, null, '2');

-- ----------------------------
-- Table structure for exclusionrole
-- ----------------------------
DROP TABLE IF EXISTS `exclusionrole`;
CREATE TABLE `exclusionrole` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `exclusionrole` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of exclusionrole
-- ----------------------------
INSERT INTO `exclusionrole` VALUES ('3', ',岗位管理');
INSERT INTO `exclusionrole` VALUES ('10', ',流程信息维护');
INSERT INTO `exclusionrole` VALUES ('11', ',流程信息维护');

-- ----------------------------
-- Table structure for fingerprintuser
-- ----------------------------
DROP TABLE IF EXISTS `fingerprintuser`;
CREATE TABLE `fingerprintuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` varchar(100) DEFAULT NULL,
  `tusercode` varchar(30) DEFAULT NULL,
  `tusername` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `encrykeyamount` int(11) DEFAULT NULL,
  `usercodesys` varchar(30) DEFAULT NULL,
  `usernamesys` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of fingerprintuser
-- ----------------------------
INSERT INTO `fingerprintuser` VALUES ('1', '1', '1', 'finger1', '1', '1', '1', '1');
INSERT INTO `fingerprintuser` VALUES ('2', '1', '2', '二', 'NormalUser', '1', '2', '2');
INSERT INTO `fingerprintuser` VALUES ('3', '1', '3', '三', 'NormalUser', '1', '3', '3');
INSERT INTO `fingerprintuser` VALUES ('4', '1', '4', '四', 'NormalUser', '1', '1002', '用户1');
INSERT INTO `fingerprintuser` VALUES ('5', '1', '5', '五', 'NormalUser', '1', '1004', '用户3');
INSERT INTO `fingerprintuser` VALUES ('6', '1', '6', '六', 'NormalUser', '1', '', '');
INSERT INTO `fingerprintuser` VALUES ('7', '1', '7', '七', 'NormalUser', '1', '', '');
INSERT INTO `fingerprintuser` VALUES ('8', '1', '8', '八', 'NormalUser', '1', '', '');
INSERT INTO `fingerprintuser` VALUES ('9', '1', '9', '九', 'NormalUser', '1', '', '');
INSERT INTO `fingerprintuser` VALUES ('10', '1', '10', '十', 'NormalUser', '1', '', '');
INSERT INTO `fingerprintuser` VALUES ('11', '1', '11', '十一', 'NormalUser', '1', '', '');
INSERT INTO `fingerprintuser` VALUES ('12', '1', '12', '十二', 'NormalUser', '1', '', '');

-- ----------------------------
-- Table structure for flowinstance
-- ----------------------------
DROP TABLE IF EXISTS `flowinstance`;
CREATE TABLE `flowinstance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `templateId` int(11) DEFAULT NULL,
  `templatexmlstring` text,
  `createuser` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `complatetime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of flowinstance
-- ----------------------------

-- ----------------------------
-- Table structure for flowinstancenode
-- ----------------------------
DROP TABLE IF EXISTS `flowinstancenode`;
CREATE TABLE `flowinstancenode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentstate` int(11) DEFAULT NULL,
  `instanceid` int(11) DEFAULT NULL,
  `stateid` varchar(50) DEFAULT NULL,
  `transactuser` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `completetime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `submituser` varchar(50) DEFAULT NULL,
  `submitoption` varchar(255) DEFAULT NULL,
  `submitresult` varchar(255) DEFAULT NULL,
  `nextuser` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of flowinstancenode
-- ----------------------------

-- ----------------------------
-- Table structure for lookupoption
-- ----------------------------
DROP TABLE IF EXISTS `lookupoption`;
CREATE TABLE `lookupoption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class` int(11) DEFAULT NULL,
  `sn` int(11) DEFAULT NULL,
  `option` varchar(50) DEFAULT NULL,
  `optionvalue` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of lookupoption
-- ----------------------------
INSERT INTO `lookupoption` VALUES ('1', '1', '1', '经理', '经理');
INSERT INTO `lookupoption` VALUES ('2', '1', '2', '员工', '员工');
INSERT INTO `lookupoption` VALUES ('3', '1', '3', '科长', '科长');
INSERT INTO `lookupoption` VALUES ('4', '2', '1', '2', '2');
INSERT INTO `lookupoption` VALUES ('5', '3', '1', '，5，', '，5，');

-- ----------------------------
-- Table structure for messagelog
-- ----------------------------
DROP TABLE IF EXISTS `messagelog`;
CREATE TABLE `messagelog` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `sendtime` datetime DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `cellphone` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `errormsg` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of messagelog
-- ----------------------------

-- ----------------------------
-- Table structure for operationlog
-- ----------------------------
DROP TABLE IF EXISTS `operationlog`;
CREATE TABLE `operationlog` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `operatingtime` datetime DEFAULT NULL,
  `operation` varchar(50) DEFAULT NULL,
  `usercode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of operationlog
-- ----------------------------

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) DEFAULT NULL,
  `parentcode` varchar(30) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `alias` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `manager` varchar(30) DEFAULT NULL,
  `cellphone` varchar(50) DEFAULT NULL,
  `tid` varchar(30) DEFAULT NULL,
  `tusernum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('2', '1', '', '0', '中国农业银行辽宁省分行', '辽宁分行', '辽宁省沈阳市和平区', '024-12345678', 'ZhangS', '12345678910', '', '2');
INSERT INTO `organization` VALUES ('3', '2', '1', '0', '沈阳市分行', '沈阳分行', '辽宁省沈阳市沈河区', '024-87654321', 'LiS', '10987654321', '', '2');
INSERT INTO `organization` VALUES ('4', '3', '2', '2', '和平区支行', '和平支行', '辽宁省沈阳市和平区', '024-12345678', 'FengB', '12345678910', '1', '2');
INSERT INTO `organization` VALUES ('5', '4', '1', '2', '大连市分行', '大连分行', '辽宁省大连市金州区', '0411-12345678', 'LiuCQ', '1234567891', '2', '2');

-- ----------------------------
-- Table structure for organizationuser
-- ----------------------------
DROP TABLE IF EXISTS `organizationuser`;
CREATE TABLE `organizationuser` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `organizationcode` varchar(30) DEFAULT NULL,
  `usercode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organizationuser
-- ----------------------------
INSERT INTO `organizationuser` VALUES ('16', '1', '1001');
INSERT INTO `organizationuser` VALUES ('17', '2', '1003');
INSERT INTO `organizationuser` VALUES ('18', '3', '1004');
INSERT INTO `organizationuser` VALUES ('19', '3', '1002');

-- ----------------------------
-- Table structure for rights
-- ----------------------------
DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` varchar(30) DEFAULT NULL,
  `operationurl` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rights
-- ----------------------------
INSERT INTO `rights` VALUES ('1', '机构维护', '机构维护');
INSERT INTO `rights` VALUES ('2', '岗位维护', '岗位维护');
INSERT INTO `rights` VALUES ('3', '人员维护', '人员维护');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `alias` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2', '1004', '1', '流程信息维护', '可以修改编辑流程图');
INSERT INTO `role` VALUES ('16', '1005', '1', '密码管理', '修改密码');
INSERT INTO `role` VALUES ('17', '1006', '1', '岗位管理', '修改岗位信息');
INSERT INTO `role` VALUES ('18', 'gh', '1', '123', 'kk');
INSERT INTO `role` VALUES ('19', '', '1', '', '');

-- ----------------------------
-- Table structure for roleright
-- ----------------------------
DROP TABLE IF EXISTS `roleright`;
CREATE TABLE `roleright` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolecode` varchar(30) DEFAULT NULL,
  `operation` varchar(30) DEFAULT NULL,
  `operationurl` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleright
-- ----------------------------

-- ----------------------------
-- Table structure for roleuser
-- ----------------------------
DROP TABLE IF EXISTS `roleuser`;
CREATE TABLE `roleuser` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `rolecode` varchar(30) DEFAULT NULL,
  `usercode` varchar(30) DEFAULT NULL,
  `expired` datetime DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleuser
-- ----------------------------
INSERT INTO `roleuser` VALUES ('1', '1005', '3', '2014-12-04 23:24:04');

-- ----------------------------
-- Table structure for subgroup
-- ----------------------------
DROP TABLE IF EXISTS `subgroup`;
CREATE TABLE `subgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` varchar(100) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `organizationcode` varchar(30) DEFAULT NULL,
  `groupauthorize` varchar(10) DEFAULT NULL,
  `usernames` varchar(500) DEFAULT NULL,
  `usercodesys1` varchar(30) DEFAULT NULL,
  `tusercode1` varchar(30) DEFAULT NULL,
  `usernamesys1` varchar(30) DEFAULT NULL,
  `usercodesys2` varchar(30) DEFAULT NULL,
  `usernamesys2` varchar(30) DEFAULT NULL,
  `tusercode2` varchar(30) DEFAULT NULL,
  `usercodesys3` varchar(30) DEFAULT NULL,
  `usernamesys3` varchar(30) DEFAULT NULL,
  `tusercode3` varchar(30) DEFAULT NULL,
  `usercodesys4` varchar(30) DEFAULT NULL,
  `usernamesys4` varchar(30) DEFAULT NULL,
  `tusercode4` varchar(30) DEFAULT NULL,
  `usercodesys5` varchar(30) DEFAULT NULL,
  `usernamesys5` varchar(30) DEFAULT NULL,
  `tusercode5` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=718 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of subgroup
-- ----------------------------
INSERT INTO `subgroup` VALUES ('715', '1', '1', '1', 'A组', '操作组', '1', 'true', '1,2', '1', '1', '1', '2', '2', '2', null, null, null, null, null, null, null, null, null);
INSERT INTO `subgroup` VALUES ('716', '1', '1', '2', 'B组', '操作组', '1', 'false', '2,3', '2', '2', '2', '3', '3', '3', null, null, null, null, null, null, null, null, null);
INSERT INTO `subgroup` VALUES ('717', '1', '1', '3', 'C组', '操作组', '1', 'false', '1,2', '1', '1', '1', '2', '2', '2', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for subgrouparrange
-- ----------------------------
DROP TABLE IF EXISTS `subgrouparrange`;
CREATE TABLE `subgrouparrange` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `tid` varchar(100) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `subgroupcode` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `begindate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `switchtime` varchar(20) DEFAULT NULL,
  `timeduration` int(11) DEFAULT NULL,
  `organizationcode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of subgrouparrange
-- ----------------------------
INSERT INTO `subgrouparrange` VALUES ('208', '1', '1', '1', '1', '2', '2014-11-26 00:00:00', null, '10:22', '480', '1');
INSERT INTO `subgrouparrange` VALUES ('209', '1', '1', '2', '2', '2', '2014-11-26 00:00:00', null, '10:22', '312', '1');
INSERT INTO `subgrouparrange` VALUES ('210', '1', '1', '4', '3', '2', '2014-11-26 00:00:00', null, '10:22', '24', '1');
INSERT INTO `subgrouparrange` VALUES ('233', '1', '1', '2', '1', '1', '2014-11-12 00:00:00', null, '1:3', '24', '1');
INSERT INTO `subgrouparrange` VALUES ('234', '1', '1', '3', '2', '1', '2014-11-12 00:00:00', null, '1:3', '96', '1');
INSERT INTO `subgrouparrange` VALUES ('235', '1', '1', '4', '3', '1', '2014-11-12 00:00:00', null, '1:3', '24', '1');
INSERT INTO `subgrouparrange` VALUES ('240', '', '1', '1', '1', '3', '2014-11-20 21:18:00', '2014-11-22 21:18:00', null, null, '1');
INSERT INTO `subgrouparrange` VALUES ('241', '', '1', '2', '2', '3', '2014-11-12 21:18:00', '2014-11-25 21:18:00', null, null, '1');

-- ----------------------------
-- Table structure for taskexport
-- ----------------------------
DROP TABLE IF EXISTS `taskexport`;
CREATE TABLE `taskexport` (
  `taskid` int(11) NOT NULL AUTO_INCREMENT,
  `tid` varchar(100) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  PRIMARY KEY (`taskid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of taskexport
-- ----------------------------
INSERT INTO `taskexport` VALUES ('1', '1', '1', '1', '3', '1');
INSERT INTO `taskexport` VALUES ('2', '1', '1', '2', '3', '1');
INSERT INTO `taskexport` VALUES ('3', '1', '1', '3', '1', '0');
INSERT INTO `taskexport` VALUES ('4', '1', '1', '4', '3', '1');

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `templateId` varchar(50) DEFAULT NULL,
  `templatexmlstring` text,
  `createuser` varchar(50) DEFAULT NULL,
  `createtime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of template
-- ----------------------------

-- ----------------------------
-- Table structure for terminal
-- ----------------------------
DROP TABLE IF EXISTS `terminal`;
CREATE TABLE `terminal` (
  `tid` varchar(100) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `ipaddress` varchar(50) DEFAULT NULL,
  `macaddress` varchar(100) DEFAULT NULL,
  `serialno` varchar(100) DEFAULT NULL,
  `modelname` varchar(100) DEFAULT NULL,
  `firmwareversion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of terminal
-- ----------------------------
INSERT INTO `terminal` VALUES ('1', '2', '192.168.1.66:35970', '', '', '', '');
INSERT INTO `terminal` VALUES ('2', '1', '192.168.2.2', 'sfsgdrytrhdvbx', '10002', '模块', '版本1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `cellphone` varchar(50) DEFAULT NULL,
  `title` varchar(10) DEFAULT NULL,
  `cardnumber` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `photo` binary(1) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `rolenames` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('19', '1', '1', '1', '1', '女', '1', '1', '员工', 'ddd', '1', null, '应急密码管理员', null);
INSERT INTO `user` VALUES ('21', '1001', '1', '1', '冯冰', '女', '111111', '11', '经理', '111', '22', null, null, '');
INSERT INTO `user` VALUES ('22', '11', '1', '1', 'lcq', '男', '111111', '11', '科长', '111', '22', null, null, null);
INSERT INTO `user` VALUES ('25', '2', '1', '1', '2', '男', '222', '2', '经理', '2', '2', null, null, null);
INSERT INTO `user` VALUES ('26', '3', '33', '1', '3', '男', '3', '3', '经理', '3', '3', null, null, null);
INSERT INTO `user` VALUES ('27', '1002', '1', '1', '用户1', '男', '122233333', '1111111', '经理', '11111111111111111', '沈昂', null, null, null);
INSERT INTO `user` VALUES ('28', '1003', '1', '1', '用户2', '男', '1222111', '2342342342', '员工', '12312124141241', '沈阳', null, null, null);
INSERT INTO `user` VALUES ('29', '1004', '1', '1', '用户3', '男', '1111123', '123123123', '经理', '13123123123', '12313123', null, null, null);

-- ----------------------------
-- Table structure for vaultlog
-- ----------------------------
DROP TABLE IF EXISTS `vaultlog`;
CREATE TABLE `vaultlog` (
  `logID` int(11) NOT NULL AUTO_INCREMENT,
  `CreateTime` datetime DEFAULT NULL,
  `tid` varchar(50) DEFAULT NULL,
  `LogIndex` int(11) DEFAULT NULL,
  `EntryDate` datetime DEFAULT NULL,
  `InOutIndication` varchar(50) DEFAULT NULL,
  `VerificationSource` varchar(50) DEFAULT NULL,
  `EventAlarmCode` varchar(50) DEFAULT NULL,
  `DoorNo` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `CardID` varchar(50) DEFAULT NULL,
  `FunctionKeyIndex` int(11) DEFAULT NULL,
  `GroupID` int(11) DEFAULT NULL,
  `EventAlarmCodeDecode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`logID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of vaultlog
-- ----------------------------
INSERT INTO `vaultlog` VALUES ('1', '2014-11-18 20:52:15', '1', '1', '2014-11-18 20:52:15', 'InDoor', 'Card +Finger', 'Code:0x3d', '0', '0', 'Code:0x3d', '0', '0', '主控制器(通)');
INSERT INTO `vaultlog` VALUES ('2', '2014-11-18 20:52:15', '1', '2', '2014-11-18 20:52:15', 'OutDoor', 'Card +Finger+ Password', 'Code:0x3e', '0', '1', 'Code:0x3e', '1', '0', '主控制器(断)');
INSERT INTO `vaultlog` VALUES ('3', '2014-11-18 20:52:15', '2', '3', '2014-11-18 20:52:15', 'InDoor', 'Card +Finger', 'Code:0x3f', '0', '2', 'Code:0x3f', '2', '0', '门位(通)');
INSERT INTO `vaultlog` VALUES ('11', '2014-11-18 20:52:15', '2', '11', '2014-11-18 20:52:15', 'InDoor', 'Card +Finger', 'Code:0x41', '0', '4', 'Code:0x41', '4', '0', '管理锁控位(通)');
INSERT INTO `vaultlog` VALUES ('12', '2014-11-18 20:52:15', '2', '12', '2014-11-18 20:52:15', 'InDoor', 'Card +Finger', 'Code:0x41', '0', '4', 'Code:0x41', '4', '0', '管理锁控位(通)');
INSERT INTO `vaultlog` VALUES ('13', '2014-11-18 20:52:15', '2', '13', '2014-11-18 20:52:15', 'InDoor', 'Card +Finger', 'Code:0x41', '0', '4', 'Code:0x41', '4', '0', '管理锁控位(通)');
INSERT INTO `vaultlog` VALUES ('14', '2014-11-18 20:52:15', '2', '14', '2014-11-18 20:52:15', 'InDoor', 'Card +Finger', 'Code:0x41', '0', '4', 'Code:0x41', '4', '0', '管理锁控位(通)');
INSERT INTO `vaultlog` VALUES ('15', '2014-11-18 20:52:15', '2', '15', '2014-11-18 20:52:15', 'InDoor', 'Card +Finger', 'Code:0x41', '0', '4', 'Code:0x41', '4', '0', '管理锁控位(通)');
INSERT INTO `vaultlog` VALUES ('16', '2014-11-18 12:31:41', '1', '68', '2009-02-16 17:37:11', 'Access IN during Normal State', 'Card', 'Unregistered User', '1', '0', '5991382', '0', '255', '');
INSERT INTO `vaultlog` VALUES ('17', '2014-11-18 12:31:41', '1', '69', '2009-02-16 17:37:11', 'Access IN during Normal State', 'Card', 'Unregistered User', '1', '0', '5991382', '0', '255', '');
INSERT INTO `vaultlog` VALUES ('18', '2014-11-18 12:31:42', '1', '70', '2009-02-16 17:37:12', 'Access IN during Normal State', 'Card', 'Unregistered User', '1', '0', '5991382', '0', '255', '');
INSERT INTO `vaultlog` VALUES ('19', '2014-11-18 12:31:58', '1', '71', '2009-02-16 17:37:28', 'Access IN during Normal State', 'Card', 'Unregistered User', '1', '0', '5991382', '0', '255', '');
INSERT INTO `vaultlog` VALUES ('20', '2014-11-18 12:42:06', '1', '72', '2009-02-16 17:47:36', 'Access IN during Normal State', 'Card', 'Unregistered User', '1', '0', '5991382', '0', '255', '');

-- ----------------------------
-- Table structure for vaultlog_decode
-- ----------------------------
DROP TABLE IF EXISTS `vaultlog_decode`;
CREATE TABLE `vaultlog_decode` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `operatingtime` datetime DEFAULT NULL,
  `operation` varchar(50) DEFAULT NULL,
  `usercode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of vaultlog_decode
-- ----------------------------
