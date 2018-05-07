<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="<c:url value="/resources/js/store.js" />"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>
    <style>
        <%@include file="../resources/css/toast.css" %>
        <%@include file="../resources/css/store.css" %>
    </style>


    <title>Store</title>
</head>
<body>

<%@include file="nav.jsp" %>

<div class="tabs is-medium  is-centered" id="tabs">
    <ul>
        <li data-tab="1" class="is-active"><a>Info</a></li>
        <li data-tab="2"><a>Job types</a></li>
        <li data-tab="3"><a>Update</a></li>
    </ul>
</div>

<div id="tab-content">
    <div class="is-active" data-content="1">
        <h1>Information about store</h1>

        <p>${store.id}</p>
        <p>${store.name}</p>
        <p>${store.city}</p>

        <form:form data-ajax="false" action="/stores/delete/${store.id}" method="get">
            <button type="submit">Remove</button>

        </form:form>
    </div>
    <div data-content="2">
        <c:forEach items="${jobTypes}" var="jobType">  
            <li>

                <h2>${jobType.type} in ${jobType.store.city}</h2>
                <p>${jobType.pricePerHour}</p>
                <form:form data-ajax="false" action="/jobtype/remove/${jobType.id}" method="get">
                    <button type="submit">Remove</button>
                </form:form>

                    
            </li>
        </c:forEach>
    </div>
    <div data-content="3">
        <h1>Update store</h1>

        <form:form data-ajax="false" action="/stores/update" method="post">
            <input type="hidden" value="${id}" name="id">
            <input type="text" name="name" value="${store.name}">
            <input type="text" name="city" value="${store.city}">

            <button type="submit">Update</button>
        </form:form>
    </div>

</div>


</div>

</body>
</html>
