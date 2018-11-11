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
                        <li><a href="profile">Profile</a></li>
                        <li><a href="projects">Applications</a></li>
                        <c:if test="${isAdmin}">
                            <li><a href="admin">manage Users</a></li>
                            <li><a href="#waitingForThis">manage Applictions</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
            <div class="projects-right">
                <div class="col-md-10 col-lg-10 toppad" >
                    <h1> Welcome to our Application ${currentUser.name} ${currentUser.lastName}</h1>
                    <p>developped by Joel Schar | Patrick Neto | Steve Henriquet | Yann Lederrey</p>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="parts/footer.jsp" %>

</body>
</html>
