<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

    <title>Job type</title>
</head>
<body>

<form:form action="/createJobType" method="post">
    <p>Enter jobtype:</p><input name="type" id="type">
    <p>Enter price:</p><input name="pricePerHour" type="number" id="pricePerHour">

    <select name="id-store" id="id-store">
        <c:forEach items="${storeList}" var="store">
            <option value="${store.id}">
                    ${store.name}
                    ${store.city}
            </option>
        </c:forEach>
    </select>

    <button type="submit">Create</button>


</form:form>

<form>
    <input id="filter" data-type="search">
</form>

<table data-role="table"
       data-column-btn-theme="b"
       data-filter="true"
       data-input="#filter"
       class="ui-responsive table-stroke">


    <thead>   
    <tr>
        <th data-priority="1">ID</th>
                 
        <th data-priority="2">JobType</th>
                 
        <th data-priority="3">Price</th>
               
        <th data-priority="4">Shop</th>
           
    </tr>
    </thead>

    <c:forEach items="${jobTypeList}" var="jobType">

        <tbody>
        <tr>
            <td>${jobType.id}</td>
            <td>${jobType.type}</td>
            <td>${jobType.pricePerHour}</td>
            <td>${jobType.store.name}, ${jobType.store.city}</td>
        </tr>
        </tbody>

    </c:forEach>
</table>
</body>
</html>
