package com.n2ksp.expense_tracker.ui.custom

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Scroller
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.ScreenDisplayUtils
import com.n2ksp.expense_tracker.utils.ViewUtils
import kotlinx.android.synthetic.main.layout_date_selector_wheel.view.*
import timber.log.Timber
import java.lang.Math.abs

//https://github.com/MirzaAhmedBaig/CircularWheelView
class DateSelectorWheel : FrameLayout {


    private val maxElementsCount: Int = 30
    private lateinit var mDetector: GestureDetector
    private var radiusForTheCircle: Int = 0
    private var mScroller: Scroller? = null
    private val FLING_VELOCITY_DOWNSCALE = 20
    private var ROTAION_ANGLE_OFFSET: Float = 0f
    private var mScrollAnimator: ValueAnimator? = null
    private var mPieRotation: Float = 0.0f
    private var lastIdlePosition = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    @SuppressLint("BinaryOperationInTimber", "SetTextI18n", "ClickableViewAccessibility")
    private fun init() {
        inflate(context, R.layout.layout_date_selector_wheel, this)

        expenseValueTextView.text = "110000"
        incomeValueTextView.text = "32500"

        amountProgressView.max = expenseValueTextView.text.toString().toInt()
        amountProgressView.progress = incomeValueTextView.text.toString().toInt()

        addView(dummyLayout, 0)

        addCenteredTextView()
        addTextView()

        mDetector = GestureDetector(context, simpleGestureListener)
        mScroller = Scroller(context)
        mScrollAnimator = ValueAnimator.ofFloat(0f, 1f)
        mScrollAnimator!!.addUpdateListener { tickScrollAnimation() }

        dummyLayout.setOnTouchListener { v, event ->
            return@setOnTouchListener mDetector.onTouchEvent(event)
        }

    }

    private fun tickScrollAnimation() {
        if (!mScroller!!.isFinished) {
            mScroller!!.computeScrollOffset()
//            setPieRotation(mScroller!!.currY.toFloat())
        } else {
            mScrollAnimator!!.cancel()
        }
    }


    private fun addCenteredTextView() {
        val c = ConstraintSet()
        dummyLayout.addView(centerText)

        c.clone(dummyLayout)
        c.connect(centerText.id, ConstraintSet.TOP, dummyLayout.id, ConstraintSet.TOP, 0)
        c.connect(centerText.id, ConstraintSet.BOTTOM, dummyLayout.id, ConstraintSet.BOTTOM, 0);
        c.connect(centerText.id, ConstraintSet.START, dummyLayout.id, ConstraintSet.START, 0);
        c.connect(centerText.id, ConstraintSet.END, dummyLayout.id, ConstraintSet.END, 0);

        c.applyTo(dummyLayout)
    }

    private val centerText = TextView(context).apply {
        textSize = 35f
        text = ""
        id = View.generateViewId()
    }

    private val dummyLayout by lazy {
        val size = ScreenDisplayUtils.getScreeSize(context = context as Activity)
        val dummyCircleDiameter = size.first * 2
        val marginParams = LayoutParams(
            dummyCircleDiameter, dummyCircleDiameter
        )

        radiusForTheCircle = dummyCircleDiameter / 2

        marginParams.setMargins(-size.first / 2, -(size.first * 1.6).toInt(), 0, 0)

        ConstraintLayout(context).apply {
            id = View.generateViewId()
            setBackgroundResource(R.drawable.circle_shape)
            layoutParams = marginParams
        }
    }


    private fun addTextView() {
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

    private fun getTextView(number: Int, size: Float, angle: Float, radius: Int): TextView {
        return TextView(context).apply {
            textSize = size
            text = "$number"
//            rotation = -angle
            id = View.generateViewId()
            setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
                .apply {
                    Timber.e("centeredTopHorizontalTextView == ${centeredTopHorizontalTextView == null}")
                    circleConstraint = centerText.id //centeredTopHorizontalTextView.id
                    circleAngle = angle
                    circleRadius = radius
                }


        }
    }


    private val simpleGestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            val scrollToRotate = vectorToScalarScroll(
                distanceX,
                distanceY,
                e2.x - (dummyLayout.width / 2 + dummyLayout.left),
                e2.y - (dummyLayout.height / 2 + dummyLayout.top)
            )

            setPieRotation(getPieRotation() - scrollToRotate / FLING_VELOCITY_DOWNSCALE)
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            val scrollToRotate = vectorToScalarScroll(
                velocityX,
                velocityY,
                e2.x - (dummyLayout.width / 2 + dummyLayout.left),
                e2.y - (dummyLayout.height / 2 + dummyLayout.top)
            )

            mScroller?.fling(
                0,
                getPieRotation().toInt(),
                0,
                scrollToRotate.toInt() / FLING_VELOCITY_DOWNSCALE,
                0,
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
            )

            mScrollAnimator!!.duration = mScroller!!.duration.toLong()
            mScrollAnimator!!.start()
            return true
        }

        override fun onDown(e: MotionEvent): Boolean {
            if (isAnimationRunning()) {
                stopScrolling()
            }
            return true
        }

    }

    private fun isAnimationRunning(): Boolean {
        return !mScroller!!.isFinished
    }

    private fun stopScrolling() {
        mScroller!!.forceFinished(true)
    }

    private fun getPieRotation(): Float {
        return mPieRotation
    }

    private fun setPieRotation(rotationParam: Float) {
        var rotation = rotationParam
        rotation = (rotation % 360 + 360) % 360

        Timber.e("Rotation :$rotation")

        mPieRotation = rotation
        dummyLayout.rotation = rotation
        val positionIndex = getCorrectPosition()

        val gap = abs(lastIdlePosition - positionIndex)
        if (gap < 1 && maxElementsCount != 30) {
            if (lastIdlePosition < positionIndex) {
//                reArrangeElements(gap, "UP")
            } else if (lastIdlePosition > positionIndex) {
//                reArrangeElements(gap, "DOWN")
            }
        }
        lastIdlePosition = positionIndex
    }

    private fun getCorrectPosition(): Int {
        var position = (((360 - mPieRotation) / ROTAION_ANGLE_OFFSET) % maxElementsCount).toInt()

        if (position < 0)
            position += maxElementsCount
        return position
    }

    private fun vectorToScalarScroll(dx: Float, dy: Float, x: Float, y: Float): Float {
        val l = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
        val crossX = -y
        val dot = crossX * dx + x * dy
        val sign = Math.signum(dot)
        return l * sign
    }

}