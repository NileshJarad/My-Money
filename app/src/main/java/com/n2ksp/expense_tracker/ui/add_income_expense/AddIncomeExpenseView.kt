package com.n2ksp.expense_tracker.ui.add_income_expense

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import com.n2ksp.expense_tracker.R
import kotlinx.android.synthetic.main.activity_add_income_expense.view.*

@SuppressLint("ViewConstructor")
class AddIncomeExpenseView(activity: AddIncomeExpenseActivity) : LinearLayout(activity) {
    init {
        initView(activity)
    }

    private fun initView(activity: AddIncomeExpenseActivity) {
        View.inflate(activity, R.layout.activity_add_income_expense, this)

        activity.setSupportActionBar(addIncomeExpenseToolbar)

        activity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }
}