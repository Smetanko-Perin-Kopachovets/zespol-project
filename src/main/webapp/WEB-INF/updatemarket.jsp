<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 12.03.2018
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form:form action="/updateMarket" method="post">

    <input type="hidden" value="${id}" name="id">
    <input type="text" name="name">
    <input type="text" name="city">

    <button type="submit">Update</button>
</form:form>
</body>
</html>
