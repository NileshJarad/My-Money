package com.n2ksp.expense_tracker.ui.custom

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.DateUtils
import com.n2ksp.expense_tracker.utils.ScreenDisplayUtils

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DateSelectorWheel : FrameLayout {

    interface DateSelectedListener {
        fun onDateSelected(dayOfMonth: Int, month: String)
    }

    var callback: DateSelectedListener? = null

    val maxElementsCount: Int = 31
    var dummyCircleRadius: Int = 0


    var currentSelectedIndex = 0
    var currentPieAngle = 0

    var selectedTextSize = 30f
    var normalTextSize = 15f


    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    val centeredTextViewForConstraintToAttach = TextView(context).apply {
        textSize = 35f
        text = ""
        id = View.generateViewId()
    }

    val angle = 360 / maxElementsCount

    val dummyCircleLayout by lazy {
        val size = ScreenDisplayUtils.getScreeSize(context = context as Activity)
        val dummyCircleDiameter = size.first * 2
        val marginParams = LayoutParams(
            dummyCircleDiameter, dummyCircleDiameter
        )

        dummyCircleRadius = dummyCircleDiameter / 2

        marginParams.setMargins(-size.first / 2, -(size.first * 1.6).toInt(), 0, 0)

        ConstraintLayout(context).apply {
            id = View.generateViewId()
            setBackgroundResource(R.drawable.circle_shape)
            layoutParams = marginParams
        }
    }

    @SuppressLint("BinaryOperationInTimber", "SetTextI18n", "ClickableViewAccessibility")
    private fun init() {
        inflate(context, R.layout.layout_date_selector_wheel, this)

        setAmounts(income = 130000, expense = 80000)
        addView(dummyCircleLayout, 0)
        addCenteredTextView()
        addTextView()
        selectViewBasedOnIndex(DateUtils.getCurrentDayOfMonth())
    }


    fun setListener(callback: DateSelectedListener) {
        this.callback = callback
    }

}