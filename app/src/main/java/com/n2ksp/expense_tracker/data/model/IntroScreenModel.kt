package com.n2ksp.expense_tracker.data.model

import android.os.Parcel
import android.os.Parcelable
import com.n2ksp.expense_tracker.R

data class IntroScreenModel(val image: Int, val title: String, val message: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(title)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IntroScreenModel> {
        override fun createFromParcel(parcel: Parcel): IntroScreenModel {
            return IntroScreenModel(parcel)
        }

        override fun newArray(size: Int): Array<IntroScreenModel?> {
            return arrayOfNulls(size)
        }
    }

}


object IntroScreenModelCreator {
    fun getIntroModels(): ArrayList<IntroScreenModel> {
        val data = ArrayList<IntroScreenModel>()

        data.add(
            IntroScreenModel(
                image = R.drawable.ic_intro_one_touch,
                title = "One Click expense management",
                message = "Track you financial activity in just few seconds"
            )
        )


        data.add(
            IntroScreenModel(
                image = R.drawable.ic_intro_chart,
                title = "Instinctive Graph",
                message = "Know where your money goes and from where it comes."
            )
        )

        data.add(
            IntroScreenModel(
                image = R.drawable.ic_intro_cloud_sync,
                title = "Cloud Backup & Export data",
                message = "Sign in to support cloud sync, export and Backup. "
            )
        )

        return data
    }
}


