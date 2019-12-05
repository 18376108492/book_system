package cn.itdan.booksystem.mapper;

import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentMapper  extends BaseMapper<Comment> {

    /**
     * 获取所有评论
     * @param article_id
     * @param offset
     * @param limit
     * @return
     */
    @Select("select * from comment where article_id= #{article_id} ORDER BY id DESC LIMIT #{offset}, #{limit}")
    List<Comment> allComments(@Param(value = "article_id") int article_id,
                              @Param(value = "offset") int offset,
                              @Param(value ="limit") int limit);

    /**
     * 获取总评论数
     * @return
     */
    @Select("SELECT COUNT(id) from comment ")
    int countAllNum();

}
