<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

    <style>
        <%@include file="../static/css/toast.css"%>
        <%@include file="../static/css/store.css"%>
    </style>

    <title>Store</title>
</head>
<body>

<div>

    <h2>Select action</h2>

    <div data-role="tabs">
            
        <ul data-role="listview" data-inset="true" class="tablist-left">
                  
            <li><a href="#info" data-ajax="false">Info</a></li>
                  
            <li><a href="#jobTypes" data-ajax="false">Job types</a></li>
                  
            <li><a href="#update" data-ajax="false">Update</a></li>
                
        </ul>

            
        <div id="info" class="ui-body-d tablist-content">

            <h1>Information about store</h1>

            <p>${store.id}</p>
            <p>${store.name}</p>
            <p>${store.city}</p>
              
        </div>
            
        <ul id="jobTypes" class="tablist-content" data-role="listview" data-inset="true">
                    
            <ul data-role="listview" data-split-icon="delete" data-split-theme="a" data-inset="true">
                  
                <c:forEach items="${jobTypes}" var="jobType">  
                    <li>

                        <h2>${jobType.type} in ${jobType.store.city}</h2>
                        <p>${jobType.pricePerHour}</p>
                        <form:form data-ajax="false" action="/jobtype/remove/${jobType.id}" method="get">
                            <button type="submit">Remove</button>
                        </form:form>

                            
                    </li>
                </c:forEach>
            </ul>


                
        </ul>

        <div id="update" class="ui-body-d tablist-content">

            <h1>Update store</h1>

            <form:form data-ajax="false" action="/updateStore" method="post">
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
