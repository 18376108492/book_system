package cn.itdan.booksystem.controller;

import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping (value = "/addComment")
    @ResponseBody
    public Reslut commentAdd(HttpServletRequest request) {
        Comment comment=new Comment();
        comment.setArticleId(Long.parseLong(request.getParameter("articleId")));
        comment.setContent(request.getParameter("content"));
        comment.setName(request.getParameter("name"));
        comment.setEmail(request.getParameter("email"));
        Reslut res= commentService.addComment(comment);
        return res;
    }

}
