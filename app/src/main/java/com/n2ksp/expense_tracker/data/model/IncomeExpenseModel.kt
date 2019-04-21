package com.n2ksp.expense_tracker.data.model

data class IncomeExpenseModel(
    val categoryInfoModel: CategoryInfoModel,
    val memo: String,
    val amount: Float,
    val date: Long
)