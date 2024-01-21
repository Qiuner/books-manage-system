package dao.impl;

import bean.BorrowRecord;
import dao.IBorrowRecordDao;
import service.impl.BookServiceImpl;
import tool.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;

public class BorrowRecordImpl implements IBorrowRecordDao {

    private final BookServiceImpl bookService=new BookServiceImpl();

    public Boolean queryBorrowRecord(Integer bookId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT * FROM BorrowRecord WHERE bookId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();
            // 查询成功
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }

        return false;
    }

    public BorrowRecord addBorrowRecord(Integer userId, Integer bookId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BorrowRecord insertedRecord = null;  // 用于保存插入的记录

        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO BorrowRecord (userId, bookId) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // 插入成功，获取生成的主键
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int generatedRecordId = resultSet.getInt(1);
                    LocalDateTime currentTimestamp = LocalDateTime.now();
                    insertedRecord = new BorrowRecord(generatedRecordId, userId, bookId, currentTimestamp);
                }
                Boolean judge=bookService.updateBookStatus(connection,bookId,1);
                if (judge){
                    // 提交事务
                    connection.commit();
                }else {
                    System.out.println("图书借阅状态修改失败,准备回滚事物");
                    rollbackTransaction(connection);
                }
            } else {
                System.out.println("借阅记录插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 发生异常，回滚事务
            rollbackTransaction(connection);
        } finally {
            setAutoCommit(connection, true);
            DBUtils.close(resultSet, preparedStatement, connection);
        }
        return insertedRecord;  // 返回插入的记录
    }

    @Override
    public Boolean deleteBorrowRecord(Integer userId, Integer bookId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false);
            // 删除借阅记录的SQL语句
            String deleteQuery = "DELETE FROM BorrowRecord WHERE userId = ? AND bookId = ?";
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            // 执行删除操作
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // 更新图书借阅状态
                Boolean judge = bookService.updateBookStatus(connection, bookId, 0); // 0 表示未借出
                if (judge) {
                    // 提交事务
                    connection.commit();
                } else {
                    System.out.println("图书借阅状态修改失败，准备回滚事务");
                    rollbackTransaction(connection);
                }
            } else {
                System.out.println("借阅记录删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 发生异常，回滚事务
            rollbackTransaction(connection);
        } finally {
            setAutoCommit(connection, true);
            DBUtils.close(resultSet, preparedStatement, connection);
        }
        return true;
    }

    // 回滚事务的方法
    private void rollbackTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    // 设置自动提交状态的方法
    private void setAutoCommit(Connection connection, boolean autoCommit) {
        if (connection != null) {
            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
