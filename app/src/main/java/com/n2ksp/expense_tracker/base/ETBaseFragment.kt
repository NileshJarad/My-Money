package com.n2ksp.expense_tracker.base

import androidx.fragment.app.Fragment
import com.n2ksp.expense_tracker.MyMoneyApplication
import com.n2ksp.expense_tracker.data.room.AppDatabase

open class ETBaseFragment : Fragment() {
    fun getAppDatabase(): AppDatabase {
        return (this.activity?.application as MyMoneyApplication).database
    }
}