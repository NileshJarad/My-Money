package com.n2ksp.expense_tracker.di.module

import android.content.Context
import com.n2ksp.expense_tracker.data.sharedpreference.SharedPrefUtil
import dagger.Module
import dagger.Provides


@Module
class SharedPrefModule {
    @Provides
    fun provideSharedPrefUtil(context: Context): SharedPrefUtil {
        return SharedPrefUtil(context = context)
    }
}