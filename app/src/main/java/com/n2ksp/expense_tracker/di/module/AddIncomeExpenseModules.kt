package com.n2ksp.expense_tracker.di.module

import com.n2ksp.expense_tracker.ui.income_expense.add_update.AddIncomeExpenseActivity
import com.n2ksp.expense_tracker.ui.income_expense.add_update.AddIncomeExpenseView
import dagger.Module
import dagger.Provides


@Module
class AddIncomeExpenseAcitivtyModule(val activity: AddIncomeExpenseActivity) {
    @Provides
    fun provideAddIncomeExpenseView(): AddIncomeExpenseView {
        return AddIncomeExpenseView(activity)
    }

}