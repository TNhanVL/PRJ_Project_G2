/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import static com.prj_project_g2.Database.DB.disconnect;
import com.prj_project_g2.Model.Mooc;
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
public class MoocDB {

    static Connection conn;
    static PreparedStatement statement;

    public static boolean existMooc(int ID) {
        boolean ok = false;
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select ID from mooc where ID = ?");
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

    public static Mooc getMooc(int ID) {
        Mooc mooc = null;

        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select * from mooc where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mooc = new Mooc(
                        resultSet.getInt("ID"),
                        resultSet.getInt("courseID"),
                        resultSet.getInt("index"),
                        resultSet.getString("title"),
                        resultSet.getString("description"));
            }

            DB.disconnect(conn);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mooc;
    }

    public static boolean insertMooc(Mooc mooc) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("insert into mooc(courseID,[index],title,description) values(?,?,?,?)");
            statement.setInt(1, mooc.getCourseID());
            statement.setInt(2, mooc.getIndex());
            statement.setString(3, mooc.getTitle());
            statement.setString(4, mooc.getDescription());
            statement.executeUpdate();

            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateMooc(Mooc mooc) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("update mooc set courseID=?, [index]=?, title=?, description=? where ID=?");
            statement.setInt(1, mooc.getCourseID());
            statement.setInt(2, mooc.getIndex());
            statement.setString(3, mooc.getTitle());
            statement.setString(4, mooc.getDescription());
            statement.setInt(5, mooc.getID());
            statement.executeUpdate();

            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteMooc(int ID) {
        try {
            if (!existMooc(ID)) {
                return false;
            }
            conn = DB.connect();
            statement = conn.prepareStatement("delete from mooc where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect(conn);
            if (!existMooc(ID)) {
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
//        Mooc m = getMooc(2);
//        m.setTitle("Who...");
//        insertMooc(m);
//        m.setDescription("yah");
//        updateMooc(m);
//          deleteMooc(3);
    }
}
