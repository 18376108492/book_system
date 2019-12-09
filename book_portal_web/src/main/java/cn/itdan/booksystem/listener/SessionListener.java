package cn.itdan.booksystem.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

/**
 * session 定时清理监听器
 */
@Component
public class SessionListener implements ServletContextListener,HttpSessionListener {

    private Logger logger=LoggerFactory.getLogger(SessionListener.class);

    @Autowired
    private Timer timer;

    //服务启动时就创建session容器
    //因为主要涉及增删所以选用LinkedList,还存在并发问题所以需要做同步
    private final static List<HttpSession> sessionList= Collections.synchronizedList(new LinkedList<HttpSession>());

    //定义一把锁（Session添加到容器和扫描容器这两个操作应该同步起来）
    private Object lock = 1;

    @Scheduled(cron = "0/1800 * * * * *")//每隔1800秒清理一次session
    public void contextInit(){
       //执行任务(每隔1800秒执行一次，1秒延迟)
       timer.schedule(new MySessionTask(sessionList, lock),1,1800*1000);
       logger.info("执行清理任务");
    }

    public void sessionCreated(HttpSessionEvent se ){
        //只要Session一创建了，就应该添加到容器中
        synchronized (lock) {
            sessionList.add(se.getSession());
        }
        logger.info("创建session");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("销毁session");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("销毁Context");
    }

}
