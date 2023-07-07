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
    boolean quizFinished = false;
    if (quizResult == null) {
        //If not take quiz yet
%>
<%@include file="./preTakeQuiz.jsp" %>
<%
} else if (quizResult.getEndTime().after(new Date())) {
    //if are taking quiz
%>
<%@include file="./quizTaking.jsp" %>
<%
} else {
%>
<%@include file="./quizResult.jsp" %>
<%
    }
%>