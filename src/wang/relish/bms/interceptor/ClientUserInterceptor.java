package wang.relish.bms.interceptor;

import wang.relish.bms.entity.ClientUserSessionEntity;
import wang.relish.bms.entity.User;
import wang.relish.bms.util.DateUtils;
import wang.relish.bms.util.MD5Utils;
import wang.relish.bms.util.TextUtils;

import java.util.concurrent.ConcurrentHashMap;


public class ClientUserInterceptor {
    private static ConcurrentHashMap<String, ClientUserSessionEntity> clientUserSessionMap = new ConcurrentHashMap<>();

    static ConcurrentHashMap<String, ClientUserSessionEntity> getClientUserSessionMap() {
        return clientUserSessionMap;
    }


    /***
     * 核对sessionId
     *
     * @param sessionId 进程id
     * @return 有ID返回true，ID超时返回no_time，错误返回error
     */
    @SuppressWarnings("unused")
    public static String checkSession(String sessionId) {
        try {
            //获取请求中携带的sessionId

            if (TextUtils.isNotEmpty(sessionId) && clientUserSessionMap.containsKey(sessionId)) {  //有session记录
                ClientUserSessionEntity clientUser = clientUserSessionMap.get(sessionId);

                if (clientUser.getSessionOverTime() > DateUtils.getCurrentTimeMillis())        //已登录且session未过期
                {
                    clientUser.addOverTime();                                        //延长失效时间
                    return "true";
                } else {                                                                        //session过期失效
                    removeLoseSession();

                    return "登录超时";                                                    //长时间未操作，请重新登录
                }
            } else {                                                                    //无session记录
                return "还没有登录";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    /**
     * 从session中获取当前用户的用户数据
     *
     * @param sessionId 用户的sessionId
     * @return userSessionMap中的UserEntity对象
     */
    @SuppressWarnings("unused")
    public static User getUserInSession(String sessionId) {
        if (clientUserSessionMap.containsKey(sessionId)) {  //有session记录
            ClientUserSessionEntity sessionClientUser = clientUserSessionMap.get(sessionId);
            return sessionClientUser.getUser();
        } else {
            return null;
        }
    }

    /**
     * 登录成功
     *
     * @param user 用户信息
     * @return sessionId
     */
    public static String loginSuccess(User user) {
        String sessionId = "sid" + DateUtils.getCurrentTimeMillis();
        sessionId = MD5Utils.toMd5(sessionId);

        //清理掉相同的用户登录信息
        for (String keyTemp : clientUserSessionMap.keySet()) {
            ClientUserSessionEntity userTemp = clientUserSessionMap.get(keyTemp);
            User UserTemp = userTemp.getUser();
            if (UserTemp.getId().equals(user.getId())) {  //相同用户已登录
                clientUserSessionMap.remove(keyTemp);
            }
        }

        ClientUserSessionEntity ClientUser = new ClientUserSessionEntity(user);
        if (sessionId != null)
            clientUserSessionMap.put(sessionId, ClientUser);
        return sessionId;
    }

    /**
     * 退出登录
     *
     * @param sessionId 当前退出用户的sessionId
     */
    public static String logout(String sessionId) {
        if (TextUtils.isNotEmpty(sessionId)) {  //移除session记录
            ClientUserSessionEntity entity = clientUserSessionMap.remove(sessionId);
            return entity.getUser().getName();
        }

        //清理所有的过期记录
        removeLoseSession();
        return "";
    }

    /**
     * 移除失效session
     */
    static void removeLoseSession() {
        long curTime = DateUtils.getCurrentTimeMillis();
        for (String keyTemp : clientUserSessionMap.keySet()) {
            ClientUserSessionEntity userTemp = clientUserSessionMap.get(keyTemp);
            if (userTemp.getSessionOverTime() <= curTime) {
                clientUserSessionMap.remove(keyTemp);
            }
        }
    }

}

