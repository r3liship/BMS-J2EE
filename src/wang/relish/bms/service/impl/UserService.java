package wang.relish.bms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.relish.bms.dao.IUserDao;
import wang.relish.bms.entity.User;
import wang.relish.bms.interceptor.ClientUserInterceptor;
import wang.relish.bms.service.IUserService;


@Service("userService")
@Transactional
public class UserService implements IUserService {

    @Autowired
    public IUserDao userDao;

    public User searchUser(String name, String pwd) {
        User u = new User();
        u.setName(name);
        u.setPwd(pwd);
        return userDao.search(u);
    }

    @Override
    public String logout(String sessionId) {
        return ClientUserInterceptor.logout(sessionId);
    }


}
