package com.n2ksp.expense_tracker.ui.intro

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.IntroScreenModelCreator
import com.n2ksp.expense_tracker.data.sharedpreference.SharedPrefUtil
import com.n2ksp.expense_tracker.di.component.DaggerIntroScreenViewComponent
import com.n2ksp.expense_tracker.di.module.ContextModule
import com.n2ksp.expense_tracker.di.module.IntroScreenViewModule
import com.n2ksp.expense_tracker.ui.main.MainActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_intro_screen.view.*
import timber.log.Timber
import javax.inject.Inject


@SuppressLint("ViewConstructor")
class IntroScreenView(activity: IntroScreenActivity) : LinearLayout(activity) {

    @Inject
    lateinit var introPageAdapter: IntroPageAdapter

    @Inject
    lateinit var sizeParams: LayoutParams

    @Inject
    lateinit var sharedPrefUtil: SharedPrefUtil

    companion object {
        private var selectedIndex = 0
        var pageCounts = 3
        const val TAG = "IntroScreenView"
    }

    init {

        DaggerIntroScreenViewComponent.builder()
            .introScreenViewModule(IntroScreenViewModule(activity.supportFragmentManager))
            .contextModule(ContextModule(context))
            .build()
            .inject(this)

        if (sharedPrefUtil.introScreenShown()) {
            startMainActivity(activity)
        } else {
            initView(activity)
        }
    }

    private fun initView(activity: IntroScreenActivity) {
        selectedIndex = 0

        View.inflate(activity, R.layout.activity_intro_screen, this)
        setupPager()
        addSplashIntroIndicator()

        skipOrDoneButton.setOnClickListener {
            sharedPrefUtil.setintroScreenShown()
            startMainActivity(activity)
        }
    }

    private fun startMainActivity(activity: IntroScreenActivity) {
        MainActivity.start(context)
        activity.finish()
    }

    private fun setupPager() {
        introViewPager.adapter = introPageAdapter
        introViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                Timber.e("onPageSelected: $position")
                selectedIndex = position
                if (selectedIndex == pageCounts - 1) {
                    skipOrDoneButton.text = context.getString(R.string.got_it)
                } else {
                    skipOrDoneButton.text = context.getString(R.string.skip)
                }
                addSplashIntroIndicator()
            }

        })

        introViewPager.setPageTransformer(false) { page, position ->
            val normalizedPosition = Math.abs(Math.abs(position) - 1)
            page.alpha = normalizedPosition
        }

    }


    @SuppressLint("CheckResult", "BinaryOperationInTimber")
    fun addSplashIntroIndicator() {
        introPageIndicatorLinearLayout.removeAllViews()
        Observable.range(0, pageCounts)
            .subscribe {
                showViewForIndication(it)
            }
    }

    private fun showViewForIndication(it: Int) {
        val indicator = Button(context)
        indicator.layoutParams = sizeParams
        if (selectedIndex >= it) {
            when (it) {
                0 -> indicator.setBackgroundResource(R.drawable.splash_indicator_one_click)
                1 -> indicator.setBackgroundResource(R.drawable.splash_indicator_grapp)
                2 -> indicator.setBackgroundResource(R.drawable.splash_indicator_cloud)
            }
        } else {
            indicator.setBackgroundResource(R.drawable.splash_indicator_normal)
        }

        introPageIndicatorLinearLayout.addView(indicator)
    }

}

class IntroPageAdapter @Inject constructor(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    private val dataList = IntroScreenModelCreator.getIntroModels()

    override fun getItem(position: Int): Fragment {
        return IntroFragment.newInstance(dataList[position])
    }

    override fun getCount(): Int = IntroScreenView.pageCounts
}


