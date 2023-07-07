/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Database;

import com.prj_project_g2.Model.Lesson;
import com.prj_project_g2.Model.QuizResult;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Duong
 */
public class LessonDB extends DB {

    public static boolean existLesson(int ID) {
        boolean ok = false;
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select ID from lesson where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt("ID") == ID) {
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

    public static Lesson getLesson(int ID) {
        Lesson lesson = null;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from lesson where ID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lesson = new Lesson(
                        resultSet.getInt("ID"),
                        resultSet.getInt("MoocID"),
                        resultSet.getString("title"),
                        resultSet.getInt("index"),
                        resultSet.getInt("type"),
                        resultSet.getInt("time"));
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lesson;
    }

    public static boolean checkLessonCompleted(int userID, int lessonID) {
        boolean ok = false;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select 1 from lessonCompleted where lessonID = ? and userID = ?");
            statement.setInt(1, lessonID);
            statement.setInt(2, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ok = true;
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        //if not completed, check if quiz not judge yet
        if (!ok) {
            Lesson lesson = LessonDB.getLesson(lessonID);
            if (lesson.getType() == 2) {
                QuizResult quizResult = QuizResultDB.getLastQuizResult(userID, lesson.getID());
                if(quizResult == null) return false;
                int numberOfCorrectQuestion = QuizResultDB.getQuizResultPoint(quizResult.getID());
                int numberOfQuestion = QuestionDB.getNumberQuestionByLessonID(lesson.getID());
                if (numberOfCorrectQuestion * 100 >= numberOfQuestion * 80) {
                    LessonDB.insertLessonCompleted(userID, lesson.getID());
                    return true;
                }
            }
        }

        return ok;
    }

    public static int getNumberLessonsCompleted(int userID, int moocID) {
        int ans = 0;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select count(*) as number from\n"
                    + "(select lessonID as ID from lessonCompleted where userID = ?) as a\n"
                    + "join\n"
                    + "(select ID from lesson where moocID = ?) as b\n"
                    + "on a.ID = b.ID");
            statement.setInt(1, userID);
            statement.setInt(2, moocID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ans = resultSet.getInt("number");
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ans;
    }

    public static boolean insertLessonCompleted(int userID, int lessonID) {
        deleteLessonCompleted(userID, lessonID);
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into lessonCompleted(lessonID, userID) values(?,?)");
            statement.setInt(1, lessonID);
            statement.setInt(2, userID);
            statement.executeUpdate();
            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static void deleteLessonCompleted(int userID, int lessonID) {
        try {
            connect();
            statement = conn.prepareStatement("delete from lessonCompleted where lessonID = ?, userID = ?");
            statement.setInt(1, lessonID);
            statement.setInt(2, userID);
            statement.execute();
            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getFirstUncompleteLessonID(int userID, int courseID) {
        int lessonID = -1;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select top 1 lessonID from\n"
                    + "(select moocIndex, lessonID, lessonIndex from\n"
                    + "(select ID as moocID, [index] as moocIndex from mooc where courseID = ?) as a\n"
                    + "join\n"
                    + "(select moocID, ID as lessonID, [index] as lessonIndex from lesson) as b on a.moocID = b.moocID) a\n"
                    + "where lessonID not in\n"
                    + "(select lessonID from lessonCompleted where userID = ?)\n"
                    + "order by moocIndex, lessonIndex;");
            statement.setInt(1, courseID);
            statement.setInt(2, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lessonID = resultSet.getInt("lessonID");
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (lessonID < 0) {
            lessonID = getLastLessonID(courseID);
        }

        return lessonID;
    }

    public static int getLastLessonID(int courseID) {
        int lessonID = -1;

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select top 1 lessonID from\n"
                    + "(select ID as moocID, [index] as moocIndex from mooc where courseID = ?) as a\n"
                    + "join\n"
                    + "(select moocID, ID as lessonID, [index] as lessonIndex from lesson) as b on a.moocID = b.moocID\n"
                    + "order by moocIndex desc, lessonIndex desc");
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lessonID = resultSet.getInt("lessonID");
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lessonID;
    }

    public static ArrayList<Lesson> getLessonsByMoocID(int moocID) {
        ArrayList<Lesson> lessons = new ArrayList<>();

        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("select * from lesson where moocID = ? order by [index]");
            statement.setInt(1, moocID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Lesson lesson = new Lesson(
                        resultSet.getInt("ID"),
                        resultSet.getInt("moocID"),
                        resultSet.getString("title"),
                        resultSet.getInt("index"),
                        resultSet.getInt("type"),
                        resultSet.getInt("time"));
                lessons.add(lesson);
            }

            disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lessons;
    }

    public static boolean insertLesson(Lesson lesson) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("insert into lesson(moocID,title,[index],[type],[time]) values(?,?,?,?,?)");
            statement.setInt(1, lesson.getMoocID());
            statement.setString(2, lesson.getTitle());
            statement.setInt(3, lesson.getIndex());
            statement.setInt(4, lesson.getType());
            statement.setInt(5, lesson.getTime());
            statement.executeUpdate();
            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateLesson(Lesson lesson) {
        try {
            //connect to database
            connect();

            statement = conn.prepareStatement("update lesson set moocID=?, title=?, [index]=?, type=?, [time]=? where ID=?");
            statement.setInt(1, lesson.getMoocID());
            statement.setString(2, lesson.getTitle());
            statement.setInt(3, lesson.getIndex());
            statement.setInt(4, lesson.getType());
            statement.setInt(5, lesson.getTime());
            statement.setInt(6, lesson.getID());
            statement.executeUpdate();

            //disconnect to database
            disconnect();
            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteLesson(int ID) {
        try {
            if (!existLesson(ID)) {
                return false;
            }
            connect();
            statement = conn.prepareStatement("delete from lesson where ID=?");
            statement.setInt(1, ID);
            statement.execute();
            disconnect();
            if (!existLesson(ID)) {
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
        System.out.println(checkLessonCompleted(1, 1));
    }
}
