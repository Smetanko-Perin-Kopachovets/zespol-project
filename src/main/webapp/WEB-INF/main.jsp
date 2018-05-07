<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="<c:url value="/resources/js/git-stats.js" />"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>

</head>
<body>

<div>

    <%@include file="nav.jsp" %>

    <section class="hero">
        <div class="hero-body">
            <div class="tile is-parent ">
                <article class="tile is-child box">
                    <p class="title">Project - Service temp employee</p>
                    <p class="subtitle">Git stats contributors </p>
                    <div class="content">
                        <p>Some text with description</p>
                    </div>
                </article>
            </div>
        </div>
    </section>

    <nav class="level" id="git-stats">
        <div class="column" id="loaderStat">
				<span class="icon has-text-centered">
					<i class="fas fa-spinner fa-3x fa-spin"></i>
				</span>
            <br>
            <p><span><strong>Parsing json GitHub</strong></span></p>
        </div>
    </nav>

</div>
</body>
</html>