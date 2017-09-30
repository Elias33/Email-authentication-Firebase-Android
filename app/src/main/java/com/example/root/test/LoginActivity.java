package com.example.root.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button userButton;
    EditText userEmail, userPass;
    TextView txt;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog= new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();
        userEmail=(EditText)findViewById(R.id.login_email);
        userPass=(EditText)findViewById(R.id.login_pass);
        userButton=(Button)findViewById(R.id.login_button);
        txt=(TextView)findViewById(R.id.sign_in);
        userButton.setOnClickListener(this);
        txt.setOnClickListener(this);


    }

    private void userLogin(){
        //do something here

        String emailAction= userEmail.getText().toString().trim();
        String passwordAction= userPass.getText().toString().trim();

        if(TextUtils.isEmpty(emailAction)){
            Toast.makeText(getApplicationContext(),"Enter the email",Toast.LENGTH_LONG).show();
            return;

        }
        if(TextUtils.isEmpty(passwordAction)){
            Toast.makeText(getApplicationContext(),"Enter the Password",Toast.LENGTH_LONG).show();
            return;

        }

        progressDialog.setMessage("Login please wait.....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(emailAction,passwordAction).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful()){
                    //
                        finish();
                    startActivity(new Intent(getApplicationContext(),profileActivity.class));

                }
                else{
                    Toast.makeText(getApplicationContext(),"Registration error",Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();

            }
        });


    }

    @Override
    public void onClick(View view) {
        if(view==userButton){
            userLogin();
        }
        if(view==txt){
            //
            startActivity(new Intent(this,MainActivity.class));

        }

    }
}
