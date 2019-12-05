package cn.itdan.booksystem.controller;

import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.service.ArticleService;
import cn.itdan.booksystem.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    private Logger logger=LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * 进入博客系统主页
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping({"/","/index"})
    public ModelAndView showIndex(@RequestParam(required=true,defaultValue="1") Integer page,
                                  @RequestParam(required=false,defaultValue="5")Integer pageSize ){
      logger.info("进入博客系统主页：/index");
        ModelAndView modelAndView =new ModelAndView("index");
        PageInfo<Article> pageInfo =articleService.selectAllArticle(page,pageSize);
        modelAndView.addObject("articles",pageInfo.getRecords());
        modelAndView.addObject("pageInfo",pageInfo);
       // logger.info("articles:"+pageInfo.getRecords());
        return modelAndView;
    }


    @GetMapping(value = "/queryAllArticle")
    @ResponseBody
    public String  queryAllArticle(){
        logger.info("获取所有文章操作：/queryAllArticle");
         List<Article> list=articleService.queryAllArticle();
          return JsonUtils.objectToJson(list);
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

}
