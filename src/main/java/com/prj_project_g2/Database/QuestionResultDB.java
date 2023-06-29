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
    
    static Connection conn;
    static PreparedStatement statement;

//    public static boolean existQuestionResult(int ID) {
//        boolean ok = false;
//        try {
//            //connect to database
//            connect();
//
//            statement = conn.prepareStatement("select ID from questionResult where ID = ?");
//            statement.setInt(1, ID);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                if (resultSet.getInt("ID") == ID) {
//                    ok = true;
//                }
//            }
//
//            //disconnect to database
//            disconnect();
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //return result
//        return ok;
//    }
//
//    public static QuestionResult getQuestionResult(int ID) {
//        QuestionResult questionResult = null;
//
//        try {
//            //connect to database
//            connect();
//
//            statement = conn.prepareStatement("select * from questionResult where ID = ?");
//            statement.setInt(1, ID);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                questionResult = new QuestionResult(
//                        resultSet.getInt("ID"),
//                        resultSet.getString("content"),
//                        resultSet.getBoolean("correct"),
//                        resultSet.getInt("questionID")
//                );
//            }
//
//            disconnect();
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return questionResult;
//    }
//
//    public static boolean insertQuestionResult(QuestionResult questionResult) {
//        try {
//            //connect to database
//            connect();
//
//            statement = conn.prepareStatement("insert into questionResult(content,correct,questionID) values (?,?,?)");
//            statement.setString(1, questionResult.getContent());
//            statement.setBoolean(2, questionResult.isCorrect());
//            statement.setInt(3, questionResult.getQuestionID());
//            statement.executeUpdate();
//            //disconnect to database
//            disconnect();
//            return true;
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return false;
//    }
//
//    public static boolean updateQuestionResult(QuestionResult questionResult) {
//        try {
//            //connect to database
//            connect();
//
//            statement = conn.prepareStatement("update questionResult set content=?, correct=?, questionID=? where ID=?");
//            statement.setString(1, questionResult.getContent());
//            statement.setBoolean(2, questionResult.isCorrect());
//            statement.setInt(3, questionResult.getQuestionID());
//            statement.setInt(4, questionResult.getID());
//
//            //disconnect to database
//            disconnect();
//            return true;
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    public static boolean deleteQuestionResult(int ID) {
//        try {
//            if (!existQuestionResult(ID)) {
//                return false;
//            }
//            connect();
//            statement = conn.prepareStatement("delete from questionResult where ID=?");
//            statement.setInt(1, ID);
//            statement.execute();
//            disconnect();
//            if (!existQuestionResult(ID)) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
}
