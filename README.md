# 个人相册
## 索引
### <a href="#1">功能</a>:star2:
### <a href="#2">技术栈</a>:wrench:
### <a href="#3">预览</a>:pig:
### <a href="#4">使用手册</a>:green_book:
### <a href="#5">开发日志</a>:scissors:
</br>
</br>
</br>

## <a name="1"> 功能</a>:star2:

- 拥有自己的照片博客:satisfied::camera:
- 登录后台，轻松管理照片、多相册 :clap:

## <a name="2"> 技术栈</a>:wrench:
- java
- Spring Boot
- JavaScript
- MySQL
- HTML

## <a name="3">预览</a>:pig:
### PC端首页
![image](https://github.com/Hikiy/Album/tree/master/README_GIF/index.gif)
本地默认进入url：http://localhost:8012/album/index

#### 主要功能

- 瀑布流显示图片

- 照片分类（一个横幅为一个分类）

- 照片点击放大

- 照片描述

- 照片日期
  </br>

  </br>

### 移动端首页
![image](https://github.com/Hikiy/Album/tree/master/README_GIF/index2.gif)
  </br>

  </br>
### 后台
![image](https://github.com/Hikiy/Album/tree/master/README_GIF/manage1.gif)

本地默认登录url：http://localhost:8012/auth/loginindex

#### 主要功能

- 权限登录

- 管理相册（每一个相册为一个页面，也就是可以实现例如 家族相册、朋友相册之类的）

- 管理相册分类（每个相册里有很多分类，一个分类为相册中显示的一个模块，拥有横幅

- 管理照片（照片可以增删改查）

- 管理账号（新增、修改密码、删除）

  </br>

  </br>

## <a name="4">使用手册</a>:green_book:

### 1.建库
在MySQL执行项目中的 `sql.sql` 建库

### 2.配置阿里云OSS
在项目中的 `application.yml` 配置
不太清楚的，可以进入 **阿里云** 中找一下 **对象存储OSS**
如果需要使用别的云，可以自行修改源码，主要修改项目中的 `OSSService.java` 。

### 3.配置数据库
在项目中的 `application.yml` 配置

### 4.启动项目
怎么启动这个就不多说了，记得将 `pom.xml` 中的依赖包添加好。
如果不知道Maven，查一下 `pom.xml` 怎么使用就好了。

### 5.登录后台系统
#### 默认url: http://localhost:8012/auth/loginindex
**默认账号密码**</br>
**账号：hiki** </br>
**密码：123456** </br>
（前提是按照项目中的 `sql.sql` 建的库）

### 6.添加内容
后台操作应该容易看懂了，

</br>

</br>

## <a name="5">开发日志</a>:scissors:

前期忘记记录..

------2019.08.06------

新增相册分类功能，实现指定分类上传图片,新增mysql建库sql

------2019.08.08-------

实现从页面上传指定分类的照片了！

------2019.08.14-------

完美实现前端界面的分类相册瀑布流显示！！研究了好久！！

实现移动端和PC端适配！！

------2019.08.16-------

实现相册编辑、添加的前端后端

实现相册分类编辑、添加的前端后端

------2019.08.17-------

实现登录权限拦截

实现照片后台管理

------2019.08.19-------

实现所有基础功能，已经可以正常使用

------2019.08.20-------

实现账号管理模块，基本完工

将相册项目从spring cloud转为spring boot

------2019.08.27-------

修复多次点击上传图片会多次上传的bug

V1.0完工