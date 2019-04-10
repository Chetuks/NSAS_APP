package com.example.nsas_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nsas_app.R;
import com.example.nsas_app.fragments.DashboardFragment;


public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final int PREFERENCES_ACTIVITY = 2;
    private TextView dashboard;
    DashboardFragment lFrag;
    DashboardFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);*/

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/
        displayFragment();
    }

    private void displayFragment() {
        fragment = new DashboardFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       /* if (R.id.menu_settings == item.getItemId()) {
            Intent intent = new Intent(getBaseContext(), Preferences.class);
            startActivityForResult(intent, PREFERENCES_ACTIVITY);
            return true;
        } else if (R.id.menu_alarm == item.getItemId()) {
            Intent intent = new Intent(getBaseContext(), AlarmMe.class);
            startActivity(intent);
            return true;
        } else if (R.id.customer_profile == item.getItemId()) {
            Intent intent1 = new Intent(getBaseContext(), CustomerProfile.class);
            startActivity(intent1);
            return true;
        } else if (R.id.checklist == item.getItemId()) {
            Intent intent1 = new Intent(getBaseContext(), Checklist.class);
            startActivity(intent1);
            return true;
        } else if (R.id.todo == item.getItemId()) {
            Intent intent1 = new Intent(getBaseContext(), ToDoListActivity.class);
            startActivity(intent1);
            return true;
        } else if (R.id.near_me == item.getItemId()) {
            Intent intent = new Intent(getBaseContext(), CategoryListActivity.class);
            startActivity(intent);
            return true;
        } else if (R.id.near_by == item.getItemId()) {
            Intent intent = new Intent(getBaseContext(), NearbyActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }*/
    return onOptionsItemSelected(null);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        if (id == R.id.activity_dash_board_id) {
            // if (fragment!=null) {
            fragment = new DashboardFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            //DrawerLayout drawerLayout=
            /*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();*/
        } else if (id == R.id.customer_profile_id) {
           /* fragment = new CustomerProfile();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();*/
            Intent intent = new Intent(HomePage.this, DocumentActivity.class);
            startActivity(intent);
        } else if (id == R.id.check_list_id) {
            Intent intent = new Intent(HomePage.this, DocumentActivity.class);
            startActivity(intent);
        } else if (id == R.id.to_do_list_id) {
            Intent intent = new Intent(HomePage.this, DocumentActivity.class);
            startActivity(intent);
        }  else if (id == R.id.settings_id) {
            Intent intent = new Intent(HomePage.this, DocumentActivity.class);
            startActivityForResult(intent, PREFERENCES_ACTIVITY);
        } else if (id == R.id.near_me) {
            Intent intent = new Intent(HomePage.this, DocumentActivity.class);
            startActivity(intent);
        }  else if (id == R.id.new_checklist) {
            Intent intent = new Intent(HomePage.this, DocumentActivity.class);
            startActivity(intent);
        } else if (id == R.id.new_dashboard) {
            fragment = new DashboardFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
