<%-- 
    Document   : header
    Created on : Jul 3, 2023, 12:52:05 AM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Database.UserDB"%>
<%@page import="com.prj_project_g2.Model.User"%>
<%@page import="com.prj_project_g2.Services.CookieServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    boolean loggedIn = false;
    User user = null;
    if (CookieServices.checkUserLoggedIn(request.getCookies())) {
        loggedIn = true;
        user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
    }
%>

<div id="header">
    <div class="left-side">
        <a href="./main">
            <img src="../public/assets/imgs/logo.png" alt="logo" class="logo">
        </a>
        <input type="text" class="search-course" name="headerSearch" placeholder="Searching">
        <div class="course-opption">
            Course
        </div>
        <div class="quesAndAns">
            Expert Q&A
        </div>
    </div>

    <div class="right-side">
        <a href="paysite.html" class="cart">
            <i class="fa-solid fa-cart-shopping"></i>
            <div class="quantity">3</div>
        </a>
        <div onclick="openMenu()" id="user" class="user">
            <a href="<%
                    if (loggedIn) {
                        out.print("#");
                    } else {
                        out.print("./login");
                    }
                     %>">
                <img src="<%
                    if (loggedIn) {
                        out.print(user.getAvatar());
                    } else {
                        out.print("https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png");
                    }
                     %>"
                     alt="avatar">
                <span class="userInfor"><%
                    if (loggedIn) {
                        out.print(user.getUsername());
                    } else {
                        out.print("Guest!");
                    }
                    %></span>
            </a>

            <div id="userMenu" class="userMenu close">
                <a href="#">
                    <i class="fa-solid fa-gear"></i>
                    <span>Setting</span>
                </a>
                <a href="#">
                    <i class="fa-solid fa-right-from-bracket"></i>
                    <span>Logout</span>
                </a>
            </div>
        </div>

        <a href="" class="notification">
            <i class="fa-sharp fa-solid fa-bell"></i>
            <div class="quantity">3</div>
        </a>

    </div>
</div>

<%if (loggedIn) {%>
<script src="../public/assets/js/option.js"></script>                    
<%}%>
