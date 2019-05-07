package com.n2ksp.expense_tracker.ui.income_expense.detail

import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProviders
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.ui.income_expense.IncomeExpensesViewModel
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.Constants
import com.n2ksp.expense_tracker.utils.DateUtils
import kotlinx.android.synthetic.main.activity_income_expense_detail.view.*

class IncomeExpenseDetailView(val activity: IncomeExpenseDetailActivity) : LinearLayout(activity) {
    private var viewModel: IncomeExpensesViewModel
    lateinit var data: IncomeExpenseModel

    init {
        initView()
        viewModel = ViewModelProviders.of(activity).get(IncomeExpensesViewModel::class.java)
    }

    private fun initView() {
        View.inflate(activity, R.layout.activity_income_expense_detail, this)


        activity.setSupportActionBar(incomeExpenseDetailToolbar)

        activity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }


        ViewCompat.setTransitionName(amountValueTextView, IncomeExpenseDetailActivity.VIEW_NAME_AMOUNT)
        ViewCompat.setTransitionName(categoryTextView, IncomeExpenseDetailActivity.VIEW_NAME_CATEGORY)
        ViewCompat.setTransitionName(memoValueTextView, IncomeExpenseDetailActivity.VIEW_NAME_MEMO)
        ViewCompat.setTransitionName(categoryImageView, IncomeExpenseDetailActivity.VIEW_NAME_IMAGE)
        ViewCompat.setTransitionName(tvTitleCategoryType, IncomeExpenseDetailActivity.VIEW_NAME_TYPE)
        ViewCompat.setTransitionName(dateValueTextView, IncomeExpenseDetailActivity.VIEW_NAME_DATE)

        if (activity.intent.hasExtra(IncomeExpenseDetailActivity.EXTRA_DATA)) {
            data = activity.intent.getParcelableExtra(IncomeExpenseDetailActivity.EXTRA_DATA)

            amountValueTextView.text = AmountUtils.getAmountFormatted(data.amount)
            categoryTextView.text = data.categoryInfoModel.categoryTitle
            memoValueTextView.text = data.memo
            categoryImageView.setImageResource(data.categoryInfoModel.categoryImage)
            categoryImageView.setColorFilter(ContextCompat.getColor(activity, data.categoryInfoModel.categoryColor))
            tvTitleCategoryType.text = data.categoryInfoModel.categoryType
            dateValueTextView.text = DateUtils.getDateInDDMMMYYY(data.date)

            if (data.categoryInfoModel.categoryType == Constants.EXPENSE) {
                tvTitleCategoryType.setTextColor(ContextCompat.getColor(activity, R.color.colorExpense))
            } else {
                tvTitleCategoryType.setTextColor(ContextCompat.getColor(activity, R.color.colorIncome))
            }
        }

        deleteImageButton.setOnClickListener {
            viewModel.deleteEntry(
                IncomeExpenseModel(
                    data.categoryInfoModel,
                    data.memo,
                    data.amount,
                    data.date,
                    data.id
                )
            )
            activity.finish()
        }
    }

    fun onOptionsItemSelected(item: MenuItem?) {
        item?.let {
            if (it.itemId == android.R.id.home) {
                activity.finish()
            }
        }
    }

}