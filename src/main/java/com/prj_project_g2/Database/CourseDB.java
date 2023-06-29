/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class CourseDB extends DB {

    public static boolean existCourse(int ID) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select ID from course where ID = ?");
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

    public static Course getCourse(int ID) {
        Course course = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from course where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                course = new Course(
                        resultSet.getInt("ID"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("organizationID"),
                        resultSet.getInt("lecturerID"),
                        resultSet.getInt("price"));
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return course;
    }

    public static boolean insertCourse(Course course) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into course(title,description,organizationID,lecturerID,price) values (?,?,?,?,?)");
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getOrganizationID());
            statement.setInt(4, course.getLecturerID());
            statement.setInt(5, course.getPrice());

            statement.executeUpdate();

            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateCourse(Course course) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("update course set title=?, description=?,organizationID=?,lecturerID=?,price=? where ID =?");
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getOrganizationID());
            statement.setInt(4, course.getLecturerID());
            statement.setInt(5, course.getPrice());
            statement.setInt(6, course.getID());

            statement.executeUpdate();

            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteCourse(int ID) {
        try {
            if (!existCourse(ID)) {
                return false;
            }
            connect();
            statement = conn.prepareStatement("delete from course where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect();
            if (!existCourse(ID)) {
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
//        System.out.println(existCourse(1));
//
        Course c = new Course(11, "Java basic", "ezsy", 1, 1, 50000);
        insertCourse(c);
//        System.out.println(existCourse(11));
//
//        System.out.println(getCourse(11));
//
//        c.setDescription("Normal");
//
//        updateCourse(c);
//
//        System.out.println(getCourse(11));

//        deleteCourse(13);
//        deleteCourse(12);
//        deleteCourse(11);

//        System.out.println(existCourse(1));
    }

}
