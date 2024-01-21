package service;

import bean.Book;

import java.sql.Connection;
import java.util.List;

public interface IBookService {

    /* 查询全部数据 */
    List<Book> queryAllBook();


    Boolean deleteBookById(String BookId);

    Boolean upBook(Book book);

    Boolean addBook(Book book);

    List<Book>  queryBook(String searchQuery);

    Boolean  updateBookStatus(Connection connection, Integer bookId, int newStatus);
}
