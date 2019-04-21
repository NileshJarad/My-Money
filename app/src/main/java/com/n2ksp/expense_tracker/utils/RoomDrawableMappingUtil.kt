package com.n2ksp.expense_tracker.utils

import com.n2ksp.expense_tracker.R

object RoomDrawableMappingUtil {
    private const val OTHER_ROOM_NAME = "other"
    private const val SALARY_ROOM_NAME = "salary"
    private const val FREELANCING_ROOM_NAME = "freelancing"
    private const val LENDING_ROOM_NAME = "lending"
    private const val FOOD_ROOM_NAME = "food"
    private const val BILL_ROOM_NAME = "bill"
    private const val CREDIT_CARD_ROOM_NAME = "credit_card"
    private const val BEAUTIFICATION_ROOM_NAME = "beautification"
    private const val BIKE_ROOM_NAME = "bike"
    private const val TRANSPORT_ROOM_NAME = "transport"
    private const val HOME_ROOM_NAME = "home"
    private const val CAR_ROOM_NAME = "car"
    private const val ENTERTAINMENT_ROOM_NAME = "entertainment"
    private const val SHOPPING_ROOM_NAME = "shopping"
    private const val CLOTHING_ROOM_NAME = "clothing"
    private const val TAX_ROOM_NAME = "tax"
    private const val TELEPHONE_ROOM_NAME = "telephone"
    private const val EDUCATION_ROOM_NAME = "education"
    private const val BOOK_ROOM_NAME = "book"
    private const val FITNESS_ROOM_NAME = "fitness"
    private const val CHILD_ROOM_NAME = "child"
    private const val MEDICAL_ROOM_NAME = "medical"

    fun getDrawableToRoomName(drawable: Int): String {
        return when (drawable) {
            //common categories name
             R.drawable.ic_others -> OTHER_ROOM_NAME
            R.drawable.ic_lending -> LENDING_ROOM_NAME

            // income categories
            R.drawable.ic_salary -> SALARY_ROOM_NAME
            R.drawable.ic_freelancing -> FREELANCING_ROOM_NAME

            // expense categories
             R.drawable.ic_food -> FOOD_ROOM_NAME
             R.drawable.ic_bill -> BILL_ROOM_NAME
             R.drawable.ic_credit_card -> CREDIT_CARD_ROOM_NAME
             R.drawable.ic_spa -> BEAUTIFICATION_ROOM_NAME
             R.drawable.ic_bike -> BIKE_ROOM_NAME
             R.drawable.ic_transport -> TRANSPORT_ROOM_NAME
             R.drawable.ic_home -> HOME_ROOM_NAME
             R.drawable.ic_car -> CAR_ROOM_NAME
             R.drawable.ic_film -> ENTERTAINMENT_ROOM_NAME
             R.drawable.ic_shopping_cart -> SHOPPING_ROOM_NAME
             R.drawable.ic_clothing -> CLOTHING_ROOM_NAME
             R.drawable.ic_tax -> TAX_ROOM_NAME
             R.drawable.ic_phone -> TELEPHONE_ROOM_NAME
             R.drawable.ic_school -> EDUCATION_ROOM_NAME
             R.drawable.ic_books -> BOOK_ROOM_NAME
             R.drawable.ic_fitness -> FITNESS_ROOM_NAME
             R.drawable.ic_child -> CHILD_ROOM_NAME
             R.drawable.ic_medical -> MEDICAL_ROOM_NAME
            else -> {
                val message = "No mapping found for Drawable To Room Name "
                RuntimeException(message)
                return message
            }
        }
    }

    fun getRoomNameToDrawable(name: String): Pair<Int, Int> {
        return when (name) {
            //common categories name
            OTHER_ROOM_NAME -> Pair(R.drawable.ic_others, R.color.colorPrimary)

            // income categories
            SALARY_ROOM_NAME -> Pair(R.drawable.ic_salary, R.color.colorPrimary)
            FREELANCING_ROOM_NAME -> Pair(R.drawable.ic_freelancing, R.color.colorPrimary)

            // expense categories
            FOOD_ROOM_NAME -> Pair(R.drawable.ic_food, R.color.colorPrimary)
            BILL_ROOM_NAME -> Pair(R.drawable.ic_bill, R.color.colorPrimary)
            TRANSPORT_ROOM_NAME -> Pair(R.drawable.ic_transport, R.color.colorPrimary)
            HOME_ROOM_NAME -> Pair(R.drawable.ic_home, R.color.colorPrimary)
            CAR_ROOM_NAME -> Pair(R.drawable.ic_car, R.color.colorPrimary)
            ENTERTAINMENT_ROOM_NAME -> Pair(R.drawable.ic_film, R.color.colorPrimary)
            SHOPPING_ROOM_NAME -> Pair(R.drawable.ic_shopping_cart, R.color.colorPrimary)
            CLOTHING_ROOM_NAME -> Pair(R.drawable.ic_clothing, R.color.colorPrimary)
            TAX_ROOM_NAME -> Pair(R.drawable.ic_tax, R.color.colorPrimary)
            TELEPHONE_ROOM_NAME -> Pair(R.drawable.ic_phone, R.color.colorPrimary)
            EDUCATION_ROOM_NAME -> Pair(R.drawable.ic_school, R.color.colorPrimary)
            BOOK_ROOM_NAME -> Pair(R.drawable.ic_books, R.color.colorPrimary)
            FITNESS_ROOM_NAME -> Pair(R.drawable.ic_fitness, R.color.colorPrimary)
            CHILD_ROOM_NAME -> Pair(R.drawable.ic_child, R.color.colorPrimary)
            MEDICAL_ROOM_NAME -> Pair(R.drawable.ic_medical, R.color.colorPrimary)
            else -> {
                val message = "No mapping found for Room Name To Drawable,Color"
                RuntimeException(message)
                return Pair(-99, -99)
            }
        }
    }

}