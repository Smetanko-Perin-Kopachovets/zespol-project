<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <script src="<c:url value="/resources/js/job.js" />"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>

    <style>
        <%@include file="../resources/css/toast.css" %>
        <%@include file="../resources/css/main.css" %>
    </style>

    <title>Job</title>

</head>
<body>
<sec:authentication var="principal" property="principal"/>

<%@include file="nav.jsp" %>

<section class="hero">
    <div class="hero-body " style="padding-right: 15px; padding-top: 0px">
        <div class="container" style="margin: 0px; width: auto">
            <h2 class="title">
                Jobs page
            </h2>
            <h2 class="subtitle">
                Serwis tymczasowego zatrudnienia
                <br>
                Below available job in database
            </h2>
            <div class="divider"></div>
            <br>

            <div class="tile is-ancestor">

                <div class="tile is-parent">
                    <div class="tile is-parent is-8">
                        <article class="tile is-child box ">

                            <input type="text" id="filterInput" onkeyup="filterTable()" placeholder="Search your job">

                            <table id="jobsTable" class="table is-fullwidth is-striped is-hoverable">
                                <thead>
                                <tr>
                                    <th>Position</th>
                                    <th>Date</th>
                                    <th>Job-time</th>
                                    <th>Salary</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${jobList}" var="Job">
                                    <tr>
                                        <td>${Job.jobType.type}</td>
                                        <td>${Job.date}</td>
                                        <td>${Job.dateTimeFrom} - ${Job.dateTimeTo}</td>
                                        <td>${Job.salary}$</td>
                                        <td>
                                            <a href="#">
                                                <i class="fas fa-wrench"></i>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="/jobs/delete/${Job.id}">
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
                                <form:form method="post" action="/jobs/create">

                                    <p class="subtitle">Add job type </p>

                                    <div class="field">
                                        <div class="control">
                                            <div class="select">
                                                <select name="jobTypeId" required>
                                                    <label>JOB TYPE</label>
                                                    <option value="" disabled selected>Choose job type</option>
                                                    <c:forEach items="${jobTypeList}" var="jobType">
                                                        <option value="${jobType.id}"> ${jobType.type} ${jobType.store.city}
                                                            - ${jobType.pricePerHour}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="field">
                                        <p class="control has-icons-left">
                                            <input id="date" type="text" placeholder="Select day" class="datepicker" name="date" required/>
                                            <span class="icon is-small is-left">
											<%--<i class="far fa-building"></i>--%>
										</span>
                                        </p>
                                    </div>

                                    <div class="field">
                                        <p class="control has-icons-left">
                                            <input id="timeFrom" type="text" placeholder="Time from" class="timepicker is-medium" name="timeFrom" required/>
                                            <span class="icon is-small is-left">
											<%--<i class="far fa-building"></i>--%>
										</span>
                                        </p>
                                    </div>

                                    <div class="field">
                                        <p class="control has-icons-left">
                                            <input id="timeTo" type="text" placeholder="Time to" class="timepicker" name="timeTo" required/>
                                            <span class="icon is-small is-left">
											<%--<i class="far fa-building"></i>--%>
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
