package wang.relish.bms.service;

import wang.relish.bms.entity.Book;

import java.util.List;

/**
 * 添加书籍
 * Created by Relish on 2016/6/29.
 */
public interface IBookService {
    /**
     * 添加书籍
     *
     * @param book 书籍信息
     * @return 插入成功与否
     */
    boolean addBook(Book book);

    /**
     * 删除书籍
     *
     * @param id 书籍ID
     * @return 删除成功与否
     */
    boolean deleteBook(int id);

    /**
     * 获得所有书籍
     *
     * @return 书籍列表
     */
    List<Book> getAllBooks();

    /**
     * 修改书籍信息
     *
     * @param b 书籍信息
     * @return 修改成功与否
     */
    boolean modifyBook(Book b);
}
