/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import static com.prj_project_g2.Database.DB.conn;
import static com.prj_project_g2.Database.DB.connect;
import static com.prj_project_g2.Database.DB.disconnect;
import static com.prj_project_g2.Database.DB.statement;
import com.prj_project_g2.Model.User;
import com.prj_project_g2.Services.CookieServices;
import com.prj_project_g2.Services.MD5;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;

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

    public static ArrayList<User> getAllUsers(Cookie[] cookies) {
        ArrayList<User> list = new ArrayList<>();

        //check admin permission
        if (!CookieServices.checkAdminLoggedIn(cookies)) {
            return list;
        }

        try {
            //connect to database
            connect();

            System.out.println("1");

            statement = conn.prepareStatement("select * from [user] where ID = ?");
            statement.setString(1, "1");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("ID"),
                        resultSet.getString("avatar"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("birthday"),
                        resultSet.getInt("countryID"),
                        resultSet.getInt("status")
                );
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
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
