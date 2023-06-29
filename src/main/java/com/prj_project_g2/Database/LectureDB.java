/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Lecturer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class LectureDB extends DB {

    public static boolean existLecture(int ID) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select ID from lecturer where ID = ?");
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

    public static Lecturer getLecturer(int ID) {
        Lecturer lecturer = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from lecturer where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lecturer = new Lecturer(ID, resultSet.getInt("userID"), resultSet.getInt("organizationID"));
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lecturer;
    }

    public static int insertLecturer(Lecturer lec) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into lecturer(userID,organizationID) values(?,?)");
            statement.setInt(1, lec.getUserID());
            statement.setInt(2, lec.getOrganizationID());
            statement.executeUpdate();
            //Indentify the last ID inserted
            int newID = DB.lastModifyID(conn);
            //disconnect to database
            disconnect();
            return newID;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return -1;
    }

    public static boolean updateLecturer(Lecturer lec) {

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("update lecturer set userID=?, organizationID=? where ID = ?");
            statement.setInt(1, lec.getUserID());
            statement.setInt(2, lec.getOrganizationID());
            statement.setInt(3, lec.getID());
            statement.executeUpdate();

            //disconnect to database
            disconnect();
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
            connect();
            statement = conn.prepareStatement("delete from lecturer where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect();
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
//        System.out.println(nearestInsertID());
//            nearestInsertID();
//            System.out.println(insertLecturer(new Lecturer(4, 4, 4)));
    }
}
