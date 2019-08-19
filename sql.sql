CREATE DATABASE album;
use album;
create table `albums`(
	`aid` int(6) unsigned auto_increment primary key comment '相册id',
    `name` varchar(10) not null comment '相册名字',
    `code` varchar(20) not null unique comment '相册代码',
    `banner` text not null comment 'banner图片地址',
    `updated` int(11) unsigned not null comment '最后更新时间'
);

create table `album_category`(
	`acid` int(6) unsigned auto_increment primary key comment '相册分类id',
    `aid` int(6) not null comment '相册id',
    `name` varchar(10) not null comment '相册分类名',
    `code` varchar(20) not null comment 'OSS存储的文件夹名',
    `banner` text not null comment 'banner图片地址',
    `priority` int(2) unsigned default 1 comment '优先级，越大越靠前',
    `status` tinyint(4) unsigned default 1 comment '0禁用 1正常 2私有',
    `created` int(11) unsigned not null comment '创建时间',
    `updated` int(11) unsigned not null comment '最后更新时间',
    INDEX aid_index (`aid`)  
);

create table `photos`(
	`pid` int(11) unsigned auto_increment primary key comment '照片id',
    `acid` int(6) unsigned not null comment '相册分类id',
    `description` varchar(20) not null comment '照片描述',
    `link` varchar(100) not null comment '照片地址',
    `status` tinyint(4) unsigned default 1 comment '照片状态:0禁用 1正常 2私有',
    `time` int(11) not null comment '照片时间',
	`created` int(11) unsigned not null comment '创建时间',
    INDEX acid_index (`acid`)  
);

create table `users`(
	`uid` int(11) unsigned auto_increment primary key comment '用户id',
    `username` varchar(30) not null comment '用户名',
    `name` varchar(10) not null comment '名称',
    `pass_salt` varchar(32) not null comment '密码盐值',
    `pass_hash` varchar(64) not null comment '密码哈希',
	`status` tinyint(4) unsigned default 1 comment '用户状态:0禁用 1正常',
    `created` int(11) unsigned not null comment '创建时间',
    `updated` int(11) unsigned not null comment '最后更新时间',
    INDEX username_index (`username`(30))  
);

insert into `users` (`username`, `name`, `pass_salt`, `pass_hash`, `status`, `created`, `updated`) VALUES ('hiki', 'Hiki', 'LCHmIT5k7iKxav2l', '9284e5211b6d77286b7d6e1a45f58cac3f3a9ed9427706453d8daafa7318dc0e', '1', '1566199160', '1566199160');