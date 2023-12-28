package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    TextView totalScoreView;
    ListView listView;
    ArrayList<Topic> topicArrayList;
    Button listQuestionButton;
    SwitchCompat modeSwitch;
    String topicChosen;
    String totalScore;
    int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Khoi tao view
        totalScoreView = findViewById(R.id.totalScore);
        listView = findViewById(R.id.topicMenu);
        listQuestionButton = findViewById(R.id.listBtn);
        modeSwitch = findViewById(R.id.modeSw);
        topicArrayList = new ArrayList<>();
        //SharedPreferences
        SharedPreferences sharedPref = MyApplication.getSharedPreferences();
        //Cap nhat Total Score
        totalScore = sharedPref.getInt("totalScore", 0) + " pts";
        totalScoreView.setText(totalScore);
        //Them topic vao menu
        Topic math = new Topic("Toán học",
                "Các câu hỏi về phép tính, các định lý toán học", R.drawable.math);
        Topic science = new Topic("Khoa học",
                "Các câu hỏi về những định luật, cấu trúc và cách vận hành của thế giới tự nhiên", R.drawable.science);
        Topic literature = new Topic("Văn học",
                "Các câu hỏi về văn học Việt Nam và nước ngoài", R.drawable.literature);
        Topic geography = new Topic("Địa lý",
                "Các câu hỏi về các vùng đất, địa hình, dân cư và các hiện tượng trên trái đất", R.drawable.geography);
        topicArrayList.add(math);
        topicArrayList.add(science);
        topicArrayList.add(literature);
        topicArrayList.add(geography);
        //set Adapter cho ListView
        MyCustomAdapter adapter = new MyCustomAdapter(topicArrayList, getApplicationContext());
        listView.setAdapter(adapter);
        //Chon chu de
        Intent intent1 = new Intent(this, MainActivity3.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topicChosen = topicArrayList.get(position).getTopicName();
                if(!modeSwitch.isChecked()) {
                    mode = 0;
                } else {
                    mode = 1;
                }
                intent1.putExtra("topicChosen", topicChosen);
                intent1.putExtra("mode", mode);
                startActivity(intent1);
            }
        });
        //Nhấn nút để chuyển đến trang danh sách câu hỏi
        Intent intent2 = new Intent(this, MainActivity5.class);
        listQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
    }
}