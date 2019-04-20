package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.ContextModule
import com.n2ksp.expense_tracker.di.module.DashboardFragmentModule
import com.n2ksp.expense_tracker.di.module.DividerItemDecorationModule
import com.n2ksp.expense_tracker.di.module.LinearLayoutMangerModule
import com.n2ksp.expense_tracker.ui.dashboard.DashboardFragment
import com.n2ksp.expense_tracker.ui.dashboard.DashboardView
import dagger.Component


@Component(modules = [DashboardFragmentModule::class])
interface DashboardFragmentComponent {
    fun inject(fragment: DashboardFragment)
}


@Component(modules = [ContextModule::class, DividerItemDecorationModule::class, LinearLayoutMangerModule::class])
interface DashboardViewComponent {
    fun inject(view: DashboardView)
}