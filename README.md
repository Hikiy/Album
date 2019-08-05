# 微信公众号自动回复系统

## 使用

1.配置wxcentre项目的service-url并启动。

2.配置wxmessage项目中的redis和eureka注册中心并启动。

## 功能

目前实现了天气回复功能

### 天气回复

用户发送城市 + 天气，自动回复该城市当天天气。

调用的是开源接口 https://www.tianqiapi.com/api/?version=v1&city=

### 阿里云对象存储

更新阿里云对象存储获取图片现实至前端

更新图片压缩处理