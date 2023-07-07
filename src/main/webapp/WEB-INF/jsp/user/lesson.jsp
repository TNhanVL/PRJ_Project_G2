<%-- 
    Document   : lesson
    Created on : Jul 3, 2023, 9:21:18 PM
    Author     : TTNhan
--%>

<%@page import="java.util.Collections"%>
<%@page import="com.prj_project_g2.Database.AnswerDB"%>
<%@page import="com.prj_project_g2.Model.Answer"%>
<%@page import="com.prj_project_g2.Database.QuestionDB"%>
<%@page import="com.prj_project_g2.Model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.prj_project_g2.Database.PostDB"%>
<%@page import="com.prj_project_g2.Model.Post"%>
<%@page import="com.prj_project_g2.Database.MoocDB"%>
<%@page import="com.prj_project_g2.Model.Mooc"%>
<%@page import="com.prj_project_g2.Model.Lesson"%>
<%@page import="com.prj_project_g2.Services.CookieServices"%>
<%@page import="com.prj_project_g2.Database.UserDB"%>
<%@page import="com.prj_project_g2.Model.User"%>
<%@page import="com.prj_project_g2.Database.LessonDB"%>
<%@page import="com.prj_project_g2.Database.CourseDB"%>
<%@page import="com.prj_project_g2.Model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //check user loggedIn
    User user = null;
    if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
        request.getSession().setAttribute("error", "You must be logged in before enter this lesson!");
        response.sendRedirect(request.getContextPath() + "/user/login");
        return;
    } else {
        user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
    }

    //check courseID
    Course course = null;
    try {
        course = CourseDB.getCourse((int) request.getAttribute("courseID"));
        if (course == null) {
            request.getSession().setAttribute("error", "Not exist the course!");
            throw new Exception("Not exist course!");
        }
    } catch (Exception e) {
        response.sendRedirect(request.getContextPath() + "/user/main");
        return;
    }

    Lesson lesson = null;
    //check exist lessonID
    try {
        int lessonID = (int) request.getAttribute("lessonID");
        lesson = LessonDB.getLesson(lessonID);
        if (lesson == null) {
            request.getSession().setAttribute("error", "Not exist the lesson!");
            throw new Exception("Not exist lesson!");
        }
    } catch (Exception e) {
    }

    //get last lesson in the course
    if (lesson == null) {
        int lessonID = LessonDB.getFirstUncompleteLessonID(user.getID(), course.getID());
        //if have no lesson in this course
        if (lessonID < 0) {
            request.getSession().setAttribute("error", "There is no lesson in this course!");
            response.sendRedirect(request.getContextPath() + "/user/main");
            return;
        }
        lesson = LessonDB.getLesson(lessonID);
    }

    Mooc mooc = MoocDB.getMooc(lesson.getMoocID());
    //check match lessonID with courseID
    if (mooc != null && mooc.getCourseID() != course.getID()) {
        request.getSession().setAttribute("error", "Not exist the this lesson in the course!");
        response.sendRedirect(request.getContextPath() + "/user/main");
        return;
    }

    //check purchased course
    try {
        if (!CourseDB.checkPurchasedCourse(user.getID(), course.getID())) {
            request.getSession().setAttribute("error", "You haven't purchased this course!");
            throw new Exception("Not purchased course yet!");
        }
    } catch (Exception e) {
        response.sendRedirect(request.getContextPath() + "/user/main");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="title" value="Yojihan Study"/>
        </jsp:include>
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/lesson.css">
        <script src="https://www.youtube.com/iframe_api"></script>
    </head>

    <body>
        <!-- HEADER -->
        <%@include file="header.jsp" %>
        <!--END HEADER -->

        <div class="main">
            <!-- Left Side -->
            <div class="leftSide">

                <div class="lesson-main">
                    <%
                        switch (lesson.getType()) {
                            //type 0 -> video
                            case 0: {
                                Post post = PostDB.getPostByLessonID(lesson.getID());
                                %>
                    <%@include file="lesson/video.jsp" %>
                    <%
                            break;
                        }
                        case 1: {
                    %>

                    <%
                            break;
                        }
                        //type 2 -> quiz
                        case 2: {
                    %>
                    <%@include file="lesson/quiz.jsp" %>
                    <%                                break;
                            }
                        }
                        %>

                </div>

                <!--Info under lesson-->
                <%@include file="lesson/lessonInfo.jsp" %>

            </div>
            <!-- Right side -->

            <%@include file="lesson/rightMenu.jsp" %>

        </div>

        <%@include file="footer.jsp" %>

        <%@include file="foot.jsp" %>

        <script src="<%out.print(request.getContextPath());%>/public/assets/js/lesson.js"></script>
        <script src="<%out.print(request.getContextPath());%>/public/assets/js/option.js"></script>

        <%@include file="popUpMessage.jsp" %>

    </body>

</html>