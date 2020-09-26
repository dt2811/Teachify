package com.example.firebaseauth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class homefrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home_layout,container,false);
        RecyclerView r= v.findViewById(R.id.recyclemycources);
        r.setLayoutManager(new LinearLayoutManager(v.getContext(),R.id.Page2,false));
        r.setAdapter(new recycleradapter(v.getContext(),data,xyz));
    }
}
