package com.n2ksp.expense_tracker.ui.categories


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.base.ETBaseActivity
import com.n2ksp.expense_tracker.data.model.CategoryInfoModelCreator
import com.n2ksp.expense_tracker.data.sharedpreference.SharedPrefUtil
import com.n2ksp.expense_tracker.ui.income_expense.add_update.SharedIncomeExpenseViewModel
import com.n2ksp.expense_tracker.utils.AppWalkThroughUtils
import com.n2ksp.expense_tracker.utils.Constants
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_categories.*


class CategoriesFragment : Fragment() {

    private lateinit var sharedViewModel: SharedIncomeExpenseViewModel
    private var allowSelection: Boolean = false
    lateinit var sharedPrefUtil: SharedPrefUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel =
            ViewModelProviders.of(activity!!).get(SharedIncomeExpenseViewModel::class.java)

        arguments?.let { arg ->
            if (arg.containsKey("allowSelection")) {
                allowSelection = arg.getBoolean("allowSelection", false)
            }
        }

        sharedPrefUtil = SharedPrefUtil(activity!!)

        val gridLayoutManager = GridLayoutManager(context, 3)
        categoriesRecyclerView.layoutManager = gridLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(context!!, R.drawable.divider_open_source)
        drawable?.let {
            dividerItemDecoration.setDrawable(it)
        }

        categoriesRecyclerView.addItemDecoration(dividerItemDecoration)

        val adapter = CategoriesAdapter()
        adapter.setAllowSelection(allowSelection = allowSelection)
        adapter.setViewModel(sharedViewModel = sharedViewModel)

        Observable.just((activity as ETBaseActivity).getAppDatabase())
            .subscribeOn(Schedulers.io())
            .subscribe {
                val categories = it.categoryDao().findByType(Constants.EXPENSE)
                (activity as ETBaseActivity).runOnUiThread {
                    adapter.attachCategories(CategoryInfoModelCreator.convertToCategoryInfoModel(categories))
                    categoriesRecyclerView.adapter = adapter
                }
            }

        expenseOrIncomeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->

            searchEditText.text = null

            if (isChecked) {
                buttonView.setTextColor(ContextCompat.getColor(context!!, R.color.colorExpense))
                buttonView.text = resources.getString(R.string.expense)
                getAndAttachCategory(Constants.EXPENSE)
            } else {
                buttonView.setTextColor(ContextCompat.getColor(context!!, R.color.colorIncome))
                buttonView.text = resources.getString(R.string.income)
                getAndAttachCategory(Constants.INCOME)
            }
        }

        if(!sharedPrefUtil.isCategoryTypeAndCategorySelectionIntroShown()) {
            AppWalkThroughUtils.showSelectCategoryType(activity as Activity, expenseOrIncomeSwitch) {

                // shown for the category switch
                AppWalkThroughUtils.showSelectCategory(activity as Activity, gridLayoutManager.getChildAt(0)) {
                    // select category
                    sharedPrefUtil.setCategoryTypeAndCategorySelectionIntroShown()
                }
            }
        }


    }


    override fun onResume() {
        super.onResume()


    }

    @SuppressLint("CheckResult")
    private fun getAndAttachCategory(type: String) {
        Observable.just((activity as ETBaseActivity).getAppDatabase())
            .subscribeOn(Schedulers.io())
            .subscribe {

                val categories = it.categoryDao().findByType(type)

                (activity as ETBaseActivity).runOnUiThread {
                    //                adapter.attachCategories(viewModel.getCategories(Constants.EXPENSE))
                    (categoriesRecyclerView.adapter as CategoriesAdapter).attachCategories(
                        CategoryInfoModelCreator.convertToCategoryInfoModel(
                            categories
                        )
                    )
                    (categoriesRecyclerView.adapter as CategoriesAdapter).notifyDataSetChanged()
                }
            }
    }


}
