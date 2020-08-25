package com.example.assignment1;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Setting Navigation Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        //Configuring Appbar Navigation
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_downtime, R.id.nav_production)
                .setDrawerLayout(drawer)
                .build();


        //Setting Appbar Config and Nav_View in Navigation Ui
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //Function for checking user is admin or normal user
        checkAdmin();

        //Changing color of ActionBar
//        ActionBar actionBar;
//        actionBar = getSupportActionBar();
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#344955"));
//        actionBar.setBackgroundDrawable(colorDrawable);

    }

    private void checkAdmin() {
        MyPrefs.prefs = getSharedPreferences(MyPrefs.MY_PREF_NAME,MODE_PRIVATE);
        String isFirstTime = MyPrefs.prefs.getString("c_id","");
        if(isFirstTime.equals("")){
            startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            finish();
        }
    }


    //Side 3 dot's Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_side, menu);
        return true;
    }


    //On menu item click on right side 3 dots
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {  logoutUser(); }
        return super.onOptionsItemSelected(item);
    }

    //Logout function when user is logging out
    private void logoutUser() {

        MyPrefs.editor = getSharedPreferences(MyPrefs.MY_PREF_NAME,MODE_PRIVATE).edit();
        MyPrefs.editor.putString("isAdmin","false");
        MyPrefs.editor.putString("isLoggedIn","false");
        MyPrefs.editor.apply();

        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }


    //For opening Nav bar from left side on click of three bars
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
