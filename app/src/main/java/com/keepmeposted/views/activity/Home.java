package com.keepmeposted.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.keepmeposted.R;

public class Home extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button btn_map,btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_map = (Button) findViewById(R.id.btnMap);
        btn_logout = (Button) findViewById(R.id.logout);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(Home.this, Map.class));
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getInstance().signOut();
            }
        });
    }
}
