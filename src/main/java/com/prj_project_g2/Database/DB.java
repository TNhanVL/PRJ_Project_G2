/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TTNhan
 */
public class DB {

    static Connection conn;
    static PreparedStatement statement;

    private static void connect() throws SQLException, ClassNotFoundException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(Config.SERVER);
        ds.setUser(Config.USER);
        ds.setPassword(Config.PASSWORD);
        ds.setPortNumber(Config.PORT);
        ds.setDatabaseName(Config.DATABASE_NAME);
        ds.setEncrypt(false);

        conn = ds.getConnection();
    }

    private static void disconnect() throws SQLException {
        conn.close();
    }

    public static void main(String[] args) throws ClassNotFoundException {

        try {
            connect();

            statement = conn.prepareStatement("select username from [user]");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
