<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <%@include file="../resources/css/store.css" %>
        <%@include file="../resources/css/main.css" %>
    </style>


    <title>Store</title>
</head>
<body>
<sec:authentication var="principal" property="principal"/>

<%@include file="nav.jsp" %>

<div class="tabs is-medium is-centered" id="tabs">
    <ul>
        <li data-tab="1" id="changeContent1" onclick="changeContent1()" class="is-active"><a>Information</a></li>
        <li data-tab="2" id="changeContent2" onclick="changeContent2()"><a>Job types</a></li>
        <sec:authorize access="hasRole('ROLE_MANAGER')">
            <li data-tab="3" id="changeContent3" onclick="changeContent3()"><a>Edit</a></li>
        </sec:authorize>
    </ul>
</div>

<div data-content="1" id="content1">

    <div class="card " style="margin-left: auto; margin-right: auto; width: 350px;">
        <header class="card-header">
            <p class="card-header-title">
                Information about store
            </p>
            <a href="#" class="card-header-icon" aria-label="more options">
                <span class="icon">
                    <i class="fas fa-angle-down" aria-hidden="true"></i>
                </span>
            </a>
        </header>
        <div class="card-content">
            <div class="content">
                <p>${store.id}</p>
                <p>${store.name}</p>
                <p>${store.city}</p>
                <p>Contains - ${jobTypesCount} job types</p>
            </div>
        </div>
        <footer class="card-footer">
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a href="#" class="card-footer-item">Edit</a>
                <a href="/stores/delete/${store.id}" class="card-footer-item">Delete</a>
            </sec:authorize>
        </footer>
    </div>
</div>

<div data-content="2" id="content2">
    <div class="tile is-ancestor">
        <div class="tile is-parent">
            <div class="tile is-parent is-8">
                <article class="tile is-child box ">

                    <table class="table is-fullwidth is-striped is-hoverable">
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Salary per hour</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${jobTypes}" var="jobType">
                            <tr>
                                <td>${jobType.type}</td>
                                <td>${jobType.pricePerHour}</td>
                                <td>
                                    <a href="#">
                                        <i class="fas fa-wrench"></i>
                                    </a>
                                </td>
                                <td>
                                    <a href="/jobtype/remove/${jobType.id}">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
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
                        <form:form method="post" action="/jobtype/create">

                            <p class="subtitle">Add job type </p>

                            <input value="${store.id}" name="storeId" type="hidden">

                            <div class="field">
                                <p class="control has-icons-left ">
                                    <input placeholder="Title" type="text" class="input" name="type">
                                    <span class="icon is-small is-left">
							<i class="fas fa-book"></i></span>
                                </p>
                            </div>

                            <div class="field">
                                <p class="control has-icons-left">
                                    <input placeholder="Price per hour" type="number" class="input" name="pricePerHour">
                                    <span class="icon is-small is-left">
							<i class="fas fa-book"></i>
						</span>
                                </p>
                            </div>

                            <div class="box has-text-centered">
                                <button class="button is-primary" type="submit">Create</button>
                            </div>

                        </form:form>
                    </article>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>

<div data-content="3" id="content3">

    <div class="tile is-ancestor">

        <div class="tile is-parent is-4">
        </div>

        <div class="tile is-parent is-4">
            <article class="tile is-child box">

                <form:form method="post" action="/stores/update">
                    <p class="subtitle">Update store </p>

                    <input type="hidden" value="${store.id}" name="id">

                    <div class="field">
                        <p class="control has-icons-left has-icons-right">
                            <input type="text" class="input" name="name" value="${store.name}">
                            <span class="icon is-small is-left">
                        <i class="fas fa-envelope"></i>
                    </span>
                            <span class="icon is-small is-right">
                        <i class="fas fa-check"></i>
                    </span>
                        </p>
                    </div>

                    <div class="field">
                        <p class="control has-icons-left">
                            <input type="text" class="input" name="city" value="${store.city}">
                            <span class="icon is-small is-left">
                        <i class="fas fa-lock"></i>
                    </span>
                        </p>
                    </div>

                    <div class="box has-text-centered">
                        <button type="submit" class="button is-primary">
                            <span>Update</span>
                        </button>
                    </div>

                </form:form>

            </article>
        </div>
    </div>
</div>

<c:if test="${not empty message}">
    <div id="snackbar"><span>${message}</span></div>
    <script>
        showToast();
    </script>
</c:if>

</body>
</html>
