package com.n2ksp.expense_tracker.ui.custom

import android.annotation.SuppressLint
import android.app.Activity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.DateUtils
import com.n2ksp.expense_tracker.utils.ScreenDisplayUtils
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
    dummyCircleLayout.addView(centeredTextViewForConstraintToAttach)

    c.clone(dummyCircleLayout)
    c.connect(centeredTextViewForConstraintToAttach.id, ConstraintSet.TOP, dummyCircleLayout.id, ConstraintSet.TOP, 0)
    c.connect(centeredTextViewForConstraintToAttach.id, ConstraintSet.BOTTOM, dummyCircleLayout.id, ConstraintSet.BOTTOM, 0)
    c.connect(centeredTextViewForConstraintToAttach.id, ConstraintSet.START, dummyCircleLayout.id, ConstraintSet.START, 0)
    c.connect(centeredTextViewForConstraintToAttach.id, ConstraintSet.END, dummyCircleLayout.id, ConstraintSet.END, 0)

    c.applyTo(dummyCircleLayout)
}


/***
 * Add dates around circle
 */

fun DateSelectorWheel.addTextView() {
    dummyCircleRadius -= ViewUtils.dpToPx(15f)
    for (i in 1 until maxElementsCount) {
        dummyCircleLayout.addView(
            getTextView(
                number = i,
                size = 15f,
                angle = (angle * i).toFloat(),
                radius = dummyCircleRadius
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
        tag = number
        gravity = Gravity.CENTER
        setOnClickListener {
            selectViewBasedOnIndex(it.tag as Int)
        }

        setTextColor(ContextCompat.getColor(context, R.color.colorWhite))

        val textSize = calculateSelectedTextSize(
            text = "23",
            textSize = selectedTextSize,
            deviceWidth = ScreenDisplayUtils.getScreeSize(context = context as Activity).first
        )

        layoutParams = ConstraintLayout.LayoutParams(
            textSize.first,
            textSize.second
        )
            .apply {
                Timber.e("centeredTopHorizontalTextView == ${centeredTopHorizontalTextView == null}")
                circleConstraint = centeredTextViewForConstraintToAttach.id
                circleAngle = angle
                circleRadius = radius
            }


    }
}

@SuppressLint("SetTextI18n")
fun DateSelectorWheel.selectViewBasedOnIndex(index: Int) {
    val rotationAngle = index * angle
    currentSelectedIndex = index

    callback?.onDateSelected(index, DateUtils.getCurrentMonth())

    currentPieAngle = 180 - rotationAngle

    dummyCircleLayout.rotation = currentPieAngle.toFloat()

    for (i in 0 until dummyCircleLayout.childCount) {
        if (dummyCircleLayout.children.elementAt(i) is TextView) {
            if (i == index) {
                (dummyCircleLayout.children.elementAt(i) as TextView).apply {
                    textSize = selectedTextSize
                    setBackgroundResource(R.drawable.circle_shape_white_transparent)
                    setPadding(
                        ViewUtils.dpToPx(20f),
                        ViewUtils.dpToPx(10f),
                        ViewUtils.dpToPx(20f),
                        ViewUtils.dpToPx(20f)
                    )
                }
            } else {
                (dummyCircleLayout.children.elementAt(i) as TextView).apply {
                    textSize = normalTextSize
                    setBackgroundResource(0)
                    setPadding(
                        ViewUtils.dpToPx(0f),
                        ViewUtils.dpToPx(0f),
                        ViewUtils.dpToPx(0f),
                        ViewUtils.dpToPx(0f)
                    )
                }
            }
        }
    }
}

fun DateSelectorWheel.calculateSelectedTextSize(
    text: CharSequence,
    textSize: Float,
    deviceWidth: Int
): Pair<Int, Int> {
    val textView = TextView(context)
    textView.setPadding(
        ViewUtils.dpToPx(20f),
        ViewUtils.dpToPx(10f),
        ViewUtils.dpToPx(20f),
        ViewUtils.dpToPx(20f)
    )

    textView.setText(text, TextView.BufferType.SPANNABLE)
    textView.textSize = textSize
    val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST)
    val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    textView.measure(widthMeasureSpec, heightMeasureSpec)
    return Pair(textView.measuredWidth, textView.measuredHeight)
}