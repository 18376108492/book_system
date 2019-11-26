package cn.itdan.booksystem.service.impl;

import cn.itdan.booksystem.pojo.Book;
import cn.itdan.booksystem.service.BookService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 书籍业务逻辑操作实现层
 */
@Transactional
@Service//service标签一定要添加的是spring的而不是dubbode
public class BookServiceImpl  extends BaseServiceImpl<Book> implements BookService {

    private Logger logger=LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public void addBook(Book book) {
        logger.info("添加图书操作："+book.toString());

        if(book==null){
           logger.error("传入的书籍为空");
           return;
        }
        //数据库中book的字段只name为非空
        if(StringUtils.isBlank(book.getName())){
            logger.error("传入的书籍名为空");
            return;
        }
          super.save(book);
          logger.info("添加图书操作成功");
    }

    @Override
    public Book QueryBookById(String id) {
        return null;

    }


    @Override
    public List<Book> getPageData(int start, int end) {

        return null;
    }


    @Override
    public List<Book> getPageData(int start, int end, String category_id) {

        return null;
    }


    @Override
    public long getTotalRecord() {
        return 1L;
    }

    public long getCategoryTotalRecord(String category_id) {

        return 1L;
    }



}
