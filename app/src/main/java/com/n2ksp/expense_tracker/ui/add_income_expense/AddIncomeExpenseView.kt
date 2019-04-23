package com.n2ksp.expense_tracker.ui.add_income_expense

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.ui.dashboard.IncomeExpensesViewModel
import kotlinx.android.synthetic.main.activity_add_income_expense.view.*
import java.util.*

@SuppressLint("ViewConstructor")
class AddIncomeExpenseView(activity: AddIncomeExpenseActivity) : LinearLayout(activity) {


    private lateinit var addIncomeExpense: IncomeExpensesViewModel
    private lateinit var sharedViewModel: SharedIncomeExpenseViewModel

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

        sharedViewModel =
            ViewModelProviders.of(activity).get(SharedIncomeExpenseViewModel::class.java)
        addIncomeExpense = ViewModelProviders.of(activity).get(IncomeExpensesViewModel::class.java)


        sharedViewModel.categoryModel?.observe(activity, Observer {
            addIncomeExpense.addExpense(
                IncomeExpenseModel(
                    categoryInfoModel = it,
                    amount = 500f,
                    memo = "Random",
                    date = Date().time
                )
            )
        })
    }


}