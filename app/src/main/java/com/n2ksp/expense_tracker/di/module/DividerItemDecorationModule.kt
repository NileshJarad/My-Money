package com.n2ksp.expense_tracker.di.module

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.n2ksp.expense_tracker.R
import dagger.Module
import dagger.Provides


@Module
class DividerItemDecorationModule {

    @Provides
    fun provideDividerItemDecoration(context: Context): DividerItemDecoration {
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(context, R.drawable.divider_open_source)
        drawable?.let {
            dividerItemDecoration.setDrawable(it)
        }

        return dividerItemDecoration
    }

}