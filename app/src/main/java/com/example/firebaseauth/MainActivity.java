package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
  private FirebaseAuth auth;
  EditText email,password,repass,name;
  TextView login,sign,forgot;
  RadioButton teacher,student;
  RadioGroup radioGroup;
  Button s,c;
    String e,category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.EmailAddress);
        password=findViewById(R.id.password);
        repass=findViewById(R.id.repassword);
        login=findViewById(R.id.Login);
        name=findViewById(R.id.Name);
        sign=findViewById(R.id.Sign);
        forgot=findViewById(R.id.forgot);
        teacher=findViewById(R.id.radio_teacher);
        student=findViewById(R.id.radio_student);
        radioGroup=findViewById(R.id.radiogroup);
        s=findViewById(R.id.signin);
        c=findViewById(R.id.create);
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                repass.setVisibility(View.GONE);
                forgot.setVisibility(View.VISIBLE);
                name.setVisibility(View.GONE);
                c.setVisibility(View.GONE);
                radioGroup.setVisibility(View.GONE);
                s.setVisibility(View.VISIBLE);
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                name.setVisibility(View.VISIBLE);
                forgot.setVisibility(View.GONE);
                repass.setVisibility(View.VISIBLE);
                c.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                s.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=auth.getCurrentUser();

    }
    public void onRadioClicked(View view) {
        // Check if button currently checked
        boolean checked = ((RadioButton) view).isChecked();

        // string with cat student or teacher
        category = view.getTag().toString();




    }
    public void signup(View view){
        String n=name.getText().toString();
        String e=email.getText().toString();
       String p=password.getText().toString();
       String r=repass.getText().toString();
       String c=category;
       if(!e.isEmpty()&&!p.isEmpty()&&r.equals(p)){
           auth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(MainActivity.this, "Registered" , Toast.LENGTH_SHORT).show();


                   }
                       else{
                       Toast.makeText(MainActivity.this, "OPPS SOMETHING WENT WRONG", Toast.LENGTH_SHORT).show();
                   }
               }
           });
       }
       else{
           Toast.makeText(this, "Oops something went wrong!!!", Toast.LENGTH_SHORT).show();
       }
    }
    public void signin(final View view){
       e=email.getText().toString();
        String p=password.getText().toString();
        if(!e.isEmpty()&&!p.isEmpty()){

            auth.signInWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sign in success", Toast.LENGTH_SHORT).show();
                        FirebaseUser u=auth.getCurrentUser();
                        Intent i=new Intent(MainActivity.this,Page2.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
        else{
            Toast.makeText(this, "Sorry something went wrong", Toast.LENGTH_SHORT).show();
        }
}
}