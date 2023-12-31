<%-- 
    Document   : rightSideCourse
    Created on : Jul 3, 2023, 10:40:20 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Database.CourseDB"%>
<%@page import="com.prj_project_g2.Model.Course"%>
<%@page import="com.prj_project_g2.Database.MoocDB"%>
<%@page import="com.prj_project_g2.Model.Mooc"%>
<%@page import="com.prj_project_g2.Model.Lesson"%>
<%@page import="com.prj_project_g2.Services.CookieServices"%>
<%@page import="com.prj_project_g2.Database.UserDB"%>
<%@page import="com.prj_project_g2.Model.User"%>
<%@page import="com.prj_project_g2.Database.LessonDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    User user1 = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
    Lesson lesson1 = LessonDB.getLesson(Integer.parseInt(request.getParameter("lessonID")));
    Mooc mooc1 = MoocDB.getMooc(lesson1.getMoocID());
    Course course1 = CourseDB.getCourse(mooc1.getCourseID());
    
%>

<div class="rightSide">
    <h4><%out.print(course1.getTitle());%></h4>

    <!-- part -->
    <div class="part open">
        <div class="partHeader">
            <h5><%out.print(mooc1.getTitle());%></h5>
            <i class="fa-solid fa-sort-down"></i>
        </div>

        <div class="partBody">
            <!-- each lesson is a div -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <i class="fa-solid fa-square-check">
                        <!-- unchecked -->
                        <!-- <i class="fa-regular fa-square"></i> -->
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <i class="fa-brands fa-youtube"></i>
                            <!-- type text -->
                            <!-- <i class="fa-solid fa-file-lines"></i> -->
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->

            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson --><!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->


        </div>

    </div>
    <!-- end part -->

    <!-- part -->
    <div class="part open">
        <div class="partHeader">
            <h5>Part 1: Hiragana 。ひらがな</h5>
            <i class="fa-solid fa-sort-down"></i>
        </div>

        <div class="partBody">
            <!-- each lesson is a div -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <i class="fa-solid fa-square-check">
                        <!-- unchecked -->
                        <!-- <i class="fa-regular fa-square"></i> -->
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <i class="fa-brands fa-youtube"></i>
                            <!-- type text -->
                            <!-- <i class="fa-solid fa-file-lines"></i> -->
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->

            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson --><!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->


        </div>

    </div>
    <!-- end part -->

    <!-- part -->
    <div class="part open">
        <div class="partHeader">
            <h5>Part 1: Hiragana 。ひらがな</h5>
            <i class="fa-solid fa-sort-down"></i>
        </div>

        <div class="partBody">
            <!-- each lesson is a div -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <i class="fa-solid fa-square-check">
                        <!-- unchecked -->
                        <!-- <i class="fa-regular fa-square"></i> -->
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <i class="fa-brands fa-youtube"></i>
                            <!-- type text -->
                            <!-- <i class="fa-solid fa-file-lines"></i> -->
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->

            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->
            <!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson --><!-- Start lesson -->
            <div class="lesson">
                <span class="lesson-status">
                    <!-- checked -->
                    <!-- <i class="fa-solid fa-square-check"> -->
                    <!-- unchecked -->
                    <i class="fa-regular fa-square"></i>
                    </i></span>
                <div class="lesson-content">
                    <p class="title">1. Row A, Ka</p>
                    <span class="description">
                        <span class="type">
                            <!-- type video -->
                            <!-- <i class="fa-brands fa-youtube"></i> -->
                            <!-- type text -->
                            <i class="fa-solid fa-file-lines"></i>
                        </span>
                        <span class="time">13 minute</span>
                    </span>
                </div>
            </div>
            <!-- End lesson -->


        </div>

    </div>
    <!-- end part -->

</div>
