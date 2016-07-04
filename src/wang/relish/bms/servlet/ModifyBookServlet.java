package wang.relish.bms.servlet;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.entity.Book;
import wang.relish.bms.service.IBookService;

/**
 * 修改书籍信息
 * Created by Relish on 2016/7/2.
 */
@Controller
@Scope(value = "prototype")
public class ModifyBookServlet extends BaseServlet<IBookService> {
    @Override
    protected JSONObject response(JSONObject json, IBookService service) {
        Book book = new Book();
        book.setId(json.getInt("id"));
        book.setName(json.getString("name"));
        book.setInventory(json.getInt("inventory"));
        boolean isModified = false;
        if (book.getName() == null) {
            isModified = service.modifyBook(book);
        }
        return simpleResponse(isModified, "修改失败", 4);
    }
}
