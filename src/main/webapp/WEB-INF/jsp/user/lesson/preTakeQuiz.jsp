<%-- 
    Document   : preTakeQuiz
    Created on : Jul 6, 2023, 7:16:02 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Database.QuestionDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="quiz-type1">
    <form action="" method="post">
        <div class="leftSide">
            <div class="leftSideContent">
                <h1>Press Take Quiz to continue!</h1>
            </div>
        </div>
        <div class="rightSide">
            <div class="time">
                <h5>Overview</h5>
                <i class="fa-regular fa-clock"></i>
                <span><%out.print(lesson.getTime());%>:00</span>
            </div>
            <div class="listQuestion">
                <h5><%out.print(QuestionDB.getNumberQuestionByLessonID(lesson.getID()));%> questions</h5>
            </div>

            <div class="finishBtn"><a href="<%out.print(request.getContextPath());%>/user/startAQuiz/<%out.print(lesson.getID());%>"><p>Take Quiz</p></a></div>
        </div>
    </form>
</div>
