package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.*
import com.n2ksp.expense_tracker.ui.dashboard.DashboardFragment
import com.n2ksp.expense_tracker.ui.dashboard.DashboardView
import dagger.Component


@Component(modules = [DashboardFragmentModule::class])
interface DashboardFragmentComponent {
    fun inject(fragment: DashboardFragment)
}


@Component(modules = [ContextModule::class, DividerItemDecorationModule::class, LinearLayoutMangerModule::class, SharedPrefModule::class])
interface DashboardViewComponent {
    fun inject(view: DashboardView)
}