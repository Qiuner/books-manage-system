package bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BorrowRecord {
    private int recordId;
    private int userId;
    private int bookId;
    private LocalDateTime borrowTime;

    public BorrowRecord(int recordId, int userId, int bookId, LocalDateTime borrowTime) {
        this.recordId = recordId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowTime = borrowTime;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(LocalDateTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", borrowTime=" + borrowTime +
                '}';
    }
}
