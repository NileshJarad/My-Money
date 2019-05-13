package com.n2ksp.expense_tracker.data.model

data class AboutUsModel(
    val contributorName: String,
    val contributorDesc: String
)


object AboutUsModelModelCreator {


    fun getContributorList(): ArrayList<AboutUsModel> {
        val data = ArrayList<AboutUsModel>()

        data.add(
            AboutUsModel(
                contributorName = "Nilesh Jarad",
                contributorDesc = "Android & iOS developer"

            )
        )

        data.add(
            AboutUsModel(
                contributorName = "Shrikant",
                contributorDesc = "Android developer"

            )
        )

        data.add(
            AboutUsModel(
                contributorName = "Kanifnath Modak",
                contributorDesc = "Android developer"

            )
        )


        return data
    }
}