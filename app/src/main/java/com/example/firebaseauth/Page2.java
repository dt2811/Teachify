package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Page2 extends AppCompatActivity {
    ArrayList<String> a;
    ListView l;
    long admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
      admin=i.getLongExtra("Admin",1);
        setContentView(R.layout.activity_page2);
        BottomNavigationView bt = findViewById(R.id.bottomNavigationView);
        bt.setOnNavigationItemSelectedListener(np);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragholder, new homefrag()).commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener np = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment current = null;
            switch (item.getItemId()) {
                case R.id.homebutton:
                    current = new homefrag();
                    break;
                case R.id.search:
                    current = new searchfrag();
                    break;
                case R.id.add:
                    if(admin==1){
                    current = new addfrag();
                    }
                    else{
                        current= new TestFragment();
                    }

                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragholder, current).commit();
            return true;
        }
    };
}