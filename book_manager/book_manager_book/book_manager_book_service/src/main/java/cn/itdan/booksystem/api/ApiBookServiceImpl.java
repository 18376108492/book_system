package cn.itdan.booksystem.api;

import cn.itdan.booksystem.pojo.Book;
import cn.itdan.booksystem.service.BookService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 实现API 接口层数据
 */
@Service(version = "${dubbo.service.version}")
//dubbo层使用的service不是spring的而是使用dubbo的
public class ApiBookServiceImpl implements  ApiBookService{

    @Autowired
    private BookService bookService;

    @Override
    public void addBook(Book book) {

    }

    @Override
    public Book QueryBookById(String id) {
        return null;
    }

    @Override
    public List<Book> getPageData(int start, int size) {
        return null;
    }

    @Override
    public List<Book> getPageData(int start, int size, String category_id) {
        return null;
    }

    @Override
    public long getTotalRecord() {
        return 0;
    }

    @Override
    public long getCategoryTotalRecord(String category_id) {
        return 0;
    }
}
