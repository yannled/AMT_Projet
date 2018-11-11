<%--
  Created by IntelliJ IDEA.
  User: Mitraillet
  Date: 05/11/2018
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
  <table class="table table-striped">
    <tr>
      <th scope="col">Last Name</th>
      <th scope="col">First Name</th>
      <th scope="col">E-mail</th>
      <th scope="col">Privilege</th>
      <th scope="col">Suspend</th>
    </tr>
    <c:forEach items="${ users }" var="user" varStatus="counter">
      <tr>
        <td><c:out value="${ user.lastName }"/></td>
        <td><c:out value="${ user.name }"/></td>
        <td><c:out value="${ user.email }"/></td>
        <td>
          <button data-toggle="modal" data-target="#privilegeUser-${user.lastName}-${user.name}-${ counter.count }">
            ${user.admin ? 'Administrator' : 'User'}
              <i class="fas fa-pencil-alt"></i></button>
        </td>
        <td>
          <button data-toggle="modal" data-target="#suspendUser-${user.lastName}-${user.name}-${ counter.count }">
            <display:column>
              <c:choose>
                <c:when test="${user.state == 0}">Suspend</c:when>
                <c:when test="${user.state == 1}">Active</c:when>
                <c:when test="${user.state == 2}">hasChangePassword</c:when>
              </c:choose>
            </display:column>
            <i class="fas fa-pencil-alt"></i></button>
        </td>
      </tr>

      <!-- START OF Modal Modify User Privilege -->
      <div class="modal fade" id="privilegeUser-${user.lastName}-${user.name}-${ counter.count }" tabindex="-1" role="dialog" aria-labelledby="Modify User"
           aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="suspend">Change Suspend</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form action="admin" method="post">
              <input class="hide" type="text" name="action" value="MODIFYPrivilege">
              <input class="hide" type="text" name="email" value=<c:out value="${ user.email }"/>>
              <div class="modal-body">
                <div class="form-group">
                  <p><c:out value="${user.email}"/></p>
                  <label for="privilege">Select the Privilege</label><br>
                  <select class="custom-select" name="privilege" id="privilege">
                    <option value="0" ${user.admin ? '' : 'selected'} >
                      User
                    </option>
                    <option value="1" ${user.admin ? 'selected' : ''} >
                      Administrator
                    </option>
                  </select>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button type="submit" class="btn btn-primary">Change Privilege</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <!-- END OF Modal Modify User Privilege -->

      <!-- START OF Modal Modify User Status -->
      <div class="modal fade" id="suspendUser-${user.lastName}-${user.name}-${counter.count}" tabindex="-1" role="dialog" aria-labelledby="Modify User Status"
           aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="modifyUserStatus">Modify User Status</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form action="admin" method="post">
              <input class="hide" type="text" name="action" value="MODIFYStatus">
              <input class="hide" type="text" name="email" value=<c:out value="${ user.email }"/> >
              <div class="modal-body">
                <div class="form-group">
                  <p><c:out value="${user.email}"/></p>
                  <label for="status">Select the Status</label><br>
                  <select class="custom-select" name="status" id="status">
                    <option value="0" ${user.state == 0 ? 'selected' : ''} >
                      Suspend
                    </option>
                    <option value="1" ${user.state == 1 ? 'selected' : ''}>
                      Active
                    </option>
                    <option value="2" ${user.state == 2 ? 'selected' : ''}>
                      hasChangePassword
                    </option>
                  </select>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-danger">Change Status</button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <!-- END OF Modal Modify User Status -->

    </c:forEach>
  </table>
</div>

<!-- PAGINATION -->

<div class="pagination pagination-center">
  <%--For displaying Previous link except for the 1st page --%>
  <c:if test="${currentPage != 1}">
    <div class="mr-3">
      <td>
        <form action="admin" method="get">
          <input style="display: none" type="text" name="value" placeholder="value"
                 value="${currentPage - 1}">
          <button>Previous</button>
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
            <form action="admin" method="get">
              <td>
                <input style="display: none" type="text" name="value" placeholder="value" value="${i}">
                <button class="font-weight-bold fs-20">${i}</button>
              </td>
            </form>
          </c:when>
          <c:otherwise>
            <form action="admin" method="get">
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
    <div class="ml-3">
      <td>
        <form action="admin" method="get">
          <input style="display: none" type="text" name="value" placeholder="value"
                 value="${currentPage + 1}">
          <button>Next</button>
        </form>
      </td>
    </div>
  </c:if>
</div>

