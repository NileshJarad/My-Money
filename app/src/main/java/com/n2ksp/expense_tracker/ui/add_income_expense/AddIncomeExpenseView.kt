package com.n2ksp.expense_tracker.ui.add_income_expense

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.CategoryInfoModel
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.ui.dashboard.IncomeExpensesViewModel
import kotlinx.android.synthetic.main.activity_add_income_expense.view.*
import kotlinx.android.synthetic.main.add_income_expense_pad.view.*
import java.util.*


@SuppressLint("ViewConstructor")
class AddIncomeExpenseView(val activity: AddIncomeExpenseActivity) : LinearLayout(activity), View.OnClickListener {


    private var selectedCategoryModel: CategoryInfoModel? = null
    private lateinit var addIncomeExpense: IncomeExpensesViewModel
    private lateinit var sharedViewModel: SharedIncomeExpenseViewModel

    private var bottomSheetBehaviour: BottomSheetBehavior<ConstraintLayout>? = null

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


        bottomSheetBehaviour = BottomSheetBehavior.from(incomeExpensePad)

        sharedViewModel.categoryModel?.observe(activity, Observer {
            this.selectedCategoryModel = it
            bottomSheetBehaviour?.state = BottomSheetBehavior.STATE_EXPANDED
            selectedCategoryImageView.setImageResource(it.categoryImage)
            selectedCategoryImageView.setColorFilter(ContextCompat.getColor(context, it.categoryColor))
            hideKeyboardFrom(context,this)
        })

        addButtonListener()
    }

    private fun addButtonListener() {
        oneButton.setOnClickListener(this)
        twoButton.setOnClickListener(this)
        threeButton.setOnClickListener(this)
        fourButton.setOnClickListener(this)
        fiveButton.setOnClickListener(this)
        sixButton.setOnClickListener(this)
        sevenButton.setOnClickListener(this)
        eightButton.setOnClickListener(this)
        nineButton.setOnClickListener(this)
        zeroButton.setOnClickListener(this)
        decimalButton.setOnClickListener(this)

        deleteNumberImageView.setOnClickListener {
            val str = amountTextView.text.toString()
            if (str.isNotEmpty()) {
                amountTextView.text = str.substring(0, str.length - 1)
            }
        }

        hideNumberPadImageView.setOnClickListener {
            bottomSheetBehaviour?.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        doneImageButton.setOnClickListener {
            var amount: Float
            amountTextView.text.toString().trim().also {
                amount = it.toFloat()
            }

            val memo = memoEditText.text.toString().trim()

            selectedCategoryModel?.let {
                addIncomeExpense.addExpense(
                    IncomeExpenseModel(
                        categoryInfoModel = it,
                        amount = amount,
                        memo = memo,
                        date = Date().time
                    )
                )

                activity.finish()
            }
        }
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            when (v.id) {
                R.id.oneButton,
                R.id.twoButton,
                R.id.threeButton,
                R.id.fourButton,
                R.id.fiveButton,
                R.id.sixButton,
                R.id.sevenButton,
                R.id.eightButton,
                R.id.nineButton,
                R.id.zeroButton,
                R.id.decimalButton -> {
                    amountTextView.append(v.text.toString())
                    hideKeyboardFrom(context, v)

                }
            }
        }
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}