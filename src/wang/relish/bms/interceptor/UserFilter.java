package wang.relish.bms.interceptor;

import wang.relish.bms.entity.ClientUserSessionEntity;
import wang.relish.bms.util.DateUtils;
import wang.relish.bms.util.TextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
public class UserFilter implements Filter {
    private static ConcurrentHashMap<String, ClientUserSessionEntity> userSessionEntityMap
            = ClientUserInterceptor.getClientUserSessionMap();

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession Session = request.getSession();
        try {


            String path = request.getRequestURI();
            //如果是登录或者注册请求则不进行过滤
            if (path.endsWith("/index.jsp") || path.endsWith("/registered.jsp")) {
                chain.doFilter(request, response);
            } else {
                //获取请求中携带的sessionId
                String sessionId = (String) Session.getAttribute("sid");
                if (TextUtils.isNotEmpty(sessionId) && userSessionEntityMap.containsKey(sessionId)) {  //有session记录
                    ClientUserSessionEntity user = userSessionEntityMap.get(sessionId);

                    if (user.getSessionOverTime() > DateUtils.getCurrentTimeMillis()) {  //已登录且session未过期
                        user.addOverTime();   //延长失效时间
                        //返回当前登录用户信息
                        request.setAttribute("resultUser", user.getUser());
                        chain.doFilter(request, response);
                    } else {  //session过期失效
                        request.setAttribute("resultCode", -2);
                        request.setAttribute("resultMessage", "长时间未操作，请重新登录");

                        //清理所有的过期记录
                        ClientUserInterceptor.removeLoseSession();

                        //重定向到一个专门的servlet，由这个servlet发送相关错误的json数据【请重新登录】
                        response.sendRedirect("");

                    }
                } else {  //无session记录
                    request.setAttribute("resultCode", -1);
                    request.setAttribute("resultMessage", "未登录");
                    //重定向到一个专门的servlet，由这个servlet发送相关错误的json数据【请重新登录】
                    response.sendRedirect("");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            //重定向到一个专门的servlet，由这个servlet发送相关错误的json数据【出错了】
            response.sendRedirect("");
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }


}
