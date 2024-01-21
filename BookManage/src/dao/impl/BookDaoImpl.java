package dao.impl;

import bean.Book;
import dao.IBookDao;
import tool.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookDao {
    /* 查询全部图书 */
    @Override
    public List<Book> queryAllBook() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Book> bookList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM book";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // 遍历查询结果集，将数据封装为 Book 对象并添加到列表中
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPublisher(rs.getString("publisher"));
                book.setLocation(rs.getString("location"));
                book.setStatus(rs.getInt("status"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            DBUtils.close(rs, stmt, conn);
        }
        return bookList;
    }
/* 删除图书 */
    @Override
    public Boolean deleteBookById(String bookId) {
        System.out.println("我是要删除图书的id"+bookId);
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM book WHERE bookId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookId);

            int affectedRows = stmt.executeUpdate();

            // 如果成功删除了至少一行记录，返回 true
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            DBUtils.close(stmt, conn);
        }

        // 如果发生异常或者没有删除任何记录，返回 false
        return false;
    }
/* 更新图书 */
    public Boolean upBook(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtils.getConnection();
            String sql = "UPDATE book SET bookName=?, publisher=?, location=? WHERE bookId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getPublisher());
            preparedStatement.setString(3, book.getLocation());
            preparedStatement.setString(4, book.getBookId());

            // 执行更新
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("数据更新成功");
                return true;
            } else {
                System.out.println("数据更新失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(preparedStatement, connection);
        }

        return false;
    }
/* 新增图书 */
    @Override
    public Boolean addBook(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtils.getConnection();
            String sql = "INSERT INTO book (bookName, publisher, location, status) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getPublisher());
            preparedStatement.setString(3, book.getLocation());
            preparedStatement.setInt(4, book.getStatus());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
    }
/* 根据字段查询图书 */
    public List<Book> queryBook(String searchQuery) {
        List<Book> books = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();

            String sql = "SELECT * FROM book WHERE bookName LIKE ? OR publisher LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchQuery + "%");
            preparedStatement.setString(2, "%" + searchQuery + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getString("bookId"));
                book.setBookName(resultSet.getString("bookName"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setLocation(resultSet.getString("location"));
                book.setStatus(resultSet.getInt("status"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }

        return books;
    }
/*该方法不独立连接数据库 */
    public Boolean updateBookStatus(Connection connection, Integer bookId, int newStatus)  {
        String updateQuery = "UPDATE Book SET status = ? WHERE bookId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, newStatus);
            preparedStatement.setInt(2, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("更新成功");
                return true;
            } else {
                System.out.println("更新失败");
                return false;
            }
        }catch (SQLException e){
            /* 出现插入异常后返回false */
            return false;
        }
    }
}
