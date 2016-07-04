package wang.relish.bms.base;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import wang.relish.bms.interceptor.ClientUserInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Servlet层基础类
 * Created by Relish on 2016/6/29.
 */
@Controller
@Scope(value = "prototype")
public abstract class BaseServlet<S> extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private WebApplicationContext ctx;
    private S service;


    /**
     * 处理JSON请求，并响应，返回JSON
     *
     * @param json    请求
     * @param service 当前Service
     * @return 响应JSON
     */
    protected abstract JSONObject response(JSONObject json, S service);

    /**
     * 是否有sessionId的入参
     *
     * @return 是否存在
     */
    protected boolean needSessionId() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext servletContext = this.getServletContext();
        ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        StringBuilder stb = new StringBuilder();
        String s;

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        while ((s = br.readLine()) != null) {
            stb.append(s);
        }

        if (stb.length() <= 0) {
            return;
        }
        System.out.println("请求JSON：" + stb.toString());

        JSONObject jo = JSONObject.fromObject(stb.toString());
        boolean canContinue = true;
        if (needSessionId()) {
            String sessionId = "noSessionId";
            if (jo.has("sessionId")) {
                sessionId = jo.getString("sessionId");
            }
            String temp_str = ClientUserInterceptor.checkSession(sessionId);
            canContinue = temp_str.equals("true");
            if (!canContinue) {
                @SuppressWarnings("all")
                JSONObject sessionIdErrorJson = new JSONObject();
                sessionIdErrorJson.element("resultCode", -1);
                sessionIdErrorJson.element("resultMessage", "session过期");
                out.write(sessionIdErrorJson.toString());
                out.flush();
                out.close();
            }
        }
        if (canContinue) {
            try {
                service = (S) ctx.getBean(getBeanName());
                JSONObject responseJson = response(jo, service);
                out.write(responseJson.toString());
                out.flush();
                out.close();
            } catch (JSONException e) {
                e.printStackTrace();
                @SuppressWarnings("all")
                JSONObject responseJson = new JSONObject();
                responseJson.element("resultCode", -2);
                responseJson.element("resultMessage", "参数错误");
                out.write(responseJson.toString());
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 获得泛型的实现类的首字母小写类名
     * 如：ILoginService->loginService
     *
     * @return S.class
     */
    public String getBeanName() {
        Type genType = getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return "object";
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (!(params[0] instanceof Class)) {
            return "object";
        }
        Class cla = (Class) params[0];
        String claName = cla.getSimpleName();
        return String.valueOf(claName.charAt(1)).toLowerCase() + claName.substring(2);
    }

    /**
     * 简单数据库操作，只会返回成功或失败
     *
     * @param isAdded 是否添加成功
     * @return 成功或失败的JSONObject
     * @see #simpleResponse(boolean, String, int)
     */
    protected JSONObject simpleResponse(boolean isAdded) {
        return simpleResponse(isAdded, "插入失败", 3);
    }

    /**
     * 为其他只需返回成功与否的数据库操作提供便利
     *
     * @param isTrue 操作是否成功
     * @param msg    失败的resultMessage
     * @param code   失败的resultCode
     * @return 成功或失败的JSONObject
     */
    protected JSONObject simpleResponse(boolean isTrue, String msg, int code) {
        JSONObject responseJson = new JSONObject();
        if (isTrue) {
            responseJson.element("resultCode", 0);
            responseJson.element("resultMessage", "成功");
        } else {
            responseJson.element("resultCode", code);
            responseJson.element("resultMessage", msg);
        }
        return responseJson;
    }
}
