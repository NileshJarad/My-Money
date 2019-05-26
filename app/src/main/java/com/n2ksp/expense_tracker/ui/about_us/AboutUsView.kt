package com.n2ksp.expense_tracker.ui.about_us

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.AboutUsModelModelCreator
import com.n2ksp.expense_tracker.di.component.DaggerAboutUsAdapterComponent
import com.n2ksp.expense_tracker.di.module.ContextModule
import kotlinx.android.synthetic.main.activity_about_us.view.*
import javax.inject.Inject

@SuppressLint("ViewConstructor")
class AboutUsView(private val activity: AboutUsActivity) : LinearLayout(activity) {


    @Inject
    lateinit var aboutUsAdapter: AboutUsAdapter

    @Inject
    lateinit var dividerItemDecoration: DividerItemDecoration

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    init {
        initView(activity)
    }

    private fun initView(activity: AboutUsActivity) {
        DaggerAboutUsAdapterComponent.builder()
            .contextModule(ContextModule(context))
            .build()
            .inject(this)

        View.inflate(activity, R.layout.activity_about_us, this)

        activity.setSupportActionBar(aboutUsToolbar)

        activity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        rvAboutUs.layoutManager = linearLayoutManager

        aboutUsAdapter.addAllData(AboutUsModelModelCreator.getContributorList())
        rvAboutUs.adapter = aboutUsAdapter

        rvAboutUs.addItemDecoration(dividerItemDecoration)
    }

    fun onOptionsItemSelected(item: MenuItem?) {
        item?.let {
            if (it.itemId == android.R.id.home) {
                NavUtils.navigateUpFromSameTask(activity)
            }
        }
    }
}
