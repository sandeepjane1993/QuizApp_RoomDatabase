package com.example.sande.quizapp_roomdatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "QuizTable")
public class QuestionTable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int Id;
    @ColumnInfo(name = "questions")
    private String question;
    @ColumnInfo(name = "opt1")
    private String option1;
    @ColumnInfo(name = "opt2")
    private String option2;
    @ColumnInfo(name = "opt3")
    private String option3;
    @ColumnInfo(name = "opt4")
    private String option4;

    public QuestionTable(){

    }

    public QuestionTable(int id, String question, String option1, String option2, String option3, String option4) {

        Id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }
}
