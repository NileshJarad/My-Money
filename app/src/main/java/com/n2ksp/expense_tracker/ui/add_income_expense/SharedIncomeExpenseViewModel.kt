package com.n2ksp.expense_tracker.ui.add_income_expense

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n2ksp.expense_tracker.data.model.CategoryInfoModel


class SharedIncomeExpenseViewModel : ViewModel() {
    var categoryModel: MutableLiveData<CategoryInfoModel>? = null

    init {
        categoryModel = MutableLiveData()
    }
}