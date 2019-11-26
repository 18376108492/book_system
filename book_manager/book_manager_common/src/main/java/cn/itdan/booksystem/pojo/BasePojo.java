package cn.itdan.booksystem.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePojo  implements Serializable {
    private Date created;//表格创建时间

    private Date updated;//更新时间

}
