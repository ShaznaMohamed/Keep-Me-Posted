package com.keepmeposted.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.keepmeposted.MainActivity;
import com.keepmeposted.R;
import com.keepmeposted.views.activity.GoogleSign;
import com.keepmeposted.views.activity.Sign_In;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());

        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

        Animation anim= AnimationUtils.loadAnimation(this,R.anim.move_up);
        ImageView imageView=(ImageView)findViewById(R.id.logo);
        imageView.setAnimation(anim);

        if (isFirstStart) {

            Intent i = new Intent(this, MyIntro.class);
            startActivity(i);
            SharedPreferences.Editor e = getPrefs.edit();
            e.putBoolean("firstStart", true);
            e.apply();
            this.finish();
        }

        else {

            Intent intent = new Intent(this, Sign_In.class);
            startActivity(intent);
            this.finish();
        }
    }
}
