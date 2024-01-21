<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
<%--    <link rel="stylesheet" href="css/bootstrap.min.css" >--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="form-group">
        <label for="name">用户名</label>
        <input type="text" class="form-control" id="name" name="name" aria-describedby="usernameHelp">
        <small id="usernameHelp" class="form-text text-muted">请输入您的用户名</small>
    </div>
    <div class="form-group">
        <label for="password">密码</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
    <div class="form-group">
        <label for="loginType">登录类型</label>
        <select class="form-control" id="loginType" name="loginType">
            <option value="admin">管理员</option>
            <option value="user">用户</option>
        </select>
    </div>
    <button type="submit" class="btn btn-outline-success my-2 my-sm-0">登录</button>
    <a href="index.jsp" class="btn btn-outline-success my-2 my-sm-0">取消</a>
    <p class="text-muted" style="opacity: 0.7;">没有账号? 去 <a href="${pageContext.request.contextPath}registerUser.jsp">注册</a></p>
</form>

</body>
</html>

