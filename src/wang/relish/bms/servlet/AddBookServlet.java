package wang.relish.bms.servlet;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.entity.Book;
import wang.relish.bms.service.IBookService;
import wang.relish.bms.util.TextUtils;


@Controller
@Scope(value = "prototype")
public class AddBookServlet extends BaseServlet<IBookService> {

    @Override
    public JSONObject response(JSONObject json, IBookService service) {
        String name = json.getString("name");
        String inventory = json.getString("inventory");
        Boolean isAdded = false;
        if (TextUtils.isNotEmpty(name) && TextUtils.isNotEmpty(inventory)) {
            name = name.trim();
            Book b = new Book();
            b.setName(name);
            b.setInventory(Integer.parseInt(inventory));
            isAdded = service.addBook(b);
        }
        return simpleResponse(isAdded);
    }
}
