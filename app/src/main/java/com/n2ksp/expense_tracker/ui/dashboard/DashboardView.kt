package com.n2ksp.expense_tracker.ui.dashboard

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


        dateSelectorWheel.viewTreeObserver.addOnGlobalLayoutListener {
            heightOfDateSelector = dateSelectorWheel.height
        }

        incomeExpenseRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

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
//                        addExpenseOrIncomeFAB.animate().y(-10f)
//                            .setDuration(100).start()
                    }
                }
                super.onScrollStateChanged(recyclerView, newState)
            }

        })


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


}