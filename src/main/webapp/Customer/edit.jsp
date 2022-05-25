<%--
  Created by IntelliJ IDEA.
  User: HELLO
  Date: 23/05/2022
  Time: 9:04 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="number" name="id" value="${dsCanSua.id}">
    <input type="text" name="name" value="${dsCanSua.name}">
    <input type="number" name="age" value="${dsCanSua.age}">
    <button>sửa</button>
</form>
</body>
</html>
