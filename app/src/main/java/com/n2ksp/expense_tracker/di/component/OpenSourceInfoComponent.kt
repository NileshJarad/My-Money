package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.ContextModule
import com.n2ksp.expense_tracker.di.module.DividerItemDecorationModule
import com.n2ksp.expense_tracker.di.module.LinearLayoutMangerModule
import com.n2ksp.expense_tracker.di.module.OpenSourceInfoModule
import com.n2ksp.expense_tracker.ui.open_source.OpenSourceInfoActivity
import com.n2ksp.expense_tracker.ui.open_source.OpenSourceInfoView
import dagger.Component


@Component(modules = [OpenSourceInfoModule::class])
interface OpenSourceInfoComponent {
    fun inject(activity: OpenSourceInfoActivity)
}


@Component(modules = [ContextModule::class, DividerItemDecorationModule::class, LinearLayoutMangerModule::class])
interface OpenSourceAdapterComponent {
    fun inject(openSourceInfoView: OpenSourceInfoView)
}