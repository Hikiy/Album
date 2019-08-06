CREATE DATABASE album;
use album;

create table `album_category`(
	`acid` int(6) unsigned auto_increment primary key,
    `name` varchar(6) not null ,
    `code` varchar(10) not null,
    `banner` text not null,
    `status` tinyint(4) unsigned default 1 comment '0禁用 1正常 2私有',
    `created` int(11) unsigned not null,
    `updated` int(11) unsigned not null,
    INDEX acid_index (`code`(10))  
);

create table `photos`(
	`pid` int(11) unsigned auto_increment primary key,
    `acid` int(6) unsigned not null,
    `description` varchar(10) not null,
    `link` varchar(50) not null,
    `status` tinyint(4) unsigned default 1 comment '0禁用 1正常 2私有',
	`created` int(11) unsigned not null,
    `updated` int(11) unsigned not null,
    INDEX acid_index (`acid`)  
);

create table `users`(
	`uid` int(11) unsigned auto_increment primary key,
    `username` varchar(30) not null,
    `name` varchar(10) not null,
    `pass_salt` varchar(32) not null,
    `pass_hash` varchar(64) not null,
	`status` tinyint(4) unsigned default 1 comment '0禁用 1正常',
    `created` int(11) unsigned not null,
    `updated` int(11) unsigned not null,
    INDEX username_index (`username`(10))  
)