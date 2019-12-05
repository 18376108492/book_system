package cn.itdan.booksystem.service;

import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;

import java.util.List;

/**
 * 评论dubbo_api接口
 */
public interface ArticleService {

    /**
     * 根据文章ID查询文章内容
     * @param id
     * @return
     */
    Article selectById(Integer id);

    /**
     * 根据文章ID查询最后的文章
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
    boolean updateArticle(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    Integer deleteById(Integer id);


    /**
     * 根据关键词去查询文章
     * @param word
     * @return
     */
    List<Article> selectByWord(String word);

    /**
     * 添加文章
     * @param article
     * @return
     */
    boolean addArticle(Article article);

    /**
     * 查询所有文章并且做好分页
     * @return
     */
    PageInfo<Article> selectAllArticle(Integer star, Integer size);
}
