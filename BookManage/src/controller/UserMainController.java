package controller;

import bean.Book;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/UserMain")

public class UserMainController extends HttpServlet {
    BookServiceImpl bookService=new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        List<Book> bookList= bookService.queryAllBook();
        System.out.println(bookList);
        /* 将数据放到请求体中 */
        req.setAttribute("AllBooks",bookList);
        resp.sendRedirect("UserMain.jsp");
    }
}
