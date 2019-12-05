package cn.itdan.booksystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * admin实体类
 */
@Data
@TableName("admin_login_log")
public class Admin  extends  BasePojo implements Serializable {

    private static final long serialVersionUID = -992132750637177227L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

}