package cn.itdan.booksystem.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.TimerTask;

/**
 * session清理任务器
 */
public class MySessionTask extends TimerTask {

    private Logger logger=LoggerFactory.getLogger(MySessionTask.class);
    private List<HttpSession> sessions;
    private Object lock;

    public  MySessionTask(List<HttpSession> sessions, Object lock){
              this.sessions=sessions;
              this.lock=lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            //遍历容器
            //只要session大于1800秒没人用就清理掉
            for (HttpSession session:sessions){
                if(System.currentTimeMillis() - session.getLastAccessedTime()>(1000*1800)){}
                     session.invalidate();
                     sessions.remove(session);
                     logger.info("清理session");
            }
        }

    }
}
