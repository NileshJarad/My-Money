package com.n2ksp.expense_tracker.utils

object SqlLiteUtil {
    const val createExpenseTable =
        "CREATE TABLE IF NOT EXISTS `income_expense` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `category_id` INTEGER NOT NULL, `memo` TEXT, `amount` INTEGER NOT NULL, `date` INTEGER NOT NULL)"
}