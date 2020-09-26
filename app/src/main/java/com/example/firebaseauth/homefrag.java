package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class homefrag extends Fragment {
    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home_layout,container,false);
        RecyclerView r= v.findViewById(R.id.recycler);
        r.setLayoutManager(new LinearLayoutManager(v.getContext(),RecyclerView.VERTICAL,false));
        ArrayList<String> des=new ArrayList<>();
        ArrayList<String> title=new ArrayList<>();
        des.add("hello");
        des.add("world");
        title.add("grey");
        title.add("code");
        ArrayList<Integer> images=new ArrayList<>();
        images.add(R.drawable.common_google_signin_btn_icon_dark);
        images.add(R.drawable.common_google_signin_btn_icon_dark);
        r.setAdapter(new recycleradapter(v.getContext(),title,des,images));
       return v;
    }
}
