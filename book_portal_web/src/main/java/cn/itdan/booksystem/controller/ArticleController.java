package cn.itdan.booksystem.controller;


import cn.itdan.booksystem.pojo.Article;
import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.service.ArticleService;
import cn.itdan.booksystem.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    /**
     * 文章展示
     *
     * @param request
     * @return
     */
    @RequestMapping("/article/getArticlebyId")
    public ModelAndView detail(HttpServletRequest request) {

        logger.info("文章展示:/getArticlebyId");

        String articleId = request.getParameter("id");
        ModelAndView modelAndView = new ModelAndView("detail");
        if (StringUtils.isNotBlank(articleId)) {
            int id = Integer.parseInt(articleId);
            // HashMap<String ,Object> map= articleService.detail(id);

            PageInfo<Comment> pageInfo = commentService.allComments(id, 1, 10);
            Article article = articleService.selectById(id);
            Article lastArticle = articleService.selectLastArticle(id);
            Article nextArticle = articleService.selectNextArticle(id);
            Integer clickNum = article.getClick();
            article.setClick(clickNum + 1);
            articleService.updateArticle(article);

            modelAndView.addObject("article", article);
            modelAndView.addObject("comments", pageInfo.getRecords());
            modelAndView.addObject("lastArticle", lastArticle);
            modelAndView.addObject("nextArticle", nextArticle);
        }
        return modelAndView;

    }

    /**
     * 添加文章
     *
     * @return
     */
    @RequestMapping("/admin/article/add")
    public String articleAdd() {
        logger.info("添加文章:/admin/article/add");
        return "/admin/article_add";
    }

    /**
     * 添加提交操作
     *
     * @param article
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/admin/article/add/do")
    public String articleAddDo(Article article, HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {
        logger.info("添加提交操作:/admin/article/add/do");
        Reslut reslut = articleService.addArticle(article);
        redirectAttributes.addFlashAttribute("msg", reslut.getMsg());
        return "redirect:/admin/article/add";
    }

    /**
     * 后台获取文章列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/admin/article/list")
    public ModelAndView articleList(@RequestParam(required = true, defaultValue = "1")
                                            Integer page,
                                    @RequestParam(required = false, defaultValue = "10")
                                            Integer pageSize) {
        logger.info("后台获取文章列表:/admin/article/list");
        PageInfo<Article> pageInfo = articleService.selectAllArticle(page, pageSize);
        ModelAndView modelAndView = new ModelAndView("/admin/article_list");
        modelAndView.addObject("articles", pageInfo.getRecords());
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    /**
     * 根据关键词搜索
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/article/search")
    public ModelAndView articleSearch(HttpServletRequest request){
        String word=request.getParameter("word");
        List<Article> reslut=null;
        if(StringUtils.isNotBlank(word)) {
             reslut = articleService.selectByWord(word);
        }
        ModelAndView modelAndView=new ModelAndView("/admin/article_list");
        modelAndView.addObject("articles",reslut);
        return modelAndView;
    }


    /**
     * 后台查看文章详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin/article/detail")
    public ModelAndView adminArticleDetail(HttpServletRequest request) {
        logger.info("后台查看文章详情：/admin/article/detail");
        int id = Integer.parseInt(request.getParameter("id"));
        logger.info("评论管理:id"+id);
        Article article = articleService.selectById(id);
        ModelAndView modelAndView = new ModelAndView("/admin/article_detail");
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    /**
     * 评论管理
     * @param request
     * @return
     */
    @RequestMapping("/admin/article/comment")
    public ModelAndView adminArticleComment(HttpServletRequest request){
        logger.info("评论管理:/admin/article/comment");
        int id=Integer.parseInt(request.getParameter("id"));
        logger.info("评论管理:id"+id);
        PageInfo<Comment> comments=commentService.allComments(id,1,10);
        ModelAndView modelAndView=new ModelAndView("/admin/comment_list");
        modelAndView.addObject("comments",comments.getRecords());
        return modelAndView;
    }

    /**
     * 编辑文章操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/article/edit")
    public ModelAndView articleEdit(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        Article article=articleService.selectById(id);
        ModelAndView modelAndView=new ModelAndView("/admin/article_edit");
        modelAndView.addObject("article",article);
        return modelAndView;
    }

    /**
     * 修改文章操作
     * @param article
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/admin/article/edit/do")
    public String articleEditDo(Article article,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttributes){
        logger.info("修改文章:/admin/article/edit/do");
        Reslut reslut= articleService.updateArticle(article);
        redirectAttributes.addFlashAttribute("msg", reslut.getMsg());
        return "redirect:/admin/article/add";
    }

    /**
     * 删除文章操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/article/del", method = RequestMethod.POST)
    @ResponseBody
    public Reslut  loginCheck(HttpServletRequest request) {
        logger.info("删除文章:/article/del");
        int id=Integer.parseInt(request.getParameter("id"));
        Reslut result=articleService.deleteById(id);
        return result;
    }









}
