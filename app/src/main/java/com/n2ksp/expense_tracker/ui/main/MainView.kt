package com.n2ksp.expense_tracker.ui.main

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
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

        val toggle = ActionBarDrawerToggle(
            activity, drawerLayout, toolbarMain,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        changeColorOfNavigationMenus()
    }

    private fun changeColorOfNavigationMenus() {
        changeIconColors(R.id.navMenuSetting, R.color.red_400)
        changeIconColors(R.id.navMenuRateUs, R.color.jungle_green)
    }

    private fun changeIconColors(navMenuId: Int, color: Int) {
        var drawable = navView.menu.getItem(0).icon

        drawable.mutate()
        drawable.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP)

//        drawable = DrawableCompat.wrap(drawable)
//        DrawableCompat.setTint(drawable, ContextCompat.getColor(context, color))
        navView.menu.getItem(0).icon = drawable
    }

    fun closeNavigationViewIfItsOpen(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
//            R.id.nav_camera -> {
//
//            }
//            R.id.nav_gallery -> {
//
//            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_manage -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}