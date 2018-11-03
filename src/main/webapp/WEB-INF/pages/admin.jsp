<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Profile</title>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
</head>
<body>
<div class="limiter">
    <div class="container-login100" style="background-image: url('images/bg-02.jpg');">
        <%@ include file="parts/nav.jsp" %>
            <div class="wrap-main">
                <div class="projects-left">
                    <nav class="navbar navbar-left">
                    <ul>
                        <li><a data-toggle="modal" data-target="#action">action</a></li>
                    </ul>
                    </nav>
                </div>
                <div class="projects-right">
                   application developers list
                </div>
            </div>
    </div>
</div>


<%@ include file="parts/footer.jsp" %>

</body>
</html>
