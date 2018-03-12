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
<head>
    <title>Job by id</title>
    <p>Hello</p>
</head>
<body>

<c:forEach items="${list1}" var="marketService">
<p>${marketService.id}</p>
<p>${marketService.name}</p>
<p>${marketService.city}</p>

</c:forEach>




</body>
</html>
