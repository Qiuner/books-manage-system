<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>用户</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<form action="${pageContext.request.contextPath}/AddUser" method="post">
  <div class="form-group">
    <label for="username">用户名</label>
    <input type="text" class="form-control" id="username" name="username" aria-describedby="usernameHelp">
    <small id="usernameHelp" class="form-text text-muted">请输入您的用户名</small>
  </div>
  <div class="form-group">
    <label for="password">密码</label>
    <input type="password" class="form-control" id="password" name="password">
  </div>
  <div class="form-group">
    <label for="age">年龄</label>
    <input type="number" class="form-control" id="age" name="age">
  </div>
  <div class="form-group">
    <label for="gender">性别</label>
    <select class="form-control" id="gender" name="gender">
      <option value="0">男性</option>
      <option value="1">女性</option>
    </select>
  </div>
  <button class="btn btn-outline-success my-2 my-sm-0" type="submit">注册用户</button>
  <a href="index.jsp" class="btn btn-outline-success my-2 my-sm-0">取消</a>
  <p class="text-muted" style="opacity: 0.7">已有账号? 去 <a href="${pageContext.request.contextPath}Login.jsp">登录</a></p>
</form>
</body>
</html>
