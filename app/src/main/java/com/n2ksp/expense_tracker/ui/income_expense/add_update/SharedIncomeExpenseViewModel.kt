package com.n2ksp.expense_tracker.ui.income_expense.add_update

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n2ksp.expense_tracker.data.model.CategoryInfoModel


class SharedIncomeExpenseViewModel : ViewModel() {
    var categoryModel: MutableLiveData<CategoryInfoModel>? = null

    init {
        categoryModel = MutableLiveData()
    }
}