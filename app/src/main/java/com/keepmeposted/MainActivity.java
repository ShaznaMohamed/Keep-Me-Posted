package com.keepmeposted;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.keepmeposted.base.BaseActivity;
import com.keepmeposted.utility.adapter.TabViewPager;
import com.keepmeposted.utility.misc.Fab;
import com.keepmeposted.views.fragment.BudgetFragment;
import com.keepmeposted.views.fragment.ShoppingListFragment;
import com.keepmeposted.views.fragment.ToDoFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAnalytics mFirebaseAnalytics;
    private MaterialSheetFab ShoppingListFab, ToDoListFab, BudgetFab;
    private TabViewPager mAdapter;
    Toolbar toolbar;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        setupToolBar();
        setupDrawer();
        setupFab();
        setupTabs();
        initialiseFirebase();

        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            handleUserProfile();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new TabViewPager(getSupportFragmentManager());
        mAdapter.addFragment(new ShoppingListFragment(), "Shopping");
        mAdapter.addFragment(new ToDoFragment(), "ToDo");
        mAdapter.addFragment(new BudgetFragment(), "Budget");
        viewPager.setAdapter(mAdapter);
    }

    private void setupToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupDrawer(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupTabs(){

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        updatePage(viewPager.getCurrentItem());

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                updatePage(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    private void initialiseFirebase(){
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void setupFab(){
        Fab fab_shopping = (Fab) findViewById(R.id.fab_shopping);
        Fab fab_todo = (Fab) findViewById(R.id.fab_todo);
        Fab fab_budget = (Fab) findViewById(R.id.fab_budget);

        View sheetViewShopping = findViewById(R.id.fab_sheet_shopping);
        View sheetViewToDo = findViewById(R.id.fab_sheet_todo);
        View sheetViewBudget = findViewById(R.id.fab_sheet_budget);

        View overlay_shopping = findViewById(R.id.overlay_shopping);
        View overlay_todo = findViewById(R.id.overlay_todo);
        View overlay_budget = findViewById(R.id.overlay_budget);

        int sheetColor = getResources().getColor(R.color.background_card);
        int fabColor = getResources().getColor(R.color.colorAccent);

        // Create material sheet FAB
        ShoppingListFab = new MaterialSheetFab<>(fab_shopping, sheetViewShopping, overlay_shopping, sheetColor, fabColor);
        ToDoListFab = new MaterialSheetFab<>(fab_todo, sheetViewToDo, overlay_todo, sheetColor, fabColor);
        BudgetFab = new MaterialSheetFab<>(fab_budget, sheetViewBudget, overlay_budget, sheetColor, fabColor);

    }

    private void updatePage(int selectedPage) {
        updateFab(selectedPage);
    }

    private void updateFab(int selectedPage) {
        switch (selectedPage) {
            case TabViewPager.SHOPPING_TAB:
                ShoppingListFab.showFab();
                ToDoListFab.hideSheetThenFab();
                BudgetFab.hideSheetThenFab();
                break;
            case TabViewPager.TODO_TAB:
                ToDoListFab.showFab();
                ShoppingListFab.hideSheetThenFab();
                BudgetFab.hideSheetThenFab();
                break;
            case TabViewPager.BUDGET_TAB:
                BudgetFab.showFab();
                ToDoListFab.hideSheetThenFab();
                ShoppingListFab.hideSheetThenFab();
            default:
                ShoppingListFab.showFab();
                ToDoListFab.hideSheetThenFab();
                BudgetFab.hideSheetThenFab();
                break;
        }
    }

    @Override
    public void onClick(View v) {

        if (ShoppingListFab.isSheetVisible()){
            ShoppingListFab.hideSheet();
        }
        else if (ToDoListFab.isSheetVisible()){
            ToDoListFab.hideSheet();
        }
        else if (BudgetFab.isSheetVisible()){
            BudgetFab.hideSheet();
        }
    }
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else if (ShoppingListFab.isSheetVisible()){
            ShoppingListFab.hideSheet();
        }
        else if (ToDoListFab.isSheetVisible()){
            ToDoListFab.hideSheet();
        }
        else if (BudgetFab.isSheetVisible()){
            BudgetFab.hideSheet();
        }

        else {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory( Intent.CATEGORY_HOME );
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }


    }
    private void handleUserProfile() {
        ImageView proPicImageView;
        NavigationView navigationView;
        TextView userName,userEmail;
        Uri ProPicURL;
        FirebaseAuth auth;

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        proPicImageView = (ImageView) header.findViewById(R.id.propicImageView);
        userName = (TextView)header.findViewById(R.id.userNametxtview);
        userEmail = (TextView)header.findViewById(R.id.userEmailtxtview);

        auth = FirebaseAuth.getInstance();
        ProPicURL = auth.getCurrentUser().getPhotoUrl();
        Picasso.with(header.getContext()).load(ProPicURL).into(proPicImageView);
        if(auth.getCurrentUser().getDisplayName() != null && auth.getCurrentUser().getEmail() != null) {
            userName.setText(auth.getCurrentUser().getDisplayName());
            userEmail.setText(auth.getCurrentUser().getEmail());
        }
    }

}
