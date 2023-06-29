/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Services.MD5;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class AdminDB extends DB {

    public static boolean existAdmin(String username) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select username from [admin] where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
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

    /**
     * check password of admin
     *
     * @param username
     * @param password
     * @param hashed true if password hashed
     * @return 0 - ok; 1 - not exist; 2 - incorrect pw
     */
    public static int checkAdmin(String username, String password, boolean hashed) {
        int status = -1;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select password from [admin] where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            //not exist
            if (!resultSet.next()) {
                status = 1;
            } else {
                String pw = resultSet.getString("password");
                if (!hashed) {
                    password = MD5.getMd5(password);
                }
                if (pw.equals(password)) {
                    //correct
                    status = 0;
                } else {
                    //not correct password
                    status = 2;
                }
            }

            //disconnect to database
            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return result
        return status;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        try {
            connect();

//            System.out.println(checkAdmin("admin", "Admin@123"));
//            System.out.println(checkAdmin("ffff", "Admin@123"));
//            System.out.println(checkAdmin("admin", "Admin@1234444"));

            System.out.println(checkAdmin("admin", "Admin@123", false));

            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
