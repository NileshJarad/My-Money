package com.n2ksp.expense_tracker.data.model

import android.graphics.Color
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.room.CategoryDBModel
import com.n2ksp.expense_tracker.utils.Constants
import com.n2ksp.expense_tracker.utils.RoomDrawableMappingUtil
import timber.log.Timber

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

    fun convertToCategoryInfoModel(categories:List<CategoryDBModel>) : ArrayList<CategoryInfoModel> {
        val categoriesModel = ArrayList<CategoryInfoModel>()
        categories.forEach {
            Timber.e("convertToCategoryInfoModel : ${it.categoryId} ${it.categoryTitle} ${it.categoryImage}")
            Timber.e("convertToCategoryInfoModel : ${RoomDrawableMappingUtil.getRoomNameToDrawable(it.categoryImage!!)} ${RoomDrawableMappingUtil.getRoomNameToDrawable(it.categoryImage!!).first}")
            categoriesModel.add(
                CategoryInfoModel(
                    it.categoryType!!,
                    it.categoryId,
                    it.categoryTitle!!,
                    RoomDrawableMappingUtil.getRoomNameToDrawable(it.categoryImage!!).first
                )
            )
        }

        return categoriesModel
    }

    fun getCategoryInfoModel(type: String): ArrayList<CategoryInfoModel> {
        val categories = ArrayList<CategoryInfoModel>()

//        if (type.equals(Constants.INCOME, ignoreCase = true)) {
//
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Salary",
//                    R.drawable.ic_salary
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Freelancing",
//                    R.drawable.ic_freelancing
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Home"
//                    ,
//                    R.drawable.ic_home
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Lending"
//                    ,
//                    R.drawable.ic_lending
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Others"
//                    ,
//                    R.drawable.ic_others
//                )
//            )
//
//
//        }
//        else {
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Food",
//                    R.drawable.ic_food
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Bill",
//                    R.drawable.ic_bill
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Lending"
//                    ,
//                    R.drawable.ic_lending
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Credit card"
//                    ,
//                    R.drawable.ic_credit_card
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Beautification"
//                    ,
//                    R.drawable.ic_spa
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Bike"
//                    ,
//                    R.drawable.ic_bike
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Transportation",
//                    R.drawable.ic_transport
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Home",
//                    R.drawable.ic_home
//                )
//            )
//
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Car",
//                    R.drawable.ic_car
//                )
//            )
//
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Entertainment",
//                    R.drawable.ic_film
//                )
//            )
//
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Shopping",
//                    R.drawable.ic_shopping_cart
//                )
//            )
//
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Clothing",
//                    R.drawable.ic_clothing
//                )
//            )
//
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Tax",
//                    R.drawable.ic_tax
//                )
//            )
//
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Telephone",
//                    R.drawable.ic_phone
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Education",
//                    R.drawable.ic_school
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Book",
//                    R.drawable.ic_books
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Fitness",
//                    R.drawable.ic_fitness
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Child",
//                    R.drawable.ic_child
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Medical",
//                    R.drawable.ic_medical
//                )
//            )
//
//            categories.add(
//                CategoryInfoModel(
//                    type,
//                     1,
//                    "Others"
//                    ,
//                    R.drawable.ic_others
//                )
//            )
//        }

        return categories
    }

    fun getCategoriesToAddInDatabase(): ArrayList<CategoryDBModel> {
        val categories = ArrayList<CategoryDBModel>()

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Salary"
                categoryType = Constants.INCOME
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_salary)
            }
        )


        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Freelancing"
                categoryType = Constants.INCOME
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_freelancing)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Home"
                categoryType = Constants.INCOME
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_home)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Lending"
                categoryType = Constants.INCOME
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_lending)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Others"
                categoryType = Constants.INCOME
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_others)
            }
        )

        // Expense category below

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Food"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_food)
            }
        )

        categories.add(

            CategoryDBModel().apply {
                categoryTitle = "Bill"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_bill)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Lending"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_lending)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Credit card"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_credit_card)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Beautification"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_spa)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Bike"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_bike)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Transportation"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_transport)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Home"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_home)
            }
        )


        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Car"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_car)
            }
        )


        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Entertainment"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_film)
            }
        )


        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Shopping"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_shopping_cart)
            }
        )


        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Clothing"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_clothing)
            }
        )


        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Tax"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_tax)
            }
        )


        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Telephone"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_phone)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Education"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_school)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Book"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_books)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Fitness"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_fitness)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Child"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_child)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Medical"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_medical)
            }
        )

        categories.add(
            CategoryDBModel().apply {
                categoryTitle = "Others"
                categoryType = Constants.EXPENSE
                categoryImage = RoomDrawableMappingUtil.getDrawableToRoomName(R.drawable.ic_others)
            }
        )

        return categories
    }
}