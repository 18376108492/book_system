package cn.itdan.booksystem.test;

import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Article测试用例
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BaseMapper<Article> baseMapper;

    @Test
    public void testSelectById() throws Exception{
     Article article=articleService.selectById(98);
        System.out.println(article.toString());
    }

    @Test 
    public void testQueryAllArticle() throws Exception{
       List<Article> list= articleService.queryAllArticle();
       for (Article article:list){
           System.out.println(article.toString());
       }
    }

    @Test
    public void testSelectLastArticle() throws Exception{
         Article article= articleService.selectLastArticle(100);
        System.out.println(article.toString());
    }

    @Test
    public void testSelectNextArticle() throws Exception{
        Article article= articleService.selectNextArticle(100);
        System.out.println(article.toString());
    }

    @Test
    public void testCountAllNum() throws Exception{
          Integer row= articleService.countAllNum();
          System.out.println(row);
    }

    @Test
    public void testaddArticle() throws Exception{
        Article article=new Article();
        article.setTitle("数据");
        article.setCatalogId(100);
        article.setContent("dasdsfdksjhfguewrhfyusgysf");
        article.setDesci("这时候的你是否想到如何实现这个功能？ \n" +
                "我们的网页明明没有刷新，可是却出现了数据的交互，也就是Ajax的强大之处。 \n" +
                "下面以一个例子来实现。 \n" +
                "搜索框和搜索按钮，我们希望在搜索框输入部分书名时，能够实现书名的自动补全功能。每当点击了相应的书名，就把内容输入到搜索框。");
     article.setKeywords("数据的交互");
     boolean b= articleService.addArticle(article);
        System.out.println(b);
    }

    @Test
    public void testUpdate() throws Exception{
      Article article=articleService.selectById(105);
      article.setClick(100000);
      article.setTitle("更新标题");
      boolean b= articleService.updateArticle(article);
        System.out.println(b);
    }

    @Test
    public void testSelectByWord() throws Exception{
      List<Article> list=articleService.selectByWord("数据");
     for (Article article:list){
         System.out.println(article.toString());
     }
    }

    @Test
    public void testDeleteByID() throws Exception{
        int row=  articleService.deleteById(105);
        System.out.println(row);
    }
    @Test
    public void testDeleteByIDDemo02() throws Exception{
        int row=  articleService.deleteById(1000);
        System.out.println(row);
    }

    @Test
    public void testDemo() throws Exception{
       PageInfo<Article> pageInfo= articleService.selectAllArticle(1,5);
       // System.out.println("pageNum:"+pageInfo.getPageNum());
       // System.out.println("pageSize:"+pageInfo.getPageSize());
        //System.out.println("pageTol:"+pageInfo.getTotal());
        System.out.println(pageInfo.getRecords());
     }

     @Test
     public void testDemo01() throws Exception{
         Page<Article> articlePage=new Page<>(0,5);
         QueryWrapper<Article> queryWrapper=new QueryWrapper();
         queryWrapper.orderByDesc("updated");
          IPage<Article> iPage= baseMapper.selectPage(articlePage,queryWrapper);
         System.out.println(iPage.getTotal());
    }
}
