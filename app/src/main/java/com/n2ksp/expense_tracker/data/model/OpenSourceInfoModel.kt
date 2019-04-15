package com.n2ksp.expense_tracker.data.model

data class OpenSourceInfoModel(val libName: String, val copyright: String, val license: String, val website: String)


object OpenSourceInfoModelCreator {


    fun getOpenSourceInfoList(): ArrayList<OpenSourceInfoModel> {
        val data = ArrayList<OpenSourceInfoModel>()

        data.add(
            OpenSourceInfoModel(
                libName = "Dagger",
                copyright = "Copyright 2012 The Dagger Authors",
                license = "Apache License, Version 2.0",
                website = "https://github.com/google/dagger"
            )
        )

        data.add(
            OpenSourceInfoModel(
                libName = "RxAndroid",
                copyright = "Copyright 2015 The RxAndroid authors",
                license = "Apache License, Version 2.0",
                website = "https://github.com/ReactiveX/RxAndroid"
            )
        )

        data.add(
            OpenSourceInfoModel(
                libName = "Timber",
                copyright = "Copyright 2013 Jake Wharton",
                license = "Apache License, Version 2.0",
                website = "https://github.com/JakeWharton/timber"
            )
        )

        return data
    }
}