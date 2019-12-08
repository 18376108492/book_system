package cn.itdan.booksystem.service;

import cn.itdan.booksystem.pojo.Admin;
import cn.itdan.booksystem.pojo.AdminLoginLog;
import cn.itdan.booksystem.pojo.Reslut;

import java.util.List;

public interface AdminService {

    /**
     * 根据ID获取admin信息
     * @param id
     * @return
     */
    Admin getById(Integer id);

    /**
     * 获取指定ID admin的登入情况
     * @param adminId
     * @return
     */
    List<AdminLoginLog> selectRencent(Integer adminId);

    /**
     * 添加登入信息
     * @param adminLoginLog
     * @return
     */
    Integer addLoginLog(AdminLoginLog adminLoginLog);

    /**
     * 获取指定ID admin的登入次数
     * @param adminId
     * @return
     */
    Integer selectCountByAdminId(Integer adminId);

    /**
     * 检查登入状况
     * @param id
     * @param password
     * @return
     */
     Reslut checkLogin(Integer id, String password);
}
