package cn.itdan.booksystem.service;

import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.PageInfo;

import java.util.List;

public interface CommentService {
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
    Integer addComment(Comment comment);

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
    boolean delById(Long id);
}
