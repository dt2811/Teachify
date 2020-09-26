package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
        String gTitle = intent.getStringExtra("TITLE");

        FirebaseFirestore ref = FirebaseFirestore.getInstance();
        ref.collection("Courses").document(gTitle).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(Video.this, "Success", Toast.LENGTH_SHORT).show();
                url = documentSnapshot.getString("video_url");
                description = documentSnapshot.getString("description");
                title = documentSnapshot.getString("name");
                play();
//                des.add(documentSnapshot.getString("description"));
//                title.add(documentSnapshot.getString("name"));
//
//                images.add(documentSnapshot.getString("img_url"));
//                r.setAdapter(new recycleradapter(getContext(), title, des, images));
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
        Uri uri = Uri.parse(url);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}