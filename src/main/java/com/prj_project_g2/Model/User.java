
package com.prj_project_g2.Model;

import java.util.Date;

/**
 *
 * @author Thanh Duong
 */
public class User {
    private int ID;
    private String avatar;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private Date birthday;
    private int countryID;

    public User() {
    }

    public User(int ID, String avatar, String username, String password, String email, String firstName, String lastName, Date birthday, int countryID) {
        this.ID = ID;
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstName;
        this.lastname = lastName;
        this.birthday = birthday;
        this.countryID = countryID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + ", avatar=" + avatar + ", username=" + username + ", password=" + password + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", birthday=" + birthday + ", countryID=" + countryID + '}';
    }
}
