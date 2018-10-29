<%--
  Created by IntelliJ IDEA.
  User: zutt
  Date: 10/19/18
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="ModifyAppModal.jsp" %>
<%@ include file="DeleteAppModal.jsp" %>
<div>
    <table class="table table-striped">
        <tr>
            <th scope="col">Application Name</th>
            <th scope="col">description</th>
            <th scope="col">API key</th>
            <th scope="col">APIsecret</th>
            <th scope="col">Edit</th>
            <th scope="col">Remove</th>
        </tr>
        <tr>
            <td>Test App Name</td>
            <td>application super coool pour manger des pommes</td>
            <td>1</td>
            <td>adeu30093nf1j304jdj3</td>
            <td class="centerIcon">
                <button data-toggle="modal" data-target="#modifyApp"><i class="fas fa-pencil-alt"></i></button>
            </td>
            <td class="centerIcon">
                <button data-toggle="modal" data-target="#deleteApp"><i class="fas fa-trash-alt"></i></button>
            </td>
        </tr>
    </table>
</div>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td>
        <form action="user" method="get">
            <input style="display: none" type="text" name="value" placeholder="value" value="${currentPage - 1}">
            <button>Previous</button>
        </form>
    </td>
</c:if>

<div>
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
</div>
