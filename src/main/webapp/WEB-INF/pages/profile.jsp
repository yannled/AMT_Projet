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
                                                            <td>
                                                                <c:if test="${emailError}">
                                                                    <c:set var="validate" value="alert-validate"/>
                                                                </c:if>
                                                                <div class="${validate}" data-validate="${emailErrorText}">
                                                                    <input type="text" class="form-control" id="email"
                                                                           name="email" aria-describedby="email"
                                                                           value="${ currentUser.email }">
                                                                </div>
                                                            </td>
                                                        </tr>
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
                                                    <c:if test="${ currentUser.admin }">
                                                        <td>Admin</td>
                                                    </c:if>
                                                    <c:if test="${ !currentUser.admin }">
                                                        <td>User</td>
                                                    </c:if>
                                                </tr>
                                                <tr>
                                                    <td>Number of applications</td>
                                                    <td>${nbrApplications}</td>
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
            </div>
        </div>
    </div>
</div>

<!-- START OF Modal RESET Password -->
<div class="modal fade" id="resetPassw" tabindex="-1" role="dialog" aria-labelledby="Modify Application"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="resetPassword">Confirme Password Reset ?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="profile" method="post">
                <input class="hide" type="text" name="action" value="RESET">
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Reset Password</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- END OF Modal RESET Password -->

<%@ include file="parts/footer.jsp" %>

</body>
</html>
