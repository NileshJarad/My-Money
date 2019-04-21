package com.n2ksp.expense_tracker.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.n2ksp.expense_tracker.R
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.content_main.view.*
import timber.log.Timber
import javax.inject.Inject


@SuppressLint("ViewConstructor")
class MainView @Inject constructor(val activity: MainActivity) : LinearLayout(activity) {
    private lateinit var navController: NavController

    init {
        initView(activity)
    }

    private fun initView(activity: MainActivity) {

        Observable.just(activity.getAppDatabase())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Timber.e("Category count : ${it.userDao().countCategories()}")

            }



        LayoutInflater.from(context).inflate(R.layout.activity_main, this, true)
        activity.setSupportActionBar(toolbarMain)
        setupDrawerLayout(activity)
        attachNavigationMenuListener()
    }

    private fun setupDrawerLayout(activity: MainActivity) {
        val toggle = ActionBarDrawerToggle(
            activity, drawerLayout, toolbarMain,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun attachNavigationMenuListener() {
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
        when (view.id) {
            R.id.navMenuOpenSource -> {
                navController.navigate(R.id.openSourceInfoActivity)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun syncNavControllerWithView(navController: NavController) {
        this.navController = navController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        NavigationUI.setupWithNavController(toolbarMain, navController, drawerLayout)
    }

}