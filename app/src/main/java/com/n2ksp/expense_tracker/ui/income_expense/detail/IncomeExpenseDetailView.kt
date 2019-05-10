package com.n2ksp.expense_tracker.ui.income_expense.detail

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.ui.income_expense.IncomeExpensesViewModel
import com.n2ksp.expense_tracker.ui.income_expense.add_update.AddIncomeExpenseActivity
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.Constants
import com.n2ksp.expense_tracker.utils.DateUtils
import kotlinx.android.synthetic.main.activity_income_expense_detail.view.*
import javax.inject.Inject


@SuppressLint("ViewConstructor")
class IncomeExpenseDetailView @Inject constructor(val activity: IncomeExpenseDetailActivity) : LinearLayout(activity) {
    private var viewModel: IncomeExpensesViewModel =
        ViewModelProviders.of(activity).get(IncomeExpensesViewModel::class.java)
    lateinit var data: IncomeExpenseModel

    init {
        initView()
    }

    private fun initView() {
        View.inflate(activity, R.layout.activity_income_expense_detail, this)

        setupToolbar()

        setupAnimationForViews()

        setDataToViews()


        // delete action handling
        deleteImageButton.setOnClickListener {

            AlertDialog.Builder(context)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                .setPositiveButton(android.R.string.yes) { _, _ ->
                    // delete entry from database
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
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }

        // edit action handling
        editImageButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Edit entry")
                .setMessage("Are you sure you want edit this entry?")

                .setPositiveButton(android.R.string.yes) { _, _ ->
                    AddIncomeExpenseActivity.start(activity, data)
                }
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
    }

    /**
     * Retrieves data from extra and set data to views
     */
    private fun setDataToViews() {
        if (activity.intent.hasExtra(IncomeExpenseDetailActivity.EXTRA_DATA)) {
            data = activity.intent.getParcelableExtra(IncomeExpenseDetailActivity.EXTRA_DATA)
            updateViewFromDB()
        }
    }

    private fun updateViewFromDB() {
        viewModel.getEntry(data.id).observe(activity, Observer {
            data = it
            amountValueTextView.text = AmountUtils.getAmountFormatted(it.amount)
            categoryTextView.text = it.categoryInfoModel.categoryTitle
            memoValueTextView.text = if (it.memo.isBlank()) {
                it.categoryInfoModel.categoryTitle
            } else {
                it.memo
            }
            categoryImageView.setImageResource(it.categoryInfoModel.categoryImage)
            categoryImageView.setColorFilter(ContextCompat.getColor(activity, it.categoryInfoModel.categoryColor))
            tvTitleCategoryType.text = it.categoryInfoModel.categoryType
            dateValueTextView.text = DateUtils.getDateInDDMMMYYY(it.date)

            if (it.categoryInfoModel.categoryType == Constants.EXPENSE) {
                tvTitleCategoryType.setTextColor(ContextCompat.getColor(activity, R.color.colorExpense))
            } else {
                tvTitleCategoryType.setTextColor(ContextCompat.getColor(activity, R.color.colorIncome))
            }
        })
    }

    /**
     * setups toolbar and show back button for navigation
     */
    private fun setupToolbar() {
        activity.setSupportActionBar(incomeExpenseDetailToolbar)

        activity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    /**
     * setups  animation to respective views
     */
    private fun setupAnimationForViews() {
        ViewCompat.setTransitionName(amountValueTextView, IncomeExpenseDetailActivity.VIEW_NAME_AMOUNT)
        ViewCompat.setTransitionName(categoryTextView, IncomeExpenseDetailActivity.VIEW_NAME_CATEGORY)
        ViewCompat.setTransitionName(memoValueTextView, IncomeExpenseDetailActivity.VIEW_NAME_MEMO)
        ViewCompat.setTransitionName(categoryImageView, IncomeExpenseDetailActivity.VIEW_NAME_IMAGE)
        ViewCompat.setTransitionName(tvTitleCategoryType, IncomeExpenseDetailActivity.VIEW_NAME_TYPE)
        ViewCompat.setTransitionName(dateValueTextView, IncomeExpenseDetailActivity.VIEW_NAME_DATE)
    }

    /**
     *  called from activity so menu option clicks are consumed
     */
    fun onOptionsItemSelected(item: MenuItem?) {
        item?.let {
            if (it.itemId == android.R.id.home) {
                activity.finish()
            }
        }
    }

    fun onResume() {
        updateViewFromDB()
    }

}