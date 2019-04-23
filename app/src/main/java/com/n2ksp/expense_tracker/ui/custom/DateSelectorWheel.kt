package com.n2ksp.expense_tracker.ui.custom

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.View
import android.widget.FrameLayout
import android.widget.Scroller
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.DateUtils
import com.n2ksp.expense_tracker.utils.ScreenDisplayUtils

//https://github.com/MirzaAhmedBaig/CircularWheelView
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DateSelectorWheel : FrameLayout {

    interface DateSelectedListener {
        fun onDateSelected(dayOfMonth: Int, month: String)
    }

    private val FLING_VELOCITY_DOWNSCALE = 30
    private var scrollAnimator: ValueAnimator? = null
    private lateinit var gestureDetector: GestureDetector
    var callback: DateSelectedListener? = null

    val maxElementsCount: Int = 31
    var dummyCircleRadius: Int = 0


    var currentSelectedIndex = 0
    var currentPieAngle = 0

    var selectedTextSize = 30f
    var normalTextSize = 15f

    var scroller: Scroller? = null

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

//        setAmounts(income = 130000, expense = 80000)
        addView(dummyCircleLayout, 0)
        addCenteredTextView()
        addTextView()
        selectViewBasedOnIndex(DateUtils.getCurrentDayOfMonth())

//        scroller = Scroller(context)
//        gestureDetector = GestureDetector(context, simpleGestureListener)
//
//        scrollAnimator = ValueAnimator.ofFloat(0f, 1f)
//        scrollAnimator!!.addUpdateListener {
//            //            tickScrollAnimation()
//        }
//        dummyCircleLayout.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
    }


    fun setListener(callback: DateSelectedListener) {
        this.callback = callback
    }

    fun setIncomeExpenseForMonth(incomeExpense: Pair<Float, Float>?) {
        incomeExpense?.let {
            setAmounts(income = it.first, expense = it.second)
        }
    }

//    private val simpleGestureListener = object : GestureDetector.SimpleOnGestureListener() {
//        override fun onDown(e: MotionEvent?): Boolean {
//            return true
//        }
//
//        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
//
//            e2?.let {
//                val scrollToRotate = vectorToScalarScroll(
//                    distanceX,
//                    distanceY,
//                    e2.x - (dummyCircleLayout.width / 2 + dummyCircleLayout.left),
//                    e2.y - (dummyCircleLayout.height / 2 + dummyCircleLayout.top)
//                )
//
//                scroller?.fling(
//                    0,
//                    currentPieAngle,
//                    0,
//                    0,
//                    0,
//                    0,
//                    0,
//                    0
//                )
//
//
//                setPieRotation(currentPieAngle - scrollToRotate / FLING_VELOCITY_DOWNSCALE)
//
//            }
//
//            return true
//        }
//
//        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
//
//            e2?.let {
//                val scrollToRotate = vectorToScalarScroll(
//                    velocityX,
//                    velocityY,
//                    e2.x - (dummyCircleLayout.width / 2 + dummyCircleLayout.left),
//                    e2.y - (dummyCircleLayout.height / 2 + dummyCircleLayout.top)
//                )
//                scroller?.fling(
//                    0,
//                    currentPieAngle,
//                    0,
//                    scrollToRotate.toInt() / FLING_VELOCITY_DOWNSCALE,
//                    0,
//                    0,
//                    Integer.MIN_VALUE,
//                    Integer.MAX_VALUE
//                )
//
//                scrollAnimator!!.duration = scroller!!.duration.toLong()
//                scrollAnimator!!.start()
//            }
//
//
//
//
//            return true
//        }
//    }
//
//    private fun setPieRotation(rotation: Float) {
//
//        currentPieAngle = ((rotation % 360 + 360) % 360).toInt()
//
//        dummyCircleLayout.rotation = currentPieAngle.toFloat()
//
//
//        correctItemPosition()
//    }
//
//    private fun correctItemPosition() {
//        var rotationAngle = 180 - currentPieAngle
//        var index = rotationAngle / angle
//
//
////        if()
//
//        Timber.e("correctItemPosition : $index")
//    }
//
//    private fun vectorToScalarScroll(dx: Float, dy: Float, x: Float, y: Float): Float {
//        val l = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
//        val crossX = -y
//        val dot = crossX * dx + x * dy
//        val sign = Math.signum(dot)
//        return l * sign
//    }


}