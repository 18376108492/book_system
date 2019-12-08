package cn.itdan.booksystem.mongodb;

import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.utils.IDUtils;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ArticleDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 将文章存储到mongoDB中
     * @param article
     * @return
     */
    public Article saveArticle(Article article){
        article.setId(Integer.valueOf((int)IDUtils.getId()));
        article.setTime(new Date());
        article.setCreated(new Date());
        article.setUpdated(new Date());
        article.setClick(0);
        return this.mongoTemplate.save(article);
    }

    /**
     * 根据文章ID查询文章内容
     * @param id
     * @return
     */
     public Article selectById(Integer id){
         Query query=new Query(Criteria.where("id").is(id));
         List<Article> list= this.mongoTemplate.find(query,Article.class);
         return list!=null &&list.size()>0 ? list.get(0): null;
     }

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> queryAllArticle(){
        Query query=new Query();
        query.with(Sort.by(
                Sort.Order.desc("updated")
        ));
       return this.mongoTemplate.find(query,Article.class);
    }

    /**
     * 根据文章ID查询最后的文章
     * @param id
     * @return
     */
    public Article selectLastArticle(Integer id){
        Query query=new Query(Criteria.where("id").lt(id));
        query.with(Sort.by(
                Sort.Order.desc("id")
        ));
        query.limit(1);
        List<Article> list= this.mongoTemplate.find(query,Article.class);
        return list!=null &&list.size()>0 ? list.get(0): null;
    }


    /**
     * 查询下一篇文章
     * @param id
     * @return
     */
     public Article selectNextArticle(Integer id){
         Query query=new Query(Criteria.where("id").gt(id));
         query.with(Sort.by(
                 Sort.Order.asc("id")
         ));
         query.limit(1);
         List<Article> list= this.mongoTemplate.find(query,Article.class);
         return list!=null &&list.size()>0 ? list.get(0): null;
     }

    /**
     * 获取文章总数
     * @return
     */
    public Integer countAllNum(){
        Query query=new Query();
        long row= this.mongoTemplate.count(query,Article.class);
        return Integer.valueOf((int)row);
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    public Boolean updateArticle(Article article){
        article.setUpdated(new Date());

        Query query = Query.query(Criteria.where("id").is(article.getId()));
        Update update = Update.update(
                "title", article.getTitle())
                .set("keywords",article.getKeywords())
                .set("desci",article.getDesci())
                .set("pic",article.getPic())
                .set("click",article.getClick())
                .set("time",article.getTime())
                .set("content",article.getContent())
                .set("updated",article.getUpdated());
        UpdateResult result= this.mongoTemplate.updateFirst(query, update, Article.class);
       if (result.getMatchedCount()>0){
           return true;
       }
       return false;
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    public Integer deleteById(Integer id){
        Query query = Query.query(Criteria.where("id").is(id));
        DeleteResult deleteResult= this.mongoTemplate.remove(query,Article.class);
        return Integer.valueOf((int)deleteResult.getDeletedCount());

    }

    /**
     * 根据关键词去查询文章
     * @param word
     * @return
     */
    public List<Article> selectByWord(String word){
        Pattern pattern=Pattern.compile("^.*"+word+".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("keywords").regex(pattern));
        return this.mongoTemplate.find(query,Article.class);
    }

    /**
     * 查询所有文章并且做好分页
     * @return
     */
    public PageInfo<Article> selectAllArticle(Integer star, Integer size){
        Query query = new Query().limit(size).skip((star - 1) * size);
        List<Article> list= this.mongoTemplate.find(query, Article.class);
        Integer total=countAllNum();
        return  new PageInfo<Article>(total,star,size,list);
    }

}
