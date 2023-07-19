<%-- 
    Document   : header
    Created on : Jul 3, 2023, 12:52:05 AM
    Author     : TTNhan
--%>

<%@page import="java.net.URL"%>
<%@page import="com.prj_project_g2.Database.CourseDB"%>
<%@page import="com.prj_project_g2.Database.UserDB"%>
<%@page import="com.prj_project_g2.Model.User"%>
<%@page import="com.prj_project_g2.Services.CookieServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    boolean loggedInHeader = false;
    User userHeader = null;
    if (CookieServices.checkUserLoggedIn(request.getCookies())) {
        loggedInHeader = true;
        userHeader = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
    }
%>

<div id="header">
    <div class="left-side">
        <a href="${contextPath}/user/main">
            <img src="${contextPath}/public/assets/imgs/logo.png" alt="logo" class="logo">
        </a>
        <form action="">
            <input type="text" class="search-course" name="headerSearch" placeholder="Searching">
        </form>
        <div class="course-opption">
            <a href="${contextPath}/user/allCourses">Courses</a>
        </div>
        <div class="quesAndAns">
            <a href="#">Expert Q&A</a>
        </div>
    </div>

    <div class="right-side">
        <a href="${contextPath}/user/cart" class="cart">
            <i class="fa-solid fa-cart-shopping"></i>
            <%
                int numberOfOrderHeader = 0;
                if (userHeader != null) {
                    numberOfOrderHeader = CourseDB.countOrderCourse(userHeader.getID());
                }
                if (numberOfOrderHeader > 0) {
            %>
            <div class="quantity"><%out.print(numberOfOrderHeader);%></div>
            <%}%>
        </a>

        <a href="" class="notification">
            <i class="fa-sharp fa-solid fa-bell"></i>
            <div class="quantity">3</div>
        </a>

        <div onclick="openMenu()" id="user" class="user">
            <a href="<%
                if (loggedInHeader) {
                    out.print("#");
                } else {
                    out.print(request.getContextPath() + "/user/login");
                }
               %>">
                <img src="<%
                    if (loggedInHeader) {

                        boolean isUrl = false;
                        try {
                            new URL(userHeader.getAvatar()).toURI();
                            isUrl = true;
                        } catch (Exception e) {
                        }

                        if (isUrl) {
                            out.print(userHeader.getAvatar());
                        } else {
                            out.print(request.getContextPath() + "/public/media/user/" + userHeader.getID() + "/" + userHeader.getAvatar());
                        }
                    } else {
                        out.print("https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png");
                    }
                     %>"
                     alt="avatar">
                <span class="userInfor"><%
                    if (loggedInHeader) {
                        out.print(userHeader.getUsername());
                    } else {
                        out.print("Guest!");
                    }
                    %></span>
            </a>

            <%
                if (loggedInHeader) {
            %>
            <div id="userMenu" class="userMenu close">
                <a href="${contextPath}/user/profile/<%out.print(userHeader.getUsername());%>">
                    <i class="fa-solid fa-user"></i>
                    <span>Profile</span>
                </a>
                <a href="#">
                    <i class="fa-solid fa-gear"></i>
                    <span>Setting</span>
                </a>
                <a href="${contextPath}/user/logout">
                    <i class="fa-solid fa-right-from-bracket"></i>
                    <span>Logout</span>
                </a>
            </div>
            <%
                }
            %>
        </div>

    </div>
</div>

<%if (loggedInHeader) {%>
<script src="${contextPath}/public/assets/js/option.js"></script>                    
<%}%>
