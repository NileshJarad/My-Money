package com.n2ksp.expense_tracker.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.base.ETBaseActivity
import com.n2ksp.expense_tracker.di.component.DaggerMainActivityComponent
import com.n2ksp.expense_tracker.di.module.MainActivityModule
import javax.inject.Inject

class MainActivity : ETBaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

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
            android.R.id.home -> findNavController(R.id.navHostFragment).navigateUp()
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.navHostFragment).navigateUp()
}