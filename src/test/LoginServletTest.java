package test;

import javax.servlet.http.HttpServlet;

/**
 * Created by Relish on 2016/7/2.
 */
@SuppressWarnings("all")
public class LoginServletTest extends HttpServlet {
/*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

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

        try {

            ServletContext servletContext = this.getServletContext();
            WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            UserService service = (UserService) ctx.getBean("userService");
            JSONObject responseJson = response(jo, service);
            out.write(responseJson.toString());
            out.flush();
            out.close();
        } catch (JSONException e) {
            e.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.element("resultCode", -2);
            responseJson.element("resultMessage", "参数错误");
            out.write(responseJson.toString());
            out.flush();
            out.close();
        }
    }

    private JSONObject response(JSONObject json, UserService service) {
        String name = json.getString("name");
        String pwd = json.getString("pwd");

        User user = null;

        if (TextUtils.isNotEmpty(name) && TextUtils.isNotEmpty(pwd)) {
            name = name.trim();
            pwd = pwd.trim();
            user = service.searchUser(name, pwd);
        }

        JSONObject responseJson = new JSONObject();
        if (user != null) {
            String userSessionId = ClientUserInterceptor.loginSuccess(user);

            responseJson.element("resultCode", 0);
            responseJson.element("resultMessage", "成功");

            JSONObject dataJson = new JSONObject();

            dataJson.element("sessionId", userSessionId);
            dataJson.element("id", user.getId());
            dataJson.element("name", user.getName());
            dataJson.element("type", user.getType());

            responseJson.elementOpt("data", dataJson);
        } else {
            responseJson.element("resultCode", 1);
            responseJson.element("resultMessage", "用户名或密码错误");
        }
        System.out.println(responseJson.toString());
        return responseJson;
    }
    */
}
