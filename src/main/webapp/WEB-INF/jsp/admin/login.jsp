<%-- 
    Document   : index
    Created on : Jun 1, 2023, 1:32:33 PM
    Author     : TTNhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.prj_project_g2.Services.CheckLoggedIn" %>

<%
    if (CheckLoggedIn.check(request.getCookies())) {
        response.sendRedirect("./dashboard");
    }
%>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Login"/>
</jsp:include>

<div class="login-box">

    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <div class="login-logo">
                <b>Students Management</b>
            </div>
            <%if (request.getSession().getAttribute("error") != null) {%>
            <div class="alert alert-danger">
                <%out.print(request.getSession().getAttribute("error"));%>
            </div>
            <%
                    request.getSession().setAttribute("error", null);
                }%>

            <!-- Create Post Form -->

            <form action="<%=request.getContextPath()%>/admin/login" method="post" id="loginForm">
                <div class="form-group">
                    <label for="username">Username</label>
                    <div class="input-group mb-3">
                        <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <i class="fa-solid fa-user"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password">Username</label>
                    <div class="input-group mb-3">
                        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-4">
                        <button type="submit" class="btn btn-primary btn-block">Login</button>
                    </div>
                    <!-- /.col -->
                    <div class="col-4">
                    </div><!-- /.col -->
                    <!-- /.col -->
                    <div class="col-4">
                        <button id="cancelButton" class="btn btn-danger btn-block">Cancel</button>
                    </div>

                </div>
            </form>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->

<%@ include file="foot.jsp" %>

<%@ include file="popupMessage.jsp" %>