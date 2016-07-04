package wang.relish.bms.servlet;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.entity.User;
import wang.relish.bms.interceptor.ClientUserInterceptor;
import wang.relish.bms.service.IUserService;
import wang.relish.bms.util.TextUtils;


@Controller
@Scope(value = "prototype")
public class LoginServlet extends BaseServlet<IUserService> {

    @Override
    protected boolean needSessionId() {
        return false;
    }

    @Override
    protected JSONObject response(JSONObject json, IUserService service) {
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
}
