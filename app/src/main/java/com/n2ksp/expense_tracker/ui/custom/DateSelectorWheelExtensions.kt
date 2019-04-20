package com.n2ksp.expense_tracker.ui.custom

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.ViewUtils
import kotlinx.android.synthetic.main.layout_date_selector_wheel.view.*
import timber.log.Timber

/**
 * Sets income, expense and balance amount to view
 *
 * It takes {income and expense} as parameter calculates the balance sets it
 */
@SuppressLint("SetTextI18n", "StringFormatInvalid")
fun DateSelectorWheel.setAmounts(income: Int, expense: Int) {
    expenseValueTextView.text = AmountUtils.getAmountFormatted(expense.toFloat())
    incomeValueTextView.text = AmountUtils.getAmountFormatted(income.toFloat())
    amountProgressView.max = income
    amountProgressView.progress = expense
    balanceValueTextView.text =
        AmountUtils.getAmountFormatted((income - expense).toFloat())
}

/***
 * Set constraints for dummy text to kept it in center
 */

fun DateSelectorWheel.addCenteredTextView() {
    val c = ConstraintSet()
    dummyLayout.addView(centerText)

    c.clone(dummyLayout)
    c.connect(centerText.id, ConstraintSet.TOP, dummyLayout.id, ConstraintSet.TOP, 0)
    c.connect(centerText.id, ConstraintSet.BOTTOM, dummyLayout.id, ConstraintSet.BOTTOM, 0)
    c.connect(centerText.id, ConstraintSet.START, dummyLayout.id, ConstraintSet.START, 0)
    c.connect(centerText.id, ConstraintSet.END, dummyLayout.id, ConstraintSet.END, 0)

    c.applyTo(dummyLayout)
}


/***
 * Add dates around circle
 */

fun DateSelectorWheel.addTextView() {
    radiusForTheCircle -= ViewUtils.dpToPx(10f)

    val angle = 360 / maxElementsCount

    for (i in 1 until maxElementsCount) {
        dummyLayout.addView(
            getTextView(
                number = i,
                size = 15f,
                angle = (angle * i).toFloat(),
                radius = radiusForTheCircle
            )
        )
    }
}


/**
 * Creates and add the dates in 360 view
 */
fun DateSelectorWheel.getTextView(number: Int, size: Float, angle: Float, radius: Int): TextView {
    return TextView(context).apply {
        textSize = size
        text = "$number"
        rotation = angle - 180
        id = View.generateViewId()
        setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
            .apply {
                Timber.e("centeredTopHorizontalTextView == ${centeredTopHorizontalTextView == null}")
                circleConstraint = centerText.id
                circleAngle = angle
                circleRadius = radius
            }


    }
}