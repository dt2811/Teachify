package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class homefrag extends Fragment {
    ArrayList<String> des = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    RecyclerView r;
    View v;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_layout, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      r = v.findViewById(R.id.recycler);
        // r.setLayoutManager(new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL, false));
        FirebaseFirestore ref = FirebaseFirestore.getInstance();
        ref.collection("Courses").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                des.add(documentSnapshot.getString("description"));
                title.add(documentSnapshot.getString("name"));


                images.add(documentSnapshot.getString("img_url"));

            }
                r.setAdapter(new recycleradapter(getContext(), title, des, images));
        }});





//        des.add("hello");
//        des.add("world");
//        title.add("grey");
//        title.add("code");
//
//        images.add(R.drawable.common_google_signin_btn_icon_dark);
//        images.add(R.drawable.common_google_signin_btn_icon_dark);
        r.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
       //r.setAdapter(new recycleradapter(getContext(), title, des, images));

    }
}
