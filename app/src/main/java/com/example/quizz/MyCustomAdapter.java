package com.example.quizz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<Topic> {
    private ArrayList<Topic> topicArrayList;
    Context context;

    public MyCustomAdapter( ArrayList<Topic> topicArrayList, Context context) {
        super(context, R.layout.my_list_item, topicArrayList);
        this.topicArrayList = topicArrayList;
        this.context = context;
    }

    private static class MyViewHolder {
        TextView topicName;
        TextView topicDescription;
        ImageView topicImage;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Topic myTopic = getItem(position);
        MyViewHolder myViewHolder;
        final View result;
        if(convertView == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.my_list_item, parent, false);
            myViewHolder.topicName = convertView.findViewById(R.id.topicName);
            myViewHolder.topicDescription = convertView.findViewById(R.id.description);
            myViewHolder.topicImage = convertView.findViewById(R.id.topicImage);
            result = convertView;
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
            result = convertView;
        }
        myViewHolder.topicName.setText(myTopic.getTopicName());
        myViewHolder.topicDescription.setText(myTopic.getDescription());
        myViewHolder.topicImage.setImageResource(myTopic.getTopicImage());
        return result;
    }
}