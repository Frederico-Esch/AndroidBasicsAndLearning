package com.frederico.drawerkot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout:DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.main_layout_drawer)
        navigationView = findViewById(R.id.nav_view)

        val fragmentManager = supportFragmentManager
        toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OPEN, R.string.CLOSE)
        toggle.drawerArrowDrawable.color = getColor(R.color.white)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener{
            val transaction = fragmentManager.beginTransaction()
            when(it.itemId){
                R.id.home_menu -> transaction.replace(R.id.fragment_holder, HomeFragment()).commit()
                R.id.Edit_Info_menu -> transaction.replace(R.id.fragment_holder, HomeFragment()).commit()
                R.id.Profile_menu -> Toast.makeText(applicationContext, "PROFILE PLACE HOLDER", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(applicationContext, "BBBBBBBBBBB", Toast.LENGTH_SHORT).show()
            }
            true
        }
        if(savedInstanceState==null){
            fragmentManager.beginTransaction().replace(R.id.fragment_holder, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.home_menu)
        }
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}