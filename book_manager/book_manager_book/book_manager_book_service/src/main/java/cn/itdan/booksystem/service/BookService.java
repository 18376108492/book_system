package cn.itdan.booksystem.service;

import cn.itdan.booksystem.pojo.Book;

import java.util.List;

/**
 * 书籍业务逻辑操作接口
 */
public interface BookService {

    /**
     * 添加图书
     * @param book 图书对象
     */
    void addBook(Book book);

    /**
     * 根据图书ID查询图书
     * @param id 图书ID
     * @return
     */
    Book QueryBookById(String id);

    /**
     * 获取全部图书并且进行分页操作
     * @param start 起始页
     * @param size 大小
     * @return
     */
    List<Book> getPageData(int start, int size);

    /**
     * 根据图书类型查询图书集合
     * @param start
     * @param size
     * @param category_id 图书类型ID
     * @return
     */
    List<Book> getPageData(int start, int size, String category_id);

    /**
     * 获取总数
     * @return
     */
    long getTotalRecord();

    /**
     * 获取类型总记录
     * @param category_id
     * @return
     */
    long getCategoryTotalRecord(String category_id);
}
