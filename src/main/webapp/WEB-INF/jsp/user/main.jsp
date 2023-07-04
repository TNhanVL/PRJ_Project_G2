<%-- 
    Document   : main
    Created on : Jul 3, 2023, 12:06:34 AM
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
            <jsp:param name="title" value="Yojihan"/>
        </jsp:include>
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/main.css">
    </head>

    <body>
        <!-- HEADER -->
        <%@include file="header.jsp" %>
        <!--END HEADER -->

        <!-- BODY -->
        <div id="body">
            <!-- ????? -->
            <div class="advertisement row">
                <div class="contentBox col-md-6">
                    <p>
                        <b>Sales end. Dreams go on.</b><br>
                        Get a second chance at the best prices 
                        of the season. Courses start at just 
                        ₫199,000. Today only.</p>
                </div>
                <div class="teamWorkImg col-md-6">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/bannerAds.png" alt="">
                </div>
            </div>

            <!-- ????? -->
            <div class="instructorAds row">
                <div class="boxContent col-md-6">
                    <div class="content">
                        <h4>Become an instructor</h4>
                        <p>Instructors from around the world teach millions of students on Udemy. We provide the tools and skills to teach what you love.
                        </p>
                    </div>
                </div>
                <div class="AdsImg col-md-6">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/instructor.png" alt="">
                </div>
            </div>

            <div class="popularCourse">
                <h2>Some popular courses</h2>
                <div class="courseSlider">
                    <div class="previousBtn">
                        <i class="fa-solid fa-chevron-left"></i>
                    </div>

                    <ul class="courseList">
                        <%  ArrayList<Course> courses = CourseDB.getAllCourses();
                            for (int i = 0; i < Math.min(3, courses.size()); i++) {
                        %>
                        <li class="listItem">
                            <a href="#">
                                <img src="<%out.print(request.getContextPath());%>/public/media/course/<%out.print(courses.get(i).getID() + "/" + courses.get(i).getImage());%>" alt="" class="courseImg">
                                <h4 class="courseName"><%out.print(courses.get(i).getTitle());%></h4>
                                <div class="courseDescription">
                                    <span class="type">
                                        <span><%out.print(courses.get(i).getDescription());%></span>
                                        <!--<span>20h</span>-->
                                    </span>
                                    <span class="price"><%out.print(courses.get(i).getPrice());%>$</span>
                                </div>
                            </a>
                            <div class="rate">
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-regular fa-star"></i>
                            </div>
                        </li>
                        <%}%>
                    </ul>

                    <div class="nextBtn">
                        <i class="fa-solid fa-chevron-right"></i>
                    </div>

                </div>
                <div  class="seperatePage">
                    <i class="fa-solid fa-circle active"></i>
                    <i class="fa-solid fa-circle"></i>
                    <i class="fa-solid fa-circle"></i>
                    <i class="fa-solid fa-circle"></i>
                </div>
                <div class="viewCourseBtn">
                    <button class="viewCourseBtn">
                        <a href="./allCourses">View All Course</a>
                    </button>
                </div>

            </div>

            <div class="advantageAds row">
                <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/side.jpg" alt="" class="ads col-md-4">
                <div class="content col-md-8">
                    <h4>Outstanding advantages</h4>
                    <ul>
                        <li> <b> Expert Instruction:</b> Learn from industry professionals who have real-world experience and can provide valuable insights to help you succeed.</li>
                        <li> <b> Flexible, On-Demand Learning:</b> Take courses whenever and wherever it's convenient for you. Our platform allows you to learn at your own pace, on your own schedule.</li>
                        <li> <b> Affordable Prices:</b> Get high-quality education at an affordable price. We believe everyone should have access to quality learning opportunities without breaking the bank.</li>
                        <li> <b> Certification and Credentials:</b> Earn certificates and credentials that demonstrate your mastery of the course material. Our programs are designed to help you get ahead in your career or start a new one.</li>
                    </ul>
                </div>
            </div>

            <div class="feedBack">
                <h2>What our learners say...</h2>
                <div class="feedBackSlider">
                    <div class="nextBtn">
                        <i class="fa-solid fa-chevron-left"></i>
                    </div>
                    <ul class="feedbackListItem">

                        <li class="feedbackItem">
                            <div class="headerItem">
                                <i class="fa-solid fa-quote-left"></i>
                            </div>
                            <div class="bodyItem">
                                Thanks u guys for the website which delivered me an opportunity to enhance my English in a quite short period of time, because of you I succeeded in getting overall band 8, with L-9 and R-8
                            </div>
                            <div class="footerItem">
                                <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/logo.png" alt="" class="userImg">
                                <span class="userInfor">Someone Else</span>
                            </div>
                        </li>

                        <li class="feedbackItem">
                            <div class="headerItem">
                                <i class="fa-solid fa-quote-left"></i>
                            </div>
                            <div class="bodyItem">
                                Thanks u guys for the website which delivered me an opportunity to enhance my English in a quite short period of time, because of you I succeeded in getting overall band 8, with L-9 and R-8
                            </div>
                            <div class="footerItem">
                                <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/logo.png" alt="" class="userImg">
                                <span class="userInfor">Someone Else</span>
                            </div>
                        </li>

                        <li class="feedbackItem">
                            <div class="headerItem">
                                <i class="fa-solid fa-quote-left"></i>
                            </div>
                            <div class="bodyItem">
                                Thanks u guys for the website which delivered me an opportunity to enhance my English in a quite short period of time, because of you I succeeded in getting overall band 8, with L-9 and R-8
                            </div>
                            <div class="footerItem">
                                <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/logo.png" alt="" class="userImg">
                                <span class="userInfor">Someone Else</span>
                            </div>
                        </li>

                        <li class="feedbackItem">
                            <div class="headerItem">
                                <i class="fa-solid fa-quote-left"></i>
                            </div>
                            <div class="bodyItem">
                                Thanks u guys for the website which delivered me an opportunity to enhance my English in a quite short period of time, because of you I succeeded in getting overall band 8, with L-9 and R-8
                            </div>
                            <div class="footerItem">
                                <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/logo.png" alt="" class="userImg">
                                <span class="userInfor">Someone Else</span>
                            </div>
                        </li>



                    </ul>
                    <div class="nextBtn">
                        <i class="fa-solid fa-chevron-right"></i>
                    </div>
                </div>
                <div  class="seperatePage">
                    <i class="fa-solid fa-circle active"></i>
                    <i class="fa-solid fa-circle"></i>
                    <i class="fa-solid fa-circle"></i>
                    <i class="fa-solid fa-circle"></i>
                </div>
                <div class="viewCourseBtn">
                    <button class="viewCourseBtn">
                        <a href="">View All</a>
                    </button>
                </div>
            </div>

            <div class="collaborate">
                <p>We collaborate with <span>100+ leading universities and companies</span></p>
                <div class="collaborateLogos">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/gg.png" alt="">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/michigan.png" alt="">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/fu.png" alt="">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/standford.png" alt="">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/hw.jpg" alt="">
                </div>
            </div>

            <div class="advertisement ads2 row">
                <div class="teamWorkImg col-md-6">
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/bannerAds.png" alt="">
                </div>
                <div class="contentBox ads2 col-md-6">
                    <p>
                        <b>Sales end. Dreams go on.</b><br>
                        Get a second chance at the best prices 
                        of the season. Courses start at just 
                        ₫199,000. Today only.</p>
                </div>

            </div>


        </div>
        <!-- END BODY -->

        <%@include file="footer.jsp" %>

        <%@include file="foot.jsp" %>

        <%@include file="popUpMessage.jsp" %>

    </body>

</html>