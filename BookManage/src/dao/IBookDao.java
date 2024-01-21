package dao;

import bean.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IBookDao {
    /*
     *查询所有书籍
     */
    List<Book> queryAllBook();

    Boolean deleteBookById(String bookId);

    Boolean upBook(Book book);

    Boolean addBook(Book book);

    List<Book>  queryBook(String searchQuery);


    Boolean updateBookStatus(Connection connection, Integer bookId, int newStatus) throws SQLException;
}
