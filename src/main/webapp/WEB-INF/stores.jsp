<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="https" uri="http://www.springframework.org/tags/form" %>
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
    <title>Create store</title>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>



<body>

<form:form action="/createStore" modelAttribute="store"  method="post">

    <%--<form:errors path="*"  cssClass="errorblock" element="div" />--%>
    <table>
        <tr>
            <td>Enter title:</td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Enter address:</td>
            <td><form:input path="city" /></td>
            <td><form:errors path="city" cssClass="error"/></td>
        </tr>
        <tr>

            <button type="submit">Create</button>

        </tr>
    </table>

</form:form>
<c:if test="${not empty message}" >${message}</c:if>

<form>
    <input id="filter" data-type="search">
</form>

<table data-role="table" data-column-btn-theme="b" data-mode="columntoggle" data-filter="true" data-input="#filter" class="ui-responsive table-stroke">

    <thead>
       <tr>
                 <th data-priority="1">ID</th>
                 <th data-priority="2">Name shop</th>
                 <th data-priority="3">Address</th>
                <th data-priority = "4">Update</th>
                <th data-priority = "5">Remove</th>
               </tr>
    </thead>
    <c:forEach items="${storesList}" var="store">
    <tbody>
    <tr>
        <td style="background: aqua">${store.id}</td>
        <td>${store.name}</td>
        <td>${store.city}</td>
        <td><form:form action="/update/${store.id}" method="get">
            <a href="/update/${store.id}"><button type="submit">Update</button></a>
        </form:form></td>
        <td>
            <a href="/deleteById/${store.id}" ><button >Delete</button></a></td>
    </tr>

    </tbody>
    </c:forEach>
</table>




</body>
</html>
