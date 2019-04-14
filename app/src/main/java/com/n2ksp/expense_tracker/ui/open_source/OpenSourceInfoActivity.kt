package com.n2ksp.expense_tracker.ui.open_source

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.n2ksp.expense_tracker.di.component.DaggerOpenSourceInfoComponent
import com.n2ksp.expense_tracker.di.module.OpenSourceInfoModule
import javax.inject.Inject

class OpenSourceInfoActivity : AppCompatActivity() {

    @Inject
    lateinit var view: OpenSourceInfoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//         view = OpenSourceInfoView(this)
        DaggerOpenSourceInfoComponent.builder()
            .openSourceInfoModule(OpenSourceInfoModule(this))
            .build()
            .inject(this)
        setContentView(view)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        view.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }
}
