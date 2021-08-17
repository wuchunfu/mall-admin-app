# mall

### 介绍
mall是一套商城系统，包括 Spring Boot后端、 Vue后台管理员前端 、微信小程序用户前端 。主要功能有商品管理、订单管理、用户管理、商品收藏、加购物车、地址管理等。

### 项目演示

演示地址：http://af255dce56f8.ngrok.io/#/

账号：**admin** 密码：**123**

提示：访问可能有点慢，无法访问说明我的服务器断网了或者关机了。

### 技术选型

后端技术：

| 技术 | 说明 | 学习地址 |
|---|---|---|
| Spring Boot | 容器 | https://spring.io |
| Spring MVC | MVC框架 | https://spring.io |
| Spring Security | 用户认证和授权 | https://spring.io |
| MyBatis Plus | ORM框架 | https://mp.baomidou.com |
| Redis | 分布式缓存 | https://redis.io |
| MySQL | 数据持久化 | https://www.mysql.com |
| Hutool | Java工具类库 | https://www.hutool.cn |
| Lombok | 对象封装工具 | https://projectlombok.org |
| Kaptcha | 验证码生成器 | https://code.google.com/archive/p/kaptcha/ |
| JWT | 用户身份验证 | https://jwt.io/introduction/ |

前端技术：

| 技术 | 说明 | 学习地址 |
|---|---|---|
| Vue | 前端框架 | https://vue3js.cn |
| Axios | HTTP框架 | http://www.axios-js.com |
| Vue-Router | 路由框架 | https://next.router.vuejs.org |
| Vuex | 状态管理框架 | https://vuex.vuejs.org |
| Element Plus | 前端UI框架 | https://element-plus.gitee.io/#/zh-CN |

微信小程序技术：

| 技术 | 说明 | 学习地址 |
|---|---|---|
| Vant Weapp | UI组件库 | https://vant-contrib.gitee.io/vant-weapp |



### 软件架构

![输入图片说明](https://github.com/zchengo/mall/blob/main/pictures/mall_sys.jpg?raw=true "mall_sys.jpg")

### 开发工具

| 开发工具 | 说明 |
|---|---|
| intelliJ IDEA | 开发IDE |
| Navicat Premium | 数据库连接工具 |
| RDM | Redis客户端连接工具 |
| Postman | API接口调试工具 |
| 微信开发者工具 | 小程序开发工具 |

### 开发环境

| 环境 | 版本 |
|---|---|
| JDK | 1.8 |
| Maven | 3.6.x |
| npm | 6.14.x |
| node | 14.13.x |
| mysql | 8.0.x |
| redis | 6.0.x |

### 运行截图

Vue后台管理员前端:

![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161531_6c5cffc0_5664025.png "mall_1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161545_4dc87245_5664025.png "mall_2.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161556_4d2123e7_5664025.png "mall_3.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161606_d24b7550_5664025.png "mall_4.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161615_52361ee2_5664025.png "mall_5.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161626_989d18e7_5664025.png "mall_6.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161637_20ecb436_5664025.png "mall_7.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161647_19cd9b43_5664025.png "mall_8.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/161703_1c09fe65_5664025.png "mall_9.png")

微信小程序用户前端:

![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/165023_51545df4_5664025.png "app_1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/165036_ab4d56b2_5664025.png "app_2.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0812/165048_b6067bdd_5664025.png "app_3.png")


### 安装教程

#### 1. 后端项目（mall）的安装与启动：

```
git clone https://gitee.com/xzchengo/mall.git
mvn install
```
用IDEA开发工具打开mall项目，分别在mall-admin模块和mall-weapp模块中找到主函数MallAdminApplication和MallWeappApplication，点击运行即可（在启动之前，请确保MySQL、Redis服务已经开启，并查看配置文件application.yml是否符合本机配置要求，同时，请确保IDEA安装了Lombok插件）

#### 2. 前端项目（mall-web）的安装与启动：

```
git clone https://gitee.com/xzchengo/mall-web.git
npm install
```

用IDEA开发工具打开mall-web项目，使用npm安装完成以后，直接点击运行即可（在启动之前，请确保本机已经安装了Node.js和npm，IDEA要安装Vue插件）

#### 3. 微信小程序（mall-app）的安装与启动：

```
git clone https://gitee.com/xzchengo/mall-app.git
npm install
```

用微信开发者工具打开mall-app，第一步，点击菜单栏中的【工具】->【构建npm】，第二步，点击右侧【详情】->【本地设置】->勾选【不校验合法域名、web-view....】。最后，点击【编译】。注意：如果是直接【预览】，需要把/utils/request.js中的basePath = http://localhost:9900 中的localhost修改成本机网络的ip地址，如果是使用的是【真机调试】则无需修改。


### 使用说明

1.  如果你在安装、启动、使用过程中遇到了问题，请在评论区留言，我看到会第一时间回复。
2.  本商城系统是1.0.0版本（即首个版本），可能有些功能有Bug，欢迎各位在评论区留言反馈。
3.  本商城系统使用MIT开源许可证，完全免费，请放心使用！

### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request