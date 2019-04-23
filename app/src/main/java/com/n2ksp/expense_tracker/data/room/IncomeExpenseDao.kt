package com.n2ksp.expense_tracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface IncomeExpenseDao {
    @Query("SELECT * FROM income_expense")
    fun getAll(): List<IncomeExpenseDBModel>

    @Query("SELECT * FROM income_expense where date > :startDate and date < :endDate")
    fun findEntriesForDate(startDate: Long, endDate: Long): List<IncomeExpenseDBModel>

    @Query("SELECT COUNT(*) from income_expense")
    fun countEntries(): Int

    @Insert
    fun insert(incomeExpense: IncomeExpenseDBModel)

    @Delete
    fun delete(incomeExpense: IncomeExpenseDBModel)

}