/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.QuestionResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class QuestionResultDB extends DB {

    public static boolean insertQuestionResult(int quizResultID, int questionID, int selectedAnswer) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into questionResult(quizResultID,questionID,selectedAnswer) values(?,?,?)");
            statement.setInt(1, quizResultID);
            statement.setInt(2, questionID);
            statement.setInt(3, selectedAnswer);
            statement.execute();

            //disconnect to database
            disconnect();

            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static void deleteQuestionResultOfQuestion(int quizResultID, int questionID) {
        try {
            connect();
            statement = conn.prepareStatement("delete from questionResult where quizResultID = ? and questionID = ?");
            statement.setInt(1, quizResultID);
            statement.setInt(2, questionID);
            statement.execute();
            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        deleteQuestionResultOfQuestion(1, 3);
    }
}
