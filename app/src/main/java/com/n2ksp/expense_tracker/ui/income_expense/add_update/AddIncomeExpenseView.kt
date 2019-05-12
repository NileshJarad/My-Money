package com.n2ksp.expense_tracker.ui.income_expense.add_update

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
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
import com.n2ksp.expense_tracker.ui.income_expense.IncomeExpensesViewModel
import com.n2ksp.expense_tracker.ui.income_expense.add_update.AddIncomeExpenseActivity.Companion.EXTRA_EDIT_INCOME_EXPENSE_ENTRY
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.AppWalkThroughUtils
import com.n2ksp.expense_tracker.utils.DateUtils
import kotlinx.android.synthetic.main.activity_add_income_expense.view.*
import kotlinx.android.synthetic.main.add_income_expense_pad.view.*
import java.util.*


@SuppressLint("ViewConstructor")
class AddIncomeExpenseView(val activity: AddIncomeExpenseActivity) : LinearLayout(activity), View.OnClickListener {


    private var selectedCategoryModel: CategoryInfoModel? = null
    private lateinit var addIncomeExpense: IncomeExpensesViewModel
    private lateinit var sharedViewModel: SharedIncomeExpenseViewModel
    private val oneCR = 1000 * 100 * 100
    private var selectedDate = Date()
    private val myCalendar = GregorianCalendar()

    private var isAddEntryScreen = true

    private var bottomSheetBehaviour: BottomSheetBehavior<ConstraintLayout>? = null

    lateinit var data: IncomeExpenseModel

    init {
        initView(activity)
    }

    private fun initView(activity: AddIncomeExpenseActivity) {
        View.inflate(activity, R.layout.activity_add_income_expense, this)


        bottomSheetBehaviour = BottomSheetBehavior.from(incomeExpensePad)

        setupToolbar()

        createViewModels()

        addObserverForCategorySelection()

        addButtonListener()

        retrieveData()
    }

    private fun addObserverForCategorySelection() {
        sharedViewModel.categoryModel?.observe(activity, Observer {
            this.selectedCategoryModel = it
            bottomSheetBehaviour?.state = BottomSheetBehavior.STATE_EXPANDED
            selectedCategoryImageView.setImageResource(it.categoryImage)
            selectedCategoryImageView.setColorFilter(ContextCompat.getColor(context, it.categoryColor))
            hideKeyboardFrom(context, this)

            dateButton.requestFocus()
            AppWalkThroughUtils.showEnterMemo(activity as Activity, memoEditText) {
                // enter memo
                memoEditText.isFocusableInTouchMode = true
                AppWalkThroughUtils.showSelectDate(activity as Activity, dateButton) {
                }

            }

        })
    }

    private fun createViewModels() {
        sharedViewModel = ViewModelProviders.of(activity).get(SharedIncomeExpenseViewModel::class.java)
        addIncomeExpense = ViewModelProviders.of(activity).get(IncomeExpensesViewModel::class.java)
    }

    private fun retrieveData() {
        if (activity.intent.hasExtra(EXTRA_EDIT_INCOME_EXPENSE_ENTRY)) {

            activity.supportActionBar?.title = "Edit Entry"

            isAddEntryScreen = false

            data = activity.intent.getParcelableExtra(EXTRA_EDIT_INCOME_EXPENSE_ENTRY)
            bottomSheetBehaviour?.state = BottomSheetBehavior.STATE_EXPANDED

            amountTextView.text = "${data.amount}"
            memoEditText.setText(data.memo)

            selectedCategoryImageView.setColorFilter(
                ContextCompat.getColor(
                    context,
                    data.categoryInfoModel.categoryColor
                )
            )
            selectedCategoryImageView.setImageResource(data.categoryInfoModel.categoryImage)

            dateButton.text = DateUtils.getDayAndMonth(data.date)

            selectedDate = Date(data.date)

            selectedCategoryModel = data.categoryInfoModel
        }
    }

    private fun setupToolbar() {
        activity.setSupportActionBar(addIncomeExpenseToolbar)
        activity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    private fun addButtonListener() {
        numberPadListeners()

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
            val amountStr = amountTextView.text.toString().trim()
            if (amountStr.isNotEmpty()) {
                var amount: Float
                amountStr.also {
                    amount = it.toFloat()
                }

                val memo = memoEditText.text.toString().trim()

                selectedCategoryModel?.let {

                    if (isAddEntryScreen) {
                        addIncomeExpense.addEntry(
                            IncomeExpenseModel(
                                categoryInfoModel = it,
                                amount = amount,
                                memo = memo,
                                date = selectedDate.time,
                                id = -99
                            )
                        )
                    } else {
                        addIncomeExpense.updateEntry(
                            IncomeExpenseModel(
                                categoryInfoModel = it,
                                amount = amount,
                                memo = memo,
                                date = selectedDate.time,
                                id = data.id
                            )
                        )
                    }

                    activity.finish()
                }
            } else {
                activity.finish()
            }

        }

        dateButton.setOnClickListener {
            DatePickerDialog(
                context, dateListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun numberPadListeners() {
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
    }

    @SuppressLint("SetTextI18n")
    var dateListener: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            selectedDate = myCalendar.time

            dateButton.text = "$dayOfMonth \n${DateUtils.getMonthName(monthOfYear)}"

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
                    setAmountInPadView(v)
                }
            }
        }
    }

    private fun setAmountInPadView(v: Button) {
        val textAmount = StringBuilder(amountTextView.text.toString().trim())
        if (v.id == R.id.decimalButton && textAmount.contains(".", ignoreCase = true)) {
            // do not add second .
            return
        } else {
            textAmount.append(v.text.toString())
        }

        val amount = AmountUtils.getOnlyFloatValue(textAmount.toString())
        if (amount < oneCR) {
            amountTextView.text = textAmount
        }
        hideKeyboardFrom(context, v)
    }


    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}