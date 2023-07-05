<%-- 
    Document   : quiz
    Created on : Jul 5, 2023, 8:55:55 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Database.QuestionDB"%>
<%@page import="com.prj_project_g2.Model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
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

            <div class="btns">
                <button>Continue</button>
                <!-- <button>Check</button> -->
            </div>
        </div>
        <div class="rightSide">
            <div class="time">
                <h5>Time remaining</h5>
                <i class="fa-regular fa-clock"></i>
                <span>15:00</span>
            </div>
            <div class="listQuestion">
                <h5>Question</h5>
                <ul>
                    <%
                        //show all questionLabel
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