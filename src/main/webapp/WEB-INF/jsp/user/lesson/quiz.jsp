<%-- 
    Document   : quiz
    Created on : Jul 5, 2023, 8:55:55 PM
    Author     : TTNhan
--%>

<%@page import="java.util.Date"%>
<%@page import="com.prj_project_g2.Model.QuizResult"%>
<%@page import="com.prj_project_g2.Database.QuizResultDB"%>
<%@page import="com.prj_project_g2.Database.QuestionDB"%>
<%@page import="com.prj_project_g2.Model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    QuizResult quizResult = QuizResultDB.getLastQuizResult(user.getID(), lesson.getID());
    if (quizResult == null) {
%>
<%@include file="./preTakeQuiz.jsp" %>
<%
} else {
    ArrayList<Question> questions = QuestionDB.getQuestionByLessonID(lesson.getID());
%>


<div class="quiz-type1">
    <form action="" method="post">
        <div class="leftSide">
            <p class="quizProgress">Question: <span>1/<%out.print(questions.size());%></span> </p>

            <%
                //show all question
                for (Question question : questions) {
                    switch (question.getType() / 10) {
                        case 0: {
            %>
            <%@include file="../question/image.jsp" %>
            <%                                                break;
                        }
                        default: {
                            break;
                        }
                    }

                }
            %>

            <%@include file="../question/updateAnswers.jsp" %>
            
            <div class="btns">
                <button>Continue</button>
                <!-- <button>Check</button> -->
            </div>
        </div>
        <div class="rightSide">
            <div class="time">
                <h5>Time remaining</h5>
                <i class="fa-regular fa-clock"></i>
                <span class="countdown"><%
                    //calculate remain time then format and print out
                    long timeSecond = (quizResult.getStartTime().getTime() + lesson.getTime() * 60000 - new Date().getTime()) / 1000;
                    if (timeSecond < 0) {
                        timeSecond = 0;
                    }
                    long seconds = timeSecond % 60;
                    timeSecond /= 60;
                    long minutes = timeSecond % 60;
                    timeSecond /= 60;
                    long hours = timeSecond;

                    String timeString = "";
                    if (hours > 0) {
                        timeString += hours + ":";
                    }
                    if (minutes < 10) {
                        timeString += "0";
                    }
                    timeString += minutes + ":";
                    if (seconds < 10) {
                        timeString += "0";
                    }
                    timeString += seconds;
                    out.print(timeString);
                    %></span>
            </div>
            <div class="listQuestion">
                <h5>Question</h5>
                <ul>
                    <%                        //show all questionLabel
                        for (int i = 1; i <= questions.size(); i++) {
                    %>
                    <li class=""><%out.print(i);%></li>
                        <%
                            }
                        %>
                </ul>
            </div>

            <div class="finishBtn"><input type="submit" value="Finish"></div>
        </div>
    </form>
</div>

<%
    }
%>