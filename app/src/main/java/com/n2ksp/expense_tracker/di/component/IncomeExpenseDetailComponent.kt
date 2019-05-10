package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.IncomeExpenseDetailModule
import com.n2ksp.expense_tracker.ui.income_expense.detail.IncomeExpenseDetailActivity
import dagger.Component


@Component(modules = [IncomeExpenseDetailModule::class])
interface IncomeExpenseDetailComponent {
    fun inject(activity: IncomeExpenseDetailActivity)
}