package com.n2ksp.expense_tracker

import android.app.Application
import com.n2ksp.expense_tracker.data.room.AppDatabase
import com.n2ksp.expense_tracker.di.component.DaggerAppComponent
import com.n2ksp.expense_tracker.di.module.ContextModule
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


class MyMoneyApplication : Application() {

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
            .inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}

