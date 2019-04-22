package com.n2ksp.expense_tracker.di.module

import com.n2ksp.expense_tracker.ui.dashboard.DashboardView
import com.n2ksp.expense_tracker.ui.main.MainActivity
import dagger.Module
import dagger.Provides

@Module
class DashboardFragmentModule(val activity: MainActivity) {

    @Provides
    fun provideDashboardView(): DashboardView {
        return DashboardView(activity = activity)
    }
}
