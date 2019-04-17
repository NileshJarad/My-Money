package com.n2ksp.expense_tracker.data.sharedpreference

import android.content.Context
import javax.inject.Inject
import android.R.id.edit
import android.content.SharedPreferences
import android.text.TextUtils
import android.preference.PreferenceManager
import android.R.attr.defaultValue


class SharedPrefUtil @Inject constructor(context: Context) {

    companion object {
        const val INTRO_SCREEN_KEY = "intro_screen_shown"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)


    fun introScreenShown(): Boolean {
        var value: Boolean = false
        if (preferences != null) {
            value = preferences.getBoolean(INTRO_SCREEN_KEY, false)
        }
        return value
    }

    fun setintroScreenShown() {
        setBooleanPreference(INTRO_SCREEN_KEY, true)
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


}