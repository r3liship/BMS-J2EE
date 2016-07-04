package wang.relish.bms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.relish.bms.dao.IBookDao;
import wang.relish.bms.entity.Book;
import wang.relish.bms.service.IBookService;

import java.util.List;

/**
 * 添加书籍-实现类
 * Created by Relish on 2016/6/29.
 */
@Service("bookService")
@Transactional
public class BookService implements IBookService {

    @Autowired
    IBookDao bookDao;

    @Override
    public boolean addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public boolean deleteBook(int id) {
        return bookDao.delBook(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public boolean modifyBook(Book b) {
        return bookDao.modifyBook(b);
    }
}
