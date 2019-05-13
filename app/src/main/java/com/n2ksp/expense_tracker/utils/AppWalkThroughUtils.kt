package com.n2ksp.expense_tracker.utils

import android.app.Activity
import android.view.View
import androidx.core.content.ContextCompat
import com.n2ksp.expense_tracker.R
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal

object AppWalkThroughUtils {

    fun showFabAddIncomeExpenseEntry(
        activity: Activity,
        view: View,
        callback: () -> Unit
    ) {
        MaterialTapTargetPrompt.Builder(activity)
            .setTarget(view)
            .setBackButtonDismissEnabled(false)
            .setPrimaryText(activity.resources.getString(R.string.title_fab_add_income_expense_walk_through))
            .setSecondaryText(activity.resources.getString(R.string.message_fab_add_income_expense_walk_through))
            .setBackgroundColour(ContextCompat.getColor(activity, R.color.colorPrimary))
            .setPromptStateChangeListener { prompt, state ->
                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                    // User has pressed the prompt target
                    callback.invoke()
                }
            }.show()
    }

    fun showSelectCategoryType(
        activity: Activity,
        view: View,
        callback: () -> Unit
    ) {
        disableClickOn(view)
        MaterialTapTargetPrompt.Builder(activity)
            .setTarget(view)
            .setBackButtonDismissEnabled(false)
            .setPrimaryText(activity.resources.getString(R.string.title_category_switch_walk_through))
            .setSecondaryText(activity.resources.getString(R.string.message_category_switch_walk_through))
            .setBackgroundColour(ContextCompat.getColor(activity, R.color.colorPrimary))
            .setPromptFocal(RectanglePromptFocal())
            .setPromptBackground(RectanglePromptBackground())
            .setPromptStateChangeListener { prompt, state ->
                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                    enableClickOn(view)
                    callback.invoke()
                } else if (state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED) {
                    enableClickOn(view)
                }
            }.show()
    }


    fun showSelectCategory(
        activity: Activity,
        view: View?,
        callback: () -> Unit
    ) {
        MaterialTapTargetPrompt.Builder(activity)
            .setTarget(view)
            .setBackButtonDismissEnabled(false)
            .setPrimaryText(activity.resources.getString(R.string.title_select_category_walk_through))
            .setSecondaryText(activity.resources.getString(R.string.message_select_category_walk_through))
            .setBackgroundColour(ContextCompat.getColor(activity, R.color.colorPrimary))
            .setPromptFocal(RectanglePromptFocal())
            .setPromptBackground(RectanglePromptBackground())
            .setPromptStateChangeListener { prompt, state ->
                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                    callback.invoke()
                }
            }.show()
    }

    fun showEnterMemo(
        activity: Activity,
        view: View,
        callback: () -> Unit
    ) {
        disableClickOn(view)
        MaterialTapTargetPrompt.Builder(activity)
            .setTarget(view)
            .setBackButtonDismissEnabled(false)
            .setPrimaryText(activity.resources.getString(R.string.title_enter_memo_walk_through))
            .setSecondaryText(activity.resources.getString(R.string.message_enter_memo_walk_through))
            .setBackgroundColour(ContextCompat.getColor(activity, R.color.colorPrimary))
            .setPromptFocal(RectanglePromptFocal())
            .setPromptBackground(RectanglePromptBackground())
            .setPromptStateChangeListener { prompt, state ->
                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                    enableClickOn(view)
                    callback.invoke()
                } else if (state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED) {
                    enableClickOn(view)
                }
            }.show()
    }

    fun showSelectDate(
        activity: Activity,
        view: View,
        callback: () -> Unit
    ) {
        MaterialTapTargetPrompt.Builder(activity)
            .setTarget(view)
            .setBackButtonDismissEnabled(false)
            .setPrimaryText(activity.resources.getString(R.string.title_select_date_walk_through))
            .setSecondaryText(activity.resources.getString(R.string.message_select_date_walk_through))
            .setBackgroundColour(ContextCompat.getColor(activity, R.color.colorPrimary))
            .setPromptFocal(RectanglePromptFocal())
            .setPromptBackground(RectanglePromptBackground())
            .setPromptStateChangeListener { prompt, state ->
                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED ||
                    state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED
                ) {
                    callback.invoke()
                    enableClickOn(view)
                }
            }.show()
    }

    private fun disableClickOn(view: View?) {
        view?.isClickable = false
        view?.isFocusable = false
    }

    private fun enableClickOn(view: View?) {
        view?.isClickable = true
        view?.isFocusable = true
        view?.isFocusableInTouchMode = true
        view?.clearFocus()
    }
}