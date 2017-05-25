package com.keepmeposted.intro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.keepmeposted.MainActivity;
import com.keepmeposted.R;
import com.keepmeposted.views.activity.GoogleSign;

public class MyIntro extends AppIntro2 {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Text", "Text", R.drawable.intro_1,  Color.parseColor("#7ECBDF")));
        addSlide(AppIntroFragment.newInstance("Text", "Text", R.drawable.intro_2, Color.parseColor("#98DAEA")));
        addSlide(AppIntroFragment.newInstance("Text", "Text", R.drawable.intro_3, Color.parseColor("#5996CD")));
        addSlide(AppIntroFragment.newInstance("Text", "Text", R.drawable.intro_4, Color.parseColor("#4C8DB3")));

        showStatusBar(false);

    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        Intent intent = new Intent(this, GoogleSign.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}