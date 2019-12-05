package cn.itdan.booksystem.mapper;

import cn.itdan.booksystem.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据ID获取admin信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM admin where id=#{id}")
    Admin getById(@Param(value = "id") Integer id);


}
