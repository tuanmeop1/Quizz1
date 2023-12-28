package com.example.quizz.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quizz.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "questionList.db";
    private static QuestionDatabase instance;
    public static synchronized QuestionDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), QuestionDatabase.class, DATABASE_NAME)
                    .createFromAsset("questionList.db").allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract QuestionDAO questionDAO();
}
