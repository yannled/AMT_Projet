<%--
  Created by IntelliJ IDEA.
  User: zutt
  Date: 10/20/18
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal Modify Application -->
<div class="modal fade" id="modifyApp" tabindex="-1" role="dialog" aria-labelledby="Modify Application"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifyApplication">Modify Application</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="#" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="ApplicationName">Application name</label>
                        <input type="text" class="form-control" id="ApplicationName" aria-describedby="application name"
                               placeholder="Application name">
                        <label for="ApplicationDescription">Application description</label>
                        <textarea class="form-control" id="ApplicationDescription" rows="5"></textarea>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
