package com.n2ksp.expense_tracker.ui.income_expense.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.base.ETBaseActivity
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.Constants
import com.n2ksp.expense_tracker.utils.DateUtils
import kotlinx.android.synthetic.main.activity_income_expense_detail.*

class IncomeExpenseDetailActivity : ETBaseActivity() {

    lateinit var data: IncomeExpenseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_expense_detail)

        ViewCompat.setTransitionName(amountValueTextView, VIEW_NAME_AMOUNT)
        ViewCompat.setTransitionName(categoryTextView, VIEW_NAME_CATEGORY)
        ViewCompat.setTransitionName(memoValueTextView, VIEW_NAME_MEMO)
        ViewCompat.setTransitionName(categoryImageView, VIEW_NAME_IMAGE)
        ViewCompat.setTransitionName(tvTitleCategoryType, VIEW_NAME_TYPE)
        ViewCompat.setTransitionName(dateValueTextView, VIEW_NAME_DATE)

        if (intent.hasExtra(EXTRA_DATA)) {
            data = intent.getParcelableExtra(EXTRA_DATA)

            amountValueTextView.text = AmountUtils.getAmountFormatted(data.amount)
            categoryTextView.text = data.categoryInfoModel.categoryTitle
            memoValueTextView.text = data.memo
            categoryImageView.setImageResource(data.categoryInfoModel.categoryImage)
            categoryImageView.setColorFilter(ContextCompat.getColor(this, data.categoryInfoModel.categoryColor))
            tvTitleCategoryType.text = data.categoryInfoModel.categoryType
            dateValueTextView.text = DateUtils.getDateInDDMMMYYY(data.date)

            if (data.categoryInfoModel.categoryType == Constants.EXPENSE) {
                tvTitleCategoryType.setTextColor(ContextCompat.getColor(this, R.color.colorExpense))
            } else {
                tvTitleCategoryType.setTextColor(ContextCompat.getColor(this, R.color.colorIncome))
            }
        }
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
}
