package service;

import bean.BorrowRecord;

public interface IBorrowRecordService {

    Boolean queryBorrowRecord(Integer bookId);

    BorrowRecord addBorrowRecord(Integer userId, Integer bookId);

    Boolean deleteBorrowRecord(Integer userId, Integer bookId);
}