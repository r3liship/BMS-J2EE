package wang.relish.bms.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.entity.BorrowRecord;
import wang.relish.bms.service.IRecordService;

import java.util.List;

/**
 * 获取所有借书记录
 * Created by Relish on 2016/7/2.
 */
@Controller
@Scope(value = "prototype")
public class ViewBorrowServlet extends BaseServlet<IRecordService> {
    @Override
    protected JSONObject response(JSONObject json, IRecordService service) {
        String sessionId = json.getString("sessionId");
        List<BorrowRecord> bRecords;
        JSONObject response = simpleResponse(sessionId != null);
        if (sessionId != null) {
            bRecords = service.getBorrowRecords();
            if (bRecords != null && bRecords.size() > 0) {
                JSONArray dataJson = new JSONArray();
                for (BorrowRecord br : bRecords) {
                    JSONObject brJson = new JSONObject();
                    brJson.element("record_id", br.getR().getId());
                    brJson.element("admin_id", br.getA().getId());
                    brJson.element("book_id", br.getB().getId());
                    brJson.element("user_id", br.getU().getId());
                    brJson.element("admin_name", br.getA().getName());
                    brJson.element("book_name", br.getB().getName());
                    brJson.element("user_name", br.getU().getName());
                    brJson.element("inventory", br.getB().getInventory());
                    brJson.element("borrow_time", br.getR().getBorrowTime());
                    dataJson.add(brJson);
                }
                response.elementOpt("data", dataJson);
            }
        }
        return response;
    }
}
