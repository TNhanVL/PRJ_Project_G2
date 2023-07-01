<%-- 
    Document   : dashboard
    Created on : Jun 29, 2023, 12:54:53 AM
    Author     : TTNhan
--%>

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
%>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Dashboard"/>
</jsp:include>

<div class="hello">
    <h1>Hi <b><%out.print(CookieServices.getUserName(request.getCookies()));%>!</b></h1>
    <button onclick="location.href = './logout';">Logout</button>
</div>

<table>
    <tr class="tableHeader">
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Status</th>
        <th>Modify</th>
    </tr>
    <%
        ArrayList<User> users = UserDB.getAllUsers();
        for (int i = 0; i < users.size(); i++) {

    %>
    <tr>
        <td><%out.print(users.get(i).getID());%></td>
        <td><%out.print(users.get(i).getUsername());%></td>
        <td><%out.print(users.get(i).getEmail());%></td>
        <td><%out.print(users.get(i).getFirstName());%></td>
        <td><%out.print(users.get(i).getLastName());%></td>
        <td><%out.print(users.get(i).getStatus());%></td>
        <td>
            <a
                href="./editUser?id=<%out.print(users.get(i).getID());%>">
                <i class="fas fa-solid fa-pen"></i>
            </a>
            <a
                href="./deleteUser?id=<%out.print(users.get(i).getID());%>">
                <i class="fa-solid fa-trash" style="color: #ff0000;"></i>
            </a>
        </td>
    </tr>
    <%}%>
</table>

<%@ include file="foot.jsp" %>

<%@ include file="popupMessage.jsp" %>