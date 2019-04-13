package com.n2ksp.expense_tracker.ui.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.n2ksp.expense_tracker.R
import kotlinx.android.synthetic.main.layout_navigation_menu_item_view.view.*

class NavigationMenuItemView : LinearLayout {

    private var image: Drawable? = null
    private var navMenuTitle: String? = null

    constructor(context: Context) : super(context) {
        initializeViews(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeViews(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initializeViews(attrs)
    }

    private fun initializeViews(attr: AttributeSet?) {


        attr.let {
            val a = context.theme.obtainStyledAttributes(
                attr,
                R.styleable.NavigationMenuItemView,
                0, 0
            )

            try {
                navMenuTitle = a.getString(R.styleable.NavigationMenuItemView_navMenuTitle)
                image = a.getDrawable(R.styleable.NavigationMenuItemView_namMenuImage)
            } finally {
                a.recycle()
            }

        }

        View.inflate(context, R.layout.layout_navigation_menu_item_view, this)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        setPadding(0, 20, 0, 20)

        ivNavImage.setImageDrawable(image)
        tvNavTitle.text = navMenuTitle

    }

}
