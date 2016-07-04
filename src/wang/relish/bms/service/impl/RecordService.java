package wang.relish.bms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.relish.bms.dao.IRecordDao;
import wang.relish.bms.entity.BorrowRecord;
import wang.relish.bms.service.IRecordService;

import java.util.List;

/**
 * 记录Service-实现
 * Created by Relish on 2016/6/30.
 */
@Service("recordService")
@Transactional
public class RecordService implements IRecordService {

    @Autowired
    IRecordDao recordDao;


    @Override
    public boolean borrowBook(int a, int b, int u) {
        return recordDao.borrowBook(a, b, u);
    }

    @Override
    public boolean returnBook(int recordId) {
        return recordDao.returnBook(recordId);
    }

    @Override
    public List<BorrowRecord> getBorrowRecords() {
        return recordDao.getBorrowRecords();
    }
}
