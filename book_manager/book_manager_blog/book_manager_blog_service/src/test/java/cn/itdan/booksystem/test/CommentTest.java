package cn.itdan.booksystem.test;


import cn.itdan.booksystem.pojo.Comment;
import cn.itdan.booksystem.pojo.PageInfo;
import cn.itdan.booksystem.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Comment测试用例
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testAllComments() throws Exception{
        PageInfo<Comment> pageInfo= commentService.allComments(93,1,20);
        System.out.println(pageInfo.toString());
    }

    @Test
    public void testCountAllNum() throws Exception{
           Integer row= commentService.countAllNum();
           System.out.println(row);
    }

    @Test
    public void testAddComment() throws Exception{
        Comment comment=new Comment();
        comment.setArticleId(93L);
        comment.setContent("这时候的你是否想到如何实现这个功能？ \\n\" +\n" +
                "                \"我们的网页明明没有刷新，可是却出现了数据的交互，也就是Ajax的强大之处。 \\n\" +\n" +
                "                \"下面以一个例子来实现。 \\n\" +\n" +
                "                \"搜索框和搜索按钮，我们希望在搜索框输入部分书名时，能够实现书名的自动补全功能。每当点击了相应的书名，就把内容输入到搜索框。");
        comment.setEmail("15645544@qq.com");
        comment.setName("面以");
        Integer integer=null;
        for (int i=0;i<6;i++) {
             integer = commentService.addComment(comment);
        }
          System.out.println(integer);
    }

    @Test
    public void testDelete() throws Exception{
       Boolean b= commentService.delById(19L);
        System.out.println(b);
    }

}
