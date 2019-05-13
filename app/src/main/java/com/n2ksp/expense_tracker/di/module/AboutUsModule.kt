package com.n2ksp.expense_tracker.di.module

import com.n2ksp.expense_tracker.ui.about_us.AboutUsActivity
import com.n2ksp.expense_tracker.ui.about_us.AboutUsView

import dagger.Module
import dagger.Provides


@Module
class AboutUsModule(var aboutUsActivity: AboutUsActivity) {
    @Provides
    fun provideMainView(): AboutUsView {
        return AboutUsView(aboutUsActivity)
    }

}