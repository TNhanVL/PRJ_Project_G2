/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Lesson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class LessonDB extends DB {

    public static boolean existLesson(int ID) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select ID from lesson where ID = ?");
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

    public static Lesson getLesson(int ID) {
        Lesson lesson = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from lesson where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lesson = new Lesson(
                        resultSet.getInt("ID"),
                        resultSet.getInt("MoocID"),
                        resultSet.getString("title"),
                        resultSet.getInt("index"),
                        resultSet.getInt("type"),
                        resultSet.getInt("time"));
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lesson;
    }

    public static ArrayList<Lesson> getLessonsByMoocID(int moocID) {
        ArrayList<Lesson> lessons = new ArrayList<>();

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from lesson where moocID = ? order by [index]");
            statement.setInt(1, moocID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Lesson lesson = new Lesson(
                        resultSet.getInt("ID"),
                        resultSet.getInt("MoocID"),
                        resultSet.getString("title"),
                        resultSet.getInt("index"),
                        resultSet.getInt("type"),
                        resultSet.getInt("time"));
                lessons.add(lesson);
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lessons;
    }

    public static boolean insertLesson(Lesson lesson) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into lesson(moocID,title,[index],[type],[time]) values(?,?,?,?,?)");
            statement.setInt(1, lesson.getMoocID());
            statement.setString(2, lesson.getTitle());
            statement.setInt(3, lesson.getIndex());
            statement.setInt(4, lesson.getType());
            statement.setInt(5, lesson.getTime());
            statement.executeUpdate();
            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateLesson(Lesson lesson) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("update lesson set moocID=?, title=?, [index]=?, type=?, [time]=? where ID=?");
            statement.setInt(1, lesson.getMoocID());
            statement.setString(2, lesson.getTitle());
            statement.setInt(3, lesson.getIndex());
            statement.setInt(4, lesson.getType());
            statement.setInt(5, lesson.getTime());
            statement.setInt(6, lesson.getID());
            statement.executeUpdate();

            //disconnect to database
            disconnect();
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
            connect();
            statement = conn.prepareStatement("delete from lesson where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect();
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
        System.out.println(getLessonsByMoocID(1));
    }
}
