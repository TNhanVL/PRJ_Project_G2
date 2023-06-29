/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Lesson;
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
public class LessonDB {

    static Connection conn;
    static PreparedStatement statement;

    public static boolean existLesson(int ID) {
        boolean ok = false;
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select ID from lesson where ID = ?");
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

    public static Lesson getLesson(int ID) {
        Lesson lesson = null;

        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select * from lesson where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lesson = new Lesson(
                        resultSet.getInt("ID"),
                        resultSet.getInt("MoocID"),
                        resultSet.getString("title"),
                        resultSet.getInt("type"));
            }

            DB.disconnect(conn);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lesson;
    }

    public static boolean insertLesson(Lesson lesson) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("insert into lesson(moocID,title,type) values(?,?, ?)");
            statement.setInt(1, lesson.getMoocID());
            statement.setString(2, lesson.getTitle());
            statement.setInt(3, lesson.getType());
            statement.executeUpdate();
            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateLesson(Lesson lesson) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("update lesson set moocID=?, title=?, type=? where ID=?");
            statement.setInt(1, lesson.getMoocID());
            statement.setString(2, lesson.getTitle());
            statement.setInt(3, lesson.getType());
            statement.setInt(4, lesson.getID());
            statement.executeUpdate();

            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteLesson(int ID) {
        try {
            if (!existLesson(ID)) {
                return false;
            }
            conn = DB.connect();
            statement = conn.prepareStatement("delete from lesson where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            DB.disconnect(conn);
            if (!existLesson(ID)) {
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
//        System.out.println(existLesson(3));
//        Lesson l = getLesson(4);
//        System.out.println(l);
//        l.setTitle("what...");
//        updateLesson(l);
//        
//        insertLesson(getLesson(5));
//          deleteLesson(5);          deleteLesson(7);
//          deleteLesson(6);

        
    }
}
