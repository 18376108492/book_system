package cn.itdan.booksystem.api;

import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;

import java.util.List;

/**
 * 评论dubbo接口
 */
public interface ApiCommentService {
    /**
     * 获取所有评论
     * @param article_id
     * @param offset
     * @param limit
     * @return
     */
    PageInfo<Comment> allComments(Integer article_id, Integer offset, Integer limit);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    Reslut addComment(Comment comment);

    /**
     * 获取所有评论数
     * @return
     */
    Integer countAllNum();

    /**
     * 删除评论
     * @param id
     * @return
     */
    Reslut delById(Long id);
}
