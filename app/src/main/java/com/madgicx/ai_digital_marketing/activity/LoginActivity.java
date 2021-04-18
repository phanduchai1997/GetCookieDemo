package com.madgicx.ai_digital_marketing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.madgicx.ai_digital_marketing.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatImageView btnFacebook = findViewById(R.id.btnFacebook);
        AppCompatImageView btnLogin = findViewById(R.id.btnLogin);
        btnFacebook.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, WebActivity.class)));
        btnLogin.setOnClickListener(v -> Toast.makeText(this, R.string.login_before, Toast.LENGTH_SHORT).show());
    }
}
