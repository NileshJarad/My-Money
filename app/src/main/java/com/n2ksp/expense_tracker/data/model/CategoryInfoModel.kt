package com.n2ksp.expense_tracker.data.model

import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.room.CategoryDBModel
import com.n2ksp.expense_tracker.utils.Constants
import com.n2ksp.expense_tracker.utils.RoomDrawableMappingUtil

/**
 * Created by SHRIKANT EKADE on 20/04/19.
 */

data class CategoryInfoModel(
    val categoryType: String,
    val categoryId: Int,
    val categoryTitle: String,
    val categoryImage: Int,
    val categoryColor: Int,
    var selected: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CategoryInfoModel) return false

        if (categoryId != other.categoryId) return false

        return true
    }

    override fun hashCode(): Int {
        return categoryId
    }
}

object CategoryInfoModelCreator {

    fun convertToCategoryInfoModel(categories: List<CategoryDBModel>): ArrayList<CategoryInfoModel> {
        val categoriesModel = ArrayList<CategoryInfoModel>()
        categories.forEach {
            categoriesModel.add(mapToCategoryInfoModel(it))
        }

        return categoriesModel
    }

    fun mapToCategoryInfoModel(it: CategoryDBModel): CategoryInfoModel {
        val roomNameToDrawable = RoomDrawableMappingUtil.getRoomNameToDrawable(it.categoryImage ?: "")
        return CategoryInfoModel(
            it.categoryType ?: "",
            it.categoryId,
            it.categoryTitle ?: "",
            roomNameToDrawable.first,
            roomNameToDrawable.second
        )

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