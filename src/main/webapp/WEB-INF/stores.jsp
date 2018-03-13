<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 06.03.2018
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta name="viewport" content="width = device-width, initial-scale = 1">
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<head>
    <title>Job by id</title>
    <p>Hello</p>
</head>
<body>

<form:form action="/createStore" method="post">

    <p style="background-color: black"> Enter title:</p> <input type="text" name="name">
    <p style="background-color: black"> Enter address:</p> <input type="text" name="city">

    <button type="submit">Create</button>

</form:form>

<c:forEach items="${storesList}" var="store">
    <p>${store.id}</p>
    <p>${store.name}</p>
    <p>${store.city}</p>

</c:forEach>


</body>
</html>
