package cn.itdan.booksystem.service.impl;

import cn.itdan.booksystem.mapper.ArticleMapper;
import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.service.ArticleService;
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
public class ArticleServiceImpl extends BaseServiceImpl<Article>
        implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    private Logger logger=LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Override
    public Article selectById(Integer id) {
        logger.info("根据ID获取文章操作id为："+id);
        Article  article=super.queryById(Long.valueOf(id));
        if(null==article){
            logger.info("根据ID获取文章操作,结果为null");
            return article;
        }
        logger.info("根据ID获取文章操作成功");
        return article;
    }

    @Override
    public List<Article> queryAllArticle() {
        logger.info("获取所有文章操作");
        List<Article> list= super.queryAll();
        logger.info("获取所有文章操作成功");
        return list;
    }

    @Override
    public Article selectLastArticle(Integer id) {
        logger.info("获取下一篇文章操作,参数id为:"+id);
        Article  article=articleMapper.selectLastArticle(id);
        if(null==article){
            logger.info("根据ID获取文章操作,结果为null");
            return article;
        }
        logger.info("根据ID获取文章操作成功");
        return article;
    }

    @Override
    public Article selectNextArticle(Integer id) {
        logger.info("获取下一篇文章操作,参数id为:"+id);
        Article  article=articleMapper.selectNextArticle(id);
        if(null==article){
            logger.info("根据ID获取文章操作,结果为null");
            return article;
        }
        logger.info("根据ID获取文章操作,结果:"+article.toString());
        return article;
    }


    @Override
    public Integer countAllNum() {
        logger.info("获取所有文章操作");
        Integer  row= articleMapper.countAllNum();
        if(null==row){
            logger.info("获取所有文章操作,总篇数为0");
            return 0;
        }
        logger.info("获取所有文章操作,总篇数为:"+row);
        return row;
    }

    @Override
    public boolean updateArticle(Article article) {

        logger.info("更新文章操作");

       //判断文章对象字段是否为空
        if(StringUtils.isBlank(article.getTitle())){
            logger.error("更新文章操作失败,传入的文章getTitle参数为null");
            return false;
        }
        if(StringUtils.isBlank(article.getKeywords())){
            logger.error("更新文章操作失败,传入的文章getKeywords参数为null");
            return false;
        }
        if(StringUtils.isBlank(article.getDesci())){
            logger.error("更新文章操作失败,传入的文章getDesci参数为null");
            return false;
        }
        if(StringUtils.isBlank(article.getContent())){
            logger.error("更新文章操作失败,传入的文章getContent参数为null");
            return false;
        }
        if(null==article.getClick()){
            logger.error("更新文章操作失败,传入的文章getClick参数为null");
            return false;
        }
        if (null==article.getTime()){
            logger.error("更新文章操作失败,传入的文章getTime参数为null");
            return false;
        }
        if (null==article.getCatalogId()){
            logger.error("更新文章操作失败,传入的文章getCatalogId参数为null");
            return false;
        }
        super.update(article);
        logger.info("更新文章操作成功");
        return true;
    }

    @Override
    public Integer deleteById(Integer id) {
        logger.info("删除文章操作,参数id为:"+id);
        Integer row= articleMapper.deleteById(id);
        logger.info("删除文章操作成功,删除条数："+row);
        return row;
    }


    @Override
    public List<Article> selectByWord(String word) {
        logger.info("根据关键词查询文章操作,参数："+word);
        List<Article> list=articleMapper.selectByWord(word);
        logger.info("根据关键词查询文章操作，操作成功");
        return list;
    }

    @Override
    public boolean addArticle(Article article) {
        logger.info("添加文章操作");

        //判断文章对象字段是否为空
        if(StringUtils.isBlank(article.getTitle())){
            logger.error("添加文章操作失败,传入的文章Title参数为null");
            return false;
        }
        if(StringUtils.isBlank(article.getKeywords())){
            logger.error("添加文章操作失败,传入的文章Keywords参数为null");
            return false;
        }
        if(StringUtils.isBlank(article.getDesci())){
            logger.error("添加文章操作失败,传入的文章Desci参数为null");
            return false;
        }
        if(StringUtils.isBlank(article.getContent())){
            logger.error("添加文章操作失败,传入的文章Content参数为null");
            return false;
        }

        if (null==article.getCatalogId()){
            logger.error("添加文章操作失败,传入的文章CatalogId参数为null");
            return false;
        }
        article.setTime(new Date());
        super.save(article);

        logger.info("添加文章成功");
        return true;
    }

    @Override
    public PageInfo<Article> selectAllArticle(Integer star, Integer size) {
        logger.info("分页查询操作");

       // QueryWrapper queryWrapper=new QueryWrapper();
        //queryWrapper.orderByDesc("updated");
       // IPage<Article> iPage= super.queryPageList(queryWrapper,star,size);
         Integer  page=(star-1)*size;
        List<Article> list=articleMapper.pageList(page,size);
        logger.info("分页查询操作成功");
        return new PageInfo<>(Long.valueOf(countAllNum()).intValue(),star,size,list);
    }
}
