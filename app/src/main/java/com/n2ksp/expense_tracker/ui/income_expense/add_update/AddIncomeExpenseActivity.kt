package com.n2ksp.expense_tracker.ui.income_expense.add_update

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.n2ksp.expense_tracker.base.ETBaseActivity
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.di.component.DaggerAddIncomeExpenseAcitivtyComponent
import com.n2ksp.expense_tracker.di.module.AddIncomeExpenseAcitivtyModule
import javax.inject.Inject


class AddIncomeExpenseActivity : ETBaseActivity() {

    @Inject
    lateinit var view: AddIncomeExpenseView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAddIncomeExpenseAcitivtyComponent.builder()
            .addIncomeExpenseAcitivtyModule(AddIncomeExpenseAcitivtyModule(this))
            .build()
            .inject(this)
        setContentView(view)


    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                android.R.id.home -> {
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_EDIT_INCOME_EXPENSE_ENTRY = "edit_income_expense_entry"
        fun start(context: Context, data: IncomeExpenseModel) {
            context.startActivity(
                Intent(context, AddIncomeExpenseActivity::class.java).apply {
                    putExtra(EXTRA_EDIT_INCOME_EXPENSE_ENTRY, data)
                }
            )
        }
    }
}
