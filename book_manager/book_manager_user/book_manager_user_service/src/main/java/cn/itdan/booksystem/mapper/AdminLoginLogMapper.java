package cn.itdan.booksystem.mapper;

import cn.itdan.booksystem.pojo.AdminLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminLoginLogMapper  extends BaseMapper<AdminLoginLog> {

    /**
     * 获取指定ID admin的登入情况
     * @param adminId
     * @return
     */
    @Select("Select  id, admin_id, date, ip from admin_login_log where admin_id = #{adminId}  ORDER BY id DESC LIMIT 2")
    List<AdminLoginLog> selectRencent(@Param(value = "adminId") Integer adminId);

    /**
     * 添加登入信息
     * @param adminLoginLog
     * @return
     */
    @Insert("insert into admin_login_log (admin_id, date,ip,created,updated) " +
            "values(#{adminLoginLog.adminId},#{adminLoginLog.date},#{adminLoginLog.ip},#{adminLoginLog.created},#{adminLoginLog.updated})")
    Integer addLoginLog(@Param(value = "adminLoginLog") AdminLoginLog adminLoginLog);

    /**
     * 获取指定ID admin的登入次数
     * @param adminId
     * @return
     */
    @Select("SELECT count(id) from admin_login_log where admin_id=#{adminId}")
    Integer selectCountByAdminId(@Param(value = "adminId") Integer adminId);
}
