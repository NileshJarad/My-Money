package com.n2ksp.expense_tracker.ui.open_source

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.OpenSourceInfoModelCreator
import kotlinx.android.synthetic.main.activity_open_source_info.view.*

@SuppressLint("ViewConstructor")
class OpenSourceInfoView(private val activity: OpenSourceInfoActivity) : LinearLayout(activity) {

    init {
        initView(activity)
    }

    private fun initView(activity: OpenSourceInfoActivity) {
        View.inflate(activity, R.layout.activity_open_source_info, this)


        activity.setSupportActionBar(toolbarOpenSource)

        activity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        rvOpenSource.layoutManager = LinearLayoutManager(activity)
        rvOpenSource.adapter = OpenSourceInfoAdapter(OpenSourceInfoModelCreator.getOpenSourceInfoList())

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rvOpenSource.addItemDecoration(dividerItemDecoration)
        val drawable = ContextCompat.getDrawable(context, R.drawable.divider_open_source)
        drawable?.let {
            dividerItemDecoration.setDrawable(it)
        }
    }

    fun onOptionsItemSelected(item: MenuItem?) {
        item?.let {
            if (it.itemId == android.R.id.home) {
                NavUtils.navigateUpFromSameTask(activity)
            }
        }
    }
}
