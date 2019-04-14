package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.OpenSourceInfoModule
import com.n2ksp.expense_tracker.ui.open_source.OpenSourceInfoActivity
import dagger.Component


@Component(modules = [OpenSourceInfoModule::class])
interface OpenSourceInfoComponent {
    fun inject(activity: OpenSourceInfoActivity)
}