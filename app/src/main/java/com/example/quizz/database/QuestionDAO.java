package com.example.quizz.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.quizz.Question;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM questions")
    List<Question> getListQuestion();
}
