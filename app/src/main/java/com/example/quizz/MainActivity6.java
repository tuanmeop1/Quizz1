package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quizz.database.QuestionDatabase;
import com.example.quizz.databinding.ActivityMain3Binding;
import com.example.quizz.databinding.ActivityMain6Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity6 extends AppCompatActivity {
    ActivityMain6Binding binding;
    List<Question> questionList;
    String question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //Lay toan bo cau hoi tu db
        questionList = new ArrayList<>();
        questionList = QuestionDatabase.getInstance(this).questionDAO().getListQuestion();
        //Lay cau hoi can hien thi
        Intent getIntent = getIntent();
        question = getIntent.getStringExtra("question");
        //Hien thi chi tiet cau hoi
        displayQuestion();
    }
    private void displayQuestion() {
        String hint;
        String mode;
        for(int i = 0; i < questionList.size(); i++) {
            if(questionList.get(i).getQuestion().equals(question)) {
                if(questionList.get(i).getMode() == 0) {
                    mode = "Dễ";
                }else {
                    mode = "Khó";
                }
                hint = "Đáp án đúng: " + questionList.get(i).getAnswer();
                binding.questionView.setText(question);
                binding.modeView.setText(mode);
                binding.answer1.setText(questionList.get(i).getOption1());
                binding.answer2.setText(questionList.get(i).getOption2());
                binding.answer3.setText(questionList.get(i).getOption3());
                binding.answer4.setText(questionList.get(i).getOption4());
                binding.hint.setText(hint);
            }
        }
    }
}