package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {
    VideoView videoView;
    //url
    private String url = "https://firebasestorage.googleapis.com/v0/b/fir-auth-a5afd.appspot.com/o/WhatsApp%20Video%202020-09-24%20at%2012.00.23.mp4?alt=media&token=369f67ce-b34c-4dcc-ae6b-81e8f87fb46e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.video_view);
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