package dao;

import bean.BorrowRecord;

import java.time.LocalDateTime;

public interface IBorrowRecordDao {
    Boolean queryBorrowRecord(Integer bookId);

    BorrowRecord addBorrowRecord(Integer userId, Integer bookId );

    Boolean deleteBorrowRecord(Integer userId, Integer bookId);
}
