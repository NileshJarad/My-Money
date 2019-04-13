package com.n2ksp.expense_tracker.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.n2ksp.expense_tracker.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.content_main.view.*


@SuppressLint("ViewConstructor")
class MainView(activity: AppCompatActivity) : LinearLayout(activity) {
    init {
        initView(activity)
    }

    private fun initView(activity: AppCompatActivity) {
        LayoutInflater.from(context).inflate(R.layout.activity_main, this, true)

        activity.setSupportActionBar(toolbarMain)

        val toggle = ActionBarDrawerToggle(
            activity, drawerLayout, toolbarMain,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navMenuOpenSource.setOnClickListener {
            onNavigationItemSelected(navMenuOpenSource)
        }

        navMenuAbout.setOnClickListener {
            onNavigationItemSelected(navMenuAbout)
        }

        navMenuSetting.setOnClickListener {
            onNavigationItemSelected(navMenuSetting)
        }


        navMenuRateUs.setOnClickListener {
            onNavigationItemSelected(navMenuRateUs)
        }

    }


    fun closeNavigationViewIfItsOpen(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }


    private fun onNavigationItemSelected(view: View): Boolean {
        // Handle navigation view item clicks here.
        when (view.id) {
            R.id.navMenuOpenSource -> {
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun syncNavControllerWithView(navController: NavController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }
}