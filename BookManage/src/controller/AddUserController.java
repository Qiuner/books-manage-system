package controller;

import bean.Book;
import bean.User;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/AddUser")

public class AddUserController extends HttpServlet {
    /* 业务层对象，通过这个对象来获得数据 */
    BookServiceImpl bookService=new BookServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        /* 准备数据 */
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Integer age = Integer.parseInt(req.getParameter("age"));
        Integer gender =  Integer.parseInt(req.getParameter("gender"));
        User user=new User(username,password,age,gender);
        System.out.println("获取到的用户数据为"+user);
        /* 进行添加 */
        Boolean judge=userService.addUser(user);
        /* 书本数据查询操作 */
        List<Book> bookList= bookService.queryAllBook();
        System.out.println(bookList);
        req.setAttribute("AllBooks",bookList);
        if (judge){
            resp.getWriter().write("<script>if(confirm('注册成功，请点击确定继续操作')){window.location.href='/index.jsp';}</script>");
        }else {
            resp.getWriter().write("<script>if(confirm('注册失败，请点击确定继续操作')){window.location.href='/index.jsp';}</script>");
        }
    }
}
