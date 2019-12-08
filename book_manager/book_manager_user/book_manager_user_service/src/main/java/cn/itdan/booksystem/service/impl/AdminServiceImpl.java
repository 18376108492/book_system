package cn.itdan.booksystem.service.impl;

import cn.itdan.booksystem.api.ApiAdminService;
import cn.itdan.booksystem.mapper.AdminLoginLogMapper;
import cn.itdan.booksystem.mapper.AdminMapper;
import cn.itdan.booksystem.pojo.Admin;
import cn.itdan.booksystem.pojo.AdminLoginLog;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<Admin>
        implements AdminService {

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminLoginLogMapper adminLoginLogMapper;


    @Override
    public Admin getById(Integer id) {
        logger.info("根据ID获取admin信息操作:参数为:"+id);
        Admin admin = adminMapper.getById(id);
        logger.info("根据ID获取admin信息操作成功");
        return admin;
    }

    @Override
    public List<AdminLoginLog> selectRencent(Integer adminId) {
        logger.info("获取指定ID admin的登入情况操作,参数为:"+adminId);
        List<AdminLoginLog> logList=adminLoginLogMapper.selectRencent(adminId);
        logger.info("获取指定ID admin的登入情况操作成功");
        return logList;
    }

    @Override
    public Integer addLoginLog(AdminLoginLog adminLoginLog) {
        if (adminLoginLog.getAdminId()==null){
            logger.error("添加登入信息操作失败，参数AdminId为空");
            return 0;
        }
        logger.info("添加登入信息操作，参数为："+adminLoginLog.toString());
        adminLoginLog.setDate(new Date());
        adminLoginLog.setCreated(new Date());
        adminLoginLog.setUpdated(new Date());
        Integer row= adminLoginLogMapper.addLoginLog(adminLoginLog);
        logger.info("添加登入信息操作成功");
        return row;
    }

    @Override
    public Integer selectCountByAdminId(Integer adminId) {
        logger.info("获取指定ID_admin的登入次数操作，参数为:"+adminId);
        Integer row= adminLoginLogMapper.selectCountByAdminId(adminId);
        logger.info("获取指定ID_admin的登入次数操作，结果为:"+row);
        return row;
    }

    public Reslut checkLogin(Integer id, String password){
        logger.info("登入检查操作");
        //1.根据ID获取用户的密码
        Admin admin= adminMapper.getById(id);
        //2.对照密码是否正确
        if(null==admin){
            return  Reslut.build(400,"用户名或密码错误");
        }
        //先取出密码加密后再校验
        if(!admin.getPassword().equals(password)){
            return  Reslut.build(400,"用户名或密码错误");
        }
        logger.info("检查成功");
        return Reslut.ok(admin);
    }


}
