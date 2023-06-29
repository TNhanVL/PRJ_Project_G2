/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Answer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class AnswerDB extends DB {

    public static boolean existAnswer(int ID) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select ID from answer where ID = ?");
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

    public static Answer getAnswer(int ID) {
        Answer answer = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from answer where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                answer = new Answer(
                        resultSet.getInt("ID"),
                        resultSet.getString("content"),
                        resultSet.getBoolean("correct"),
                        resultSet.getInt("questionID")
                );
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return answer;
    }

    public static boolean insertAnswer(Answer answer) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into answer(content,correct,questionID) values (?,?,?)");
            statement.setString(1, answer.getContent());
            statement.setBoolean(2, answer.isCorrect());
            statement.setInt(3, answer.getQuestionID());
            statement.executeUpdate();
            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateAnswer(Answer answer) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("update answer set content=?, correct=?, questionID=? where ID=?");
            statement.setString(1, answer.getContent());
            statement.setBoolean(2, answer.isCorrect());
            statement.setInt(3, answer.getQuestionID());
            statement.setInt(4, answer.getID());

            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteAnswer(int ID) {
        try {
            if (!existAnswer(ID)) {
                return false;
            }
            connect();
            statement = conn.prepareStatement("delete from answer where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect();
            if (!existAnswer(ID)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
//        Answer a = getAnswer(1);
//        System.out.println(a);
//        Answer a1 = getAnswer(2);
//        a1.setCorrect(true);
//        updateAnswer(a1);
//        deleteAnswer(3);
//        insertAnswer(a);

    }
}
