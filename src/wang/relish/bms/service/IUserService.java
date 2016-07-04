package wang.relish.bms.service;

import wang.relish.bms.entity.User;

public interface IUserService {

    User searchUser(String name, String pwd);

    String logout(String sessionId);
}
