package com.n2ksp.expense_tracker.ui.income_expense.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.n2ksp.expense_tracker.base.ETBaseActivity
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel

class IncomeExpenseDetailActivity : ETBaseActivity() {
    private lateinit var incomeExpenseDetailView: IncomeExpenseDetailView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        incomeExpenseDetailView = IncomeExpenseDetailView(this)
        setContentView(incomeExpenseDetailView)
//        setContentView(R.layout.activity_income_expense_detail)
    }

    companion object {
        const val VIEW_NAME_AMOUNT = "view_name_amount"
        const val VIEW_NAME_MEMO = "view_name_memo"
        const val VIEW_NAME_IMAGE = "view_name_image"
        const val VIEW_NAME_CATEGORY = "view_name_category"
        const val VIEW_NAME_DATE = "view_name_date"
        const val VIEW_NAME_TYPE = "view_name_type"
        const val EXTRA_DATA = "extra_data"
        fun start(
            context: Context,
            activityOptions: ActivityOptionsCompat,
            data: IncomeExpenseModel
        ) {
            val intent = Intent(context, IncomeExpenseDetailActivity::class.java)
            intent.putExtra(EXTRA_DATA, data)
            ActivityCompat.startActivity(context, intent, activityOptions.toBundle())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        incomeExpenseDetailView.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }
}
