<%--
  Created by IntelliJ IDEA.
  User: HELLO
  Date: 22/05/2022
  Time: 4:43 CH
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sách  </h1>
<a href="/Customers?action=create">add</a>
<c:forEach items="${danhSach}" var="customer">
    <h3> ${customer.id},${customer.name},${customer.age},
        <a href="/Customers?action=edit&id=${customer.id}">edit</a>,
        <a href="/Customers?action=delete&id=${customer.id}">delete</a></h3>
</c:forEach>
</body>
</html>
