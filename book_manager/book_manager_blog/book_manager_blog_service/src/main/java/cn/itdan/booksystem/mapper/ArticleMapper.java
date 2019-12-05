package cn.itdan.booksystem.mapper;

import cn.itdan.booksystem.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 获取上一篇文章
     * @param id
     * @return
     */
    @Select("SELECT  id,title from article WHERE id < #{id} ORDER BY id ASC limit 1")
    Article selectLastArticle(@Param(value = "id") Integer id);

    /**
     * 获取下一篇文章
     * @param id
     * @return
     */
    @Select("SELECT  id,title from article WHERE id > #{id} ORDER BY id ASC limit 1")
    Article selectNextArticle(@Param(value = "id") Integer id);

    /**
     * 获取文章总数
     * @return
     */
    @Select("SELECT COUNT(id) from  article")
    int countAllNum();

    /**
     * 根据关键词搜索
     * @param word
     * @return
     */
    @Select("select * from article where title LIKE CONCAT(CONCAT('%', #{word}), '%') OR content  LIKE CONCAT(CONCAT('%', #{word}), '%') ")
    List<Article> selectByWord(@Param(value = "word") String word);

    /**
     * 根据ID删除文章
     * @param id
     * @return
     */
    @Select("DELETE from article where id=#{id}")
    Integer deleteById(@Param(value = "id")Long id);

    /**
     * 分页查询
     * @param star
     * @param size
     * @return
     */
    @Select("select * from article order by updated desc limit #{star} , #{size}")
    List<Article> pageList(@Param(value = "star")Integer star, @Param(value = "size")Integer size);

}
