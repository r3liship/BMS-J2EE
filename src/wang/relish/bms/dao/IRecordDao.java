package wang.relish.bms.dao;

import wang.relish.bms.entity.BorrowRecord;

import java.util.List;

/**
 * 记录Dao
 * Created by Relish on 2016/6/29.
 */
public interface IRecordDao {
    /**
     * 借书记录
     *
     * @param a 管理员id
     * @param b 书籍id
     * @param u 用户id（借阅者id）
     * @return 插入成功与否
     */
    boolean borrowBook(int a, int b, int u);

    /**
     * 还书记录
     *
     * @param recordId 记录ID
     * @return 插入成功与否
     */
    boolean returnBook(int recordId);

    /**
     * 获取所有借书记录
     *
     * @return 所有借书记录
     */
    List<BorrowRecord> getBorrowRecords();
}
