<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>welcome</title>
</head>
<body>
<h2>Welcome</h2>
<c:forEach items="${ users }" var="user" varStatus="status">
    <p>N°<c:out value="${ status.count }"/> : <c:out value="${ user.name }"/> : <c:out value="${ user.lastName }"/> :
        <c:out value="${ user.email }"/> !</p>
</c:forEach>
<ul>
</ul>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td>
        <form action="user" method="get">
            <input style="display: none" type="text" name="value" placeholder="value" value="${currentPage - 1}">
            <button>Previous</button>
        </form>
    </td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <form action="user" method="get">
                        <td>
                            <input style="display: none" type="text" name="value" placeholder="value" value="${i}">
                            <button>${i}</button>
                        </td>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="user" method="get">
                        <td>
                            <input style="display: none" type="text" name="value" placeholder="value" value="${i}">
                            <button>${i}</button>
                        </td>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </form>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
<td>
    <form action="user" method="get">
        <input style="display: none" type="text" name="value" placeholder="value" value="${currentPage + 1}">
        <button>Next</button>
    </form>
    </c:if>
</body>
</html>
