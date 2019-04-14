package com.n2ksp.expense_tracker.di.module

import com.n2ksp.expense_tracker.ui.main.MainActivity
import com.n2ksp.expense_tracker.ui.main.MainView
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule(var mainActivity: MainActivity) {


    @Provides
    fun provideMainView(): MainView {
        return MainView(mainActivity)
    }

}