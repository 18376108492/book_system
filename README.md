# 1.简介
* 本仓库为个人项目练习模块，模块分为：书城模块+博客模块+权限管理模块，现在完成了博客模块。
* 本项目项目前端使用的bootstrap+jquery,后端使用的springboot+dubbo+mybatis-plus。
# 2.项目功能
### （1） 博客系统分为：博客前台和博客后台两部分。
* 博客前台：全文章的全浏览，新增文章评论和文章评论的全浏览，文章搜索。
* 博客后台：文章管理（文章的CRUD），文章评论的管理，admin的登入情况，登入的IP，时间，端口号以登入次数。
* 还写入了防用户重复提交功能，session定时清理
### (2)博客系统架构图
![](https://github.com/18376108492/book_system/blob/master/src/main/resources/img/20191214205329.png)
# 3.项目部署
### （1）本地部署
* download项目到idea中，在各小项目resource文件夹下application.propertis配置文件中配置好自己本地的mysql,redis,dubbo和mongodb。
* 启动bolg模块和user模块下的springboot的启动类，启动BlogProvider.class和UserProvider.class。
* 启动web模块下的springboot的启动类，启动WebApplication.class。
* 当三个模块的启动好后，访问http://localhost:9091，就会跳转博客前台，访问http://localhost:9091/admin,在没有登入的情况下会被登入拦截器拦截跳转至登入界面登入，登入后进入博客后台系统。
### （2）Linux部署
* 将项目文件打成zip包（其他包也行，能在Linux上解压就可以）。
![](https://github.com/18376108492/book_system/blob/master/src/main/resources/img/do-01.png)
* 将压缩好的文件上传到linux系统上，并将其解压后，cd 项目名 进入项目中，通过mvn install -Dmaven.test.skip=true,将项目下的模块打成jar包。
![](https://github.com/18376108492/book_system/blob/master/src/main/resources/img/do-02.png)
![](https://github.com/18376108492/book_system/blob/master/src/main/resources/img/do-03.png)
![](https://github.com/18376108492/book_system/blob/master/src/main/resources/img/do-04.png)
![](https://github.com/18376108492/book_system/blob/master/src/main/resources/img/do-05.png)
*创建一个文件，将bolg模块，user模块和web模块的jar拷出放入文件中，通过Java -jar jar名 去运行jar包即可。
![](https://github.com/18376108492/book_system/blob/master/src/main/resources/img/do-06.png)
![](https://github.com/18376108492/book_system/blob/master/src/main/resources/img/do-07.png)
# 4.项目浏览
