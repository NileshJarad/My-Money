package com.n2ksp.expense_tracker.di.module

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides


@Module
class LinearLayoutMangerModule {

    @Provides
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

}