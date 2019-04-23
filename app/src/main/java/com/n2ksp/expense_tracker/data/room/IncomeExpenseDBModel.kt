package com.n2ksp.expense_tracker.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.n2ksp.expense_tracker.utils.RoomDateConverter
import java.util.*


@Entity(tableName = "income_expense")
@TypeConverters(RoomDateConverter::class)
class IncomeExpenseDBModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "category_id")
    var categoryId: Int = 0

    @ColumnInfo(name = "memo")
    var memo: String? = ""

    @ColumnInfo(name = "amount")
    var amount: Float = 0.0f

    @ColumnInfo(name = "date")
    var date = Date()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IncomeExpenseDBModel) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


}