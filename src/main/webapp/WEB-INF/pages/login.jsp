<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
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
    <!--===============================================================================================-->


</head>
<body>

<c:set var="emailValidate" value=""/>
<c:set var="passwordvalidate" value=""/>

<!-- Test email -->
<c:if test="${errors[0].error}">
    <c:set var="emailValidate" value="alert-validate"/>
</c:if>

<!-- Test password -->
<c:if test="${errors[1].error}">
    <c:set var="passwordvalidate" value="alert-validate"/>
</c:if>

<div class="limiter">
    <div class="container-login100" style="background-image: url('images/bg-02.jpg');">
        <div class="wrap-login100 p-t-90 p-b-30 p-r-30 p-l-30">
            <form action="login" method="post" class="login100-form validate-form">
					<span class="login100-form-title p-b-40">
						Login
					</span>

                <div class="wrap-input100 m-b-16 ${emailValidate}" data-validate="${errors[0].errorText}">
                    <input id="inputEmail" class="input100" type="text" name="email" placeholder="Email" value="${errors[0].value}">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 m-b-20 ${passwordvalidate}" data-validate = "${errors[1].errorText}">
						<span class="btn-show-pass">
							<i class="fa fa fa-eye"></i>
						</span>
                    <input id="inputPassword" class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn">
                    <button id="buttonSubmit" class="login100-form-btn">
                        Enter
                    </button>
                </div>

                <div class="flex-col-c p-t-100">
						<span class="txt2 p-b-10">
							Don’t have an account?
						</span>

                    <a href="register" class="txt3 bo1 hov1">
                        Sign up now
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>

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