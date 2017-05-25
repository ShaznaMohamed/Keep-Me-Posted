package com.keepmeposted.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.keepmeposted.R;

public class RecipeActivity extends AppCompatActivity {

    public Button urlButton;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        setTitle("Recipe Book");
        setupToolBar();

        urlButton = (Button)findViewById(R.id.btnRecipe);

        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), URLActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
