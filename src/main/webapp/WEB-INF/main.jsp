<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="<c:url value="/resources/js/git-stats.js" />"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>

    <style>
        <%@include file="../resources/css/main.css" %>
    </style>

</head>
<body>

<%@include file="nav.jsp" %>

<section class="hero">
    <div class="hero-body" style="padding: 10px">
        <div class="container">
            <h2 class="title">
                Main page
            </h2>
            <h2 class="subtitle">
                Serwis tymczasowego zatrudnienia
                <br>
                Below stats from GitHub
            </h2>
            <div class="divider"></div>
        </div>
    </div>
</section>

<div class="content" style="margin: 15px;">
    <div class="box">


        <nav class="level" id="git-stats">
            <div class="column has-text-centered" id="loaderStat">
					<span class="icon ">
						<i class="fas fa-spinner fa-3x fa-spin"></i>
					</span>
                <br>
                <p><span><strong>Parsing json GitHub</strong></span></p>
            </div>
        </nav>

    </div>
</div>

</body>
</html>