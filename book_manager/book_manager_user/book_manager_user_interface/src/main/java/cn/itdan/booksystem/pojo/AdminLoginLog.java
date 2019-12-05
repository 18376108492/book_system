package cn.itdan.booksystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * admin登入信息类
 */

@Data
@TableName("admin_login_log")
public class AdminLoginLog extends BasePojo  implements Serializable {

    private static final long serialVersionUID = 2298794804909049415L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Integer adminId;

    private Date date;

    private String ip;

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getLocalTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/M/dd HH:mm");//设置日期格式
        String dates = df.format(date);
        return dates;

    }
}