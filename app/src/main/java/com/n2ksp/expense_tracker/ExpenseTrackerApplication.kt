package com.n2ksp.expense_tracker

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree


class ExpenseTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}

