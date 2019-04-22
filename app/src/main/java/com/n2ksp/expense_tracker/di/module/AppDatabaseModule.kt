package com.n2ksp.expense_tracker.di.module

import android.content.Context
import com.n2ksp.expense_tracker.data.room.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseModule {
    @Provides
    fun provideAppDataBase(context: Context): AppDatabase {
        return AppDatabase.getAppDatabase(context)
    }
}