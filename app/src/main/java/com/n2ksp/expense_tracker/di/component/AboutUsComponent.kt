package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.*
import com.n2ksp.expense_tracker.ui.about_us.AboutUsActivity
import com.n2ksp.expense_tracker.ui.about_us.AboutUsView
import com.n2ksp.expense_tracker.ui.open_source.OpenSourceInfoActivity
import com.n2ksp.expense_tracker.ui.open_source.OpenSourceInfoView
import dagger.Component


@Component(modules = [AboutUsModule::class])
interface AboutUsComponent {
    fun inject(activity: AboutUsActivity)
}


@Component(modules = [ContextModule::class, DividerItemDecorationModule::class, LinearLayoutMangerModule::class])
interface AboutUsAdapterComponent {
    fun inject(aboutUsView: AboutUsView)
}