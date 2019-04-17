package com.n2ksp.expense_tracker.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.n2ksp.expense_tracker.R
import kotlinx.android.synthetic.main.layout_date_selector_wheel.view.*
import androidx.core.content.ContextCompat
import com.n2ksp.expense_tracker.utils.ViewUtils
import timber.log.Timber


//https://github.com/MirzaAhmedBaig/CircularWheelView


//https://sriramramani.wordpress.com/2015/05/06/custom-viewgroups/

//https://medium.com/devnibbles/constraintlayout-circular-positioning-9489b11cb0e5

class DateSelectorWheel : LinearLayout {


    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    private val dummyConstraintLayout by lazy {
        ConstraintLayout(context).apply {
            id = View.generateViewId()
            background = ContextCompat.getDrawable(context, R.drawable.ic_nav_about)
        }

    }


    @SuppressLint("BinaryOperationInTimber", "SetTextI18n")
    private fun init() {

        orientation = VERTICAL
        inflate(context, R.layout.layout_date_selector_wheel, this)

        incomeValueTextView.text = "110000"
        expenseValueTextView.text = "32500"

        amountProgressView.max = incomeValueTextView.text.toString().toInt()
        amountProgressView.progress = expenseValueTextView.text.toString().toInt()


        addCircleView()

    }

    private fun addCircleView() {

        val textView = TextView(context).apply {
            id = View.generateViewId()
            text = "30"
            setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            layoutParams =
                ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .apply {
                        circleConstraint = dummyConstraintLayout.id
                        circleRadius =
                            (((dummyConstraintLayout.measuredHeight / 2) - (dummyConstraintLayout.measuredHeight / 2) * 0.2f).toInt())
                        circleAngle = 90f
//                        circleAngle = if (viewType == RIGHT)
//                            (((ROTAION_ANGLE_OFFSET * multiplier) - 90f) % 360f)
//                        else
//                            (((ROTAION_ANGLE_OFFSET * multiplier) + 90f) % 360f)
                        rotation = circleAngle
                    }

        }

        dummyConstraintLayout.addView(textView)

    }
}