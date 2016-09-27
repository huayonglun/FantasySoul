/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : fantasysoul

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2016-09-27 20:56:06
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `comment_info`
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info` (
  `comment_id` int(11) NOT NULL auto_increment,
  `content` varchar(1000) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `receive_user_id` int(11) NOT NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment_info
-- ----------------------------

-- ----------------------------
-- Table structure for `post_info`
-- ----------------------------
DROP TABLE IF EXISTS `post_info`;
CREATE TABLE `post_info` (
  `post_id` int(11) NOT NULL auto_increment,
  `title` varchar(255) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_info` varchar(5000) default NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_info
-- ----------------------------

-- ----------------------------
-- Table structure for `post_topic_relation`
-- ----------------------------
DROP TABLE IF EXISTS `post_topic_relation`;
CREATE TABLE `post_topic_relation` (
  `relation_id` int(11) NOT NULL auto_increment,
  `post_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`relation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_topic_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `resource_category_info`
-- ----------------------------
DROP TABLE IF EXISTS `resource_category_info`;
CREATE TABLE `resource_category_info` (
  `category_id` int(11) NOT NULL auto_increment,
  `category_name` varchar(64) NOT NULL,
  `root_category_id` varchar(64) NOT NULL,
  `parent_category_id` varchar(64) default NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_category_info
-- ----------------------------

-- ----------------------------
-- Table structure for `resource_info`
-- ----------------------------
DROP TABLE IF EXISTS `resource_info`;
CREATE TABLE `resource_info` (
  `resource_id` int(11) NOT NULL auto_increment,
  `resource_name` varchar(128) NOT NULL,
  `category_id` int(11) NOT NULL,
  `summary` varchar(3000) default NULL,
  `download_count` int(10) unsigned zerofill NOT NULL,
  `user_id` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `del` tinyint(1) unsigned zerofill NOT NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_info
-- ----------------------------

-- ----------------------------
-- Table structure for `resource_tag_info`
-- ----------------------------
DROP TABLE IF EXISTS `resource_tag_info`;
CREATE TABLE `resource_tag_info` (
  `tag_id` int(11) NOT NULL auto_increment,
  `tag_name` varchar(64) NOT NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_tag_info
-- ----------------------------

-- ----------------------------
-- Table structure for `resource_tag_relation`
-- ----------------------------
DROP TABLE IF EXISTS `resource_tag_relation`;
CREATE TABLE `resource_tag_relation` (
  `relation_id` int(11) NOT NULL auto_increment,
  `resource_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`relation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_tag_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `topic_info`
-- ----------------------------
DROP TABLE IF EXISTS `topic_info`;
CREATE TABLE `topic_info` (
  `topic_id` int(11) NOT NULL auto_increment,
  `topic_name` varchar(64) NOT NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic_info
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL auto_increment,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `code` varchar(64) NOT NULL,
  `state` int(1) default NULL,
  `sex` int(1) default NULL,
  `telphone` varchar(11) default NULL,
  `address` varchar(255) default NULL,
  `interest` varchar(255) default NULL,
  `gmt_create` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
