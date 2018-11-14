<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Change Password</title>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
</head>
<body>

<c:set var="oldPasswordValidate" value=""/>
<c:set var="passwordValidate" value=""/>
<c:set var="secondPasswordValidate" value=""/>

<!-- Test Old Password -->
<c:if test="${errors[0].error}">
    <c:set var="oldPasswordValidate" value="alert-validate"/>
</c:if>

<!-- Test password -->
<c:if test="${errors[1].error}">
    <c:set var="passwordValidate" value="alert-validate"/>
</c:if>

<!-- Test secondPassword -->
<c:if test="${errors[2].error}">
    <c:set var="secondPasswordValidate" value="alert-validate"/>
</c:if>


<div class="limiter">
    <div class="container-login100" style="background-image: url('images/bg-02.jpg');">
        <div class="wrap-login100 p-t-90 p-b-30 p-r-30 p-l-30">
            <form action="pswchange" method="post" class="login100-form validate-form">
					<span class="login100-form-title p-b-40">
						Set A New Password
					</span>

                <div class="wrap-input100 m-b-20 ${oldPasswordValidate}" data-validate = "${errors[0].errorText}">
						<span class="btn-show-pass">
							<i class="fa fa fa-eye"></i>
						</span>
                    <input id="inputPassword" class="input100" type="password" name="oldpass" placeholder="Old Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 m-b-20 ${passwordValidate}" data-validate = "${errors[1].errorText}">
						<span class="btn-show-pass">
							<i class="fa fa fa-eye"></i>
						</span>
                    <input id="inputPassword1" class="input100" type="password" name="pass1" placeholder="New Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 m-b-20 ${secondPasswordValidate}" data-validate = "${errors[2].errorText}">
						<span class="btn-show-pass">
							<i class="fa fa fa-eye"></i>
						</span>
                    <input id="inputPassword2" class="input100" type="password" name="pass2" placeholder="Repeat Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn">
                    <button id="buttonSubmit" class="login100-form-btn">
                        Enter
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>

</body>
</html>