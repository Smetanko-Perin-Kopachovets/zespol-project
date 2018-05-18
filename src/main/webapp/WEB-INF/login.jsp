<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="<c:url value="/resources/js/main.js" />"></script>
    <script src="<c:url value="/resources/js/store.js" />"></script>

    <style>
        <%@include file="../resources/css/main.css" %>
        <%@include file="../resources/css/toast.css" %>
        <%@include file="../resources/css/store.css" %>

    </style>

    <title>Login</title>

</head>
<body>

<div class="tabs is-medium is-centered" id="tabs">
    <ul>
        <li data-tab="1" id="changeContent1" onclick="changeContent1()" class="is-active"><a>Login</a></li>
        <li data-tab="2" id="changeContent2" onclick="changeContent2()"><a>Registration</a></li>
    </ul>
</div>

<div data-content="1" id="content1">

    <div class="tile is-ancestor">

        <div class="tile is-parent is-4">
        </div>

        <div class="tile is-parent is-4">

            <article class="tile is-child box has-text-centered">

                <span class="subtitle">Login into your account</span>
                <br>
                <br>
                <div class="divider"></div>
                <br>

                <form action="/login" method="post">

                    <div class="field">
                        <p class="control has-icons-left ">
                            <input placeholder="Email" type="text" class="input" name="email">
                            <span class="icon is-small is-left">
							<i class="far fa-envelope"></i></span>
                        </p>
                    </div>

                    <div class="field">
                        <p class="control has-icons-left ">
                            <input placeholder="Password" type="password" class="input" name="password">
                            <span class="icon is-small is-left">
							<i class="fas fa-key"></i></span>
                        </p>
                    </div>

                    <button class="button is-primary " type="submit">
                        Sign in
                    </button>

                </form>
            </article>
        </div>
    </div>
</div>

<div data-content="2" id="content2">

    <div class="tile is-ancestor">

        <div class="tile is-parent is-4">
        </div>

        <div class="tile is-parent is-4" >
            <article class="tile is-child box has-text-centered">

                <form action="/registration" method="post">

                    <span class="subtitle">Create new account</span>
                    <br>
                    <br>
                    <div class="divider"></div>
                    <br>

                    <div class="columns">

                        <div class="column">
                            <p class="control has-icons-left ">
                                <input placeholder="Email" type="text" class="input" name="email">
                                <span class="icon is-small is-left">
							<i class="far fa-envelope"></i></span>
                            </p>
                        </div>

                        <div class="column">
                            <p class="control has-icons-left ">
                                <input placeholder="Password" type="password" class="input" name="password">
                                <span class="icon is-small is-left">
							<i class="fas fa-key"></i></span>
                            </p>
                        </div>

                    </div>

                    <div class="columns">

                        <div class="column">
                            <p class="control has-icons-left ">
                                <input placeholder="First name" type="text" class="input" name="firstName">
                                <span class="icon is-small is-left">
							<i class="fas fa-book"></i></span>
                            </p>
                        </div>

                        <div class="column">
                            <p class="control has-icons-left ">
                                <input placeholder="Last name" type="text" class="input" name="lastName">
                                <span class="icon is-small is-left">
							<i class="fas fa-book"></i></span>
                            </p>
                        </div>

                    </div>

                    <div class="field">
                        <p class="control has-icons-left ">
                            <input placeholder="Address" type="text" class="input" name="address">
                            <span class="icon is-small is-left">
							<i class="fas fa-location-arrow"></i></span>
                        </p>
                    </div>

                    <button class="button is-primary " type="submit">
                        Create account
                    </button>

                </form>
            </article>
        </div>
    </div>
</div>

<c:if test="${errorMessage != null}">
    <div id="snackbar"><span>${errorMessage}</span></div>
    <script>
        showToast();
    </script>
</c:if>

<c:if test="${logoutMessage != null}">
    <div id="snackbar"><span>${logoutMessage}</span></div>
    <script>
        showToast();
    </script>
</c:if>

</body>
</html>
