package com.n2ksp.expense_tracker.utils

import androidx.recyclerview.widget.DiffUtil
import com.n2ksp.expense_tracker.data.model.CategoryInfoModel

/**
 * Created by SHRIKANT EKADE on 2019-04-21.
 */

class CategoryDiffUtilCallback(private val oldList: List<CategoryInfoModel>, private val newList: List<CategoryInfoModel>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].categoryTitle == newList[newItemPosition].categoryTitle

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}