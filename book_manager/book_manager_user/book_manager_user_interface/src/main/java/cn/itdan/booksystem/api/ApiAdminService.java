package cn.itdan.booksystem.api;

import cn.itdan.booksystem.pojo.Admin;
import cn.itdan.booksystem.pojo.AdminLoginLog;

import java.util.List;

public interface ApiAdminService {

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

}
