<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="https" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

    <style>
        <%@include file="../static/css/toast.css"%>

    </style>
    <title>Stores</title>

</head>
<body>

<form:form data-ajax="false" action="/createStore" modelAttribute="store" method="post">

    <table>
        <tr>
            <td>Enter title:</td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Enter address:</td>
            <td><form:input path="city"/></td>
            <td><form:errors path="city" cssClass="error"/></td>
        </tr>
        <tr>

            <button type="submit">Create</button>

        </tr>
    </table>

</form:form>

<form>
    <input id="filter" data-type="search">
</form>

<table data-role="table"
       data-column-btn-theme="b"
       data-mode="columntoggle"
       data-filter="true"
       data-input="#filter"
       class="ui-responsive table-stroke" >

    <thead>
       
    <tr>
        <th data-priority="1">ID</th>
        <th data-priority="2">Name shop</th>
        <th data-priority="3">Address</th>
        <th data-priority="4">Update</th>
        <th data-priority="5">Remove</th>
               
    </tr>
    </thead>

    <c:forEach items="${storeList}" var="store">
        <tbody>
        <tr>
            <td>${store.id}</td>
            <td>${store.name}</td>
            <td>${store.city}</td>
            <td>
                <form:form data-ajax="false" action="/update/${store.id}" method="get">
                    <button type="submit">Update</button>
                </form:form>
            </td>
            <td>
                <form:form data-ajax="false" action="/delete/${store.id}" method="post">
                    <button type="submit" class="ui-btn ui-shadow ui-corner-all ui-icon-delete ui-btn-icon-notext">Delete</button>
                </form:form>
            </td>
        </tr>

        </tbody>
    </c:forEach>
</table>

<c:if test="${not empty message}">

    <div id="snackbar"><span>${message}</span></div>

    <script>
        console.log("Get method");
        var toast = document.getElementById("snackbar");
        toast.className = "show";
        setTimeout(function () {
            toast.className = toast.className.replace("show", "");
        }, 3000);
    </script>

</c:if>

</body>
</html>
