package cn.itdan.booksystem.listener;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.TimerTask;

/**
 * session清理任务器
 */
public class MySessionTask extends TimerTask {

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
            //只要session大于400秒没人用就清理掉
            for (HttpSession session:sessions){
                if(System.currentTimeMillis() - session.getLastAccessedTime()>(1000*400)){}
                     session.invalidate();
                     sessions.remove(session);
            }
        }

    }
}
