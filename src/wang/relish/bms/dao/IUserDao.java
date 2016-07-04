package wang.relish.bms.dao;


import wang.relish.bms.entity.Record;
import wang.relish.bms.entity.User;

import java.util.List;

@SuppressWarnings("unused")
public interface IUserDao {
    /**
     * 新建一个用户信息
     *
     * @param u 用户信息
     * @return 成功与否
     */
    boolean add(User u);

    /**
     * 通过用户名和密码进行查找,返回用户
     *
     * @param u 用户信息
     * @return 用户信息
     */
    User search(User u);

    /**
     * 判断用户是否存在
     *
     * @param name 用户名
     * @return 存在与否
     */
    //boolean isExistByName(String name);

    /**
     * 更新用户信息：修改密码
     *
     * @param id  用户id
     * @param pwd 密码
     * @return 用户信息
     */
    //User updatePwd(int id, String pwd);

    /**
     * 通过id得到User信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User findById(int id);

    /**
     * 显示所有借书记录
     *
     * @return 记录列表
     */
    List<Record> viewBorrowedBookList();

    List<Record> viewBorrowedBookListByUserId();

    List<Record> viewBorrowedBookListByBookId();

    List<Record> viewBorrowedBookListByAdminId();
}
