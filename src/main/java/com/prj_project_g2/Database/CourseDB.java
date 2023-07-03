/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                        resultSet.getString("image"),
                        resultSet.getString("description"),
                        resultSet.getInt("organizationID"),
                        resultSet.getInt("lecturerID"),
                        resultSet.getDouble("unSalePrice"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("rate"));
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return course;
    }

    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from course");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getInt("ID"),
                        resultSet.getString("title"),
                        resultSet.getString("image"),
                        resultSet.getString("description"),
                        resultSet.getInt("organizationID"),
                        resultSet.getInt("lecturerID"),
                        resultSet.getDouble("unSalePrice"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("rate"));
                courses.add(course);
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public static boolean insertCourse(Course course) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into course(title,[image],[description],organizationID,lecturerID,unSalePrice,price,rate) values (?,?,?,?,?,?,?,?)");
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getImage());
            statement.setString(3, course.getDescription());
            statement.setInt(4, course.getOrganizationID());
            statement.setInt(5, course.getLecturerID());
            statement.setDouble(6, course.getUnSalePrice());
            statement.setDouble(7, course.getPrice());
            statement.setDouble(8, course.getRate());

            statement.execute();

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

            statement = conn.prepareStatement("update course set title=?, [image]=?, [description]=?,organizationID=?,lecturerID=?,unSalePrice=?,price=?,rate=? where ID =?");
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getImage());
            statement.setString(3, course.getDescription());
            statement.setInt(4, course.getOrganizationID());
            statement.setInt(5, course.getLecturerID());
            statement.setDouble(6, course.getUnSalePrice());
            statement.setDouble(7, course.getPrice());
            statement.setDouble(8, course.getRate());
            statement.setInt(9, course.getID());

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

    public static boolean checkOrderCourse(int userID, int courseID) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select 1 from orderCourse where userID = ? and courseID = ?");
            statement.setInt(1, userID);
            statement.setInt(2, courseID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

            //disconnect to database
            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return result
        return false;
    }

    public static boolean insertOrderCourse(int userID, int courseID) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into orderCourse(userID, courseID) values (?,?)");
            statement.setInt(1, userID);
            statement.setInt(2, courseID);

            statement.execute();

            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(existCourse(1));
//
//        Course c = new Course(11, "Java basic", "https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://d15cw65ipctsrr.cloudfront.net/17/6b66f0a7ea11e7a885e33e8374f520/software_development_lifecycle_logo_pexels_CC0.jpg?auto=format&dpr=1&w=100&h=100&fit=clamp", "ezsy", 1, 1, 1000, 500, 4.5);
//        insertCourse(c);

//        System.out.println(checkOrderCourse(1, 1));
//
//        System.out.println(getCourse(11));
        System.out.println(insertOrderCourse(1, 1));
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
