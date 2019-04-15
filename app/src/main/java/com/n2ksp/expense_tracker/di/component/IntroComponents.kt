package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.IntroScreenActivityModule
import com.n2ksp.expense_tracker.di.module.IntroScreenViewModule
import com.n2ksp.expense_tracker.ui.intro.IntroScreenActivity
import com.n2ksp.expense_tracker.ui.intro.IntroScreenView
import dagger.Component


@Component(modules = [IntroScreenActivityModule::class])
interface IntroScreenActivityComponent {
    fun inject(activity: IntroScreenActivity)
}


@Component(modules = [IntroScreenViewModule::class])
interface IntroScreenViewComponent {
    fun inject(view: IntroScreenView)
}