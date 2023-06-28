/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import static com.prj_project_g2.Database.DB.disconnect;
import com.prj_project_g2.Model.Post;
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
public class PostDB {

    static Connection conn;
    static PreparedStatement statement;

    public static boolean existPost(int ID) {
        boolean ok = false;
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select ID from post where ID = ?");
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

    public static Post getPost(int ID) {
        Post post = null;

        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("select * from post where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("ID"),
                        resultSet.getString("content"),
                        resultSet.getInt("lessonID"));
            }

            DB.disconnect(conn);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return post;
    }

    public static boolean insertPost(Post post) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("insert into post(content,lessonID) values (?,?)");
            statement.setString(1, post.getContent());
            statement.setInt(2, post.getLessonID());
            statement.executeUpdate();
            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updatePost(Post post) {
        try {
            //connect to database
            conn = DB.connect();

            statement = conn.prepareStatement("update post set content=?, lessonID=? where ID=?");
            statement.setString(1, post.getContent());
            statement.setInt(2, post.getLessonID());
            statement.setInt(3, post.getID());
            statement.executeUpdate();

            //disconnect to database
            DB.disconnect(conn);
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deletePost(int ID) {
        try {
            if (!existPost(ID)) {
                return false;
            }
            conn = DB.connect();
            statement = conn.prepareStatement("delete from post where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect(conn);
            if (!existPost(ID)) {
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
//        System.out.println(getPost(1));
//        Post p = getPost(3);
//        p.setContent("Hmm...");
//        updatePost(p);
        deletePost(3);
        
    }
}
