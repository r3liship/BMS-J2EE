package wang.relish.bms.base;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wang.relish.bms.util.HibernateUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Dao层基础类
 * Created by Relish on 2016/6/29.
 */
public abstract class BaseDao<T> {

    /**
     * 增
     *
     * @param t 实体类型
     * @return 添加成功与否
     */
    public boolean save(T t) {
        Session session = HibernateUtil.getSession();
        Transaction tran = session.beginTransaction();
        try {
            session.save(t);
            tran.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tran.rollback();
            session.close();
            return false;
        }
        session.close();
        return true;
    }

    /**
     * 获取最大ID
     *
     * @return 最大ID
     */
    public int getMaxId() {
        Session session = HibernateUtil.getSession();
        Transaction tran = session.beginTransaction();
        String hql = "select max(id) from " + getGenericType();
        return Integer.parseInt(session.createQuery(hql).uniqueResult().toString());
    }

    /**
     * 删
     *
     * @param id id
     * @return 删除成功与否
     */
    public boolean delete(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tran = session.beginTransaction();
        try {
            session.delete(id);
            tran.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tran.rollback();
            session.close();
            return false;
        }
        session.close();
        return true;
    }


    /**
     * 查
     *
     * @param id id
     * @return 实体类型
     */
    @SuppressWarnings("unchecked")
    public T findById(int id) {
        Session session = HibernateUtil.getSession();
        T t = (T) session.get(getGenericType(), id);
        session.close();
        return t;
    }

    /**
     * 改
     *
     * @param t 修改后的实体类型
     * @return 修改成功与否
     */
    public boolean update(T t) {
        Session session = HibernateUtil.getSession();
        Transaction tran = session.beginTransaction();
        try {
            session.update(t);
            tran.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tran.rollback();
            session.close();
            return false;
        }
        session.close();
        return true;
    }

    /**
     * 其中BaseDao<T>是泛型类
     * 在父类中声明getGenericType
     * 子类继承具体的BaseDao<User>
     * 那么在子类中就可以通过getGenericType(0)获取到User的class.
     *
     * @return T.class
     */
    private Class getGenericType() {
        Type genType = getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (!(params[0] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[0];
    }
}
