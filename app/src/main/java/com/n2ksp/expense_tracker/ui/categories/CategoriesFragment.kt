package com.n2ksp.expense_tracker.ui.categories


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.CategoryInfoModelCreator
import com.n2ksp.expense_tracker.utils.Constants
import kotlinx.android.synthetic.main.fragment_categories.*


class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoriesRecyclerView.layoutManager = GridLayoutManager(context, 3)


        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(context!!, R.drawable.divider_open_source)
        drawable?.let {
            dividerItemDecoration.setDrawable(it)
        }

        categoriesRecyclerView.addItemDecoration(dividerItemDecoration)

        val adapter = CategoriesAdapter()
        adapter.attachCategories(CategoryInfoModelCreator.getCategoryInfoModel(Constants.EXPENSE))
        categoriesRecyclerView.adapter = adapter

        expenseOrIncomeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                buttonView.setTextColor(ContextCompat.getColor(context!!, R.color.colorExpense))
                buttonView.text = "Expense"
                adapter.attachCategories(CategoryInfoModelCreator.getCategoryInfoModel(Constants.EXPENSE))
                adapter.notifyDataSetChanged()
            } else {
                buttonView.setTextColor(ContextCompat.getColor(context!!, R.color.colorIncome))
                buttonView.text = "Income"
                adapter.attachCategories(CategoryInfoModelCreator.getCategoryInfoModel(Constants.INCOME))
                adapter.notifyDataSetChanged()
            }


        }
    }

}
