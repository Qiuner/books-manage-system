package controller;

import bean.Book;
import bean.BorrowRecord;
import bean.LoginJudge;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.impl.BookServiceImpl;
import service.impl.BorrowRecordServiceImpl;

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
@WebServlet("/QueryAndAddBorrowRecord")
public class AddBorrowRecordController extends HttpServlet {
    private BorrowRecordServiceImpl borrowRecordService=new BorrowRecordServiceImpl();
    private BookServiceImpl bookService=new BookServiceImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        /* 解析json格式数据 */
        String jsonString = getJsonString(req);
        System.out.println("接收到的JSON数据：" + jsonString);
        Map<String, Object> jsonMap = objectMapper.readValue(jsonString, new TypeReference<>() {});
        Integer bookId = (Integer) jsonMap.get("bookId");
        System.out.println("解析后的图书编号：" + bookId);

        /* 先进行查询有没有 */
        Boolean judge=borrowRecordService.queryBorrowRecord(bookId);
        HttpSession session = req.getSession();
        LoginJudge loginJudge =(LoginJudge) session.getAttribute("user");
        if (!judge){
            System.out.println("没有找到借阅记录,正在进行插入");
            BorrowRecord borrowRecord1=borrowRecordService.addBorrowRecord(loginJudge.getId(),bookId);
            System.out.println("借阅数据为"+borrowRecord1);
        }else {
            System.out.println("找到了借阅记录，准备提示");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // 设置响应状态为 400 Bad Request
            resp.getWriter().write("图书已被借阅");  // 将错误信息写回前端
            return;
        }
        /* 图书数据更新操作 */
        System.out.println("这是用户页的数据更新");
        List<Book> bookList= bookService.queryAllBook();
        System.out.println("已经完成了借阅记录表和图书表修改操作，目前图书数据是"+bookList);
        req.setAttribute("AllBooks",bookList);
        resp.setStatus(HttpServletResponse.SC_OK);  // 设置响应状态为 400 Bad Request
        resp.getWriter().write("借阅记录表和图书表操作成功");  // 将错误信息写回前端
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
