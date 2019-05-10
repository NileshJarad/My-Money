package com.n2ksp.expense_tracker.di.module

import com.n2ksp.expense_tracker.ui.income_expense.detail.IncomeExpenseDetailActivity
import com.n2ksp.expense_tracker.ui.income_expense.detail.IncomeExpenseDetailView
import dagger.Module
import dagger.Provides


@Module
class IncomeExpenseDetailModule(var activity: IncomeExpenseDetailActivity) {

    @Provides
    fun provideIncomeExpenseDetailView(): IncomeExpenseDetailView {
        return IncomeExpenseDetailView(activity)
    }
}

