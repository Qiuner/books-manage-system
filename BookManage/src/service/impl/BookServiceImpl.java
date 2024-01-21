package service.impl;

import bean.Book;
import dao.impl.BookDaoImpl;
import service.IBookService;

import java.sql.Connection;
import java.util.List;

public class BookServiceImpl implements IBookService {
    private BookDaoImpl bookDao =new BookDaoImpl();
    /* 查询全部书本 */
    @Override
    public List<Book> queryAllBook() {
        List<Book>  bookList= bookDao.queryAllBook();
        return bookList;
    }
    /* 删除一本书 */
    @Override
    public Boolean deleteBookById(String bookId) {
        return bookDao.deleteBookById(bookId);
    }
    /* 修改书本 */
    @Override
    public Boolean upBook(Book book) {
        return  bookDao.upBook(book);
    }
    /* 增加加书本 */
    @Override
    public Boolean addBook(Book book) {
        return bookDao.addBook(book);
    }
    /* 查询书本 */
    @Override
    public List<Book>  queryBook(String searchQuery) {
        List<Book>  book=bookDao.queryBook(searchQuery);
        return book;
    }
    /* 更新书本借阅状态 */
    @Override
    public Boolean updateBookStatus(Connection connection, Integer bookId, int newStatus) {
        return  bookDao.updateBookStatus(connection,bookId,newStatus);
    }
}
