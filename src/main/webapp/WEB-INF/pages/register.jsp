<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
</head>
<body>

<c:set var="firstNameValidate" value=""/>
<c:set var="lastNameNameValidate" value=""/>
<c:set var="emailValidate" value=""/>
<c:set var="passwordValidate" value=""/>
<c:set var="secondPasswordValidate" value=""/>

<!-- Test firstName -->
<c:if test="${errors[0].error}">
    <c:set var="firstNameValidate" value="alert-validate"/>
</c:if>

<!-- Test LastName -->
<c:if test="${errors[1].error}">
    <c:set var="lastNameNameValidate" value="alert-validate"/>
</c:if>

<!-- Test Email -->
<c:if test="${errors[2].error}">
    <c:set var="emailValidate" value="alert-validate"/>
</c:if>

<!-- Test password -->
<c:if test="${errors[3].error}">
    <c:set var="passwordValidate" value="alert-validate"/>
</c:if>

<!-- Test secondPassword -->
<c:if test="${errors[4].error}">
    <c:set var="secondPasswordValidate" value="alert-validate"/>
</c:if>

<div class="limiter">
    <div class="container-login100" style="background-image: url('images/bg-02.jpg');">
        <div class="wrap-login100 p-t-90 p-b-30 p-r-30 p-l-30">
            <form action="register" method="post" class="login100-form validate-form">
					<span class="login100-form-title p-b-40">
						Register
					</span>

                <div class="wrap-input100 m-b-16 ${firstNameValidate}" data-validate="${errors[0].errorText}">
                    <input class="input100" type="text" name="first-name" placeholder="First Name" value="${errors[0].value}">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 m-b-16 ${lastNameNameValidate}" data-validate="${errors[1].errorText}">
                    <input class="input100" type="text" name="last-name" placeholder="Last Name" value="${errors[1].value}">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 m-b-16 ${emailValidate}" data-validate="${errors[2].errorText}">
                    <input class="input100" type="text" name="email" placeholder="Email" value="${errors[2].value}">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 m-b-20 ${passwordValidate}" data-validate = "${errors[3].errorText}">
						<span class="btn-show-pass">
							<i class="fa fa fa-eye"></i>
						</span>
                    <input class="input100" type="password" name="pass1" placeholder="Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 m-b-20 ${secondPasswordValidate}" data-validate = "${errors[4].errorText}">
						<span class="btn-show-pass">
							<i class="fa fa fa-eye"></i>
						</span>
                    <input class="input100" type="password" name="pass2" placeholder="Repeat Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        Submit
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>

</body>
</html>