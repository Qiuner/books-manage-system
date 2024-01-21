package controller;

import bean.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/DeleteBook")

public class DeleteBookController extends HttpServlet {
    private final BookServiceImpl bookService = new BookServiceImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        /* 解析json格式数据 */
        String jsonString = getJsonString(req);
        System.out.println("接收到的JSON数据：" + jsonString);
        Map<String, Object> jsonMap = objectMapper.readValue(jsonString, new TypeReference<>() {});
        String bookId = (String) jsonMap.get("bookId");
        System.out.println("解析后的图书编号：" + bookId);

        /* 进行删除数据 */
        Boolean judge= bookService.deleteBookById(bookId);
        System.out.println("删除"+judge);

        /*
         * 再次更新数据,此处存在问题，要手动刷新才能显示删除后的数据，我也不知道为什么，初步推测可能是作用域中的数据没有删除干净
         *  */
        List<Book> bookList= bookService.queryAllBook();
        System.out.println("现在是删除按钮在"+bookList);
        HttpSession session = req.getSession();
        session.setAttribute("AllBooks",bookList);
        req.getRequestDispatcher("index.jsp").forward(req,resp);

    }
    private String getJsonString(HttpServletRequest request) throws IOException {
        // 从请求中读取JSON数据
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
