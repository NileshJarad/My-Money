package com.n2ksp.expense_tracker.di.module

import com.n2ksp.expense_tracker.ui.open_source.OpenSourceInfoActivity
import com.n2ksp.expense_tracker.ui.open_source.OpenSourceInfoView
import dagger.Module
import dagger.Provides


@Module
class OpenSourceInfoModule(var openSourceInfoActivity: OpenSourceInfoActivity) {

    @Provides
    fun provideMainView(): OpenSourceInfoView {
        return OpenSourceInfoView(openSourceInfoActivity)
    }

}