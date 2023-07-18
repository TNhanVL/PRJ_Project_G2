<%-- 
    Document   : signup
    Created on : Jul 5, 2023, 7:55:04 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Model.User"%>
<%@page import="com.prj_project_g2.Services.CookieServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (CookieServices.checkUserLoggedIn(request.getCookies())) {
        response.sendRedirect("./main");
        return;
    }

    User user = (User) request.getAttribute("userSignUp");
    if (user == null) {
        user = new User();
    }
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/signup.css">
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/toast.css">
        <title>Login page</title>
    </head>

    <body>
        <div id="main">
            <div class="box">
                <form action="<%out.print(request.getContextPath());%>/user/signup" method="post">

                    <input type="text" required="required" name="avatar" value="<%if (user.getEmail() != null) {
                                out.print(user.getAvatar());
                            }%>" style="display: none">
                    
                    <h2>Sign up</h2>
                    <div class="inputBox">
                        <input type="text" placeholder="Enter your username" required="required" name="username">
                        <span>Username</span>
                        <i></i>
                    </div>
                    <div class="inputBox">
                        <input type="text" placeholder="Enter your email" required="required" name="email" value="<%if (user.getEmail() != null) {
                                out.print(user.getEmail());
                            }%>" <%if (user.getEmail() != null) {
                                    out.print("readonly");
                                }%>>
                        <span>Email</span>
                        <i></i>
                    </div>
                    <div class="inputBox-name">
                        <div class="inputBox">
                            <input type="text" placeholder="First name" required="required" name="firstName" value="<%if (user.getFirstName() != null) {
                                    out.print(user.getFirstName());
                                }%>">
                            <span>First Name</span>
                            <i></i>
                        </div>
                        <div class="inputBox">
                            <input type="text" placeholder="Last name" required="required" name="lastName" value="<%if (user.getLastName() != null) {
                                    out.print(user.getLastName());
                                }%>">
                            <span>Last Name</span>
                            <i></i>
                        </div>
                    </div>

                    <div class="inputBox date">
                        <label for="">Birthday</label>
                        <input type="date" value="2023-01-01" required="required" name="birthday">
                        <i></i>
                    </div>

                    <div class="inputBox country">
                        <label for="country">Country</label>
                        <select name="country" id="country">
                            <option value="1">Vietnam</option>
                            <option value="2">Japan</option>
                            <option value="3">China</option>
                            <option value="4">Korean</option>
                        </select>
                        <i></i>
                    </div>

                    <div class="inputBox">
                        <input type="password" placeholder="Enter your password" required="required" name="password">
                        <span>Password</span>
                        <i></i>
                    </div>

                    <input type="submit" value="Register">

                </form>

            </div>
        </div>
        <%@include file="foot.jsp" %>

        <%@include file="popUpMessage.jsp" %>
    </body>

</html>