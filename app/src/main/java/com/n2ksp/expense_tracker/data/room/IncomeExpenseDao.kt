package com.n2ksp.expense_tracker.data.room

import androidx.room.*


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

    @Update
    fun update(incomeExpense: IncomeExpenseDBModel)

    @Delete
    fun delete(incomeExpense: IncomeExpenseDBModel)

    @Query("SELECT SUM(amount) FROM income_expense where date > :timeStartMonth and date < :timeEndMonth and type == :type")
    fun getTotalForMonthByType(timeStartMonth: Long, timeEndMonth: Long, type: String): Float

    @Query("SELECT * FROM income_expense where  id = :id")
    fun getEntry(id: Int): IncomeExpenseDBModel


}