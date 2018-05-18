<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 16.05.2018
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="<c:url value="/resources/js/main.js" />"></script>
    <script src="<c:url value="/resources/js/job.js" />"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/css/materialize.min.css">



    <style>
        <%@include file="../resources/css/toast.css" %>
        <%@include file="../resources/css/main.css" %>
    </style>


    <title>Job</title>


</head>
<body>

<%@include file="nav.jsp" %>


<div class="tile is-ancestor">
    <div class="tile is-parent">
        <div class="tile is-parent is-8">
            <article class="tile is-child box ">

                <table class="table is-fullwidth is-striped is-hoverable">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Title</th>
                        <th>Date</th>
                        <th>TimeFrom</th>
                        <th>TimeTo</th>
                        <th>Salary per job</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${jobList}" var="Job">
                        <tr>
                            <td>${Job.id}</td>
                            <td>${Job.jobType.type}</td>
                            <td>${Job.date}</td>
                            <td>${Job.dateTimeFrom}</td>
                            <td>${Job.dateTimeTo}</td>
                            <td>${Job.salary}</td>
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

        <div class="tile is-parent is-4">
            <article class="tile is-child box">
                <form:form method="post" action="/jobs/create">

                    <p class="subtitle">Add job type </p>


                    <div class="field">
                        <div class="control">
                            <div class="select">
                                <select name="jobTypeId">
                                    <label>JOB TYPE</label>
                                    <option value="" disabled selected>Choose your option</option>
                                    <c:forEach items="${jobTypeList}" var="jobType">
                                        <option value="${jobType.id}"> ${jobType.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>


                    <div class="input-field">
                        <i class="material-icons prefix">date_range</i>
                        <label for="date"> Date </label>
                        <input id="date" type="text" class="datepicker" name="date"/>
                    </div>

                    <div class="input-field">
                        <i class="material-icons prefix">access_time</i>
                        <label for="timeFrom"> Start time </label>
                        <input id="timeFrom" type="text" class="timepicker"  name="timeFrom"/>
                    </div>

                    <div class="input-field">
                        <i class="material-icons prefix">timer_off</i>
                        <label for="timeTo"> End time </label>
                        <input id="timeTo" type="text" class="timepicker"  name="timeTo"/>

                    </div>

                    <div class="box has-text-centered">
                        <button class="button is-primary" type="submit">Create</button>
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
