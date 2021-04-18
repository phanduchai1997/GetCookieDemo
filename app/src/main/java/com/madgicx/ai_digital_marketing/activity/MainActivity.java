package com.madgicx.ai_digital_marketing.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.madgicx.ai_digital_marketing.R;
import com.madgicx.ai_digital_marketing.pager.MainSliderAdapter;
import com.madgicx.ai_digital_marketing.service.GlideImageLoadingService;
import com.madgicx.ai_digital_marketing.utils.ShareUtils;

import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView imgPre;
    private AppCompatImageView imgNext;
    private Slider viewPager;
    private AppCompatImageView txtLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPre = findViewById(R.id.imgPre);
        imgNext = findViewById(R.id.imgNext);
        viewPager = findViewById(R.id.viewPager);
        txtLogin = findViewById(R.id.txtLogin);

        Slider.init(new GlideImageLoadingService());
        viewPager.setAdapter(new MainSliderAdapter());
        listener();
    }

    private void listener() {
        imgPre.setOnClickListener(v -> {
            if (viewPager.selectedSlidePosition != 0) {
                viewPager.setSelectedSlide(viewPager.selectedSlidePosition - 1);
            }
        });
        imgNext.setOnClickListener(v -> {
            if (viewPager.selectedSlidePosition == viewPager.getAdapter().getItemCount() - 1) {
                if (!ShareUtils.INSTANCE.getString(this, "c_user", "").equals("")) {
                    Intent intent = new Intent(this, InsideActivity.class);
                    intent.putExtra("first", false);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
            } else {
                viewPager.setSelectedSlide(viewPager.selectedSlidePosition + 1);
            }
        });
        txtLogin.setOnClickListener(v -> {
            if (!ShareUtils.INSTANCE.getString(this, "c_user", "").equals("")) {
                Intent intent = new Intent(this, InsideActivity.class);
                intent.putExtra("first", false);
                startActivity(intent);
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
        });
    }
}
