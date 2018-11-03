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
                    <ul>
                        <li><button data-toggle="modal" data-target="#resetPassw">Reset Password</button></li>
                        <li><button data-toggle="modal" data-target="#changePrivilege">Change Privilege</button></li>
                        <li><button data-toggle="modal" data-target="#changeSuspend">Suspend Account</button></li>
                    </ul>
                </div>
                <div class="projects-right">
                    <form action="profile" method="post">
                        <input class="hide" type="text" name="action" value="MODIFY">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="selectUser">Lastname </label>
                                <input type="text" class="form-control" id="Lastname" name="name" aria-describedby="Lastname"
                                       value="${ currentUser.lastName }">
                                <label for="selectUser">FirstName </label>
                                <input type="text" class="form-control" id="FirstName" name="firstName" aria-describedby="FirstName"
                                       value="${ currentUser.name }">
                                <label for="selectUser">Email </label>
                                <input type="text" class="form-control" id="email" name="email" aria-describedby="email"
                                       value="${ currentUser.email }">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Modify</button>
                        </div>
                    </form>
                </div>
            </div>
    </div>
</div>

<!-- START OF Modal RESET Application -->
<div class="modal fade" id="resetPassw" tabindex="-1" role="dialog" aria-labelledby="Modify Application"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="resetPassword">Reset password</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="profile" method="post">
                <input class="hide" type="text" name="action" value="RESET">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="selectUser">Select the User</label>
                        <select name="selectUser" id="selectUser">
                            <c:forEach items="${users}" var="userValue">
                                <option value="${userValue.email}">
                                        ${userValue.lastName}-${userValue.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Reset Password</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- END OF Modal RESET Application -->

<!-- START OF Modal PRIVILEGE Application -->
<div class="modal fade" id="changePrivilege" tabindex="-1" role="dialog" aria-labelledby="Modify Application"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="changePriv">Change privilege</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="profile" method="post">
                <input class="hide" type="text" name="action" value="RESET">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="selectUser2">Select the User</label>
                        <select name="selectUser" id="selectUser2">
                            <c:forEach items="${users}" var="userValue">
                                <option value="${userValue.email}">
                                        ${userValue.lastName}-${userValue.name}
                                </option>
                            </c:forEach>
                        </select>
                        <label for="choosePriv">Select the User</label>
                        <select name="selectUser" id="choosePriv">
                                <option value="0">
                                    Administrator
                                </option>
                                <option value="1">
                                    User
                                </option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Change Privilege</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- END OF Modal PRIVILEGE Application -->

<!-- START OF Modal PRIVILEGE Application -->
<div class="modal fade" id="changeSuspend" tabindex="-1" role="dialog" aria-labelledby="Modify Application"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="suspend">Change Suspend</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="profile" method="post">
                <input class="hide" type="text" name="action" value="RESET">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="selectUser3">Select the User</label>
                        <select name="selectUser" id="selectUser3">
                            <c:forEach items="${users}" var="userValue">
                                <option value="${userValue.email}">
                                    ${userValue.lastName}-${userValue.name}
                                </option>
                            </c:forEach>
                        </select>
                        <label for="choosePriv">Select the User</label>
                        <select name="selectUser" id="chooseSuspend">
                            <option value="0">
                                Suspend
                            </option>
                            <option value="1">
                                Active
                            </option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Change Privilege</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>

</body>
</html>
