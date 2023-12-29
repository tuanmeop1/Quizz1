package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.quizz.database.QuestionDatabase;
import com.example.quizz.databinding.ActivityMain3Binding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    ActivityMain3Binding binding;
    String topicChosen;
    List<Question> questionList;
    List<Question> selectedQuestionList;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int index;
    int score;
    int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        index = 0;
        score = 0;
        questionList = new ArrayList<>();
        //Lay ten chu de da chon
        Intent getIntent = getIntent();
        topicChosen = getIntent.getStringExtra("topicChosen");
        mode = getIntent.getIntExtra("mode", 0);
        //Lay toan bo danh sach cau hoi tu db
        questionList = QuestionDatabase.getInstance(this).questionDAO().getListQuestion();
        //Lay danh sach cau hoi theo chu de da chon
        getSelectedQuestionList();
        //Random thu tu cau hoi
        Collections.shuffle(selectedQuestionList);
        //Hien thi cau hoi
        setQuestion();
        Intent intent = new Intent(this, MainActivity4.class);
        binding.answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lay dap an duoc chon
                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                //Kiem tra xem da chon dap an hay chua, neu chua thi coi nhu tra loi sai
                if (radioButtonID == -1) {
                    intent.putExtra("score", score);
                    intent.putExtra("topicChosen", topicChosen);
                    intent.putExtra("mode", mode);
                    startActivity(intent);
                    finish();
                }else {
                    radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
                    String selectedAnswer = (String) radioButton.getText();
                    radioButton.setChecked(false);
                    //Kiem tra dap an
                    if(selectedAnswer.equals(selectedQuestionList.get(index).getAnswer())) {
                        index++;
                        score = getScore();
                        //Kiem tra xem het cau hoi hay chua, neu chua tiep tuc hien thi cau hoi tiep theo
                        if(index >= selectedQuestionList.size()) {
                            intent.putExtra("score", score);
                            intent.putExtra("topicChosen", topicChosen);
                            intent.putExtra("mode", mode);
                            startActivity(intent);
                            finish();
                        }else {
                            setQuestion();
                        }
                    }else {
                        intent.putExtra("score", score);
                        intent.putExtra("topicChosen", topicChosen);
                        intent.putExtra("mode", mode);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void setQuestion() {
        binding.textView5.setText(selectedQuestionList.get(index).getQuestion());
        binding.option1.setText(selectedQuestionList.get(index).getOption1());
        binding.option2.setText(selectedQuestionList.get(index).getOption2());
        binding.option3.setText(selectedQuestionList.get(index).getOption3());
        binding.option4.setText(selectedQuestionList.get(index).getOption4());
    }

    private void getSelectedQuestionList() {
        selectedQuestionList = new ArrayList<>();
        for(int i = 0; i < questionList.size(); i++) {
            if(questionList.get(i).getTopic().equals(topicChosen) && questionList.get(i).getMode() == mode) {
                selectedQuestionList.add(questionList.get(i));
            }
        }
    }

    private int getScore() {
        if(mode == 0) {
            return ++score;
        }else {
            return score += 2;
        }
    }
}

