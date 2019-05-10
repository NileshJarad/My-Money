package com.n2ksp.expense_tracker.utils

import java.text.DecimalFormat

object AmountUtils {
    //    private const val rupeeSymbol = "\u20A8"
    private const val rupeeSymbol = "\u20B9"

    fun getAmountFormatted(amount: Float = 0.0f): String {
        val formatter = DecimalFormat("##,##,###.##")
        return "$rupeeSymbol ${formatter.format(amount)}"
    }

    fun getOnlyFloatValue(textAmount: String): Float {
        val textAmountAfterRemovedSymbol = textAmount.removePrefix(rupeeSymbol).replace(",", "").trim()
        if (textAmountAfterRemovedSymbol == "." ||
            textAmountAfterRemovedSymbol.isNullOrEmpty() ||
            textAmountAfterRemovedSymbol.isEmpty()
        ) {
            return 0f
        }
        return textAmountAfterRemovedSymbol.toFloat()
    }
}