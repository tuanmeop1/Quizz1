package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizz.databinding.ActivityMain4Binding;
import com.example.quizz.databinding.ActivityMain6Binding;

public class MainActivity4 extends AppCompatActivity {
    ActivityMain4Binding binding;
    TextView scoreView;
    Button finishBtn;
    Button tryAgainBtn;
    String score;
    int totalScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //SharedPreferences
        SharedPreferences sharedPref = MyApplication.getSharedPreferences();
        SharedPreferences.Editor editor = sharedPref.edit();
        //nhan Score tu man choi
        Intent getIntent = getIntent();
        score = String.valueOf(getIntent.getIntExtra("score", 0));
        Intent intent1 = new Intent(this, MainActivity.class);
        Intent intent2 = new Intent(this, MainActivity3.class);
        binding.score.setText(score);
        //Cap nhat Total Score
        totalScore = sharedPref.getInt("totalScore", 0) + Integer.parseInt(score);
        editor.putInt("totalScore", totalScore);
        editor.apply();
        //Hoan thanh
        binding.finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });
        //Choi lai
        binding.restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
        //Chia se thanh tich
        binding.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "Số câu trả lời đúng của tôi là: " + score);
                startActivity(Intent.createChooser(i, "Choose a platform"));
            }
        });
    }
}