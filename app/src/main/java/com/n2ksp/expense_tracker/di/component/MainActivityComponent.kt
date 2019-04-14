package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.MainActivityModule
import com.n2ksp.expense_tracker.ui.main.MainActivity
import dagger.Component


@Component(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}