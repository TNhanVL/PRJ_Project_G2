/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import static com.prj_project_g2.Database.DB.disconnect;
import com.prj_project_g2.Model.Organization;
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
public class OrganizationDB {

    static Connection conn;
    static PreparedStatement statement;

    public static boolean existOrganization(int ID) {
        boolean ok = false;
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select ID from organization where ID = ?");
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

    public static Organization getOrganization(int ID) {
        Organization organization = null;

        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select * from organization where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                organization = new Organization(
                        resultSet.getInt("ID"),
                        resultSet.getString("name"),
                        resultSet.getString("logo"),
                        resultSet.getString("description"));
            }

            DB.disconnect(conn);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return organization;
    }

    public static boolean insertOrganization(Organization organization) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("insert into organization(name,logo,description) values (?,?,?)");
            statement.setString(1, organization.getName());
            statement.setString(2, organization.getLogo());
            statement.setString(3, organization.getDescription());
            statement.executeUpdate();

            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateOrganization(Organization organization) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("update organization set name=?, logo=?,description=? where ID =?");
            statement.setString(1, organization.getName());
            statement.setString(2, organization.getLogo());
            statement.setString(3, organization.getDescription());
            statement.setInt(4, organization.getID());
            statement.executeUpdate();

            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteOrganization(int ID) {
        try {
            if (!existOrganization(ID)) {
                return false;
            }
            conn = DB.connect();
            statement = conn.prepareStatement("delete from organization where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect(conn);
            if (!existOrganization(ID)) {
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
        System.out.println(existOrganization(1));
//        Organization o = new Organization(1, "Vin Group", "htttps://VinID.jpg", "very well");
////        System.out.println(getOrganization(2));
//        o.setLogo("htttps://VinID.logo.jpg");
//        o.setID(2);
//        o.setDescription("well");
//        o.setName("FPT");
//        updateOrganization(o);
//        deleteOrganization(4);
    }
}
