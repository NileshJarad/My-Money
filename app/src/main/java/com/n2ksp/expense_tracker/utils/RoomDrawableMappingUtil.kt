package com.n2ksp.expense_tracker.utils

import com.n2ksp.expense_tracker.R

object RoomDrawableMappingUtil {
    private const val OTHER_ROOM_NAME = "other"
    private const val SALARY_ROOM_NAME = "salary"
    private const val INVESTMENT = "investment"
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
            R.drawable.ic_others -> OTHER_ROOM_NAME //1
            R.drawable.ic_lending -> LENDING_ROOM_NAME //2
            R.drawable.ic_investment -> INVESTMENT //3

            // income categories
            R.drawable.ic_salary -> SALARY_ROOM_NAME //1
            R.drawable.ic_freelancing -> FREELANCING_ROOM_NAME//2

            // expense categories
            R.drawable.ic_food -> FOOD_ROOM_NAME //1
            R.drawable.ic_bill -> BILL_ROOM_NAME //2
            R.drawable.ic_credit_card -> CREDIT_CARD_ROOM_NAME //3
            R.drawable.ic_spa -> BEAUTIFICATION_ROOM_NAME //4
            R.drawable.ic_bike -> BIKE_ROOM_NAME //5
            R.drawable.ic_transport -> TRANSPORT_ROOM_NAME //6
            R.drawable.ic_home -> HOME_ROOM_NAME //7
            R.drawable.ic_car -> CAR_ROOM_NAME //8
            R.drawable.ic_film -> ENTERTAINMENT_ROOM_NAME //9
            R.drawable.ic_shopping_cart -> SHOPPING_ROOM_NAME //10
            R.drawable.ic_clothing -> CLOTHING_ROOM_NAME //11
            R.drawable.ic_tax -> TAX_ROOM_NAME //12
            R.drawable.ic_phone -> TELEPHONE_ROOM_NAME //13
            R.drawable.ic_school -> EDUCATION_ROOM_NAME //14
            R.drawable.ic_books -> BOOK_ROOM_NAME //15
            R.drawable.ic_fitness -> FITNESS_ROOM_NAME //16
            R.drawable.ic_child -> CHILD_ROOM_NAME //17
            R.drawable.ic_medical -> MEDICAL_ROOM_NAME //18
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
            OTHER_ROOM_NAME -> Pair(R.drawable.ic_others, R.color.orange_a400) //1
            LENDING_ROOM_NAME -> Pair(R.drawable.ic_lending, R.color.purple_a400) //2
            INVESTMENT -> Pair(R.drawable.ic_investment, R.color.pink_a200) //2


            // income categories
            SALARY_ROOM_NAME -> Pair(R.drawable.ic_salary, R.color.teal_800) //1
            FREELANCING_ROOM_NAME -> Pair(R.drawable.ic_freelancing, R.color.cyan_800) //2

            // expense categories
            FOOD_ROOM_NAME -> Pair(R.drawable.ic_food, R.color.pink_a400) //1
            BILL_ROOM_NAME -> Pair(R.drawable.ic_bill, R.color.green_900) //2
            CREDIT_CARD_ROOM_NAME -> Pair(R.drawable.ic_credit_card, R.color.light_blue_a400) //3
            BEAUTIFICATION_ROOM_NAME -> Pair(R.drawable.ic_spa, R.color.deep_orange_a400) //4
            BIKE_ROOM_NAME -> Pair(R.drawable.ic_bike, R.color.orange_a400) //5
            TRANSPORT_ROOM_NAME -> Pair(R.drawable.ic_transport, R.color.cyan_600) //6
            HOME_ROOM_NAME -> Pair(R.drawable.ic_home, R.color.indigo_a400) //7
            CAR_ROOM_NAME -> Pair(R.drawable.ic_car, R.color.cyan_800) //8
            ENTERTAINMENT_ROOM_NAME -> Pair(R.drawable.ic_film, R.color.purple_a400) //9
            SHOPPING_ROOM_NAME -> Pair(R.drawable.ic_shopping_cart, R.color.orange_a400) //10
            CLOTHING_ROOM_NAME -> Pair(R.drawable.ic_clothing, R.color.deep_purple_a400) //11
            TAX_ROOM_NAME -> Pair(R.drawable.ic_tax, R.color.pink_a400) //12
            TELEPHONE_ROOM_NAME -> Pair(R.drawable.ic_phone, R.color.green_900) //13
            EDUCATION_ROOM_NAME -> Pair(R.drawable.ic_school, R.color.purple_a400) //14
            BOOK_ROOM_NAME -> Pair(R.drawable.ic_books, R.color.cyan_600) //15
            FITNESS_ROOM_NAME -> Pair(R.drawable.ic_fitness, R.color.indigo_a200) //16
            CHILD_ROOM_NAME -> Pair(R.drawable.ic_child, R.color.pink_a200) //17
            MEDICAL_ROOM_NAME -> Pair(R.drawable.ic_medical, R.color.indigo_a400) //18
            else -> {
                val message = "No mapping found for Room Name To Drawable,Color"
                RuntimeException(message)
                return Pair(-99, -99)
            }
        }
    }

}