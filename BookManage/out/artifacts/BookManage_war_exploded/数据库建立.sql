create database  bookManage;
use  bookManage;



# 管理员表
CREATE TABLE Admin (
                       adminId INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,  -- 添加密码字段
                       age INT NOT NULL,
                       gender TINYINT NOT NULL
);
INSERT INTO Admin (name, password, age, gender)
VALUES ('管理员一', '123', 18, 0),
       ('Qiu', '123', 19, 1);


# 用户表
CREATE TABLE User (
                      userId INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      password VARCHAR(100) NOT NULL,  -- 添加密码字段
                      age INT NOT NULL,
                      gender TINYINT NOT NULL
);
INSERT INTO User (name, password, age, gender)
VALUES ('张三', '123', 18, 0),
       ('李四', '123', 34, 1),
       ('王五', '123', 12, 1);


# 书本表
CREATE TABLE Book (
     bookId INT AUTO_INCREMENT PRIMARY KEY,
     bookName VARCHAR(100) NOT NULL,
     publisher VARCHAR(100) NOT NULL,
     location VARCHAR(100) NOT NULL,
     status TINYINT NOT NULL
);
INSERT INTO Book (bookName, publisher, location, status)
VALUES ('红楼梦', '人民文学出版社', '一楼二号', 0),
       ('Java从入门到精通', '机械工业出版社', '二楼一号', 1),
       ('三国演义', '人民教育出版社', '一楼三号', 1);
# 借阅记录表
CREATE TABLE BorrowRecord (
                              recordId INT AUTO_INCREMENT PRIMARY KEY,
                              userId INT,
                              bookId INT,
                              borrowTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (userId) REFERENCES User(userId),
                              FOREIGN KEY (bookId) REFERENCES Book(bookId)
);
