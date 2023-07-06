<%-- 
    Document   : multipleChoiceMultiOption
    Created on : Jul 5, 2023, 6:50:44 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Database.QuestionResultDB"%>
<%@page import="com.prj_project_g2.Model.Question"%>
<%@page import="java.util.Collections"%>
<%@page import="com.prj_project_g2.Database.AnswerDB"%>
<%@page import="com.prj_project_g2.Model.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p><b>Choose all correct answer:</b></p>
<%
//    Question question = null;
    ArrayList<Answer> answers = AnswerDB.getAnswersByQuestionID(question.getID());
    Collections.shuffle(answers);
    for (Answer answer : answers) {
        String answerName = "answer" + answer.getID();
        boolean checked = QuestionResultDB.CheckQuestionResult(quizResult.getID(), question.getID(), answer.getID());
%>
<label for="<%out.print(answerName);%>"><input type="checkbox" id="<%out.print(answerName);%>" name="question<%out.print(question.getID());%>"
                                               value="<%out.print(answer.getID());%>"
                                               <%if (checked) {
                                                       out.print("checked");
                                                   }%>
                                               ><%out.print(answer.getContent());%></label><br>
    <%
        }
    %>
