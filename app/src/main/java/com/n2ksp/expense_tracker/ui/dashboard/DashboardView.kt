package com.n2ksp.expense_tracker.ui.dashboard

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.AmountUtils
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardView(val activity: Context) : LinearLayout(activity) {

    init {
        initView()
    }

    private fun initView() {

        View.inflate(activity, R.layout.fragment_dashboard, this)

        incomeExpenseRecyclerView.layoutManager = LinearLayoutManager(context)

        val dashboardIncomeExpenseAdapter = DashboardIncomeExpenseAdapter()
        incomeExpenseRecyclerView.adapter = dashboardIncomeExpenseAdapter

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        val drawable = ContextCompat.getDrawable(context!!, R.drawable.divider_open_source)
        drawable?.let {
            dividerItemDecoration.setDrawable(it)
        }
        incomeExpenseRecyclerView.addItemDecoration(dividerItemDecoration)


        dateIncomeTextView.text = AmountUtils.getAmountFormatted(210f)
        dateExpenseTextView.text = AmountUtils.getAmountFormatted(160f)

    }

}