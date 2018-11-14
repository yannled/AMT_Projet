<%--
  Created by IntelliJ IDEA.
  User: zutt
  Date: 10/19/18
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <c:forEach items="${ applications }" var="application" varStatus="counter">
            <tr>
                <td><c:out value="${ application.name }"/></td>
                <td><c:out value="${ application.description }"/></td>
                <td><c:out value="${ application.apikey }"/></td>
                <td><c:out value="${ application.apiSecret }"/></td>
                <td class="centerIcon">
                    <button data-toggle="modal" id="modifyAppButton-${ application.apikey }"
                            data-target="#modifyApp-${ application.apikey }"><i class="fas fa-pencil-alt"></i></button>
                </td>
                <td class="centerIcon">
                    <button data-toggle="modal" id="deleteAppButton-${ application.apikey }" data-target="#deleteApp-${ application.apikey }"><i
                            class="fas fa-trash-alt"></i></button>
                </td>
            </tr>

            <!-- START OF Modal Modify Application -->
            <div class="modal fade" id="modifyApp-${ application.apikey }" tabindex="-1" role="dialog"
                 aria-labelledby="Modify Application"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modifyApplication">Modify Application</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="applications" method="post">
                            <input class="hide" type="text" name="action" value="MODIFY">
                            <input class="hide" type="text" name="apiKey" value=<c:out
                                    value="${ application.apikey }"/>>
                            <c:if test="${not empty showUser && not empty userEmail}">
                                <input style="display: none" type="text" name="showUser" placeholder="showUser"
                                       value="${showUser}">
                                <input style="display: none" type="text" name="userEmail" placeholder="userEmail"
                                       value="${userEmail}">
                            </c:if>
                            <c:if test="${currentPage != 1}">
                              <input style="display: none" type="text" name="value" placeholder="value"
                                     value="${currentPage}">
                            </c:if>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="ApplicationName">Application name</label>
                                    <input type="text" class="form-control" id="ApplicationName" name="name"
                                           aria-describedby="application name"
                                           value="<c:out value="${ application.name }"/>">
                                    <label for="ApplicationDescription">Application description</label>
                                    <textarea class="form-control" name="description"
                                              id="ApplicationDescription" rows="5"><c:out
                                            value="${ application.description }"/></textarea>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button id="submitAppModify" type="submit" class="btn btn-primary">Save changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- END OF Modal Modify Application -->

            <!-- START OF Modal DELETE Application -->
            <div class="modal fade" id="deleteApp-${ application.apikey }" tabindex="-1" role="dialog"
                 aria-labelledby="Delete Application"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteApplication">Delete Application</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="applications" method="post">
                            <input class="hide" type="text" name="action" value="DELETE">
                            <input class="hide" type="text" name="apiKey" value=<c:out
                                    value="${ application.apikey }"/>>
                            <c:if test="${not empty showUser && not empty userEmail}">
                                <input style="display: none" type="text" name="showUser" placeholder="showUser"
                                       value="${showUser}">
                                <input style="display: none" type="text" name="userEmail" placeholder="userEmail"
                                       value="${userEmail}">
                            </c:if>
                          <c:if test="${currentPage != 1}">
                              <input style="display: none" type="text" name="value" placeholder="value"
                                     value="${currentPage}">
                          </c:if>
                            <div class="modal-body">
                                <div class="form-group">
                                    <h6> Are you sure to delete this application ? : <strong><c:out
                                            value="${ application.name }"/></strong></h6>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button id="submitAppDelete" type="submit" class="btn btn-danger">Delete</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- END OF Modal DELETE Application -->
        </c:forEach>
    </table>
</div>

<!-- PAGINATION -->

<div class="pagination pagination-center">
    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <div class="mr-3">
            <td>
                <form action="applications" method="get">
                    <input style="display: none" type="text" name="value" placeholder="value"
                           value="${currentPage - 1}">
                    <c:if test="${not empty showUser && not empty userEmail}">
                        <input style="display: none" type="text" name="showUser" placeholder="showUser"
                               value="${showUser}">
                        <input style="display: none" type="text" name="userEmail" placeholder="userEmail"
                               value="${userEmail}">
                    </c:if>
                    <button id="previousPage">Previous</button>
                </form>
            </td>
        </div>
    </c:if>

    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table class="pagination-table" border="0" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <form action="applications" method="get">
                            <td>
                                <input style="display: none" type="text" name="value" placeholder="value" value="${i}">
                                <c:if test="${not empty showUser && not empty userEmail}">
                                    <input style="display: none" type="text" name="showUser" placeholder="showUser"
                                           value="${showUser}">
                                    <input style="display: none" type="text" name="userEmail" placeholder="userEmail"
                                           value="${userEmail}">
                                </c:if>
                                <button class="font-weight-bold fs-20">${i}</button>
                            </td>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="applications" method="get">
                            <td>
                                <input style="display: none" type="text" name="value" placeholder="value" value="${i}">
                                <c:if test="${not empty showUser && not empty userEmail}">
                                    <input style="display: none" type="text" name="showUser" placeholder="showUser"
                                           value="${showUser}">
                                    <input style="display: none" type="text" name="userEmail" placeholder="userEmail"
                                           value="${userEmail}">
                                </c:if>
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
        <div class="ml-3">
            <td>
                <form action="applications" method="get">
                    <input style="display: none" type="text" name="value" placeholder="value"
                           value="${currentPage + 1}">
                    <c:if test="${not empty showUser && not empty userEmail}">
                        <input style="display: none" type="text" name="showUser" placeholder="showUser"
                               value="${showUser}">
                        <input style="display: none" type="text" name="userEmail" placeholder="userEmail"
                               value="${userEmail}">
                    </c:if>
                    <button id="nextPage">Next</button>
                </form>
            </td>
        </div>
    </c:if>
</div>
