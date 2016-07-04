package wang.relish.bms.entity;


import wang.relish.bms.util.DateUtils;

public class ClientUserSessionEntity {

    private final long overTime = 7 * 24 * 60 * 60 * 1000;  //7天失效时间

    private User user;  //后台管理员backUser对象
    private long sessionOverTime;  //session失效时间

    /**
     * 实例化 UserSessionEntity 对象，默认设置sessionOverTime失效时间为7天
     *
     * @param user UserEntity对象
     */
    public ClientUserSessionEntity(User user) {
        this.user = user;
        this.sessionOverTime = DateUtils.getCurrentTimeMillis() + overTime;
    }

    /**
     * 延长失效时间（从现在开始7天）
     */
    public void addOverTime() {
        this.sessionOverTime = DateUtils.getCurrentTimeMillis() + overTime;
    }


    /**
     * User对象
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * User对象
     *
     * @param user 用户
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * session过期时间
     *
     * @return sessionOverTime
     */
    public long getSessionOverTime() {
        return sessionOverTime;
    }

    /**
     * session过期时间
     *
     * @param overTime 过期时间
     */
    @SuppressWarnings("unused")
    public void setSessionOverTime(long overTime) {
        this.sessionOverTime = overTime;
    }

}
