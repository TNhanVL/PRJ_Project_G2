/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Country;
import com.prj_project_g2.Model.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class CountryDB extends DB {

    public static Country getCountry(int ID) {
        Country country = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select [name] from country where CountryID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                country = new Country(ID, resultSet.getString("name"));
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return country;
    }

    public static ArrayList<Country> getAllCountry() {
        ArrayList<Country> countries = new ArrayList<>();

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from country");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Country country = new Country(resultSet.getInt("countryID"), resultSet.getString("name"));
                countries.add(country);
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return countries;
    }

    public static void main(String[] args) {
        System.out.println(getAllCountry());
    }

}
