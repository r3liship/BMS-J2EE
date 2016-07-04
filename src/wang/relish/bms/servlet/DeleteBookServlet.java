package wang.relish.bms.servlet;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.service.IBookService;

/**
 * Created by Relish on 2016/7/2.
 */
@Controller
@Scope(value = "prototype")
public class DeleteBookServlet extends BaseServlet<IBookService> {

    @Override
    protected JSONObject response(JSONObject json, IBookService service) {
        Integer bookId = json.getInt("book_id");
        boolean isAdded = service.deleteBook(bookId);
        return simpleResponse(isAdded);
    }

}
