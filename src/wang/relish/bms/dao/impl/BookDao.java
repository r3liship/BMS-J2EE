package wang.relish.bms.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import wang.relish.bms.base.BaseDao;
import wang.relish.bms.dao.IBookDao;
import wang.relish.bms.entity.Book;
import wang.relish.bms.util.HibernateUtil;

import java.util.List;

/**
 * 书籍Dao-实现类
 * Created by Relish on 2016/6/29.
 */
@Service("bookDao")
public class BookDao extends BaseDao<Book> implements IBookDao {

    @Override
    public boolean addBook(Book book) {
        book.setId(getMaxId()+1);
        return save(book);
    }

    @Override
    public boolean delBook(int id) {
        return delete(id);
    }


    @Override
    public boolean modifyBook(Book book) {
        return update(book);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getAllBooks() {
        Session session = HibernateUtil.getSession();
        Query q = session.createQuery("from Book");
        return q.list();
    }
}
