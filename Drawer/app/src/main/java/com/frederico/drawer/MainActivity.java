package com.frederico.drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    String Name = "", Email = "", Age = "", Cel = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.main_layout_drawer);
        navigationView = findViewById(R.id.main_layout_nav_view);
        FragmentManager fragmentManager =  getSupportFragmentManager();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            FragmentTransaction frag = fragmentManager.beginTransaction();
            switch (item.getItemId()){

                case R.id.drawer_menu_home_frag:
                frag.replace(R.id.main_layout_fragment_container, new HomeFragment()).commit();
                break;

                case R.id.drawer_menu_edit_frag:
                    frag.replace(R.id.main_layout_fragment_container, new EditFragment((name, email, age, cel) -> {
                        Name = (name.trim() != "") ? name : Name;
                        Age = (age.trim() != "") ? age : Age;
                        Email = (email.trim() != "") ? email : Email;
                        Cel = (cel.trim() != "") ? cel : Cel;
                        updateHeader();
                    })).commit();
                    break;

                case R.id.drawer_menu_profile_frag:
                    verifyValues();
                    frag.replace(R.id.main_layout_fragment_container, new ProfileFragment(Name, Email, Age, Cel)).commit();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        if(savedInstanceState == null){
            fragmentManager.beginTransaction().replace(R.id.main_layout_fragment_container, new HomeFragment()).commit();
        }


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    void updateHeader(){
        if(Name != null && Name != ""){
            TextView nameTextView = navigationView.getHeaderView(0).findViewById(R.id.drawer_header_name);
            nameTextView.setText(Name);
        }
        if(Email != null && Email != ""){
            TextView emailTextView = navigationView.getHeaderView(0).findViewById(R.id.drawer_header_email);
            emailTextView.setText(Email);
        }
    }

    void verifyValues(){
        if(Name.trim() == "" || Name == null){
            Name = getString(R.string.profile_frag_name).replace(":"," ") + getString(R.string.wasntInformed);
        }
        if(Age == "" || Age == null){
            Age = getString(R.string.profile_frag_age).replace(":"," ") + getString(R.string.wasntInformed);
        }
        if(Cel == "" || Cel == null){
            Cel = getString(R.string.profile_frag_cel).replace(":"," ") + getString(R.string.wasntInformed);
        }
        if(Email.trim() == "" || Email == null){
            Email = getString(R.string.profile_frag_email).replace(":"," ") + getString(R.string.wasntInformed);
        }
    }
}