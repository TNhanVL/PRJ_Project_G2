<%-- 
    Document   : dashboard
    Created on : Jun 29, 2023, 12:54:53 AM
    Author     : TTNhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.prj_project_g2.Services.CheckLoggedIn" %>

<%
    if (!CheckLoggedIn.check(request.getCookies())) {
        response.sendRedirect("./login");
    }
%>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Dashboard"/>
</jsp:include>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <button onclick="location.href = './logout';">Logout</button>
    </body>
</html>

<%@ include file="foot.jsp" %>

<%@ include file="popupMessage.jsp" %>