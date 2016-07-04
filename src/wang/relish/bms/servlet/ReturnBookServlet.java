package wang.relish.bms.servlet;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.service.IRecordService;

/**
 * 还书
 * Created by Relish on 2016/7/2.
 */
@Controller
@Scope(value = "prototype")
public class ReturnBookServlet extends BaseServlet<IRecordService>{

    @Override
    protected JSONObject response(JSONObject json, IRecordService service) {
        int recordId = json.getInt("record_id");
        boolean isModified = service.returnBook(recordId);
        return simpleResponse(isModified,"还书失败",5);
    }
}
