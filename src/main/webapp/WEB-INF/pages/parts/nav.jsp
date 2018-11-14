<%--
  Created by IntelliJ IDEA.
  User: joel
  Date: 10/12/18
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrap-nav">
    <nav class="navbar">
        <ul>
            <li><a href="home">Home</a></li>
            <li><a href="applications">Applications</a></li>
            <c:if test="${isAdmin}">
                <li><a href="admin">Manage User</a></li>
            </c:if>
        </ul>
        <ul class="ml-auto">
            <li>
                <a href="profile">Profile</a>
            </li>
            <li>
            <a href="logout">Logout</a>
            </li>
        </ul>
    </nav>
</div>

