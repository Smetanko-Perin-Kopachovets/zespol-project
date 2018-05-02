<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="https" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

    <script src="<c:url value="/resources/js/store.js" />"></script>
    <style>
        <%@include file="../resources/css/toast.css"%>
    </style>

    <title>Stores</title>

</head>
<body>

<form:form data-ajax="false" action="/stores/create" modelAttribute="store" method="post">

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
       class="ui-responsive table-stroke">

    <thead>
       
    <tr>
        <th data-priority="1">ID</th>
        <th data-priority="2">Name shop</th>
        <th data-priority="3">Address</th>
        <th data-priority="4">More</th>
              
    </tr>
    </thead>

    <c:forEach items="${storeList}" var="store">
        <tbody>
        <tr>
            <td>${store.id}</td>
            <td>${store.name}</td>
            <td>${store.city}</td>

            <td>
                <form:form data-ajax="false" action="stores/get/${store.id}" method="get">
                    <button type="submit">More</button>
                </form:form>
            </td>
        </tr>

        </tbody>
    </c:forEach>
</table>

<c:if test="${not empty message}">
    <div id="snackbar"><span>${message}</span></div>
    <script>
        window.showToast();
    </script>
</c:if>

</body>
</html>
