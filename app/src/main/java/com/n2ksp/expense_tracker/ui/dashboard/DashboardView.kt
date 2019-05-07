package com.n2ksp.expense_tracker.ui.dashboard

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.di.component.DaggerDashboardViewComponent
import com.n2ksp.expense_tracker.di.module.ContextModule
import com.n2ksp.expense_tracker.ui.custom.DateSelectorWheel
import com.n2ksp.expense_tracker.ui.income_expense.detail.IncomeExpenseDetailActivity
import com.n2ksp.expense_tracker.ui.income_expense.list.DashboardIncomeExpenseAdapter
import com.n2ksp.expense_tracker.ui.main.MainActivity
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import javax.inject.Inject


@SuppressLint("ViewConstructor")
class DashboardView(val activity: MainActivity) : LinearLayout(activity) {

    private var currentSelectedDay: Int = 0
    private var currentSelectedMonth: Int = DateUtils.getCurrentMonthInt()
    private lateinit var viewModel: IncomeExpensesViewModel

    @Inject
    lateinit var adapter: DashboardIncomeExpenseAdapter

    @Inject
    lateinit var dividerItemDecoration: DividerItemDecoration

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager


    private var heightOfDateSelector = 0
    var currentTopMargin = 0
    private val threshold = 2
    private var SCROLL_SPEED_DOWNSCALE = 4

    init {

        DaggerDashboardViewComponent.builder()
            .contextModule(ContextModule(activity))
            .build()
            .inject(this)

        initView()
    }

    private fun initView() {

        View.inflate(activity, R.layout.fragment_dashboard, this)

        viewModel = ViewModelProviders.of(activity).get(IncomeExpensesViewModel::class.java)

        setupRecyclerView()

        setIncomeExpenseAmounts()

        dateSelectorWheel.setListener(object : DateSelectorWheel.DateSelectedListener {
            override fun onDateSelected(dayOfMonth: Int, month: String) {
                currentSelectedDay = dayOfMonth
                currentDayTextView.text = "$dayOfMonth"
                currentMonthTextView.text = month
                getDataForExpenseIncome(dayOfMonth)
            }
        })


        dateSelectorWheel.viewTreeObserver.addOnGlobalLayoutListener {
            heightOfDateSelector = dateSelectorWheel.height
        }


        addExpenseOrIncomeFAB.setOnClickListener {
            Navigation.findNavController(activity, R.id.navHostFragment).navigate(R.id.addIncomeExpenseActivity)
        }

        setMonthDataExpenseIncome()
        setDates()
    }

    private fun setMonthDataExpenseIncome() {
        viewModel.getIncomeAndExpenseTotalForMonth(currentSelectedMonth).observe(activity, Observer {
            dateSelectorWheel.setIncomeExpenseForMonth(it)
        })
    }

    private fun getDataForExpenseIncome(dayOfMonth: Int) {
        viewModel.getListForDay(dayOfMonth, currentSelectedMonth).observe(activity, Observer {
            if (it.size == 0) {
                showEmptyScreen()
            } else {
                adapter.addAllData(it)
                showData()
                incomeExpenseRecyclerView.removeOnScrollListener(scrollListener)
                incomeExpenseRecyclerView.addOnScrollListener(scrollListener)
            }
        })
    }

    private fun setupRecyclerView() {
        incomeExpenseRecyclerView.layoutManager = linearLayoutManager
        incomeExpenseRecyclerView.adapter = adapter
        incomeExpenseRecyclerView.addItemDecoration(dividerItemDecoration)

        adapter.setItemCallBack { data, view ->
            var options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                Pair(
                    view.findViewById(R.id.incomeOrExpenseAmount) as View,
                    IncomeExpenseDetailActivity.VIEW_NAME_AMOUNT
                ),
                Pair(
                    view.findViewById(R.id.memoTextView) as View,
                    IncomeExpenseDetailActivity.VIEW_NAME_MEMO
                ),
                Pair(
                    view.findViewById(R.id.incomeOrExpenseImageView) as View,
                    IncomeExpenseDetailActivity.VIEW_NAME_IMAGE
                )
                , Pair(
                    view.findViewById(R.id.incomeOrExpenseCategoryTextView) as View,
                    IncomeExpenseDetailActivity.VIEW_NAME_CATEGORY
                ), Pair(
                    view.findViewById(R.id.incomeOrExpenseIndicatorView) as View,
                    IncomeExpenseDetailActivity.VIEW_NAME_TYPE
                ), Pair(
                    currentDayTextView,
                    IncomeExpenseDetailActivity.VIEW_NAME_DATE
                )
            )

            IncomeExpenseDetailActivity.start(activity, options, data)

        }
    }

    private val scrollListener =
        object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (threshold < Math.abs(dy)) {
                    if (dy > 0) {
                        currentTopMargin += dy / SCROLL_SPEED_DOWNSCALE
                        if (heightOfDateSelector < currentTopMargin) {
                            currentTopMargin = heightOfDateSelector
                        }
                    } else {
                        currentTopMargin += dy / SCROLL_SPEED_DOWNSCALE
                        if (0 > currentTopMargin) {
                            currentTopMargin = 0
                        }
                    }

                    val params = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(
                        0,
                        -currentTopMargin,
                        0,
                        0
                    )
                    dateSelectorWheel.layoutParams = params
                    addExpenseOrIncomeFAB.animate().scaleX(0.0f)
                        .scaleY(0.0f)
                        .setDuration(10).start()


                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {


                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        addExpenseOrIncomeFAB.animate().scaleX(1.0f)
                            .scaleY(1.0f)
                            .setDuration(100).start()
                        checkIfAtLeastHalfViewIsScrolled()

                    }

                    RecyclerView.SCROLL_STATE_DRAGGING -> {
                    }
                }
                super.onScrollStateChanged(recyclerView, newState)
            }

        }

    private fun showEmptyScreen() {
        incomeExpenseRecyclerView.visibility = View.GONE
        emptyViewLinearLayout.visibility = View.VISIBLE
        setIncomeExpenseAmounts(0f, 0f)
    }

    private fun showData() {
        val income = adapter.getIncomeExpenseAmounts()
        setIncomeExpenseAmounts(income = income.first, expense = income.second)
        incomeExpenseRecyclerView.visibility = View.VISIBLE
        emptyViewLinearLayout.visibility = View.GONE
    }

    private fun setDates() {
        currentSelectedDay = DateUtils.getCurrentDayOfMonth()
        currentDayTextView.text = "$currentSelectedDay"
        currentMonthTextView.text = DateUtils.getCurrentMonth()
    }

    private fun setIncomeExpenseAmounts(income: Float = 0.0f, expense: Float = 0.0f) {
        dateIncomeTextView.text = AmountUtils.getAmountFormatted(income)
        dateExpenseTextView.text = AmountUtils.getAmountFormatted(expense)
    }

    private fun checkIfAtLeastHalfViewIsScrolled() {
        val findFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
        currentTopMargin =
            if (heightOfDateSelector / 2 > currentTopMargin || findFirstCompletelyVisibleItemPosition == 1) {
                0
            } else {
                heightOfDateSelector
            }

        val params = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            0,
            -currentTopMargin,
            0,
            0
        )
        dateSelectorWheel.layoutParams = params
    }

    fun onResume() {
        getDataForExpenseIncome(currentSelectedDay)
        setMonthDataExpenseIncome()
    }


}