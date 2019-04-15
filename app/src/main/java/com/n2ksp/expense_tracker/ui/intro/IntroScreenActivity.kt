package com.n2ksp.expense_tracker.ui.intro

import android.os.Bundle
import com.n2ksp.expense_tracker.base.ETBaseActivity
import com.n2ksp.expense_tracker.di.component.DaggerIntroScreenActivityComponent
import com.n2ksp.expense_tracker.di.module.IntroScreenActivityModule
import javax.inject.Inject

class IntroScreenActivity : ETBaseActivity() {
    @Inject
    lateinit var view: IntroScreenView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerIntroScreenActivityComponent.builder()
            .introScreenActivityModule(IntroScreenActivityModule(this))
            .build()
            .inject(this)

        setContentView(view)
    }
}
