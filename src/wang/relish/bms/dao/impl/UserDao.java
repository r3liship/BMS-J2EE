package wang.relish.bms.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import wang.relish.bms.base.BaseDao;
import wang.relish.bms.dao.IUserDao;
import wang.relish.bms.entity.Record;
import wang.relish.bms.entity.User;
import wang.relish.bms.util.HibernateUtil;

import java.util.List;

/**
 * UserDao
 * Created by Relish on 2016/6/28.
 */

@Service("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

    @Override
    public boolean add(User u) {
        return save(u);
    }

    @SuppressWarnings("all")
    @Override
    public User search(User u) {
        Session session = HibernateUtil.getSession();
        String hql = "from User where name = ? and pwd = ?";
        Query q = session.createQuery(hql);
        q.setString(0, u.getName());
        q.setString(1, u.getPwd());

        List<User> l = (List<User>) q.list();
        if (l.size() != 0) {
            User user = l.get(0);
            //清除密码信息
            user.setPwd(null);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User findById(int id) {
        return super.findById(id);
    }

    //TODO 映射关系搞不清楚
    @Override
    public List<Record> viewBorrowedBookList() {
        return null;
    }

    //TODO 映射关系搞不清楚
    @Override
    public List<Record> viewBorrowedBookListByUserId() {
        return null;
    }

    //TODO 映射关系搞不清楚
    @Override
    public List<Record> viewBorrowedBookListByBookId() {
        return null;
    }

    //TODO 映射关系搞不清楚
    @Override
    public List<Record> viewBorrowedBookListByAdminId() {
        return null;
    }


}
