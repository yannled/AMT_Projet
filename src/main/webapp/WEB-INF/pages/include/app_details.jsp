<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="projects-right">
    <c:if test="${empty application.name}">No application found</c:if>

    <c:if test="${not empty application.name}">
    <table class="table table-striped">
        <tr>
            <td><strong>Application</strong></td>
            <td>${application.name}</td>
            <td></td>
        <tr style="max-height: 150px">
            <td><strong>Description</strong></td>
            <td style="word-break: break-all; height: 150px">
                <div style="overflow-y:scroll; height:100%">
                    ${ application.description }
                </div>
            </td>
            <td></td>
        </tr>
        <c:if test="${not empty users}">
            <tr>
                <td><strong>Developpers</strong></td>
                <td>
                    <c:forEach items="${ users }" var="user" varStatus="counter">
                       ${user.name} ${user.lastName} <br/>
                    </c:forEach>
                </td>
                <td>
                    <form action="details_project?idProject=${application.id}" method="post">
                        <select name="addUser">
                            <c:forEach items="${ allUsers }" var="user" varStatus="counter">
                                <option name="${user.id}" value="${user.id}">${user.name} ${user.lastName}</option>>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-danger">Add User</button>
                    </form>
                </td>
            </tr>
        </c:if>
        <tr>
            <td><strong>API Key</strong></td>
            <td>${application.apikey}</td>
            <td></td>
        </tr
        <tr>
            <td><strong>API Secret Key</strong></td>
            <td>${application.apiSecret}</td>
            <td></td>
        </tr>
    </table>
    </c:if>
</div>