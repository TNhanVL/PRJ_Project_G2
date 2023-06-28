/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Model;

/**
 *
 * @author Thanh Duong
 */
public class Answer {
    private int ID;
    private String content;
    private boolean correct;

    public Answer() {
    }

    public Answer(int ID, String content, boolean correct) {
        this.ID = ID;
        this.content = content;
        this.correct = correct;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Answer{" + "ID=" + ID + ", content=" + content + ", correct=" + correct + '}';
    }
    
    
}
