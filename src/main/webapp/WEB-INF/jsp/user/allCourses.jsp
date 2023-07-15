<%-- 
    Document   : allCourse
    Created on : Jul 4, 2023, 9:20:17 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Database.CourseDB"%>
<%@page import="com.prj_project_g2.Model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="title" value="All Courses"/>
        </jsp:include>
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/courseShop.css">
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/responsive.css">
    </head>

    <body>
        <!-- HEADER -->
        <%@include file="header.jsp" %>
        <!--END HEADER -->

        <!-- Shop course -->
        <div id="main">
            <div class="advertisement row">
                <div class="contentBox col-md-6">
                    <h1>Learn with Yojihan</h1>
                </div>
                <div class="teamWorkImg col-md-6">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/teamwork.png" alt="">
                </div>
            </div>
            <div class="allCourse">
                <!-- A package -->
                <div class="package">
                    <div class="packageHeader">
                        <h1>Programer</h1>
                    </div>
                    <div class="packageBody">
                        <ul class="courseList">
                            <!-- course -->
                            <%
                                ArrayList<Course> courses = CourseDB.getAllCourses();
                                for (Course course : courses) {
                            %>
                            <li class="listItem">
                                <a href="./course/<%out.print(course.getID());%>">
                                    <img src="<%out.print(request.getContextPath());%>/public/media/course/<%out.print(course.getID() + "/" + course.getImage());%>" alt="" class="courseImg">
                                    <h4 class="courseName"><%out.print(course.getTitle());%></h4>
                                    <div class="courseDescription">
                                        <span class="type">
                                            <span><%out.print(course.getDescription());%></span>
                                            <span><%
                                                int sumTimeInMinute = CourseDB.getSumTimeOfCourse(course.getID());
                                                out.print(Math.round(sumTimeInMinute / 6.0) / 10.0);
                                                %>h</span>
                                        </span>
                                        <div class="rateAndPrice">
                                            <div class="rate">
                                                <span><%out.print(course.getRate());%></span>
                                                <i class="fa-solid fa-star"></i>
                                                <span>(0 reviewer)</span>
                                            </div>
                                            <span class="price"><%out.print(course.getPrice());%>$</span>
                                        </div>
                                    </div>
                                </a>

                            </li>
                            <%}%>
                            <!-- end course -->

                        </ul>
                    </div>
                </div>

            </div>
        </div>
        <!-- End shop Course -->

        <%@include file="footer.jsp" %>

        <%@include file="foot.jsp" %>

        <%@include file="popUpMessage.jsp" %>

    </body>

</html>