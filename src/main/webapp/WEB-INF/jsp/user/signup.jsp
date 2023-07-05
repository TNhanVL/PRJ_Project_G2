<%-- 
    Document   : signup
    Created on : Jul 5, 2023, 7:55:04 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Services.CookieServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (CookieServices.checkUserLoggedIn(request.getCookies())) {
        response.sendRedirect("./main");
        return;
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
                <form action="">

                    <h2>Sign up</h2>
                    <div class="inputBox">
                        <input type="text" required="required" name="username">
                        <span>Email or Phone number</span>
                        <i></i>
                    </div>
                    <div class="inputBox">
                        <input type="password" required="required" name="password">
                        <span>Password</span>
                        <i></i>
                    </div>
                    <div class="inputBox">
                        <input type="password" required="required" name="password">
                        <span>Confirm Password</span>
                        <i></i>
                    </div>
                    <input type="submit" value="Register">

                    <p id="message"></p>

                </form>

                <div class="accountLogin">
                    <h6> or </h6>
                    <div class="loginWithGG">
                        <a href="">
                            <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/logogoogle.png" alt="">
                            <p>Login with Google</p>
                        </a>
                    </div>
                </div>

                <script>
                    // Đợi 10 giây trước khi xóa p
                    setTimeout(function () {
                        var messageDiv = document.getElementById('message');
                        messageDiv.parentNode.removeChild(messageDiv);
                    }, 5000);
                </script>
            </div>
        </div>
        <%@include file="foot.jsp" %>

        <%@include file="popUpMessage.jsp" %>
    </body>

</html>