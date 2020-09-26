package com.example.firebaseauth;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.*;

public class TestFragment extends Fragment {
    private ListView listView;
    private TextView question;
    private EditText answer;
    private Button next;
    private Button done;
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;
    FirebaseFirestore fb;
    List<String> Question, Answers,useranswer;
    int i=0,j=0;
    View v;
    Map<String, Object> data;
    int count=0;
    int n=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_test, container, false);
        listView = v.findViewById(R.id.listview2);
        list = new ArrayList<String>();
        Question = new ArrayList<String>();
        Answers = new ArrayList<String>();
        useranswer=new ArrayList<String>();
        question = v.findViewById(R.id.test_question);
        answer = v.findViewById(R.id.test_answer);
        next = v.findViewById(R.id.next_button);
        done = v.findViewById(R.id.done_button);
        fb = FirebaseFirestore.getInstance();
        fb.collection("Test").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot t : queryDocumentSnapshots) {
                    list.add(t.getId());
                    afterdata();
                }
            }
        });
       done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int u=0;u<n/2;u++){
                    if( useranswer.get(u)!=null){
                        if(useranswer.get(u).equals(Answers.get(u))){
                            count++;
                        }

                    }
                    else{
                        Toast.makeText(getContext(),String.valueOf(count), Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(getContext(), String.valueOf(count), Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j<n/2){
                    useranswer.add(answer.getText().toString());
                    question.setText(Question.get(j));
                    j++;}
                else{
                    Toast.makeText(getContext(), "CLICK DONE TEST FINISH", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    public void afterdata() {

        arrayAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setVisibility(View.GONE);

                fb.collection("Test").document(list.get(i)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {
                            {
                      data=task.getResult().getData();
                          questionload();
                            }

                        }
                    }
                });
            }
        });
    }
    public void questionload(){
        n=data.size();
        if(n!=0){
        for (Map.Entry<String,Object> entry : data.entrySet()){
            if(i!=n%2){
                Answers.add((String) entry.getValue());

            }
            else{
                Question.add((String) entry.getValue());
            }
            i++;
            Log.i("i", (String) entry.getValue());

        }
        }
        else{
            Toast.makeText(getContext(), "Sorry something went wrong", Toast.LENGTH_SHORT).show();
        }
        question.setVisibility(View.VISIBLE);
        answer.setVisibility(View.VISIBLE);

        next.setVisibility(View.VISIBLE);
        done.setVisibility(View.VISIBLE);
    }








}
