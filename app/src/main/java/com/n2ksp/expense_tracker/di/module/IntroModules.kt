package com.n2ksp.expense_tracker.di.module

import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import com.n2ksp.expense_tracker.ui.intro.IntroPageAdapter
import com.n2ksp.expense_tracker.ui.intro.IntroScreenActivity
import com.n2ksp.expense_tracker.ui.intro.IntroScreenView
import com.n2ksp.expense_tracker.utils.ViewUtils
import dagger.Module
import dagger.Provides


@Module
class IntroScreenActivityModule(val activity: IntroScreenActivity) {
    @Provides
    fun provideIntroScreenView(): IntroScreenView {
        return IntroScreenView(activity = activity)
    }
}

@Module(includes = [ContextModule::class, SharedPrefModule::class])
class IntroScreenViewModule(private val supportFragmentManager: FragmentManager) {

    @Provides
    fun provideLayoutPrams(): LinearLayout.LayoutParams {
        val layoutParams = LinearLayout.LayoutParams(
            ViewUtils.dpToPx(30f), ViewUtils.dpToPx(10f)
        )
        layoutParams.marginStart = ViewUtils.dpToPx(15f)
        return layoutParams
    }

    @Provides
    fun providePagerAdapter(): IntroPageAdapter {
        return IntroPageAdapter(supportFragmentManager)
    }
}
