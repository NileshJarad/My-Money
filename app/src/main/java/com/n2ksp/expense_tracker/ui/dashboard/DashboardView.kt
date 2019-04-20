package com.n2ksp.expense_tracker.ui.dashboard

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.di.component.DaggerDashboardViewComponent
import com.n2ksp.expense_tracker.di.module.ContextModule
import com.n2ksp.expense_tracker.ui.custom.DateSelectorWheel
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import javax.inject.Inject

@SuppressLint("ViewConstructor")
class DashboardView(val activity: AppCompatActivity) : LinearLayout(activity) {

    @Inject
    lateinit var adapter: DashboardIncomeExpenseAdapter

    @Inject
    lateinit var dividerItemDecoration: DividerItemDecoration

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    init {

        DaggerDashboardViewComponent.builder()
            .contextModule(ContextModule(activity))
            .build()
            .inject(this)

        initView()
    }

    private fun initView() {

        View.inflate(activity, R.layout.fragment_dashboard, this)

        incomeExpenseRecyclerView.layoutManager = linearLayoutManager

        incomeExpenseRecyclerView.adapter = adapter

        incomeExpenseRecyclerView.addItemDecoration(dividerItemDecoration)
        dateIncomeTextView.text = AmountUtils.getAmountFormatted(210f)
        dateExpenseTextView.text = AmountUtils.getAmountFormatted(160f)

        currentDayTextView.text = "${DateUtils.getCurrentDayOfMonth()}"
        currentMonthTextView.text = DateUtils.getCurrentMonth()

        dateSelectorWheel.setListener(object : DateSelectorWheel.DateSelectedListener {
            override fun onDateSelected(dayOfMonth: Int, month: String) {
                currentDayTextView.text = "$dayOfMonth"
                currentMonthTextView.text = month
            }
        })


    }


}