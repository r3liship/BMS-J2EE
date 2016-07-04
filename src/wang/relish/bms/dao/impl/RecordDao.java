package wang.relish.bms.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import wang.relish.bms.base.BaseDao;
import wang.relish.bms.dao.IBookDao;
import wang.relish.bms.dao.IRecordDao;
import wang.relish.bms.dao.IUserDao;
import wang.relish.bms.entity.BorrowRecord;
import wang.relish.bms.entity.Record;
import wang.relish.bms.util.HibernateUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 记录Dao-实现
 * Created by Relish on 2016/6/29.
 */
@SuppressWarnings("unused")
@Service("recordDao")
public class RecordDao extends BaseDao<Record> implements IRecordDao {

    @Override
    public boolean borrowBook(int a, int b, int u) {
        Record record = new Record();
        record.setAdminId(a);
        record.setBookId(b);
        record.setUserId(u);
        record.setType(0);//0：借书
        record.setBorrowTime(new Timestamp(System.currentTimeMillis()));
        record.setId(getMaxId()+1);
        return save(record);
    }

    @Override
    public boolean returnBook(int id) {
        Record record = findById(id);
        record.setType(1);//1:还书
        record.setReturnTime(new Timestamp(System.currentTimeMillis()));
        return update(record);
    }

    @Override
    public List<BorrowRecord> getBorrowRecords() {
        List<BorrowRecord> bRecords = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        String hqlRecord = "from Record where type = 0";
        Query qRecord = session.createQuery(hqlRecord);
        @SuppressWarnings("unchecked")
        List<Record> records = qRecord.list();
        IUserDao ud = new UserDao();
        IBookDao bd = new BookDao();
        for (Record record : records) {
            BorrowRecord bRecord = new BorrowRecord();
            bRecord.setR(record);
            bRecord.setA(ud.findById(record.getAdminId()));
            bRecord.setB(bd.findById(record.getBookId()));
            bRecord.setU(ud.findById(record.getUserId()));
            bRecords.add(bRecord);
        }
        return bRecords;
    }

}
