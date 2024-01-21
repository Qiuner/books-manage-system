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

@WebServlet("/UpBook")

public class UpBookController extends HttpServlet {
    BookServiceImpl bookService=new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 获取表单提交的数据
        String bookId = req.getParameter("bookId");
        String bookName = req.getParameter("bookName");
        String publisher = req.getParameter("publisher");
        String location = req.getParameter("location");
        Book book=new Book();

        book.setBookId(bookId);
        book.setBookName(bookName);
        book.setPublisher(publisher);
        book.setLocation(location);


        bookService.upBook(book);

        // 更新图书数据
        List<Book> bookList = bookService.queryAllBook();
        System.out.println(bookList);
        HttpSession session = req.getSession();
        session.setAttribute("AllBooks", bookList);
        resp.sendRedirect("index.jsp");
    }
}
