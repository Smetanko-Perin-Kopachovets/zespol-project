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

    <script src="<c:url value="/resources/js/store.js" />"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>
    <style>
        <%@include file="../resources/css/toast.css" %>
        <%@include file="../resources/css/main.css" %>
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
                Workplace page
            </h2>
            <h2 class="subtitle">
                Serwis tymczasowego zatrudnienia
                <br>
                Below available workplace in database
            </h2>
            <div class="divider"></div>
            <br>

            <div class="tile is-ancestor" id="contentAddWorkPlace">

                <div class="tile is-parent">
                    <div class="tile is-parent is-8">
                        <article class="tile is-child box ">

                            <table class="table is-fullwidth is-striped is-hoverable">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Address</th>
                                    <th>Details</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${storeList}" var="store">
                                    <tr>
                                        <td>${store.id}</td>
                                        <td>${store.name}</td>
                                        <td>${store.city}</td>
                                        <td>
                                            <a href="stores/get/${store.id}" class="button is-primary is-small">More</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>
                        </article>
                    </div>
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <div class="tile is-parent is-4">
                            <article class="tile is-child box">

                                <p class="subtitle">Form for adding new workplace </p>
                                <form:form action="/stores/create" modelAttribute="store" method="post">
                                    <div class="field">
                                        <p class="control has-icons-left has-icons-right">
                                            <form:input path="name" class="input" placeholder="Title"/>
                                            <span class="icon is-small is-left">
											<i class="fas fa-book"></i>
										</span>
                                        </p>
                                    </div>

                                    <div class="field">
                                        <p class="control has-icons-left">
                                            <form:input path="city" class="input" placeholder="City"/>
                                            <span class="icon is-small is-left">
											<i class="far fa-building"></i>
										</span>
                                        </p>
                                    </div>

                                    <div class="box has-text-centered">
                                        <button type="submit" class="button is-primary">
                                            <span>Create</span>
                                        </button>
                                    </div>

                                </form:form>

                            </article>
                        </div>
                    </sec:authorize>
                </div>
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
