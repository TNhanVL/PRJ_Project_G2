/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.QuizResult;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class QuizResultDB extends DB {

    public static boolean existQuizResult(int ID) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select ID from quizResult where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt("ID") == ID) {
                    ok = true;
                }
            }

            //disconnect to database
            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return result
        return ok;
    }

    public static QuizResult getQuizResult(int ID) {
        QuizResult quizResult = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from quizResult where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                quizResult = new QuizResult(
                        resultSet.getInt("ID"),
                        resultSet.getInt("lessonID"),
                        resultSet.getInt("userID"),
                        resultSet.getTimestamp("startTime"),
                        resultSet.getTimestamp("endTime")
                );
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return quizResult;
    }

    public static QuizResult getLastQuizResult(int userID, int lessonID) {
        QuizResult quizResult = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select top 1 * from quizResult where userID = ? and lessonID = ? order by startTime desc");
            statement.setInt(1, userID);
            statement.setInt(2, lessonID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                quizResult = new QuizResult(
                        resultSet.getInt("ID"),
                        resultSet.getInt("lessonID"),
                        resultSet.getInt("userID"),
                        resultSet.getTimestamp("startTime"),
                        resultSet.getTimestamp("endTime")
                );
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return quizResult;
    }

    public static int insertQuizResult(QuizResult quizResult) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into quizResult(lessonID,userID,startTime,endTime) values(?,?,?,?)");
            statement.setInt(1, quizResult.getLessonID());
            statement.setInt(2, quizResult.getUserID());
            statement.setString(3, dateFormat.format(quizResult.getStartTime()));
            statement.setString(4, dateFormat.format(quizResult.getEndTime()));
            statement.executeUpdate();

            int newID = lastModifyID(conn);

            //disconnect to database
            disconnect();

            return newID;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return -1;
    }

    public static boolean updateQuizResult(QuizResult quizResult) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("update quizResult set lessonID=?,userID=?, startTime=?, endTime=? where ID=?");
            statement.setInt(1, quizResult.getLessonID());
            statement.setInt(2, quizResult.getUserID());
            statement.setString(3, dateFormat.format(quizResult.getStartTime()));
            statement.setString(4, dateFormat.format(quizResult.getEndTime()));
            statement.setInt(5, quizResult.getID());
            statement.executeUpdate();

            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteQuizResult(int ID) {
        try {
            if (!existQuizResult(ID)) {
                return false;
            }
            connect();
            statement = conn.prepareStatement("delete from quizResult where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect();
            return !existQuizResult(ID);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
