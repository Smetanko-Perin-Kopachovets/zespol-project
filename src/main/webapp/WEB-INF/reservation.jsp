<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.05.2018
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta name="viewport" content="width = device-width, initial-scale = 1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
<script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/js/materialize.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/css/materialize.min.css">

<script src="<c:url value="/resources/js/job.js" />"></script>
<script src="<c:url value="/resources/js/store.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

<style>
    <%@include file="../resources/css/toast.css" %>
    <%@include file="../resources/css/store.css" %>
    <%@include file="../resources/css/main.css" %>
</style>

<head>
    <title>Reservation</title>

</head>
<body>

<%@include file="nav.jsp" %>

<sec:authorize access="hasRole('ROLE_MANAGER')">
<div class="tile is-parent is-8">
    <article class="tile is-child box ">

        <input type="text" id="filterInput" onkeyup="filterTable()" placeholder="Search your job">

        <table id="reservationTable" class="table is-fullwidth is-striped is-hoverable">
            <thead>
            <tr>
                <th>Position</th>
                <th>Date</th>
                <th>Job-time</th>
                <th>Job Type</th>
                <th>User name</th>
                <th>Salary</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reservationList}" var="Reservation">
                <tr>
                    <td>${Reservation.id}</td>
                    <td>${Reservation.job.date}</td>
                    <td>${Reservation.job.dateTimeFrom} - ${Reservation.job.dateTimeTo}</td>
                    <td>${Reservation.job.jobType.type}</td>
                    <td>${Reservation.user.firstName}</td>
                    <td>${Reservation.job.salary}$</td>
                    <td>
                        <a href="/reservation/accept/${Reservation.id}">
                            <i class="fas fa-check-circle"></i>
                        </a>
                    </td>
                    <td>
                        <a href="/reservation/reject/${Reservation.id}">
                            <i class="far fa-times-circle"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </article>
</div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_WORKER')">
    <div class="tabs is-medium is-centered" id="tabs">
        <ul>
            <li data-tab="1" id="changeContent1" onclick="changeContent1()" class="is-active"><a>Check</a></li>
            <li data-tab="2" id="changeContent2" onclick="changeContent2()"><a>Accept</a></li>
        </ul>
    </div>

    <div data-content = "1" id = "content1">
    <div class="tile is-parent is-8">
        <article class="tile is-child box ">

            <input type="text" id="filterInputs" onkeyup="filterTable()" placeholder="Search your job">

            <table id="reservationTables" class="table is-fullwidth is-striped is-hoverable">
                <thead>
                <tr>
                    <th>Position</th>
                    <th>Date</th>
                    <th>Job-time</th>
                    <th>Job Type</th>
                    <th>Store</th>
                    <th>City</th>
                    <th>Salary</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${reservationListOnUser}" var="Reservation">
                    <tr>
                        <td>${Reservation.id}</td>
                        <td>${Reservation.job.date}</td>
                        <td>${Reservation.job.dateTimeFrom} - ${Reservation.job.dateTimeTo}</td>
                        <td>${Reservation.job.jobType.type}</td>
                        <td>${Reservation.job.jobType.store.name}</td>
                        <td>${Reservation.job.jobType.store.city}</td>
                        <td>${Reservation.job.salary}$</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </article>
    </div>
    </div>

    <div data-content = "2" id = "content2">
    <div class="tile is-parent is-8">
        <article class="tile is-child box ">

            <input type="text" id="filterInpu" onkeyup="filterTable()" placeholder="Search your job">

            <table id="reservationTabl" class="table is-fullwidth is-striped is-hoverable">
                <thead>
                <tr>
                    <th>Position</th>
                    <th>Date</th>
                    <th>Job-time</th>
                    <th>Job Type</th>
                    <th>Store</th>
                    <th>City</th>
                    <th>Salary</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${acceptListJobOnUser}" var="ReservationJob">
                    <tr>
                        <td>${ReservationJob.id}</td>
                        <td>${ReservationJob.date}</td>
                        <td>${ReservationJob.dateTimeFrom} - ${ReservationJob.dateTimeTo}</td>
                        <td>${ReservationJob.jobType.type}</td>
                        <td>${ReservationJob.jobType.store.name}</td>
                        <td>${ReservationJob.jobType.store.city}</td>
                        <td>${ReservationJob.salary}$</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </article>
    </div>
    </div>
</sec:authorize>

<c:if test="${not empty message}">
    <div id="snackbar"><span>${message}</span></div>
    <script>
        showToast();
    </script>
</c:if>
</body>
</html>
