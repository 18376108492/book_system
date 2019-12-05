package cn.itdan.booksystem.service.impl;

import cn.itdan.booksystem.mapper.CommentMapper;
import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class CommentServiceImpl  extends BaseServiceImpl<Comment>
        implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    private Logger logger=LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public PageInfo<Comment> allComments(Integer article_id, Integer star, Integer size) {
        logger.info("获取所有评论操作,参数--->article_id:"+article_id+"/n offset:"+star+"/n size:"+size);

//        //添加查询条件
//        QueryWrapper queryWrapper=new QueryWrapper();
//        //根据更新时间获取
//        queryWrapper.eq("article_id",article_id);
//        queryWrapper.orderByDesc("updated");
//
//         IPage<Comment> pageList =super.queryPageList(queryWrapper,offset,limit);
        //List<Comment> commentList=commentMapper.pageList(article_id,offset,limit);
        Integer page=(star-1)*size;
        List<Comment> commentList=  commentMapper.allComments(article_id,page,size);
        logger.info("获取所有评论操作成功");

        return new PageInfo<Comment>(Long.valueOf(countAllNum()).intValue(),
                star,
                size,
                commentList);
    }

    @Override
    public Integer addComment(Comment comment) {
        logger.info("添加文章操作");

        //判断文章对象字段是否为空
        if(comment.getArticleId()==null){
            logger.error("添加评论操作失败,传入的文章ArticleId参数为null");
            return 0;
        }
        if(StringUtils.isBlank(comment.getContent())){
            logger.error("添加评论操作失败,传入的文章Content参数为null");
            return 0;
        }
        comment.setDate(new Date());
        int row= super.save(comment);

        logger.info("添加文章操作成功");
        return row;
    }

    @Override
    public Integer countAllNum() {
         logger.info("获取总评论数操作");
         Integer row= commentMapper.countAllNum();
         logger.info("获取总评论数操作成功,总评论数为:"+row);
         return row;
    }

    @Override
    public boolean delById(Long id) {
        logger.info("删除评论操作");
        int row= super.deleteById(id);
        logger.info("删除评论操作成功");
        if (row>0){
            return true;
        }
        return false;
    }
}
