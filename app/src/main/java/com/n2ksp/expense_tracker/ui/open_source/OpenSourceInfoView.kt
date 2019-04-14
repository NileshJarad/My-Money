package com.n2ksp.expense_tracker.ui.open_source

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.OpenSourceInfoModelCreator
import com.n2ksp.expense_tracker.di.component.DaggerOpenSourceAdapterComponent
import com.n2ksp.expense_tracker.di.module.ContextModule
import kotlinx.android.synthetic.main.activity_open_source_info.view.*
import javax.inject.Inject

@SuppressLint("ViewConstructor")
class OpenSourceInfoView(private val activity: OpenSourceInfoActivity) : LinearLayout(activity) {


    @Inject
    lateinit var openSourceInfoAdapter: OpenSourceInfoAdapter

    @Inject
    lateinit var dividerItemDecoration: DividerItemDecoration

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    init {
        initView(activity)
    }

    private fun initView(activity: OpenSourceInfoActivity) {
        DaggerOpenSourceAdapterComponent.builder()
            .contextModule(ContextModule(context))
            .build()
            .inject(this)

        View.inflate(activity, R.layout.activity_open_source_info, this)

        activity.setSupportActionBar(toolbarOpenSource)

        activity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        rvOpenSource.layoutManager = linearLayoutManager

        openSourceInfoAdapter.addAllData(OpenSourceInfoModelCreator.getOpenSourceInfoList())
        rvOpenSource.adapter = openSourceInfoAdapter

        rvOpenSource.addItemDecoration(dividerItemDecoration)
    }

    fun onOptionsItemSelected(item: MenuItem?) {
        item?.let {
            if (it.itemId == android.R.id.home) {
                NavUtils.navigateUpFromSameTask(activity)
            }
        }
    }
}
