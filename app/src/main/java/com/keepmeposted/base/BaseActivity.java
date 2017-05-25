package com.keepmeposted.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.keepmeposted.R;
import com.keepmeposted.MapsActivity_1;
import com.keepmeposted.views.activity.GoogleSign;
import com.keepmeposted.views.activity.PantryListCategoryActivity;
import com.keepmeposted.views.activity.RecipeActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import com.keepmeposted.R;
import com.keepmeposted.views.activity.Settings;

import static com.keepmeposted.R.id.action_settings;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == action_settings) {
            Intent intent = new Intent(this,Settings.class);
            this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_nearplace) {
            Toast.makeText(getApplicationContext(), "Near By Places Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MapsActivity_1.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_pantry) {
            Intent intent = new Intent(this, PantryListCategoryActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_recipe) {
            Intent intent = new Intent(this, RecipeActivity.class);
            this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
