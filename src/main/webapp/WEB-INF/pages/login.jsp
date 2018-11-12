<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
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

<%@ include file="parts/footer.jsp" %>

</body>
</html>