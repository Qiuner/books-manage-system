package controller;

import bean.Book;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/QueryBook")

public class QueryBookController extends HttpServlet {
    BookServiceImpl bookService=new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String searchQuery = req.getParameter("queryBook");
        List<Book> books =bookService.queryBook(searchQuery);

        System.out.println(books);

        // 更新图书数据
        List<Book> bookList = bookService.queryAllBook();
        System.out.println(bookList);
        HttpSession session = req.getSession();
        session.setAttribute("AllBooks", books);

        /* 根据不同页面发出的查询请求有不同的应对 */
        if (session.getAttribute("admin")!=null){
            resp.sendRedirect("index.jsp");
        }else if(session.getAttribute("user")!=null){
            resp.sendRedirect("UserMain.jsp");
        }
    }
}
