package com.n2ksp.expense_tracker.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.n2ksp.expense_tracker.R


object ViewUtils {
    fun changeIconDrawableToGray(context: Context, drawable: Drawable) {
        changeIconDrawableColor(context, drawable, R.color.colorWhite)
    }

    fun changeIconDrawableToPrimaryColor(context: Context, drawable: Drawable) {
        changeIconDrawableColor(context, drawable, R.color.colorPrimary)
    }

    private fun changeIconDrawableColor(context: Context, drawable: Drawable?, p: Int) {
        if (drawable != null) {
            drawable.mutate()
            drawable.setColorFilter(ContextCompat.getColor(context, p), PorterDuff.Mode.SRC_ATOP)
        }
    }

    fun dpToPx(dp: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return Math.round(dp * density)
    }

    fun pxToDp(px: Float): Float {
        val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
        return px / (densityDpi / 160f)
    }

}// This class is not publicly instantiable
