package com.n2ksp.expense_tracker.base

import androidx.appcompat.app.AppCompatActivity
import com.n2ksp.expense_tracker.MyMoneyApplication
import com.n2ksp.expense_tracker.data.room.AppDatabase

open class ETBaseActivity : AppCompatActivity() {

    fun getAppDatabase(): AppDatabase {
        return (this.application as MyMoneyApplication).database
    }

}
