package com.example.prit.reservation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ename;
    private EditText epass;
    private Button login;
    private TextView txtinfo,tvregis,tvforgot;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    int count=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ename = (EditText)findViewById(R.id.username);
        epass = (EditText)findViewById(R.id.pass);
        login = (Button)findViewById(R.id.btnlogin);
        txtinfo = (TextView)findViewById(R.id.tvremain);
        tvregis = (TextView)findViewById(R.id.tvregister);
        progressDialog = new ProgressDialog(this);
        tvforgot = (TextView)findViewById(R.id.tvforgotPass);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null){
            finish();
            startActivity(new Intent(MainActivity.this,booking.class));
        }
        login.setOnClickListener(this);
        tvregis.setOnClickListener(this);
        tvforgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnlogin:
            {
                final String userN = ename.getText().toString();
                final String userP = epass.getText().toString();

                validate(userN,userP);
                break;
            }
            case R.id.tvregister:{
                startActivity(new Intent(MainActivity.this,Registration.class));
                break;
            }
            case R.id.tvforgotPass:{
                startActivity(new Intent(MainActivity.this,PasswordActivity.class));
            }
        }
    }

    private void validate(String uN,String uP){
        progressDialog.setMessage("Wait a second!");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(uN, uP).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
//                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this,booking.class));
                    checkEmailVarification();
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    count--;
                    txtinfo.setText("No. of attempts remaning: "+count);
                    progressDialog.dismiss();
                    if (count==0){
                        login.setEnabled(false);
                        login.setBackgroundResource(R.drawable.invisible);
                    }
                }
            }
        });
    }
    private void checkEmailVarification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        startActivity(new Intent(MainActivity.this,booking.class));
//        if (emailflag){
//            finish();
//            startActivity(new Intent(MainActivity.this,booking.class));
//        }
//        else{
//            Toast.makeText(this,"please! Verify your email",Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
    }
}

