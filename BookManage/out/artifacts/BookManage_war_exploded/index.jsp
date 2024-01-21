<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>图书管理系统主页</title>
<%--  本地样式突然不能用了--%>
<%--  <link href="css/bootstrap.min.css" rel="stylesheet">--%>
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

<c:if test="${admin!=null}">
      <p class="nav-link"> 欢迎您,管理员${admin.name}</p>
</c:if>
        </li>
      </ul>
      <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/QueryBook" method="post">
        <input class="form-control mr-sm-2" type="search" placeholder="搜索图书" aria-label="Search" name="queryBook">
      </form>
<c:if test="${admin==null}">
<a href="Login.jsp" class="btn btn-outline-success my-2 my-sm-0">登录</a>
        <div class="mx-2"></div>
        <a href="registerUser.jsp" class="btn btn-outline-success my-2 my-sm-0">注册</a>
</c:if>
<c:if test="${admin!=null}">
  <a href="${pageContext.request.contextPath}/LoginOut" class="btn btn-outline-success my-2 my-sm-0">退出登录</a>
</c:if>
    </div>
  </nav>
</div>
<br>
<%--防止虚拟目录--%>
<a class="btn  btn-outline-success" href="${pageContext.request.contextPath}/main" role="button">刷新藏书</a>

<c:if test="${admin!=null}">
  <a class="btn btn-outline-success" href="#" role="button" data-bs-toggle="modal" data-bs-target="#secondModal">
    新增图书
  </a>
  <div class="modal fade" id="secondModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel1">新增图书</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form action="${pageContext.request.contextPath}/AddBook" method="post">
            <div class="form-group">
              <label for="bookName1" class="col-form-label">书本名称:</label>
              <input type="text" class="form-control" id="bookName1" name="bookName">
            </div>
            <div class="form-group">
              <label for="publisher1" class="col-form-label">出版社:</label>
              <input type="text" class="form-control" id="publisher1" name="publisher">
            </div>
            <div class="form-group">
              <label for="location1" class="col-form-label">书本位置:</label>
              <input type="text" class="form-control" id="location1" name="location">
            </div>
            <input type="hidden" id="bookIdHidden1" name="bookId">
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
              <button type="submit" class="btn btn-primary">提交</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</c:if>
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
          <td>已借出</td>
        </c:when>
        <c:otherwise>
          <td>未被借阅</td>
        </c:otherwise>
      </c:choose>

        <c:if test="${admin!=null}">
        <td>
          <button class="btn btn-outline-danger delete-book-btn" role="button">删除图书</button>
          <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-book-id="${books.bookId}" onclick="fillModalForm(this)">
            修改图书
          </button>
        </td>
      </c:if>
    </tr>
  </c:forEach>
  </tbody>
</table>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">修改书本</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/UpBook" method="post">
          <div class="form-group">
            <label for="bookName" class="col-form-label">修改书本名称:</label>
            <input type="text" class="form-control" id="bookName" name="bookName">
          </div>
          <div class="form-group">
            <label for="publisher" class="col-form-label">修改出版社:</label>
            <input type="text" class="form-control" id="publisher" name="publisher">
          </div>
          <div class="form-group">
            <label for="location" class="col-form-label">修改书本位置:</label>
            <input type="text" class="form-control" id="location" name="location">
          </div>
          <input type="hidden" id="bookIdHidden" name="bookId">
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-primary">提交</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

</body>
<script src="js/axios.min.js"></script>
<script src="js/axios.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/jQuery.js"></script>
<script>
  document.addEventListener("DOMContentLoaded", function () {
    var deleteBookBtns = document.querySelectorAll('.delete-book-btn');

    // 删除按钮点击
    deleteBookBtns.forEach(function (btn) {
      btn.addEventListener('click', function (event) {
        event.preventDefault();
        // 获取按钮所在的<tr>元素的data-book-id属性值
        var trElement = btn.closest('tr');
        var bookId = trElement.getAttribute('data-book-id'); // 修改此行以正确获取data-book-id
        console.log(bookId);

        var dataToSend = {
          bookId: bookId,
        };

        axios.post('/DeleteBook', dataToSend)
                .then(function (response) {
                  console.log(response.data);
                  console.log("请求成功")
                  console.log(dataToSend)
                })
                .catch(function (error) {
                  console.error('请求失败啦:', error);
                });
      });
    });
  });


  function fillModalForm(button) {
    // 获取所点击按钮所在行的数据
    var row = $(button).closest('tr');
    var bookId = row.data('book-id');
    var bookName = row.data('book-name');
    var publisher = row.data('book-publisher');
    var location = row.data('book-location');

    // 设置模态框表单中相应字段的默认值
    $('#bookIdHidden').val(bookId);  // 设置隐藏字段的值
    $('#bookName').val(bookName);
    $('#publisher').val(publisher);
    $('#location').val(location);
  }
</script>
</html>
