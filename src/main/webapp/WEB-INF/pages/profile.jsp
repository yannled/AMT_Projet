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
                        <li><a data-toggle="modal" data-target="#resetPassw">Reset Password</a></li>
                        <li><a data-toggle="modal" data-target="#changePrivilege">Change Privilege</a></li>
                        <li><a data-toggle="modal" data-target="#changeSuspend">Suspend Account</a></li>
                        <li><a href="profile?modify">Modify Profile</a></li>
                    </ul>
                </nav>
            </div>
            <div class="projects-right">
                <div class="col-md-10 col-lg-10 toppad">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">${currentUser.name} ${currentUser.lastName}</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row toppad">
                                <div class="col-md-4 col-lg-2" align="center">
                                    <div class="profile-pic">
                                       <!-- <img alt="User Pic" src="images/squirrel.jpg">-->
                                        <img src="data:image/jpg;base64,${currentUser.base64Avatar}" width="240" height="300"/>
                                    </div>
                                </div>
                                <c:choose>
                                    <c:when test="${modify == true}">
                                        <div class="col-md-6 col-lg-6">

                                            <form action="profile" method="post" enctype="multipart/form-data">
                                                <input class="hide" type="text" name="action" value="MODIFY">
                                                <div class="form-group">
                                                    <table class="table table-user-information">
                                                        <tbody>
                                                        <tr>
                                                            <td>First Name</td>
                                                            <td><input type="text" class="form-control"
                                                                       id="FirstName"
                                                                       name="firstName" aria-describedby="FirstName"
                                                                       value="${ currentUser.name }"></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Last Name</td>
                                                            <td>
                                                            <input type="text" class="form-control"
                                                                   id="Lastname"
                                                                   name="lastName" aria-describedby="Lastname"
                                                                   value="${ currentUser.lastName }"></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Email</td>
                                                            <td><input type="text" class="form-control" id="email"
                                                                       name="email" aria-describedby="email"
                                                                       value="${ currentUser.email }"></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Account Type</td>
                                                            <td>Admin</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Number of applications</td>
                                                            <td>1000</td>
                                                        </tr>
                                                        <tr>
                                                        <tr>
                                                            <td>Avatar</td>
                                                            <td><input type="file" name="avatar" /></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <a href="profile" class="btn btn-secondary">
                                                    Close
                                                </a>
                                                <button type="submit" class="btn btn-primary">Apply</button>
                                            </form>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-md-6 col-lg-6">
                                            <table class="table table-user-information">
                                                <tbody>
                                                <tr>
                                                    <td>First Name</td>
                                                    <td>${ currentUser.name }</td>
                                                </tr>
                                                <tr>
                                                    <td>Last Name</td>
                                                    <td>${ currentUser.lastName }</td>
                                                </tr>
                                                <tr>
                                                    <td>Email</td>
                                                    <td><a href="mailto:${ currentUser.email }">${ currentUser.email }</a></td>
                                                </tr>
                                                <tr>
                                                    <td>Account Type</td>
                                                    <td>Admin</td>
                                                </tr>
                                                <tr>
                                                    <td>Number of applications</td>
                                                    <td>1000</td>
                                                </tr>
                                                <tr>
                                                <tr>
                                                    <td>Home Address</td>
                                                    <td>Kathmandu,Nepal</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>
                        <div class="panel-footer">

                        </div>

                    </div>
                </div>


                <!--
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

                   -->
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
