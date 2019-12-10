package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText username_editText, password_editText;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi variabel dari layout activity_main //
        username_editText = findViewById(R.id.username_login);
        password_editText = findViewById(R.id.password_login);
        login_button = findViewById(R.id.login_button);

        Preferences.getUserSharedPreferences(getBaseContext());

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void login() {
        username_editText.setError(null);
        password_editText.setError(null);
        View fokus = null;
        boolean cancel = false;

        String username = username_editText.getText().toString();
        String password = password_editText.getText().toString();

        if (TextUtils.isEmpty(username)) {
            username_editText.setError("This field is required");
            fokus = username_editText;
            cancel = true;
        } else if (!userCheck(username)) {
            username_editText.setError("This username is not found");
            fokus = username_editText;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            password_editText.setError("This field is required");
            fokus = password_editText;
            cancel = true;
        } else if (!passwordCheck(password)) {
            password_editText.setError("This password is incorrect");
            fokus = username_editText;
            cancel = true;
        }

        if (cancel) {
            fokus.requestFocus();
        } else {
            loggedIn();
        }
    }

    private void loggedIn() {
        startActivity(new Intent(getBaseContext(), SecondActivity.class));
        finish();
    }

    private boolean userCheck(String user) {
        return user.equals(Preferences.getUserAccount().get(0).getUsername());
    }

    private boolean passwordCheck(String password) {
        return  password.equals(Preferences.getUserAccount().get(0).getPassword());
    }
}
