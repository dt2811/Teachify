package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.icu.text.CaseMap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Video extends AppCompatActivity {
    VideoView videoView;


    //url
    String url;
    String title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        description=intent.getStringExtra("Description");
        url=intent.getStringExtra("Image_url");

       FirebaseFirestore ref = FirebaseFirestore.getInstance();
        ref.collection("Courses").document(title).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(Video.this, "Success", Toast.LENGTH_SHORT).show();
                url = documentSnapshot.getString("video_url");

                play();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Video.this, "Failed", Toast.LENGTH_SHORT).show();
                play();
            }
        });

    }

    public void play() {
        TextView videotitle = findViewById(R.id.video_title);
        TextView Description = findViewById(R.id.video_description);
        videoView = findViewById(R.id.video_view);
        videotitle.setText(title);
        Description.setText(description);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(this.videoView);
        this.videoView.setMediaController(mediaController);
        Uri uri = Uri.parse(url);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}