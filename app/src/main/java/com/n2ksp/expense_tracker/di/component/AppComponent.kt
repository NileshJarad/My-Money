package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.MyMoneyApplication
import com.n2ksp.expense_tracker.data.room.AppDatabase
import com.n2ksp.expense_tracker.di.module.AppDatabaseModule
import com.n2ksp.expense_tracker.di.module.ContextModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, AppDatabaseModule::class])
interface AppComponent {
    fun inject(app: MyMoneyApplication)
    fun appDatabase(): AppDatabase
}