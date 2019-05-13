package com.n2ksp.expense_tracker.data.sharedpreference

import android.content.Context
import android.preference.PreferenceManager
import android.text.TextUtils
import javax.inject.Inject


class SharedPrefUtil @Inject constructor(context: Context) {

    companion object {
        const val INTRO_SCREEN_KEY = "intro_screen_shown"
        const val ADD_FAB_INTRO = "add_fab_intro"
        const val SELECT_CATEGORY_TYPE_AND_CATEGORY = "select_category_type_and_category"
        const val DATA_ENTRY = "data_entry"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)


    fun introScreenShown(): Boolean {
        return preferences.getBoolean(INTRO_SCREEN_KEY, false)
    }

    fun setintroScreenShown() {
        setBooleanPreference(INTRO_SCREEN_KEY, true)
    }


    fun setFabIntroShown() {
        setBooleanPreference(ADD_FAB_INTRO, value = true)
    }

    fun isFabIntroScreenShown(): Boolean {
        return preferences.getBoolean(ADD_FAB_INTRO, false)
    }

    private fun setStringPreference(key: String, value: String): Boolean {
        if (preferences != null && !TextUtils.isEmpty(key)) {
            val editor = preferences.edit()
            editor.putString(key, value)
            return editor.commit()
        }
        return false
    }

    private fun setBooleanPreference(key: String, value: Boolean): Boolean {
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putBoolean(key, value)
            return editor.commit()
        }
        return false
    }

    fun setCategoryTypeAndCategorySelectionIntroShown() {
        setBooleanPreference(SELECT_CATEGORY_TYPE_AND_CATEGORY, value = true)
    }

    fun isCategoryTypeAndCategorySelectionIntroShown(): Boolean {
        return preferences.getBoolean(SELECT_CATEGORY_TYPE_AND_CATEGORY, false)
    }


    fun setDateEntryIntroShown() {
        setBooleanPreference(DATA_ENTRY, value = true)
    }

    fun isDateEntryIntroShown(): Boolean {
        return preferences.getBoolean(DATA_ENTRY, false)
    }


}