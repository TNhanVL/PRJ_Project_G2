/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import static com.prj_project_g2.Database.DB.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TTNhan
 */
public class UserDB {

    static Connection conn;
    static PreparedStatement statement;

    public static boolean existUser(String username) {
        boolean ok = false;
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select username from [user] where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
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

    public static void main(String[] args) throws ClassNotFoundException {

        try {
            conn = DB.connect();

            statement = conn.prepareStatement("select username from [user]");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

            DB.disconnect(conn);
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
