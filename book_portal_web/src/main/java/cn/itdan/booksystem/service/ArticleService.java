package cn.itdan.booksystem.service;

import cn.itdan.booksystem.api.ApiArticleService;
import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.utils.JsonUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Reference(version = "${dubbo.service.version}")
    private ApiArticleService apiArticleService;

    @Autowired
    private CommentService commentService;
    /**
     * 查询所有文章
     * @return
     */
    public List<Article> queryAllArticle(){
        return apiArticleService.queryAllArticle();
    }

    /**
     * 获取所有文章并且分页
     * @param star
     * @param size
     * @return
     */
    public PageInfo<Article> selectAllArticle(Integer star,Integer size){
       return   apiArticleService.selectAllArticle(star,size);
    }

    /**
     * 根据ID查询文章内容
     * @param id
     * @return
     */
    public Article selectById(Integer id){
       return apiArticleService.selectById(id);
    }

    /**
     * 根据文章ID查询最后的文章
     * @param id
     * @return
     */
   public Article selectLastArticle(Integer id){
       return apiArticleService.selectLastArticle(id);
   }

    /**
     * 查询下一篇文章
     * @param id
     * @return
     */
    public Article selectNextArticle(Integer id){
        return  apiArticleService.selectNextArticle(id);
    }

    /**
     * 更新文章信息
     * @param article
     * @return
     */
    public Reslut updateArticle(Article article) {
        return  apiArticleService.updateArticle(article);
    }

    /**
     * 文章展示
     * @param id
     * @return
     */
    public Map<String ,Object> detail(Integer id){
        HashMap<String,Object> map=new HashMap<>();
        PageInfo <Comment> pageInfo =commentService.allComments(id,0,10);
        Article article = apiArticleService.selectById(id);
        Article lastArticle = apiArticleService.selectLastArticle(id);
        Article nextArticle = apiArticleService.selectNextArticle(id);
        Integer clickNum=article.getClick();
        article.setClick(clickNum+1);
        apiArticleService.updateArticle(article);

        map.put("article",article);
        map.put("lastArticle",lastArticle);
        map.put("nextArticle",nextArticle);
        map.put("comments",pageInfo.getRecords());

        return map;
    }

    /**
     * 获取文章总数
     * @return
     */
    public Integer countAllNum() {
       return apiArticleService.countAllNum();
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    public Reslut addArticle(Article article) {
     return   apiArticleService.addArticle(article);
    }

    /**
     * 根据关键词去查询文章
     * @param word
     * @return
     */
    public Reslut selectByWord(String word){
      return    apiArticleService.selectByWord(word);
    }

}
