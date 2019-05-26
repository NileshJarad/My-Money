package com.n2ksp.expense_tracker.ui.about_us


import android.os.Bundle
import com.n2ksp.expense_tracker.base.ETBaseActivity
import com.n2ksp.expense_tracker.di.component.DaggerAboutUsComponent
import com.n2ksp.expense_tracker.di.module.AboutUsModule
import javax.inject.Inject

class AboutUsActivity : ETBaseActivity() {

    @Inject
    lateinit var view: AboutUsView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAboutUsComponent.builder()
            .aboutUsModule(AboutUsModule(this))
            .build()
            .inject(this)
        setContentView(view)

    }
}
