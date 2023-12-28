package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.quizz.database.QuestionDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    ListView questionListView;
    List<Question> questionList;
    List<String> onlyQuestionList;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        questionListView = findViewById(R.id.questionList);

        //Lay toan bo danh sach cau hoi tu db
        questionList = new ArrayList<>();
        questionList = QuestionDatabase.getInstance(this).questionDAO().getListQuestion();

        //Lay rieng cau hoi trong danh sach cau hoi tu db
        onlyQuestionList = new ArrayList<>();
        for(int i = 0; i < questionList.size(); i++) {
            onlyQuestionList.add(questionList.get(i).getQuestion());
        }

        //Gan rieng cau hoi trong danh sach cau hoi tu db vao onlyQuestionList
        getOnlyQuestionList();

        //Hien thi list cau hoi
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, onlyQuestionList);
        questionListView.setAdapter(arrayAdapter);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.topic);

        //Loc cau hoi theo chu de
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = autoCompleteTextView.getText().toString();
                onlyQuestionList = new ArrayList<>();
                for(int i = 0; i < questionList.size(); i++) {
                    if(questionList.get(i).getTopic().equals(value)) {
                        onlyQuestionList.add(questionList.get(i).getQuestion());
                    }
                }
                arrayAdapter.clear();  // Xóa các mục hiện tại
                arrayAdapter.addAll(onlyQuestionList);  // Thêm các mục mới
                arrayAdapter.notifyDataSetChanged();  // Thông báo thay đổi
            }
        });

        //Tra ve list toan bo cau hoi sau khi khong chon topic nao nua
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    getOnlyQuestionList();
                    arrayAdapter.clear();
                    arrayAdapter.addAll(onlyQuestionList);
                    arrayAdapter.notifyDataSetChanged();
                    // Buộc AutoCompleteTextView mất focus
                    autoCompleteTextView.clearFocus();
                }
            }
        });

        //Xu ly su kien an de xem chi tiet cau hoi
        Intent intent = new Intent(this, MainActivity6.class);
        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("question", onlyQuestionList.get(position));
                startActivity(intent);
            }
        });
    }

    //Lay rieng cau hoi trong danh sach cau hoi tu db
    private void getOnlyQuestionList() {
        onlyQuestionList = new ArrayList<>();
        for(int i = 0; i < questionList.size(); i++) {
            onlyQuestionList.add(questionList.get(i).getQuestion());
        }
    }
}