package cn.itdan.booksystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


/**
 * 书籍类
 */
@Data
@TableName("book")
public class Book extends BasePojo implements Serializable {

    private static final long serialVersionUID = -1290192553261494686L;

    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    private String name;//书名
    private String author;//作者
    private String description;//描述
    private double price;//价格

    //记住图片的名称，后面就可以根据名字的名称来查看图片了
    private String image;

    //记住分类的id
    private String category_id;

}
