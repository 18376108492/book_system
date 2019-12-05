package cn.itdan.booksystem.service;

import cn.itdan.booksystem.api.ApiCommentService;
import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    @Reference(version = "${dubbo.service.version}")
    private ApiCommentService apiCommentService;


    /**
     * 获取所有评论
      * @param article_id
     * @param offset
     * @param limit
     * @return
     */
    public PageInfo<Comment> allComments(Integer article_id, Integer offset, Integer limit){
        return apiCommentService.allComments(article_id,offset,limit);
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public Reslut addComment(Comment comment){
        return apiCommentService.addComment(comment);
    }

    public Integer countAllNum(){return  apiCommentService.countAllNum();
    }

}
