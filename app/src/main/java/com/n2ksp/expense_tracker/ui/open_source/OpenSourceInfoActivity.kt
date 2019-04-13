package com.n2ksp.expense_tracker.ui.open_source

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class OpenSourceInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var view = OpenSourceInfoView(this)
        setContentView(view)
    }
}
