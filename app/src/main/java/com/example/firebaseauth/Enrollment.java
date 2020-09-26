package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class Enrollment extends AppCompatActivity {
    private ImageView imageView;
    private TextView title;
    private TextView description;
    String title1;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        title1 = i.getStringExtra("TITLE");
        String description1 = i.getStringExtra("Description");
        String image_url = i.getStringExtra("Image_url");
        setContentView(R.layout.activity_enrollment);
        imageView = findViewById(R.id.enroll_image);
        title = findViewById(R.id.course_title);
        description = findViewById(R.id.course_description);
        title.setText(title1);
        description.setText(description1);
        Glide.with(this).load(image_url).centerCrop().into(imageView);


    }


    public void enroll(View view) {
        firebaseFirestore.collection("Users").document(auth.getUid())
                .update("courses", FieldValue.arrayUnion(title1)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Enrollment.this, "Welcome!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}