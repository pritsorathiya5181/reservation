package com.example.prit.reservation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private EditText uName,uEmail,uPass,uAge;
    private Button btnreg;
    private TextView uLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView uProfile;
    Boolean check = false;
    String userN,userE,userP,userA;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private static int PICK_IMAGE = 123;
    Uri imagepath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                uProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        uName = (EditText)findViewById(R.id.etusername);
        uEmail = (EditText)findViewById(R.id.etEmail);
        uPass = (EditText)findViewById(R.id.editText3);
        btnreg = (Button)findViewById(R.id.btnregister);
        uLogin = (TextView)findViewById(R.id.tvback);
        uAge = (EditText)findViewById(R.id.etage);
        uProfile = (ImageView)findViewById(R.id.ivProfile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        btnreg.setOnClickListener(this);
        uLogin.setOnClickListener(this);
        uProfile.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnregister:
            {
                if (validate(check)){
                    // database code
                    String user_email = uEmail.getText().toString().trim();
                    String user_pass = uPass.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(Registration.this,"Successfully registration,Upload Complete!",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Registration.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(Registration.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            }
            case R.id.tvback: {
                startActivity(new Intent(Registration.this, MainActivity.class));
                break;
            }
            case R.id.ivProfile: {
                Intent intent = new Intent();
                intent.setType("image/*"); // applocation/* for app and audio/* for audio file
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        }
    }

    private boolean validate(boolean result){

         userN = uName.getText().toString();
         userE = uEmail.getText().toString();
         userP = uPass.getText().toString();
         userA = uAge.getText().toString();


        if (userN.isEmpty() || userE.isEmpty() || userP.isEmpty() || userA.isEmpty() || imagepath == null){
            Toast.makeText(Registration.this,"Please, Fill all entry",Toast.LENGTH_SHORT).show();
            result=false;
        }
        else {
            result = true;
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(Registration.this,"Successfully registration,verification mail sent!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Registration.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(Registration.this,"Verification mail has'nt been sent!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profiles Pic");
        UploadTask uploadTask = imageReference.putFile(imagepath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registration.this,"Upload Failed!",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Registration.this,"Successfully Uploaded!",Toast.LENGTH_SHORT).show();
            }
        });

        UserProfile userProfile = new UserProfile(userA,userE,userN,userP);
       // UserProfile userProfile = new UserProfile(userA,userE,userN);
        myRef.setValue(userProfile);
    }
}
