package com.example.firebaseauth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;

public class TestFragment extends Fragment {
    private ListView listView;
    private TextView question;
    private EditText answer;
    private Button next;
    private Button done;
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_test,container,false);
        listView=v.findViewById(R.id.listview2);
        list=new ArrayList<String>();
        list.add("aaaaaaa");
        list.add("aadb");
        list.add("cdadad");
        question=v.findViewById(R.id.test_question);
        answer=v.findViewById(R.id.test_answer);
        next=v.findViewById(R.id.next_button);
        done=v.findViewById(R.id.done_button);
        arrayAdapter=new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setVisibility(View.GONE);
                question.setVisibility(View.VISIBLE);
                answer.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);
            }
        });



        return v;
    }
}
