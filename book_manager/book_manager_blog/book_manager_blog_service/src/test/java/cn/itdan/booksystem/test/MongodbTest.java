package cn.itdan.booksystem.test;


import cn.itdan.booksystem.mongodb.ArticleDAO;
import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.service.ArticleService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbTest {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private ArticleService articleService;

    @Test
    public void testSave() throws Exception{
      List<Article> list= articleService.queryAllArticle();
      for (Article article:list ){
         Article article1= articleDAO.saveArticle(article);
          System.out.println(article1);
      }
//        Article article= articleService.selectById(111);
//         Article article1=  articleDAO.saveArticle(article);
//        System.out.println(article1.toString());
    }

    @Test
    public void testQueryAll() throws Exception{
     List<Article> list=articleDAO.queryAllArticle();
     for (Article article:list){
         System.out.println(article.toString());
     }
    }

    @Test
    public void testSelectById() throws Exception{
      Article article= articleDAO.selectById(111);
        System.out.println(article);
    }

    @Test
    public void testSelectLastArticle() throws Exception{
      Article article= articleDAO.selectLastArticle(100);
        System.out.println(article);
    }

    @Test
    public void testselectNextArticle() throws Exception{
       Article article=  articleDAO.selectNextArticle(100);
        System.out.println(article);
     }

     @Test
     public void testcountAllNum() throws Exception{
        Integer row= articleDAO.countAllNum();
         System.out.println(row);
    }
    @Test
    public void testupdateArticle() throws Exception{
        Article article= articleDAO.selectById(111);
        article.setTitle("mongodb_test");
//        UpdateResult updateResult= articleDAO.updateArticle(article);
//        System.out.println(updateResult.getMatchedCount());
//        System.out.println(updateResult.getModifiedCount());
//        System.out.println(updateResult.getUpsertedId());
     }

     @Test
     public void testDelete() throws Exception{
//         DeleteResult deleteResult= articleDAO.deleteById(111);
//         System.out.println(deleteResult.getDeletedCount());
//         System.out.println(deleteResult.wasAcknowledged());
    }


    @Test
    public void testQ() throws Exception{
         List<Article> list=articleDAO.selectByWord("spring");
         for (Article article:list){
             System.out.println(article.toString());
         }
    }

    @Test
    public void testselectAllArticle() throws Exception{
        PageInfo<Article> pageInfo= articleDAO.selectAllArticle(1,5);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPageSize());
        System.out.println(pageInfo.getPageNum());
        System.out.println(pageInfo.getRecords());
    }

}
