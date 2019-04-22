package com.n2ksp.expense_tracker.di.module

import com.n2ksp.expense_tracker.ui.add_income_expense.AddIncomeExpenseActivity
import com.n2ksp.expense_tracker.ui.add_income_expense.AddIncomeExpenseView
import dagger.Module
import dagger.Provides


@Module
class AddIncomeExpenseAcitivtyModule(val activity: AddIncomeExpenseActivity) {
    @Provides
    fun provideAddIncomeExpenseView(): AddIncomeExpenseView {
        return AddIncomeExpenseView(activity)
    }

}