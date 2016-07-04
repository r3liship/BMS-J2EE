package wang.relish.bms.servlet;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.service.impl.RecordService;
import wang.relish.bms.util.TextUtils;

/**
 * 借书
 * Created by Relish on 2016/6/30.
 */
@Controller
@Scope(value = "prototype")
public class BorrowBookServlet extends BaseServlet<RecordService> {


    @Override
    protected JSONObject response(JSONObject json, RecordService service) {
        Integer adminId = json.getInt("admin_id");
        Integer bookId = json.getInt("bookId");
        Integer user_id = json.getInt("user_id");
        boolean isAdded = false;
        if (TextUtils.isNotEmpty(adminId) &&
                TextUtils.isNotEmpty(bookId) &&
                TextUtils.isNotEmpty(user_id)) {
            isAdded = service.borrowBook(adminId, bookId, user_id);
        }
        return simpleResponse(isAdded);
    }

}
