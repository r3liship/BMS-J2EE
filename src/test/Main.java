package test;

import org.junit.Test;
import wang.relish.bms.dao.impl.BookDao;
import wang.relish.bms.dao.impl.UserDao;
import wang.relish.bms.entity.Book;
import wang.relish.bms.entity.User;
import wang.relish.bms.servlet.LoginServlet;

import javax.servlet.ServletException;
import java.util.ArrayList;

/**
 * 测试
 * Created by Relish on 2016/6/28.
 */
public class Main {

    /**
     * 连接数据库
     */
    @Test
    public void conn() {
        UserDao dao = new UserDao();
        User u = dao.findById(1);
        System.out.println(u.getName());
    }


    @Test//Lambda Expression
    public void test() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.forEach(System.out::println);
    }

    @Test
    public void addBook() {
        Book b = new Book();
        b.setName("JAVA2");
        b.setInventory(2);
        BookDao dao = new BookDao();
        System.out.println(dao.addBook(b));
    }

    @Test
    public void loginServlet() throws ServletException {
        LoginServlet servlet = new LoginServlet();
        System.out.println(servlet.getBeanName());
    }

}
