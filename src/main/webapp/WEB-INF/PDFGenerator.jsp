<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="https" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/js/materialize.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/css/materialize.min.css">


    <script src="<c:url value="/resources/js/main.js" />"></script>
    <script src="<c:url value="/resources/js/PDFGenerator.js" />"></script>
    <style>
        <%@include file="../resources/css/toast.css" %>
        <%@include file="../resources/css/main.css" %>
        <%@include file="../resources/css/PDFGenerator.css" %>
    </style>


    <title>Stores</title>

</head>
<body>
<sec:authentication var="principal" property="principal"/>

<%@include file="nav.jsp" %>

<section class="hero">
    <div class="hero-body" style="padding: 10px">
        <div class="container">
            <h2 class="title">
                Pdf generator page
            </h2>
            <h2 class="subtitle">
            </h2>
            <div class="containerPB">
                <ul class="progressbar">
                    <li id="workplace" class="active">Workplace</li>
                    <li id="time">Time</li>
                    <li id="download">Download</li>
                </ul>
            </div>
        </div>
    </div>

    <div class="tile is-ancestor">

        <div class="tile is-parent">
            <div class="tile is-parent is-3">
            </div>

            <div class="tile is-parent is-6">
                <article id="workplaceContent" class="tile is-child box has-text-centered ">
                    <form:form method="post" action="/generatePDF/download/PDFName.pdf">
                    <span>Select workplace and user</span>

                    <div class="field">
                        <div class="control">
                            <div class="select is-medium">
                                <select id="selectStore" name="storeId">
                                    <option value="0">All</option>
                                    <c:forEach items="${stores}" var="store">
                                        <option value="${store.id} ">${store.name} - ${store.city} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="field">
                        <div class="control">
                            <div class="select is-medium">
                                <select id="selectUser" name="userId">
                                    <option value="0">All</option>
                                    <c:forEach items="${users}" var="user">
                                        <option value="${user.id} ">${user.firstName} - ${user.email} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <a class="button is-primary" onclick="changeContent1()"> Next</a>
                </article>

                <article id="timeContent" class="tile is-child box has-text-centered">
                    <span>Select time period: </span>
                    <div class="field">
                        <p class="control has-icons-left">
                            <input id="dateFrom" type="text" placeholder="Select date from" class="datepicker"
                                   name="dateFrom" required/>
                            <span class="icon is-small is-left">
                                    <%--<i class="far fa-building"></i>--%>
                            </span>
                        </p>
                    </div>

                    <div class="field">
                        <p class="control has-icons-left">
                            <input id="dateTo" type="text" placeholder="Select date to" class="datepicker" name="dateTo"
                                   required/>
                            <span class="icon is-small is-left">
                                    <%--<i class="far fa-building"></i>--%>
                            </span>
                        </p>
                    </div>

                    <a class="button" onclick="changeContent2()"> Next</a>
                </article>

                <article id="downloadContent" class="tile is-child box ">
                    Get your pdf

                    <button type="submit" class="button is-primary">Download</button>

                    </form:form>
                </article>

            </div>

        </div>
    </div>
</section>


<c:if test="${not empty message}">
    <div id="snackbar"><span>${message}</span></div>
    <script>
        showToast();
    </script>
</c:if>

</body>
</html>
