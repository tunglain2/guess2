package com.tunglain.guess2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText edEmail;
    private EditText edPassword;
    private TextView emailText;
    private TextView pwdText;
    private Button login;
    private LoginViewModel viewModel;
    private boolean isDataValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();


        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        viewModel.getLoginStateLiveData().observe(this, new Observer<LoginState>() {
            @Override
            public void onChanged(@Nullable LoginState loginState) {
                updateContent(loginState);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                viewModel.validate(email,password);
                if (isDataValid) {
                    if ("steven@gmail.com".equals(email) && "Aa123456".equals(password)) {
                        setResult(RESULT_OK);
                        finish();
                    }else {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("登入結果")
                                .setMessage("帳號密碼錯誤")
                                .setPositiveButton("OK",null)
                                .show();
                    }
                }
            }
        });
    }

    private void updateContent(@Nullable LoginState loginState) {
        emailText.setText(loginState.getEmailMessage());
        pwdText.setText(loginState.getPasswordMessage());
        isDataValid = loginState.isDataValid();
    }

    private void findViews() {
        edEmail = findViewById(R.id.mail);
        edPassword = findViewById(R.id.password);
        emailText = findViewById(R.id.tv_mail);
        pwdText = findViewById(R.id.tv_password);
        login = findViewById(R.id.login);
    }
}
