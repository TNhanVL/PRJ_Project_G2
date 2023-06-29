/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.User;
import com.prj_project_g2.Services.MD5;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TTNhan
 */
public class UserDB extends DB {

    public static boolean existUser(String username) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select username from [user] where username = ?");
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
     * check password of a user
     *
     * @param username
     * @param password
     * * @param hashed true if password hashed
     * @return 0 - ok; 1 - not exist; 2 - incorrect pw
     */
    public static int checkUser(String username, String password, boolean hashed) {
        int status = -1;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select password from [user] where username = ?");
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
                if (pw.equals(MD5.getMd5(password))) {
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

    public static User getUser(int ID) {
        User user = null;

        try {
            //connect to database
            connect();

            System.out.println("1");

            statement = conn.prepareStatement("select * from [user] where ID = ?");
            statement.setString(1, "1");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
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

        return user;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        User user = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from [user] where ID = ?");
            statement.setString(1, "1");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
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

        System.out.println(user);
    }
}
