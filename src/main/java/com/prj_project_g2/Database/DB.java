/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
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
public class DB {

    static Connection conn;
    static PreparedStatement statement;

    static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(Config.SERVER);
        ds.setUser(Config.USER);
        ds.setPassword(Config.PASSWORD);
        ds.setPortNumber(Config.PORT);
        ds.setDatabaseName(Config.DATABASE_NAME);
        ds.setEncrypt(false);

        return ds.getConnection();
    }

    static void disconnect(Connection conn) throws SQLException {
        conn.close();
    }

    public static void main(String[] args) throws ClassNotFoundException {

        try {
            conn = connect();

            statement = conn.prepareStatement("select username from [user]");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

            disconnect(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
