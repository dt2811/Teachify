package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import static com.example.firebaseauth.R.id.fragholder;

public class addfrag extends Fragment {
    int c=0;
    EditText e1,e2,e3;
    Button b1,b2;
    String ntname;
    View v;
    HashMap<String,String > q=new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.add_frag,container,false);
       e1=v.findViewById(R.id.name);
        e2=v.findViewById(R.id.questions);
        e3=v.findViewById(R.id.answers);
        b1=v.findViewById(R.id.Nextbutton);
        b2=v.findViewById(R.id.Donebutton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!e1.getText().toString().isEmpty()) {
                    ntname=e1.getText().toString();
                   e1.setVisibility(View.INVISIBLE);
                    if(e2.getText().toString().isEmpty() ||e3.getText().toString().isEmpty()){
                        Toast.makeText(v.getContext(), "Oops you left field empty", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        c++;
                       q.put(String.valueOf(c),e2.getText().toString());
                       String answer="ANS"+String.valueOf(c);
                       q.put(answer,e3.getText().toString());
                       e1.setText(" ");
                       e2.setText(" ");
                        Toast.makeText(v.getContext(), " SAVED ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FirebaseFirestore fb=FirebaseFirestore.getInstance();
                fb.collection("Tests").document(ntname).set(q);
                Toast.makeText(v.getContext(), " Launched", Toast.LENGTH_SHORT).show();
                //getFragmentManager().beginTransaction().replace(R.layout.home_layout, fragholder).commit();
            }
        });

        return v;
    }
}
