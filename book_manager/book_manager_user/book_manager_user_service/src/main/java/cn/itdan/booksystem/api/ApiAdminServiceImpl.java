package cn.itdan.booksystem.api;

import cn.itdan.booksystem.pojo.Admin;
import cn.itdan.booksystem.pojo.AdminLoginLog;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.service.AdminService;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}")
public class ApiAdminServiceImpl implements  ApiAdminService{

    private Logger logger=LoggerFactory.getLogger(ApiAdminServiceImpl.class);

    @Autowired
    private AdminService adminService;

    @Override
    public Admin getById(Integer id) {
        if(id==null){
            logger.error("根据ID获取admin信息操作出错,id为null");
            throw new RuntimeException("根据ID获取admin信息操作出错,id为null");
        }
        Admin admin=adminService.getById(id);
        return admin;
    }

    @Override
    public List<AdminLoginLog> selectRencent(Integer adminId) {
        if(adminId==null){
            logger.error("获取指定ID admin的登入情况操作出错,adminId为null");
            throw new RuntimeException("获取指定ID admin的登入情况操作出错,adminId为null");
        }
        List<AdminLoginLog> logList= adminService.selectRencent(adminId);
        return logList;
    }

    @Override
    public Integer addLoginLog(AdminLoginLog adminLoginLog) {
        if(adminLoginLog==null){
            logger.error("添加登入信息操作出错,adminLoginLog为null");
            throw new RuntimeException("添加登入信息操作出错,adminLoginLog为null");
        }
        Integer row=adminService.addLoginLog(adminLoginLog);
        return row;
    }

    @Override
    public Integer selectCountByAdminId(Integer adminId) {
        if(adminId==null){
            logger.error("获取指定ID admin的登入次数操作出错,adminId为null");
            throw new RuntimeException("获取指定ID admin的登入次数操作出错,adminId为null");
        }
       Integer row= adminService.selectCountByAdminId(adminId);
        return row;
    }

    @Override
    public Reslut checkLogin(Integer id, String password) {
        if(null==id){
           logger.error("登入检查失败，id为null");
           return Reslut.build(400,"登入检查失败，id为null");
        }
        if(null==password){
            logger.error("登入检查失败，password为null");
            return Reslut.build(400,"登入检查失败，password为null");
        }
        return adminService.checkLogin(id,password);
    }

}
