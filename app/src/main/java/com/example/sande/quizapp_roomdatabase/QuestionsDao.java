package com.example.sande.quizapp_roomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionsDao {

    @Insert
    void insert(QuestionTable questions);
    @Insert
    void insertMultipleMovies (List<QuestionTable> quizzesList);

    @Query("DELETE FROM QuizTable")
    void deleteAll();

    @Query("SELECT * from QuizTable")
    List<QuestionTable> getAllQuestions();

}
