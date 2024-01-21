package controller;

import bean.LoginJudge;
import bean.Book;
import service.impl.AdminServiceImpl;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet("/login")

public class LoginController extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    AdminServiceImpl adminService = new AdminServiceImpl();
    BookServiceImpl bookService =new BookServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("name");
        String password = req.getParameter("password");
        String loginType = req.getParameter("loginType");


        System.out.println("已接收到请求");
        /* 封装一个类，这个类满足接下来的操作所用数据*/
        LoginJudge loginJudge=new LoginJudge(null,null,false);

        if (loginType.equals("admin")) {
            loginJudge = adminService. loginVerification(username, password);
            System.out.println("正在查询管理员");
        }
        if (loginType.equals("user")) {
            loginJudge  = userService.loginVerification(username, password);
        }


        if (loginJudge.getJudge()&&loginType.equals("admin")) {
            /* 添加管理员特有功能的按钮 */
            System.out.println("已查询到管理员信息");
            HttpSession session = req.getSession();

            session.setAttribute("temp",true);

            // 将放回对象存储 Session 中
            session.setAttribute("admin", loginJudge);
            session.setMaxInactiveInterval(3600);
            System.out.println("登录成功");
            /* 将图书数据存储起来，用来渲染页面 */
            List<Book> AllBooks= bookService.queryAllBook();
            session.setAttribute("AllBooks", AllBooks);
            resp.sendRedirect("index.jsp");

        }
        else if (loginJudge.getJudge()){
            /* 用户登录成功后 */
            HttpSession session = req.getSession();
            session.setAttribute("user", loginJudge);
            session.setMaxInactiveInterval(3600);
            System.out.println("用户id是"+loginJudge.getId());
            List<Book> AllBooks= bookService.queryAllBook();
            session.setAttribute("AllBooks", AllBooks);
            resp.sendRedirect("UserMain.jsp");
        }
        else {
            System.out.println("登录失败");
                resp.getWriter().write("<script>alert('登录失败');</script>");
            resp.sendRedirect("index.jsp");
        }
    }
}
