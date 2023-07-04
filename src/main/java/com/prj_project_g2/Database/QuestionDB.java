/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Question;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class QuestionDB extends DB {

    public static boolean existQuestion(int ID) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select ID from question where ID = ?");
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

    public static Question getQuestion(int ID) {
        Question question = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from question where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                question = new Question(
                        resultSet.getInt("ID"),
                        resultSet.getInt("lessonID"),
                        resultSet.getInt("index"),
                        resultSet.getString("content"),
                        resultSet.getInt("type"),
                        resultSet.getInt("point")
                );
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return question;
    }

    public static ArrayList<Question> getAllQuestionByLessonID(int lessonID) {
        ArrayList<Question> questions = new ArrayList<>();

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from question where lessonID = ?");
            statement.setInt(1, lessonID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Question question = new Question(
                        resultSet.getInt("ID"),
                        resultSet.getInt("lessonID"),
                        resultSet.getInt("index"),
                        resultSet.getString("content"),
                        resultSet.getInt("type"),
                        resultSet.getInt("point")
                );
                questions.add(question);
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return questions;
    }

    public static boolean insertQuestion(Question question) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into question(lessonID,[index],content,[type],point) values (?,?,?,?)");
            statement.setInt(1, question.getLessonID());
            statement.setInt(2, question.getIndex());
            statement.setString(3, question.getContent());
            statement.setInt(4, question.getType());
            statement.setInt(5, question.getPoint());
            statement.executeUpdate();
            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateQuestion(Question question) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("update question set lessonID=?, [index]=?, content=?, [type]=?, point=? where ID=?");
            statement.setInt(1, question.getLessonID());
            statement.setInt(2, question.getIndex());
            statement.setString(3, question.getContent());
            statement.setInt(4, question.getType());
            statement.setInt(5, question.getPoint());
            statement.setInt(6, question.getID());

            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteQuestion(int ID) {
        try {
            if (!existQuestion(ID)) {
                return false;
            }
            connect();
            statement = conn.prepareStatement("delete from question where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect();
            if (!existQuestion(ID)) {
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
        System.out.println(getAllQuestionByLessonID(2));
    }
}
