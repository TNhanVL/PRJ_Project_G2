/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Question;
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
public class QuestionDB {

    static Connection conn;
    static PreparedStatement statement;

    public static boolean existQuestion(int ID) {
        boolean ok = false;
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select ID from question where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt("ID") == ID) {
                    ok = true;
                }
            }

            //disconnect to database
            DB.disconnect(conn);
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
            conn = DB.connect();

            statement = conn.prepareStatement("select * from question where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                question = new Question(
                        resultSet.getInt("ID"),
                        resultSet.getString("content"),
                        resultSet.getInt("type"),
                        resultSet.getInt("point")
                );
            }

            DB.disconnect(conn);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return question;
    }

    public static boolean insertQuestion(Question question) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("insert into question(content,type,point) values (?,?,?)");
            statement.setString(1, question.getContent());
            statement.setInt(2, question.getType());
            statement.setInt(3, question.getPoint());
            statement.executeUpdate();
            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateQuestion(Question question) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("update question set content=?, type=?, point=? where ID=?");
            statement.setString(1, question.getContent());
            statement.setInt(2, question.getType());
            statement.setInt(3, question.getPoint());
            statement.setInt(4, question.getID());

            //disconnect to database
            DB.disconnect(conn);
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
            conn = DB.connect();
            statement = conn.prepareStatement("delete from question where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            DB.disconnect(conn);
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
//        Question q = getQuestion(2);
//        System.out.println(q);
////        insertQuestion(q);
//        q.setPoint(5);
//        updateQuestion(q);
//        deleteQuestion(3);
    }
}
