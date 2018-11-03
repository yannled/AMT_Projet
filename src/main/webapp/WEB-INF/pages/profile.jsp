<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V16</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/pages.css">
    <link rel="stylesheet" type="text/css" href="css/projects.css">
    <!--===============================================================================================-->


</head>
<body>
<div class="limiter">
    <div class="container-login100" style="background-image: url('images/bg-02.jpg');">
        <%@ include file="include/nav.jsp" %>
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
<!-- END OF Modal PRIVILEGE Application -->
<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>