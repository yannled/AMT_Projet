<%--
  Created by IntelliJ IDEA.
  User: p-stackouses
  Date: 10/19/18
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="projects-right">
    <c:if test="${empty application.name}">No application found</c:if>

    <c:if test="${not empty application.name}">
    <table class="table table-striped">
        <tr>
            <th scope="col">Application Name</th>
            <th scope="col">Description</th>
            <th scope="col">Developers</th>
            <th scope="col">API key</th>
            <th scope="col">APIsecret</th>
        </tr>
        <tr>
            <td>${ application.name }</td>
            <td><c:out value="${ application.description }"/></td>
            <c:if test="${not empty users}">
                <td>
                <c:forEach items="${ users }" var="user" varStatus="counter">
                        <c:out value="${ user.lastName }"/>
                        <c:out value=" ${ user.name }"/>
                </c:forEach>
                </td>
            </c:if>
            <td><c:out value="${ application.apikey }"/></td>
            <td><c:out value="${ application.apiSecret }"/></td>
        </tr>
    </table>
    </c:if>
</div>