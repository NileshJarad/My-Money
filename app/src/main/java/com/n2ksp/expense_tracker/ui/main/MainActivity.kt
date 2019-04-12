package com.n2ksp.expense_tracker.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.n2ksp.expense_tracker.R

class MainActivity : AppCompatActivity() {

    private lateinit var mainView: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = MainView(this)
        setContentView(mainView)
    }

    override fun onBackPressed() {
        if (!mainView.closeNaviagtionViewIfItsOpen()) {
            super.onBackPressed()
        }
    }

    //
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}