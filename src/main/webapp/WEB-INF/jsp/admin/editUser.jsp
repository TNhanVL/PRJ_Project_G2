<%-- 
    Document   : dashboard
    Created on : Jun 29, 2023, 12:54:53 AM
    Author     : TTNhan
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.prj_project_g2.Database.UserDB"%>
<%@page import="com.prj_project_g2.Database.AdminDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.prj_project_g2.Model.User"%>
<%@page import="com.prj_project_g2.Services.CookieServices" %>

<%
    if (!CookieServices.checkAdminLoggedIn(request.getCookies())) {
        response.sendRedirect("./login");
    }

    //Get ID
    int ID;
    try {
        ID = Integer.parseInt(request.getParameter("id"));
        if (UserDB.getUser(ID) == null) {
            throw new Exception();
        }
    } catch (Exception e) {
        response.sendRedirect("./dashboard");
        return;
    }
%>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Dashboard"/>
</jsp:include>

<body class="not-login-page">

<div class="hello">
    <h1>Hi <b><%out.print(CookieServices.getUserName(request.getCookies()));%>!</b></h1>
    <button onclick="location.href = './logout';">Logout</button>
</div>

<div class="container-fluid">

    <div class="row">
        <!-- left column -->
        <div class="col-md-12 content">

            <div class="card card-primary">
                <div class="card-header">
                    <h3 class="card-title">Edit User</h3>
                </div>
                <!-- /.card-header -->
                <!-- form start -->

                <%
                    User user = UserDB.getUser(ID);
                %>

                <form action="./editUser?id=<%out.print(user.getID());%>" method="post" id="updateUserForm">
                    <div class="card-body">

                        <div class="form-group">
                            <label for="exampleInputEmail1">ID</label>
                            <input type="text" class="form-control" value="<%out.print(user.getID());%>" disabled>
                        </div>
                        <div class="form-group">
                            <input type="text" name="ID" value="<%out.print(user.getID());%>" style="display: none">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">avatar</label>
                            <input type="text" class="form-control" id="name"
                                   name="avatar" placeholder="Avatar" value="<%out.print(user.getAvatar());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">username</label>
                            <input type="text" class="form-control" id="name"
                                   name="username" placeholder="Username" value="<%out.print(user.getUsername());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">password</label>
                            <input type="text" class="form-control" id="gender"
                                   name="password" placeholder="Password" value="<%out.print(user.getPassword());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">email</label>
                            <input type="email" class="form-control" id="email"
                                   name="email" placeholder="Email" value="<%out.print(user.getEmail());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">firstName</label>
                            <input type="text" class="form-control" id="phone"
                                   name="firstName" placeholder="First Name" value="<%out.print(user.getFirstName());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">lastName</label>
                            <input type="text" class="form-control" id="address"
                                   name="lastName" placeholder="Last Name" value="<%out.print(user.getLastName());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">role</label>
                            <input type="text" class="form-control" id="address"
                                   name="role" value="<%out.print(user.getRole());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">birthday</label>
                            <input type="date" class="form-control" id="birthday"
                                   name="birthday" placeholder="Birthday" value="<%out.print(user.getBirthday());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">countryID</label>
                            <input type="text" class="form-control" id="address"
                                   name="countryID" value="<%out.print(user.getCountryID());%>" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">status</label>
                            <input type="text" class="form-control" id="address"
                                   name="status" placeholder="Status" value="<%out.print(user.getStatus());%>" required>
                        </div>
                        <div></div>
                    </div>
                    <!-- /.card-body -->

                    <div class="card-footer">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>


            </div>

        </div>
        <!--/.col (left) -->
        <!--/.col (right) -->
    </div>

</div>

</body>
<%@ include file="foot.jsp" %>

<%@ include file="popupMessage.jsp" %>