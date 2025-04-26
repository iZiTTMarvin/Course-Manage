# 大学选课系统

## 介绍
大学选课系统是一个旨在简化和自动化学生选课流程的软件解决方案。它提供了一个用户友好的界面，允许学生查看课程信息、选择课程、管理他们的课程表以及跟踪选课状态。同时，教师和行政人员也可以通过系统管理课程信息、发布公告和监督选课活动。

项目地址: [https://gitee.com/bestwishes0203/Course-Manage](https://gitee.com/bestwishes0203/Course-Manage)

## 软件截图


![输入图片说明](https://foruda.gitee.com/images/1716718145631087904/1a37531b_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718169562429290/e60e5cae_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718180624015790/093d489f_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718194852305418/92b2a4a9_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718218756303068/778ee097_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718227277113889/9de13237_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718238164818990/1d5c91da_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718245291875716/8d233ebd_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718252864168692/daf25872_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718260488280886/21f43124_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718277377848368/f424724f_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718292100306799/c3d6d0b5_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718359435928479/8262f80c_10378675.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1716718385015687670/9476edfc_10378675.png "屏幕截图")


## 软件架构
大学选课系统采用B/S架构模式，前端使用Vue.js框架构建动态网页，后端采用Spring Boot进行RESTful API的开发，数据库使用MySQL进行数据存储。系统整体分为以下几个主要模块：

- **用户管理模块**：负责处理用户的注册、登录和权限管理。
- **课程管理模块**：允许教师添加、编辑和删除课程信息。
- **选课管理模块**：学生可以浏览课程、进行选课操作。
- **数据报表模块**：生成选课报表，供教师和管理员使用。

## 安装教程

1. **环境准备**：确保已安装Java环境、MySQL数据库和Node.js。
2. **获取代码**：通过Gitee仓库克隆或下载源代码到本地。
3. **数据库配置**：导入项目中的SQL文件到MySQL数据库中。
4. **后端部署**：在后端项目根目录下执行`mvn clean install`，然后运行Spring Boot应用。
5. **前端部署**：在前端项目目录下执行`npm install`安装依赖，然后`npm run serve`启动前端开发服务器。

## 使用说明

1. **登录系统**：使用学号和密码登录选课系统。
2. **浏览课程**：在首页查看所有可供选择的课程。
3. **选择课程**：点击想要选择的课程，进行选课操作。
4. **查看课程表**：在个人中心查看已选课程的课程表。
5. **系统管理**：管理员可以进行用户和课程的管理。

## 参与贡献

1. **Fork 本仓库**：在Gitee平台上Fork本项目到你的账户下。
2. **新建 Feat_xxx 分支**：从你的Fork中的主分支checkout出新的分支进行开发。
3. **提交代码**：完成功能开发后，提交你的代码。
4. **新建 Pull Request**：向原仓库提交Pull Request，等待review。

## 特技

1. **多语言支持**：使用 `Readme_XXX.md` 来支持不同的语言版本，如 `Readme_en.md` 表示英文版。
2. **Gitee 官方博客**：[blog.gitee.com](https://blog.gitee.com) 官方博客，了解更多开源资讯。
3. **探索优秀开源项目**：通过 [https://gitee.com/explore](https://gitee.com/explore) 了解Gitee上的其他优秀开源项目。
4. **GVP 项目**：[GVP](https://gitee.com/gvp) 即Gitee最有价值开源项目，展示评定出的优秀开源项目。
5. **官方使用手册**：[https://gitee.com/help](https://gitee.com/help) 提供了Gitee平台的使用帮助和指南。
6. **Gitee 封面人物**：[https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/) 展示Gitee会员风采的栏目。

---

## 近期修复与优化说明

- **用户查询功能修复**：
    - 修复了前端传递空参数导致SQL出现`%null%`的问题，避免无效数据查询。
    - 前后端“入学年份”字段统一为`startYear`，彻底解决因字段不一致导致的查询失败。
    - 后端查询逻辑由固定实体参数改为`Map<String, Object>`，支持灵活动态条件查询。
    - MyBatis动态SQL优化，仅在参数非空时拼接对应查询条件，避免无效SQL。
    - 默认查询`state=1`的用户，提升查询结果的准确性。
    - 代码位置涉及：`UserController.java`、`UserMapper.java`、`UserMapper.xml`、`user.ts`、`User.vue`。

---

## 启动项目详细步骤

### 后端（Spring Boot）
1. 进入后端目录：
   ```
   cd springboot
   ```
2. 启动后端服务（需JDK和Maven）：
   ```
   mvn spring-boot:run
   ```
   或先打包后运行：
   ```
   mvn clean package
   java -jar target/springboot-0.0.1-SNAPSHOT.jar
   ```
3. 确保`application.yml`数据库配置正确，MySQL服务已开启。

### 前端（Vue）
1. 进入前端目录：
   ```
   cd spring-ui
   ```
2. 安装依赖（首次或依赖变更时）：
   ```
   npm install
   ```
3. 启动前端服务：
   ```
   npm run serve
   ```
4. 浏览器访问 http://localhost:8080

> 如遇端口占用、依赖安装慢或数据库连接异常，请根据报错信息检查配置或联系开发者协助。
