package com.madgicx.ai_digital_marketing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.madgicx.ai_digital_marketing.R;
import com.madgicx.ai_digital_marketing.model.Madgicx;
import com.madgicx.ai_digital_marketing.utils.NetWorkUtils;
import com.madgicx.ai_digital_marketing.utils.ShareUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        if (NetWorkUtils.isNetworkConnected(this)) {
            database.child("madgicx").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue(Madgicx.class).enable) {
                        if (!ShareUtils.INSTANCE.getString(SplashActivity.this, "c_user", "").equals("")) {
                            Intent intent = new Intent(SplashActivity.this, InsideActivity.class);
                            intent.putExtra("first", false);
                            startActivity(intent);
                        } else {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }
                    } else {
//                        startActivity(new Intent(SplashActivity.this, HelloActivity.class));
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    startActivity(new Intent(SplashActivity.this, HelloActivity.class));
                    finish();
                }
            });
        } else {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashActivity.this, HelloActivity.class));
                finish();
            }, 2000);
        }
    }
}
