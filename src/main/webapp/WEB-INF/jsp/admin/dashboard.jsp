<%-- 
    Document   : dashboard
    Created on : Jun 29, 2023, 12:54:53 AM
    Author     : TTNhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.prj_project_g2.Services.CookieServices" %>

<%
    if (!CookieServices.checkAdminLoggedIn(request.getCookies())) {
        response.sendRedirect("./login");
    }
%>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Dashboard"/>
</jsp:include>

<div class="hello">
    <h1>Hi <b><%out.print(CookieServices.getUserName(request.getCookies()));%>!</b></h1>
    <button onclick="location.href = './logout';">Logout</button>
</div>

<%@ include file="foot.jsp" %>

<%@ include file="popupMessage.jsp" %>