package cn.itdan.booksystem.test;

import cn.itdan.booksystem.pojo.Admin;
import cn.itdan.booksystem.pojo.AdminLoginLog;
import cn.itdan.booksystem.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void testDemo01() throws Exception{
       Admin admin=adminService.getById(10080);
        System.out.println(admin.toString());
     }

     @Test
     public void testDemo02() throws Exception{
           AdminLoginLog adminLoginLog=new AdminLoginLog();
           adminLoginLog.setIp("123.154.45.55");
           adminLoginLog.setAdminId(10080);
           Integer row= adminService.addLoginLog(adminLoginLog);
    }

    @Test
    public void testDemo03() throws Exception{
          List<AdminLoginLog> adminLoginLogList=  adminService.selectRencent(10080);
          for (AdminLoginLog adminLoginLog:adminLoginLogList){
              System.out.println(adminLoginLog.toString());
          }
    }

    @Test
    public void testDemo04() throws Exception{
           Integer row=  adminService.selectCountByAdminId(10080);
        System.out.println(row);
    }

    @Test
    public void testDemo05() throws Exception{
        String newPassword=DigestUtils.md5DigestAsHex("1234".getBytes());
        System.out.println("newPassword"+newPassword);
    }
}
