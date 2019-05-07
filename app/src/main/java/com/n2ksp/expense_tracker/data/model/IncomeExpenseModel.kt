package com.n2ksp.expense_tracker.data.model

import android.os.Parcel
import android.os.Parcelable

data class IncomeExpenseModel(
    val categoryInfoModel: CategoryInfoModel,
    val memo: String,
    val amount: Float,
    val date: Long,
    val id : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(CategoryInfoModel::class.java.classLoader),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readLong(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(categoryInfoModel, flags)
        parcel.writeString(memo)
        parcel.writeFloat(amount)
        parcel.writeLong(date)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IncomeExpenseModel> {
        override fun createFromParcel(parcel: Parcel): IncomeExpenseModel {
            return IncomeExpenseModel(parcel)
        }

        override fun newArray(size: Int): Array<IncomeExpenseModel?> {
            return arrayOfNulls(size)
        }
    }

}