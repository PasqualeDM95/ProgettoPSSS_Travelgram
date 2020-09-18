package com.psss.travelgram.viewmodel;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.psss.travelgram.R;
import com.psss.travelgram.model.repository.AuthRepository;
import com.psss.travelgram.view.activity.MainActivity;

public class AuthViewModel extends ViewModel {

    /*private MutableLiveData<String> mText;

    public AuthViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }*/

    private AuthRepository authRepo = new AuthRepository();


    public String checkCredentials(EditText emailText, EditText passwordText){
        String email = emailText.getText().toString().trim();   // trim elimina gli spazi all'inizio e alla fine
        String password = passwordText.getText().toString().trim();

        if (email.isEmpty()) return "email is empty";
        if (password.isEmpty()) return "password is empty";
        return "OK";
    }


    public Task<AuthResult> loginUser(EditText emailText, EditText passwordText) {
        String email = emailText.getText().toString().trim();   // trim elimina gli spazi all'inizio e alla fine
        String password = passwordText.getText().toString().trim();
        return authRepo.loginUser(email, password);
    }

}