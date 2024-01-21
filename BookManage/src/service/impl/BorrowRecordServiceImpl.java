package service.impl;

import bean.BorrowRecord;
import dao.impl.BorrowRecordImpl;
import service.IBorrowRecordService;

import java.time.LocalDateTime;

public class BorrowRecordServiceImpl implements IBorrowRecordService {
    BorrowRecordImpl borrowRecord=new BorrowRecordImpl();
    @Override
    public Boolean queryBorrowRecord(Integer bookId) {
        return borrowRecord.queryBorrowRecord(bookId);
    }

    @Override
    public BorrowRecord addBorrowRecord(Integer userId, Integer bookId) {
        BorrowRecord borrowRecord1=borrowRecord.addBorrowRecord(userId,bookId);
        return borrowRecord1;
    }

    @Override
    public Boolean deleteBorrowRecord(Integer userId, Integer bookId) {
        return borrowRecord.deleteBorrowRecord(userId,bookId);
    }
}
