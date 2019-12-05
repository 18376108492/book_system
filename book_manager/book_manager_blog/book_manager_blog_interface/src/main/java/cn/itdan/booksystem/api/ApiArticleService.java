package cn.itdan.booksystem.api;

import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;

import java.util.List;

/**
 * 文章dubbo_api接口
 */
public interface ApiArticleService {

    /**
     * 根据文章ID查询文章内容
     * @param id
     * @return
     */
    Article selectById(Integer id);

    /**
     * 获取上篇文章
     * @param id
     * @return
     */
    Article selectLastArticle(Integer id);

    /**
     * 查询下一篇文章
     * @param id
     * @return
     */
    Article selectNextArticle(Integer id);

    /**
     * 查询所有文章
     * @return
     */
    List<Article> queryAllArticle();

    /**
     * 获取文章总数
     * @return
     */
    Integer countAllNum();

    /**
     * 更新文章
     * @param article
     * @return
     */
    Reslut updateArticle(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    Reslut deleteById(Integer id);


    /**
     * 根据关键词去查询文章
     * @param word
     * @return
     */
    Reslut selectByWord(String word);

    /**
     * 添加文章
     * @param article
     * @return
     */
    Reslut addArticle(Article article);

    /**
     * 查询所有文章并且做好分页
     * @return
     */
    PageInfo<Article> selectAllArticle(Integer star, Integer size);

}
