<%-- 
    Document   : lesson
    Created on : Jul 3, 2023, 9:21:18 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Database.LessonDB"%>
<%@page import="com.prj_project_g2.Database.CourseDB"%>
<%@page import="com.prj_project_g2.Model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = null;
    if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
        response.sendRedirect("./main");
    } else {
        user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
    }

    Lesson lesson = null;
    //check exist lessonID
    try {
        int lessonID = Integer.parseInt(request.getParameter("lessonID"));
        System.out.println(LessonDB.existLesson(lessonID));
    } catch (Exception e) {
        response.sendRedirect("./main");
    }

%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="title" value="Yojihan Study"/>
        </jsp:include>
        <link rel="stylesheet" href="../public/assets/css/lesson.css">
    </head>

    <body>
        <!-- HEADER -->
        <%@include file="header.jsp" %>
        <!--END HEADER -->

        <div class="main">
            <!-- Left Side -->
            <div class="leftSide">
                <video src="../public/assets/videos/baclieu.mp4" controls>
                    Trình duyệt của bạn không hỗ trợ video.
                </video>
                <div class="tab">
                    <button class="tablinks" onclick="openTab(event, 'Overview')">Overview</button>
                    <button class="tablinks" onclick="openTab(event, 'QandA')">Q&A</button>
                    <button class="tablinks" onclick="openTab(event, 'Rating')">Rating</button>
                </div>

                <!-- Tab content -->
                <div id="Overview" class="tabcontent active">
                    <div class="content">
                        <ul>
                            <li>Fluently master the Hiragana and Katakana alphabets, over 100 Kanji characters, and the
                                basic grammar of N5 level.</li>
                            <li>Use Japanese to communicate in simple situations with native speakers.</li>
                            <li>Confidently conquer the JLPT with the highest score.</li>
                            <li>Have a solid grasp of fundamental knowledge as a prerequisite for studying higher levels of
                                Japanese.</li>
                            <li>PROVIDE dedicated support to address any concerns or questions from students regarding
                                study-related issues as well as working in Japan.</li>
                            <li>Save costs when registering for the N5-N4, N5-N3 combo courses.</li>
                        </ul>
                    </div>
                </div>

                <div id="QandA" class="tabcontent">
                    <div class="content">
                        <div class="writeComment">
                            <form action="">
                                <input placeholder="Write your comment" type="text" name="comment">
                                <input type="submit" value="Post">
                            </form>
                        </div>

                        <!-- comment area -->
                        <div class="comments">
                            <!-- start a comment -->
                            <div class="comment">
                                <img src="../public/assets/imgs/D1.jpg" alt="" class="userAvatar">
                                <div class="commentContent">
                                    <p class="userName">Nguyen Thanh Duong</p>
                                    <p class="content">
                                        W3Schools is optimized for learning and training. Examples might be simplified to improve reading and learning. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using W3Schools, you agree to have read and accepted our terms of use, cookie and privacy policy.
                                    </p>
                                    <span class="option">
                                        <span class="time">03/07/2023</span>
                                        <!-- Edit -->
                                        <!-- <span>Edit</span> -->
                                        <!-- Reply -->
                                        <span>Reply</span>
                                    </span>
                                </div>
                            </div>
                            <!-- end a comment -->
                            <div class="comment">
                                <img src="../public/assets/imgs/instructor.png" alt="" class="userAvatar">
                                <div class="commentContent">
                                    <p class="userName">Nguyen Thanh Duong</p>
                                    <p class="content">
                                        W3Schools is optimized for learning and training. Examples might be simplified to improve reading and learning. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using W3Schools, you agree to have read and accepted our terms of use, cookie and privacy policy.
                                    </p>
                                    <span class="option">
                                        <span class="time">03/07/2023</span>
                                        <!-- Edit -->
                                        <!-- <span>Edit</span> -->
                                        <!-- Reply -->
                                        <span>Reply</span>
                                    </span>
                                </div>
                            </div>
                            <!-- end a comment -->
                            <div class="comment">
                                <img src="../public/assets/imgs/logo.png" alt="" class="userAvatar">
                                <div class="commentContent">
                                    <p class="userName">Nguyen Thanh Duong</p>
                                    <p class="content">
                                        W3Schools is optimized for learning and training. Examples might be simplified to improve reading and learning. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content. While using W3Schools, you agree to have read and accepted our terms of use, cookie and privacy policy.
                                    </p>
                                    <span class="option">
                                        <span class="time">03/07/2023</span>
                                        <!-- Edit -->
                                        <!-- <span>Edit</span> -->
                                        <!-- Reply -->
                                        <span>Reply</span>
                                    </span>
                                </div>
                            </div>
                            <!-- end a comment -->
                        </div>

                        <ul class="menu">
                            <li><i class="fa-sharp fa-solid fa-angle-left"></i></li>
                            <li class="active"><a href="#">1</a></li>
                            <li class=""><a href="#">2</a></li>
                            <li class=""><a href="#">3</a></li>
                            <li class=""><a href="#">4</a></li>
                            <li class=""><a href="#">5</a></li>
                            <li class=""><a href="#">6</a></li>
                            <li class=""><a href="#">7</a></li>
                            <li class=""><i class="fa-sharp fa-solid fa-angle-right"></i></li>
                        </ul>
                    </div>
                </div>

                <div id="Rating" class="tabcontent active">
                    <div class="rating-container">
                        <div class="rating-left">
                            <h1>4.5</h1>
                            <i class="fas fa-star star"></i>
                            <i class="fas fa-star star"></i>
                            <i class="fas fa-star star"></i>
                            <i class="fas fa-star star"></i>
                            <i class="fas fa-star star"></i>
                        </div>
                        <div class="rating-right">
                            <div class="rating-detail">
                                <div class="progress-bar">
                                    <div class="progress" style="width: 80%;"></div>
                                </div>
                                <span>5<i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i></span>

                                <span>80%</span>
                            </div>
                            <div class="rating-detail">
                                <div class="progress-bar">
                                    <div class="progress" style="width: 10%;"></div>
                                </div>
                                <span>4<i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fa-regular fa-star"></i></span>

                                <span>10%</span>
                            </div>
                            <div class="rating-detail">
                                <div class="progress-bar">
                                    <div class="progress" style="width: 7%;"></div>
                                </div>
                                <span>3<i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i></span>

                                <span>7%</span>
                            </div>
                            <div class="rating-detail">
                                <div class="progress-bar">
                                    <div class="progress" style="width: 2%;"></div>
                                </div>
                                <span>2<i class="fas fa-star star"></i>
                                    <i class="fas fa-star star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i></span>

                                <span>2%</span>
                            </div>
                            <div class="rating-detail">
                                <div class="progress-bar">
                                    <div class="progress" style="width: 1%;"></div>
                                </div>
                                <span>1<i class="fas fa-star star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star">

                                    </i></span>

                                <span>1%</span>
                            </div>
                        </div>
                    </div>
                    <!-- Rating coment of user -->
                    <div class="rating-comments">
                        <!-- start a rating -->
                        <div class="rating-content">
                            <img src="../public/assets/imgs/gg.png" alt="" class="rateUserImg">
                            <div class="content">
                                <p class="UserRateName">
                                    Google
                                </p>
                                <div class="UserRateComment">
                                    Phần này thì hiển thị cái đánh giá của nguyên course, rồi hiển thị user nào đã rate
                                    Phần này thì hiển thị cái đánh giá của nguyên course, rồi hiển thị user nào đã rate
                                </div>
                                <div class="rateOption">
                                    <span class="like">
                                        <i class="fa-solid fa-thumbs-up"></i>
                                        <i class="fa-solid fa-thumbs-down"></i>
                                    </span>
                                    <span class="report"><b>Report</b></span>
                                </div>
                            </div>
                        </div>
                        <!-- end a rating -->
                        <!-- start a rating -->
                        <div class="rating-content">
                            <img src="../public/assets/imgs/gg.png" alt="" class="rateUserImg">
                            <div class="content">
                                <p class="UserRateName">
                                    Google
                                </p>
                                <div class="UserRateComment">
                                    Phần này thì hiển thị cái đánh giá của nguyên course, rồi hiển thị user nào đã rate
                                    Phần này thì hiển thị cái đánh giá của nguyên course, rồi hiển thị user nào đã rate
                                </div>
                                <div class="rateOption">
                                    <span class="like">
                                        <i class="fa-solid fa-thumbs-up"></i>
                                        <i class="fa-solid fa-thumbs-down"></i>
                                    </span>
                                    <span class="report"><b>Report</b></span>
                                </div>
                            </div>
                        </div>
                        <!-- end a rating -->
                        <!-- start a rating -->
                        <div class="rating-content">
                            <img src="../public/assets/imgs/gg.png" alt="" class="rateUserImg">
                            <div class="content">
                                <p class="UserRateName">
                                    Google
                                </p>
                                <div class="UserRateComment">
                                    Phần này thì hiển thị cái đánh giá của nguyên course, rồi hiển thị user nào đã rate
                                    Phần này thì hiển thị cái đánh giá của nguyên course, rồi hiển thị user nào đã rate
                                </div>
                                <div class="rateOption">
                                    <span class="like">
                                        <i class="fa-solid fa-thumbs-up"></i>
                                        <i class="fa-solid fa-thumbs-down"></i>
                                    </span>
                                    <span class="report"><b>Report</b></span>
                                </div>
                            </div>
                        </div>
                        <!-- end a rating -->


                    </div>

                </div>
            </div>
            <!-- Right side -->

            <%@include file="rightSideCourse.jsp" %>

        </div>

        <%@include file="footer.jsp" %>

        <%@include file="foot.jsp" %>

        <script src="../public/assets/js/lesson.js"></script>

        <%@include file="popUpMessage.jsp" %>

    </body>

</html>