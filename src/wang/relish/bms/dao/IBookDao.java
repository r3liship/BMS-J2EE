package wang.relish.bms.dao;

import wang.relish.bms.entity.Book;

import java.util.List;

/**
 * 书籍Dao
 * Created by Relish on 2016/6/29.
 */
@SuppressWarnings("unused")
public interface IBookDao {
    /**
     * 添加书籍
     *
     * @param book 书籍信息
     * @return 是否添加成功
     */
    boolean addBook(Book book);

    /**
     * 删除书籍
     *
     * @param bookId 书籍ID
     * @return 是否删除成功
     */
    boolean delBook(int bookId);

    /**
     * 获取所有书籍
     *
     * @return 书籍
     */
    List<Book> getAllBooks();

    /**
     * 修改书籍信息
     *
     * @param book 书籍信息
     * @return 是否修改成功
     */
    boolean modifyBook(Book book);

    /**
     * 通过Id查询书籍
     *
     * @param bookId id
     * @return 书籍
     */
    Book findById(int bookId);
}
