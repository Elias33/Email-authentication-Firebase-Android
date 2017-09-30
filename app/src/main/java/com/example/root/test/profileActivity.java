package com.example.root.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileActivity extends AppCompatActivity implements View.OnClickListener {


    TextView txt;
    Button b;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

        }

        FirebaseUser user= firebaseAuth.getCurrentUser();
        txt=(TextView)findViewById(R.id.userId);
        txt.setText("WELCOME USER"+ user.getEmail());
        b=(Button)findViewById(R.id.btn);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==b){
            firebaseAuth.signOut();
            startActivity(new Intent(this,LoginActivity.class));

        }

    }
}
