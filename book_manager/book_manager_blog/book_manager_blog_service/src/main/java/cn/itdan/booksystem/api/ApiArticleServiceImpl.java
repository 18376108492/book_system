package cn.itdan.booksystem.api;

import cn.itdan.booksystem.mongodb.ArticleDAO;
import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.service.ArticleService;
import cn.itdan.booksystem.utils.JsonUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service(version = "${dubbo.service.version}")
//dubbo层使用的service不是spring的而是使用dubbo的
public class ApiArticleServiceImpl implements   ApiArticleService{

    private Logger logger=LoggerFactory.getLogger(ApiArticleServiceImpl.class);

    @Autowired
   private ArticleDAO articleDAO;
 //   private ArticleService articleService;


    @Override
    public Article selectById(Integer id) {
        if (null==id){
            logger.error("根据ID获取文章操作,传入的ID为空");
           // return Reslut.build(400,"根据ID获取文章操作,传入的ID为空");
         throw  new RuntimeException("根据ID获取文章操作,传入的ID为空");
        }
        Article article=articleDAO.selectById(id);
        return  article;
        //return Reslut.ok(JsonUtils.objectToJson(article));
    }

    @Override
    public Article selectLastArticle(Integer id) {
        if (null==id){
            logger.error("根据ID获取文章操作,传入的ID为空");
           // return Reslut.build(400,"根据ID获取文章操作,传入的ID为空");
            throw  new RuntimeException("根据ID获取文章操作,传入的ID为空");
        }
        Article article=articleDAO.selectLastArticle(id);
       return article;
        //  return Reslut.ok(JsonUtils.objectToJson(article));
    }

    @Override
    public Article selectNextArticle(Integer id) {
        if (null==id){
            logger.error("根据ID获取文章操作,传入的ID为空");
            throw  new RuntimeException("根据ID获取文章操作,传入的ID为空");
           // return Reslut.build(400,"根据ID获取文章操作,传入的ID为空");
        }
        Article article=articleDAO.selectNextArticle(id);
        return article;
        //return Reslut.ok(JsonUtils.objectToJson(article));
    }

    @Override
    public List<Article> queryAllArticle() {
        List<Article> list= articleDAO.queryAllArticle();
        return list;
    }

    @Override
    public Integer countAllNum() {
        Integer row= articleDAO.countAllNum();
        return row;
    }

    @Override
    public Reslut updateArticle(Article article) {
        if(null==article){
            logger.error("更新文章操作失败，传入的参数为空");
            return Reslut.build(400,"更新文章操作失败，传入的参数为空");
        }
        //更新前获取之前对象的图片路径,点击数，发表时间
        //获取文章ID
        boolean b= articleDAO.updateArticle(article);
        if(b){
            return Reslut.ok();
        }
        return Reslut.build(400,"更新文章操作失败");
    }

    @Override
    public Reslut deleteById(Integer id) {
        if (null==id){
            logger.error("删除文章操作,传入的ID为空");
            return Reslut.build(400,"删除文章操作,传入的ID为空");
        }
       int row=articleDAO.deleteById(id);
           return Reslut.build(200,"操作条数:"+row);
    }

    @Override
    public List<Article> selectByWord(String word) {
        if (StringUtils.isBlank(word)){
            logger.info("根据关键词搜索文章操作,传入的关键词为空");
           new ArrayList<Article>();
        }
        List<Article> list= articleDAO.selectByWord(word);
        return list;
    }

    @Override
    public Reslut addArticle(Article article) {
        if(null==article){
            logger.error("添加文章操作失败，传入的参数为空");
            return Reslut.build(400,"添加文章操作失败，传入的参数为空");
        }
        Article saveArticle= articleDAO.saveArticle(article);
        if(saveArticle!=null){
            return Reslut.ok();
        }
        return Reslut.build(400,"添加文章操作失败");
    }

    @Override
    public PageInfo<Article> selectAllArticle(Integer star, Integer size) {
        return articleDAO.selectAllArticle(star,size);
    }
}
