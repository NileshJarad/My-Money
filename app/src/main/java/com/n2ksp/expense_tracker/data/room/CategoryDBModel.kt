package com.n2ksp.expense_tracker.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category")
class CategoryDBModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    var categoryId: Int = 0

    @ColumnInfo(name = "category_type")
    var categoryType: String? = null

    @ColumnInfo(name = "category_title")
    var categoryTitle: String? = null

    @ColumnInfo(name = "category_image")
    var categoryImage: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CategoryDBModel) return false

        if (categoryId != other.categoryId) return false

        return true
    }

    override fun hashCode(): Int {
        return categoryId
    }


}