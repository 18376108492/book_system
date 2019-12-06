package cn.itdan.booksystem.api;

import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.service.CommentService;
import cn.itdan.booksystem.utils.JsonUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


@Service(version = "${dubbo.service.version}")
//dubbo层使用的service不是spring的而是使用dubbo的
public class ApiCommentServiceImpl implements ApiCommentService{

    private Logger logger=LoggerFactory.getLogger(ApiCommentServiceImpl.class);

    @Autowired
    private CommentService commentService;

    @Override
    public PageInfo<Comment> allComments(Integer article_id, Integer offset, Integer limit) {
        if(article_id==null){
           logger.error("获取所有评论操作失败，传入的参数article_id为空");
           return new PageInfo<>(0,offset,limit,new ArrayList<Comment>());
        }
        PageInfo<Comment> pageInfo= commentService.allComments(article_id,offset,limit);

       return  pageInfo;
    }

    @Override
    public Reslut addComment(Comment comment) {
        if(comment==null){
            logger.error("添加评论失败，传入的参数为空");
            return Reslut.build(400,"添加评论失败");
        }
       Integer row= commentService.addComment(comment);
        return Reslut.build(200,"添加评论成功，操作条数为:"+row);
    }

    @Override
    public Integer countAllNum() {
        return commentService.countAllNum();
    }

    @Override
    public Reslut delById(Long id) {
        if(id==null){
            logger.error("删除评论操作失败，传入的参数id为空");
            return Reslut.build(400,"删除评论操作失败");
        }
        boolean b= commentService.delById(id);
         if(b){
             return Reslut.ok();
         }
        return Reslut.build(400,"删除评论操作失败");
    }
}
