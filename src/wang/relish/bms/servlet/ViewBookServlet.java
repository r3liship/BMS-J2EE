package wang.relish.bms.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import wang.relish.bms.base.BaseServlet;
import wang.relish.bms.entity.Book;
import wang.relish.bms.service.IBookService;

import java.util.List;

/**
 * 查看书籍
 * Created by Relish on 2016/7/2.
 */
public class ViewBookServlet extends BaseServlet<IBookService> {

    @Override
    protected JSONObject response(JSONObject json, IBookService service) {
        String sessionId = json.getString("sessionId");
        List<Book> books;
        JSONObject response = simpleResponse(sessionId != null);
        if (sessionId != null) {
            books = service.getAllBooks();
            if (books != null && books.size() > 0) {
                JSONArray dataJson = new JSONArray();
                for (Book book : books) {
                    JSONObject bookJson = new JSONObject();
                    bookJson.element("id", book.getId());
                    bookJson.element("name", book.getName());
                    bookJson.element("inventory", book.getInventory());
                    dataJson.add(bookJson);
                }
                response.elementOpt("data", dataJson);
            }
        }
        return response;
    }
}
