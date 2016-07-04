package wang.relish.bms.servlet;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.service.IUserService;
import wang.relish.bms.util.TextUtils;


@Controller
@Scope(value = "prototype")
public class LogoutServlet extends BaseServlet<IUserService> {

    @Override
    protected JSONObject response(JSONObject json, IUserService service) {
        String sessionId = json.getString("sessionId");
        JSONObject responseJson = new JSONObject();
        String user = null;
        if (TextUtils.isNotEmpty(sessionId)) {
            sessionId = sessionId.trim();
            user = service.logout(sessionId);
        }
        if (user != null) {
            responseJson.element("resultCode", 0);
            responseJson.element("resultMessage", "成功");
        } else {
            responseJson.element("resultCode", 2);
            responseJson.element("resultMessage", "session不存在");
        }
        return responseJson;
    }
}
