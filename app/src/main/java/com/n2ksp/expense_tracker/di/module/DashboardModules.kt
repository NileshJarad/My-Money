package com.n2ksp.expense_tracker.di.module

import androidx.appcompat.app.AppCompatActivity
import com.n2ksp.expense_tracker.ui.dashboard.DashboardView
import dagger.Module
import dagger.Provides

@Module
class DashboardFragmentModule(val activity: AppCompatActivity) {

    @Provides
    fun provideDashboardView(): DashboardView {
        return DashboardView(activity = activity)
    }
}
