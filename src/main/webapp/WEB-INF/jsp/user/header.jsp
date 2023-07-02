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
        <a href="">
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
        </a>
        <div class="user">
            <a href="">
                <img src="<%
                    if (loggedIn) {
                        out.print(user.getAvatar());
                    } else {
                        out.print("https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png");
                    }
                     %>" alt="avatar">
                <span class="userInfor"><%
                    if (loggedIn) {
                        out.print(user.getUsername());
                    } else {
                        out.print("Guest!");
                    }
                    %></span>
            </a>
        </div>

        <a href="" class="notification">
            <i class="fa-sharp fa-regular fa-bell"></i>
        </a>

    </div>
</div>
