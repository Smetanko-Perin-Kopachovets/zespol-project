<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 06.03.2018
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta name = "viewport" content = "width = device-width, initial-scale = 1">
<link rel = "stylesheet" href = "https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src = "https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src = "https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<head>
    <title style="background-color: green"> Jobs</title>
</head>
<body>

<h1 style="background-color: green"> Add new job </h1>


<form:form action="/createJob" method="post">

    <p style="background-color: black"> Enter store:</p> <input type="text" name="store">
    <p style="background-color: black"> Enter title:</p> <input type="text" name="title">


    <button type="submit">Create</button>


</form:form>

<form:form action="/jobs/market" method="get">
    <button type="submit">Show</button>
</form:form>

<form:form action="/jobs/show" method="get">
    <button type="submit">Show all job</button>
</form:form>




<%--<c:forEach items="${list}" var="jobServise">--%>
    <%--<p>${jobServise.id}</p>--%>
    <%--<p>${jobServise.store}</p>--%>
    <%--<p>${jobServise.title}</p>--%>
<%--</c:forEach>--%>


</body>
</html>
