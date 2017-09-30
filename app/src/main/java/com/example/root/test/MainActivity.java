package com.example.root.test;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText e1,e2;
    Button buttonRegister;
    TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(),profileActivity.class));

        }
        progressDialog= new ProgressDialog(this);
        e1=(EditText)findViewById(R.id.user_email);
        e2=(EditText)findViewById(R.id.user_pass);
        buttonRegister=(Button)findViewById(R.id.user_button);
        textViewSignup=(TextView)findViewById(R.id.txt_view);

        buttonRegister.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);


    }
    private void registerUser(){
        String email= e1.getText().toString().trim();
        String pass= e2.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Enter the email",Toast.LENGTH_LONG).show();
            return;

        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(getApplicationContext(),"Enter the Password",Toast.LENGTH_LONG).show();
            return;

        }

        progressDialog.setMessage("Registering please wait.....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //register successfuly completed
                    finish();
                    startActivity(new Intent(getApplicationContext(),profileActivity.class));



                }
                else{
                    Toast.makeText(MainActivity.this,"Couldn't sign in",Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();


            }
        });
    }

    //




    @Override
    public void onClick(View view) {
        if(view==buttonRegister){
            //register method

            registerUser();
        }
        else if(view==textViewSignup){
            //Will open activity here
            startActivity(new Intent(MainActivity.this,LoginActivity.class));

        }

    }
}
