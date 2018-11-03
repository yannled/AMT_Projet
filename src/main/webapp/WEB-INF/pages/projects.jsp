<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Projects</title>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
</head>
<body>
<div class="limiter">
    <div class="container-login100" style="background-image: url('images/bg-02.jpg');">
        <%@ include file="parts/nav.jsp" %>
            <div class="wrap-main">
                <div class="projects-left">
                    <nav class="navbar navbar-left">
                    <ul>
                        <li><a data-toggle="modal" data-target="#addApp">Add Application</a></li>
                    </ul>
                    </nav>
                </div>
                <div class="projects-right">
                    <%@ include file="include/listapp.jsp" %>
                </div>
            </div>
    </div>
</div>

<!-- START OF Modal ADD Application -->
<div class="modal fade" id="addApp" tabindex="-1" role="dialog" aria-labelledby="Modify Application"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifyApplication">Add Application</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="projects" method="post">
                <input class="hide" type="text" name="action" value="ADD">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="ApplicationName">Application name</label>
                        <input type="text" class="form-control" id="ApplicationName" name="name" aria-describedby="application name"
                               placeholder="Application name">
                        <label for="ApplicationDescription">Application description</label>
                        <textarea class="form-control" name="description" id="ApplicationDescription" rows="5"></textarea>
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
<!-- END OF Modal ADD Application -->

<%@ include file="parts/footer.jsp" %>

</body>
</html>