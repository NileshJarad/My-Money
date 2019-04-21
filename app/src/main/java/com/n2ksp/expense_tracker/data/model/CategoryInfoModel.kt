package com.n2ksp.expense_tracker.data.model

import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.Constants

/**
 * Created by SHRIKANT EKADE on 20/04/19.
 */

data class CategoryInfoModel(
    val categoryType: String,
    val categoryId: String,
    val categoryTitle: String,
    val categoryImage: Int
)

object CategoryInfoModelCreator {
    
    fun getCategoryInfoModel(type: String): ArrayList<CategoryInfoModel> {
        val categories = ArrayList<CategoryInfoModel>()

        if (type.equals(Constants.INCOME,ignoreCase = true)) {

            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Freelancing",
                    R.drawable.ic_money_24dp
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Salary",
                    R.drawable.ic_wallet_24dp
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Others"
                    ,
                    R.drawable.ic_others_24dp
                )
            )

        } else {

            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Food",
                    R.drawable.ic_food
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Bill",
                    R.drawable.ic_others_24dp
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Transportation",
                    R.drawable.ic_transport_24dp
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Home",
                    R.drawable.ic_home_24dp
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Car",
                    R.drawable.ic_car_24dp
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Entertainment",
                    R.drawable.ic_film
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Shopping",
                    R.drawable.ic_shopping_cart_24dp
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Clothing",
                    R.drawable.ic_hanger
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Tax",
                    R.drawable.ic_tax
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                    "1",
                    "Telephone",
                    R.drawable.ic_phone_24dp
                )
            )
        }

        return categories
    }
}