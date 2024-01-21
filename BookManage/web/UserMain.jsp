<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理系统主页</title>
<%--    <link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">主页 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <c:if test="${user!=null}">
                        <p class="nav-link"> 欢迎您,用户${user.name}</p>
                    </c:if>
            </ul>
            <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/QueryBook" method="post">
                <input class="form-control mr-sm-2" type="search" placeholder="搜索图书" aria-label="Search" name="queryBook">
            </form>
            <a href="${pageContext.request.contextPath}/LoginOut" class="btn btn-outline-success my-2 my-sm-0">退出登录</a>
        </div>
    </nav>
</div>
<br>
<a class="btn  btn-outline-success" href="${pageContext.request.contextPath}/UserMain" role="button">刷新藏书</a>
<table class="table">
    <thead>
    <tr>
        <th>图书编号</th>
        <th>书名</th>
        <th>出版社</th>
        <th>图书位置</th>
        <th>借阅状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="books" items="${AllBooks}">
        <tr data-book-id="${books.bookId}" data-book-name="${books.bookName}" data-book-publisher="${books.publisher}" data-book-location="${books.location}">
            <td>${books.bookId}</td>
            <td>${books.bookName}</td>
            <td>${books.publisher}</td>
            <td>${books.location}</td>
            <c:choose>
                <c:when test="${books.status == 1}">
                    <td id="status${books.bookId}">已借出</td>
                </c:when>
                <c:otherwise>
                    <td id="status${books.bookId}">未被借阅</td>
                </c:otherwise>
            </c:choose>
                <td>
                    <button class="btn btn-outline-Success delete-book-btn" role="button" onclick="borrowBook(this)">借阅图书</button>
                    <button class="btn btn-outline-Warning delete-book-btn" role="button" onclick="returnBook(this)">归还图书</button>
                </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script src="js/axios.min.js"></script>
<script src="js/axios.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/jQuery.js"></script>
<script>
    function borrowBook(button) {
        var row = $(button).closest('tr');
        var bookId = row.data('book-id');

        console.log('你点击的图书id是:', bookId);
        var dataToSend = {
            bookId: bookId
        };

        axios.post('/QueryAndAddBorrowRecord', dataToSend)
            .then(function (response) {
                console.log('请求成功:', response);
                if (response.status === 200 && response.data === '借阅记录表和图书表操作成功') {
                    // 更新对应行的状态列内容
                    var statusColumn = $('#status' + bookId);
                    statusColumn.text('已借出');
                }
            })
            .catch(function (error) {
                console.error('请求失败:', error);
                if (error.response.status === 400 && error.response.data === '图书已被借阅') {
                    alert('图书已被借阅');
                } else {
                    alert('请求失败: ' + error.message);
                }
            });
    }

    function returnBook(button) {
        var row = $(button).closest('tr');
        var bookId = row.data('book-id');
        console.log('你点击的图书id是:', bookId);
        var dataToSend = {
            bookId: bookId
        };

        axios.post('/DeleteBorrowRecord', dataToSend)
            .then(function (response) {
                console.log('请求成功:', response);
                if (response.status === 200 && response.data === '归还图书成功') {
                    // 更新对应行的状态列内容
                    var statusColumn = $('#status' + bookId);
                    statusColumn.text('未被借阅');
                }
            })
            .catch(function (error) {
                console.error('请求失败:', error);
                if (error.response.status === 400 && error.response.data === '该图书未被借阅') {
                    alert('该图书未被借阅');
                } else {
                    alert('请求失败: ' + error.message);
                }
            });

    }
</script>
</html>
