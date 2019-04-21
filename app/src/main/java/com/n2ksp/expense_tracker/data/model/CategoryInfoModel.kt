package com.n2ksp.expense_tracker.data.model

import android.graphics.Color
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.Constants

/**
 * Created by SHRIKANT EKADE on 20/04/19.
 */

data class CategoryInfoModel(
    val categoryType: String,
    val categoryId: Int,
    val categoryTitle: String,
    val categoryImage: Int,
    val categoryColor: Int = Color.CYAN
)

object CategoryInfoModelCreator {

    fun getCategoryInfoModel(type: String): ArrayList<CategoryInfoModel> {
        val categories = ArrayList<CategoryInfoModel>()

        if (type.equals(Constants.INCOME, ignoreCase = true)) {


            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Salary",
                    R.drawable.ic_salary
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Freelancing",
                    R.drawable.ic_freelancing
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Home"
                    ,
                    R.drawable.ic_home
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Lending"
                    ,
                    R.drawable.ic_lending
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Others"
                    ,
                    R.drawable.ic_others
                )
            )


        } else {

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Food",
                    R.drawable.ic_food
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Bill",
                    R.drawable.ic_bill
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Lending"
                    ,
                    R.drawable.ic_lending
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Credit card"
                    ,
                    R.drawable.ic_credit_card
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Beautification"
                    ,
                    R.drawable.ic_spa
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Bike"
                    ,
                    R.drawable.ic_bike
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Transportation",
                    R.drawable.ic_transport
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Home",
                    R.drawable.ic_home
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Car",
                    R.drawable.ic_car
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Entertainment",
                    R.drawable.ic_film
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Shopping",
                    R.drawable.ic_shopping_cart
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Clothing",
                    R.drawable.ic_clothing
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Tax",
                    R.drawable.ic_tax
                )
            )


            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Telephone",
                    R.drawable.ic_phone
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Education",
                    R.drawable.ic_school
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Book",
                    R.drawable.ic_books
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Fitness",
                    R.drawable.ic_fitness
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Child",
                    R.drawable.ic_child
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Medical",
                    R.drawable.ic_medical
                )
            )

            categories.add(
                CategoryInfoModel(
                    type,
                     1,
                    "Others"
                    ,
                    R.drawable.ic_others
                )
            )
        }

        return categories
    }
}