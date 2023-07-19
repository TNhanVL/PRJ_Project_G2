<%-- 
    Document   : profile
    Created on : Jul 7, 2023, 11:21:33 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Model.Lecturer"%>
<%@page import="com.prj_project_g2.Model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.prj_project_g2.Model.Organization"%>
<%@page import="com.prj_project_g2.Database.LecturerDB"%>
<%@page import="com.prj_project_g2.Database.OrganizationDB"%>
<%@page import="com.prj_project_g2.Database.CountryDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //get user that want to show profile
    String profileUsername = (String) request.getAttribute("username");
    User user = UserDB.getUserByUsername(profileUsername);

    if (user == null) {
        request.getSession().setAttribute("error", "Not exist this username!");
        response.sendRedirect(request.getContextPath() + "/user/main");
        return;
    }

    boolean guest = true;
    //Get user loggedIn
    if (CookieServices.checkUserLoggedIn(request.getCookies())) {
        User userLoggedin = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
        guest = (userLoggedin == null || user.getID() != userLoggedin.getID());
    }

    Lecturer lecturer = LecturerDB.getLecturer(user.getID());
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="../head.jsp">
            <jsp:param name="title" value="Yojihan"/>
        </jsp:include>
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/profile.css">
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/responsive.css">
    </head>

    <body>
        <!-- HEADER -->
        <%@include file="../header.jsp" %>
        <!--END HEADER -->

        <!-- BODY -->

        <div id="main">
            <div id="info" class="tabcontent active">
                <div class="infor">
                    <div class="userTags">
                        <div class="inforTag">
                            <div class="avatar">
                                <img src="<%
                                    if (loggedInHeader) {

                                        boolean isUrl = false;
                                        try {
                                            new URL(user.getAvatar()).toURI();
                                            isUrl = true;
                                        } catch (Exception e) {
                                        }

                                        if (isUrl) {
                                            out.print(user.getAvatar());
                                        } else {
                                            out.print(request.getContextPath() + "/public/media/user/" + user.getID() + "/" + user.getAvatar());
                                        }
                                    } else {
                                        out.print("https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png");
                                    }
                                     %>" alt="">
                            </div>
                            <div class="name">
                                <h4><%out.print(user.getFirstName() + " " + user.getLastName());%></h4>
                            </div>



                            <div class="orgranization">
                                <p><%out.print(CountryDB.getCountry(user.getCountryID()).getName());%></p>
                                <%
                                    if (lecturer != null) {
                                        Organization organization = OrganizationDB.getOrganization(lecturer.getOrganizationID());
                                %>
                                <p>Lecturer of <%out.print(organization.getName());%></p>
                                <img class="org" src="<%out.print(request.getContextPath());%>/public/media/organization/<%out.print(organization.getID() + "/" + organization.getLogo());%>" alt="">
                                <%
                                    }
                                %>
                            </div>



                            <!-- <div class="socialMedia" content="Comming Soon">
                            <i class="fa-brands fa-facebook"></i>
                            <i class="fa-brands fa-instagram"></i>
                            <i class="fa-brands fa-github"></i>
                            <i class="fa-solid fa-envelope"></i>
                        </div> -->
                        </div>
                        <div class="expTag">
                            <h4>Experience</h4>
                            <p class="element"><i class="fa-sharp fa-regular fa-clock"></i>Total learning hours
                                <span><%
                                    int sumTimeCompletedInMinute = CourseDB.getSumTimeCompletedOfAllCourses(user.getID());
                                    out.print(Math.round(sumTimeCompletedInMinute / 6.0) / 10.0);
                                    %></span>
                            </p>
                            <p class="element"><i class="fa-solid fa-cart-shopping"></i>Courses purchased<span><%out.print(CourseDB.getNumberPurchasedCourse(user.getID()));%></span></p>
                            <p class="element"><i class="fa-regular fa-circle-check"></i>Courses completed <span><%out.print(CourseDB.getNumberCompletedCourse(user.getID()));%></span>
                            </p>
                            <p class="element"><i class="fa-sharp fa-solid fa-certificate"></i>Courses created
                                <span><%out.print(CourseDB.getNumberCreatedCourse(user.getID()));%></span>
                            </p>
                            <p class="element">Learn since 2020</p>
                        </div>
                    </div>

                    <%@include file="allCourses.jsp" %>

                </div>
            </div>
        </div>

        <!-- END BODY -->

        <%@include file="../footer.jsp" %>

        <%@include file="../foot.jsp" %>

        <script src="<%out.print(request.getContextPath());
        %>/public/assets/js/option.js"></script>

        <%@include file="../popUpMessage.jsp" %>

    </body>

</html>