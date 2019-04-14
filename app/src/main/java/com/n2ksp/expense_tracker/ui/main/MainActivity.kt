package com.n2ksp.expense_tracker.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.base.ETBaseActivity
import com.n2ksp.expense_tracker.di.component.DaggerMainActivityComponent
import com.n2ksp.expense_tracker.di.module.MainActivityModule
import javax.inject.Inject

class MainActivity : ETBaseActivity() {

    @Inject
    lateinit var mainView: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build().inject(this)

        setContentView(mainView)

        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        mainView.syncNavControllerWithView(navController)
    }

    override fun onBackPressed() {
        if (!mainView.closeNavigationViewIfItsOpen()) {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}