package com.psss.travelgram.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.psss.travelgram.R;
import com.psss.travelgram.viewmodel.AuthViewModel;


public class LoginActivity extends AppCompatActivity implements OnClickListener {

    private Button loginBtn;
    private EditText email, password;
    private AuthViewModel authViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // e-mail e password
        //        email = (EditText) findViewById(R.id.loginEmail);
        //        password = (EditText) findViewById(R.id.loginPassword);
        //
        //        // testo per registrarsi
        //        TextView login = (TextView) findViewById(R.id.clickSignup);
        //        login.setOnClickListener(this);
        //
        //        // bottone LogIn
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
    }

    /*
    Intent intent = new Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(intent, 0);
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri file = data.getData();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference imageRef = storageRef.child("prova/"+file.getLastPathSegment());
            UploadTask uploadTask = imageRef.putFile(file);

            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    Toast.makeText(getApplicationContext(), "fallito", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    Toast.makeText(getApplicationContext(), "successo", Toast.LENGTH_SHORT).show();
                    // ...
                }
            });
        }
    }*/


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.clickSignup:
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                break;
            case R.id.loginBtn:
                loginUser();
                break;
            default:
                break;
        }
    }



    private void loginUser() {
        String result = authViewModel.checkCredentials(email, password);

        switch(result){
            case "email is empty":
                email.setError(getString(R.string.email_required));
                email.requestFocus();
                break;

            case "password is empty":
                password.setError(getString(R.string.password_required));
                password.requestFocus();
                break;

            case "OK":
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                loginBtn.setVisibility(View.GONE);

                Task<AuthResult> login = authViewModel.loginUser(email, password);
                login.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            loginBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }




}