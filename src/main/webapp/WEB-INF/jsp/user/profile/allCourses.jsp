<%-- 
    Document   : allCourses
    Created on : Jul 16, 2023, 1:28:52 AM
    Author     : TTNhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="purchasedCourseAndCreatedCourse">

    <div class="purchasedCourse">
        <h3>All taken courses</h3>

        <%
            ArrayList<Course> courses = CourseDB.getAllPurchasedCourses(user.getID());
            for (Course course : courses) {
        %>

        <!-- a course -->
        <div class="courseTaken">
            <img src="<%out.print(request.getContextPath());%>/public/media/course/<%out.print(course.getID() + "/" + course.getImage());%>" alt="" class="courseImg">
            <div class="courseInfor">
                <a href="<%out.print(request.getContextPath());%>/user/course/<%out.print(course.getID());%>">
                    <p class="courseName"><%out.print(course.getTitle());%></p>
                    <%
                        if (guest) {
                    %>
                    <div class="rate">
                        <span><%out.print(course.getRate());%></span>
                        <i class="fa-solid fa-star"></i>
                        <span>(2423 reviewer)</span>
                    </div>
                    <%
                        }
                    %>
                </a>
                <%
                    if (!guest) {
                        int sumTimeOfCourse = CourseDB.getSumTimeOfCourse(course.getID());
                        int sumOfCompletedTime = CourseDB.getSumTimeCompletedOfCourse(user.getID(), course.getID());
                        int percent = 100;
                        if (sumTimeOfCourse != 0) {
                            percent = (int) 100f * sumOfCompletedTime / sumTimeOfCourse;
                        }
                %>
                <div class="ProgressviewMode">

                    <progress class="courseProgress" value="<%out.print(percent);%>" max="100"></progress>
                    <div class="notCompleted ">
                        <p>In progress <span><%out.print(percent);%></span>%</p>
                    </div>

                    <div class="completed">
                        <p>Completed</p>
                        <%
                            if (CourseDB.checkCertificate(user.getID(), course.getID())) {
                                String certificateName = CourseDB.getCertificateName(user.getID(), course.getID());
                        %>
                        <a href="<%out.print(request.getContextPath());%>/public/media/certificate/<%out.print(certificateName);%>">View certificate</a>
                        <%
                            }
                        %>
                    </div>

                </div>

                <%
                    }
                %>

            </div>
        </div>
        <!-- end course -->

        <%
            }
        %>

    </div>

    <div class="purchasedCourse">
        <h3>All created courses</h3>


        <%
            if (lecturer != null) {
                courses = CourseDB.getAllCreatedCourses(user.getID());
                for (Course course : courses) {
        %>

        <!-- a course -->
        <div class="courseTaken">
            <img src="<%out.print(request.getContextPath());%>/public/media/course/<%out.print(course.getID() + "/" + course.getImage());%>" alt="" class="courseImg">
            <div class="courseInfor">
                <a href="<%out.print(request.getContextPath());%>/user/course/<%out.print(course.getID());%>">
                    <p class="courseName"><%out.print(course.getTitle());%></p>
                    <div class="rate">
                        <span><%out.print(course.getRate());%></span>
                        <i class="fa-solid fa-star"></i>
                        <span>(2423 reviewer)</span>
                    </div>
                </a>

            </div>
        </div>
        <!-- end course -->

        <%
                }
            }
        %>

    </div>
</div>
