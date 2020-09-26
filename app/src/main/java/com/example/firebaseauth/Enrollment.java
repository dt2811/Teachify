package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Enrollment extends AppCompatActivity {
private ImageView imageView;
private TextView title;
private TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);
        imageView=findViewById(R.id.enroll_image);
        title=findViewById(R.id.course_title);
        description=findViewById(R.id.course_description);
    }

    public void enroll(View view) {
    }
}