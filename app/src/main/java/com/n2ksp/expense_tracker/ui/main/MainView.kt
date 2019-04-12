package com.n2ksp.expense_tracker.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.n2ksp.expense_tracker.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.app_bar_main.view.*

@SuppressLint("ViewConstructor")
class MainView(activity: AppCompatActivity) : LinearLayout(activity), NavigationView.OnNavigationItemSelectedListener {
    init {
        initView(activity)
    }

    private fun initView(activity: AppCompatActivity) {
        LayoutInflater.from(context).inflate(R.layout.activity_main, this, true)


        activity.setSupportActionBar(toolbarMain)

        fabAdd.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            activity, drawerLayout, toolbarMain,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    fun closeNaviagtionViewIfItsOpen(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }

        return false;
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {

            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}