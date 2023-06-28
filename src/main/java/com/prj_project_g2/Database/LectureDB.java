/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import static com.prj_project_g2.Database.DB.disconnect;
import com.prj_project_g2.Model.Lecturer;
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
public class LectureDB {

    static Connection conn;
    static PreparedStatement statement;

    public static boolean existLecture(int ID) {
        boolean ok = false;
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select ID from lecturer where ID = ?");
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

    public static Lecturer getLecturer(int ID) {
        Lecturer lecturer = null;

        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select * from lecturer where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lecturer = new Lecturer(ID, resultSet.getInt("userID"), resultSet.getInt("organizationID"));
            }

            DB.disconnect(conn);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lecturer;
    }

    public static boolean insertLecturer(Lecturer lec) {

        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("insert into lecturer(userID,organizationID) values(?,?)");
            statement.setInt(1, lec.getUserID());
            statement.setInt(2, lec.getOrganizationID());
            statement.executeUpdate();

            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateLecturer(Lecturer lec) {

        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("update lecturer set userID=?, organizationID=? where ID = ?");
            statement.setInt(1, lec.getUserID());
            statement.setInt(2, lec.getOrganizationID());
            statement.setInt(3, lec.getID());
            statement.executeUpdate();

            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean deleteLecturer(int ID) {
        try {
            if (!existLecture(ID)) {
                return false;
            }
            conn = DB.connect();
            statement = conn.prepareStatement("delete from lecturer where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect(conn);
            if (!existLecture(ID)) {
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
//        
//        Lecturer le = new Lecturer(3, 1, 1);
//        insertLecturer(le);
//        
//        Lecturer l2 = getLecturer(4);
//        l2.setOrganizationID(1);

//        System.out.println("**************************");
//        System.out.println(l2.toString());
//        System.out.println("**************************");
        //     System.out.println(deleteLecturer(4));
    }
}
